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
 * @param <T>
 */
@EventHandler(name="incluir", replyTo="grupos")
public class GrupoIncluirHandler extends Handler<Grupo> {

	@Override
    public Event<MessageOut> apply(Event<MessageIn<Grupo>> event) {
		log.info("----> GrupoIncluirHandler.");
	    return event(createOutput(event, null));
    }

}
