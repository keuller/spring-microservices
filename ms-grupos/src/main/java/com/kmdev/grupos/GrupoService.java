package com.kmdev.grupos;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kmdev.grupos.domain.Grupo;
import com.kmdev.wbus.AbstractCommandHandler;

/**
 * @author keuller.magalhaes at gmail.com
 * @version 1.0
 */
@RestController
@RequestMapping("/")
public class GrupoService extends AbstractCommandHandler<Grupo> {

	public String getPrefix() {
		return "grupos";
	}
	
}
