package com.teodoro.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class GuiaArrecadacaoController {

	/* ****   Requisicão SEM Codigo de Barras  **** */
	@RequestMapping(value = "/nobarcode", 
			method = RequestMethod.GET, 
		    produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public GuiaArrecadacaoSemCodigoBarras guia() {
		GuiaArrecadacaoSemCodigoBarras guia = new GuiaArrecadacaoSemCodigoBarras();
		return guia;
	}
	
	/* ****   Requisicão COM Codigo de Barras  **** */
	@RequestMapping(value = "/barcode", 
			method = RequestMethod.GET, 
		    produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public List<Object> guia2() {
		GuiaArrecadacaoSemCodigoBarras guia_sem_codigo = new GuiaArrecadacaoSemCodigoBarras();
		String valor_referente = guia_sem_codigo.getValor_total();
		String data_vencimento = guia_sem_codigo.getData_vencimento();
		String numero_documento = guia_sem_codigo.getNumero_documento();
		
		Integer aux_id_valor_referente = (int) (6 + Math.random() * 4);
		String id_valor_referente = aux_id_valor_referente.toString();
		
		GuiaArrecadacaoComCodigoBarras guia_com_codigo = new GuiaArrecadacaoComCodigoBarras(
				"8", "5", id_valor_referente, valor_referente, "0064", data_vencimento);
		guia_com_codigo.setNumeroDocumento(numero_documento);
		guia_com_codigo.get(id_valor_referente);
		
		List<Object> object = new ArrayList<Object>();
		object.add(guia_sem_codigo);
		object.add(guia_com_codigo);
		return object;
	}
}
