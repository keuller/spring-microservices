package com.kmdev.wbus;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.Reactor;
import reactor.event.Event;
import reactor.event.selector.Selectors;
import reactor.function.Consumer;
import com.kmdev.wbus.message.MessageIn;
import com.kmdev.wbus.message.MessageOut;
import com.kmdev.wbus.stereotype.EventHandler;

/**
 * 
 * @author keuller.magalhaes at gmail.com
 * @version 1.0
 */
public abstract class AbstractCommandHandler<T> {

    protected final Log log = LogFactory.getLog(getClass());
    
    @Autowired
    ApplicationContext context;

    @Autowired
    Reactor eventBus;

    @Autowired
    EventStore eventStore;
    
    protected String timeout;
    
    private final ConcurrentMap<String, Promise> promises = new ConcurrentHashMap<>();

    public abstract String getPrefix();
    
    @PostConstruct
    public void init() {
        final String prefix = getPrefix();
        this.timeout = eventStore.getProperty("timeout");
        
        if (log.isDebugEnabled()) {
        	log.debug("Reply Topic Prefix: ".concat(prefix));
        	log.debug("Promise timeout: ".concat(this.timeout));
        }
        
        eventBus.on(Selectors.object(prefix.concat(".reply")), new Consumer<Event<MessageOut>>() {
            public void accept(Event<MessageOut> event) {
                MessageOut message = event.getData();
                
                Promise promise = (Promise) promises.get(message.id);
                
                if (promise == null) {
                    log.warn("No promise object found with ID " + message.id);
                    return;
                }
                
                if (!message.status.code.equals("0")) {
                    promise.setErrorResult(message);
                } else
                    promise.setResult(message);
            }
        });
        
        // registra todos os eventos do store
        String entry = "";
        Map<String,Object> handlers = context.getBeansWithAnnotation(EventHandler.class);
        Set<String> names = handlers.keySet();
        for(String name : names) {
        	Handler<?> handler = (Handler<?>) handlers.get(name);
        	EventHandler annotation = handler.getClass().getAnnotation(EventHandler.class);
        	entry = getPrefix().concat(".").concat(annotation.name());
            eventBus.receive(Selectors.object(entry), handler);
        }
        names = null;
        handlers = null;
    }
    
    protected Promise processMessage(final MessageIn<?> message) {
        final String prefix  = getPrefix();
        final String key = UUID.randomUUID().toString();
        
        // cria o objeto que representa a promise que sera enviada ao cliente
        if (null == timeout) timeout = "5000";
        final Promise reply = new Promise(new Long(timeout));

        // notifica o barramento de eventos
        message.id = prefix.concat("-").concat(key);
        final String topic = prefix.concat(".").concat(message.action);
        eventBus.send(topic, Event.wrap(message, prefix.concat(".reply")));
        reply.onCompletion(callback(message.id));
        
        // adiciona a promise no pool
        promises.put(message.id, reply);
        
        // retorna a promise
        return reply;
    }
    
    protected Runnable callback(final String key) {
        return new Runnable() {
            public void run() {
                promises.remove(key);
            }
        };
    }
    
    private Object paramConverter(String value) {
        if (value.indexOf(".") > 0) { // numero float
            Double val = TypeConverter.toDouble(value);
            if (null != val) return val;
        } else if (value.matches("[0-9]")) {
            Integer val = TypeConverter.toInt(value);
            if (null != val) return val;
        }
        return value;
    }
    
    protected MessageIn<?> processRequest(final HttpServletRequest req) {
        final MessageIn<?> message = new MessageIn<>("find", null);
        
        // gera o mapa de parametros
        java.util.Map<String,Object> params = new Hashtable<>();
        Enumeration<String> names = req.getParameterNames();
        while(names.hasMoreElements()) {
            String key = names.nextElement();
            if (key.equalsIgnoreCase("action")) {
                message.action = req.getParameter(key);
            } else {
                params.put(key, paramConverter(req.getParameter(key)));
            }
        }
        
        message.params = params;
        return message;
    }
    
    @RequestMapping(method=RequestMethod.GET, produces="application/json")
    public Promise execute(HttpServletRequest req) {
        return processMessage(processRequest(req));
    }
    
    @RequestMapping(method=RequestMethod.POST, produces="application/json")
    public Promise execute(@RequestBody MessageIn<T> message) {
        return processMessage(message);
    }
    
}
