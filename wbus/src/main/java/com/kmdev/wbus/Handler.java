package com.kmdev.wbus;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import reactor.event.Event;
import reactor.function.Function;
import com.kmdev.wbus.message.MessageIn;
import com.kmdev.wbus.message.MessageOut;

/**
 * 
 * @author keuller.magalhaes at gmail.com
 * @version 1.0
 */
public abstract class Handler<T> implements Function<Event<MessageIn<T>>, Event<MessageOut>> {

	protected final Log log = LogFactory.getLog(getClass());
    
    public MessageIn<T> getData(final Event<MessageIn<T>> event) {
        return (MessageIn<T>) event.getData();
    }
    
    public MessageOut createOutput(Event<MessageIn<T>> event, Object result) {
        MessageIn<T> message = getData(event);
        MessageOut output = new MessageOut();
        output.data = result;
        output.action = message.action;
        output.status.code = "0";
        output.id = message.id;
        return output;
    }
    
    protected Event<MessageOut> event(MessageOut out) {
    	return Event.wrap(out);
    }
    
}
