package com.kmdev.grupos.handlers;

import reactor.event.Event;
import com.kmdev.grupos.domain.Grupo;
import com.kmdev.wbus.Handler;
import com.kmdev.wbus.message.MessageIn;
import com.kmdev.wbus.message.MessageOut;
import com.kmdev.wbus.stereotype.EventHandler;

/**
 * @author keuller.magalhaes at gmail.com
 * @version 1.0
 */
@EventHandler(name="listar", replyTo="contatos")
public class GrupoListarHandler extends Handler<Grupo> {

	@Override
    public Event<MessageOut> apply(Event<MessageIn<Grupo>> event) {
		log.info("----> GrupoListarHandler.");
		final Grupo bean = new Grupo();
		Integer id = (Integer) event.getData().params.get("id");
		if (null != id) {
			bean.id = "grupos-89f9722-0kdf-972j-082nsdjh2445";
			bean.nome = "Grupo de Teste";
		}
		MessageOut out = createOutput(event, bean);
	    return event(out);
    }

}
