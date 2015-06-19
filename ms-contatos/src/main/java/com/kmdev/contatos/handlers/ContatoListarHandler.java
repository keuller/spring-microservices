package com.kmdev.contatos.handlers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import reactor.event.Event;
import com.kmdev.contatos.domain.Contato;
import com.kmdev.wbus.Handler;
import com.kmdev.wbus.message.MessageIn;
import com.kmdev.wbus.message.MessageOut;
import com.kmdev.wbus.stereotype.EventHandler;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;

/**
 * 
 * @author keuller.magalhaes at gmail.com
 * @version 1.0
 */
@EventHandler(name="listar", replyTo="contatos")
public class ContatoListarHandler extends Handler<Contato> {

	@Autowired
	private RestTemplate restClient;
	
	@Autowired
	private DiscoveryClient client;
	
	@Override
    public Event<MessageOut> apply(Event<MessageIn<Contato>> event) {
		final List<Contato> lista = new java.util.ArrayList<>();
		MessageOut out = null;
		Integer id = (Integer) event.getData().params.get("id");
		
		List<InstanceInfo> instances = client.getInstancesById("grupos-service");
		if (instances != null && instances.size() > 0) {
			log.info("----> Total de Instancias: " + instances.size());
		}
		
		if (null != id) {
			Contato bean = new Contato();
			bean.id = "contato-9j47sb334-08sf-9dms";
			bean.nome = "Abdoral Gusmao";
			bean.email = "ab.gusmao@zipmail.com";
			bean.grupoId = getGrupo();
			log.info("----> Grupo: " + bean.grupoId);
			out = createOutput(event, bean);
		} else {
			out = createOutput(event, lista);
		}
	    return event(out);
    }

	private String getGrupo() {
		try {
			ResponseEntity<MessageOut> message = restClient.exchange("http://grupos-service/v1/grupos?id=1", HttpMethod.GET, null, MessageOut.class);
			log.info(message.getBody());
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		log.info("---->>>> AI SIM <<<<-------");
		return "";
	}
}
