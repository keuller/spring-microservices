package com.kmdev.contatos;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kmdev.contatos.domain.Contato;
import com.kmdev.wbus.AbstractCommandHandler;

/**
 * 
 * @author keuller.magalhaes at gmail.com
 * @version 1.0
 */
@RestController
@RequestMapping("/")
public class ContatosService extends AbstractCommandHandler<Contato> {

	@Override
    public String getPrefix() {
	    return "contatos";
    }

}
