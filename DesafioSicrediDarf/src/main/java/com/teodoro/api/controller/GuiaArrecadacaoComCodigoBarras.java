package com.teodoro.api.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/* ****   Classe que Gera a Representacão Numerica do Codigo de Barras  **** */
public class GuiaArrecadacaoComCodigoBarras {
	
	private String identificacao_do_produto;
	private String identificacao_do_segmento;
	private String identificacao_do_valor_referente;
	private String valor_referente;
	private String identificacao_da_empresa_orgao;
	private String campo_livre_utilizacao_empresa;
	private String codigo_linha_digital;
	
	/* ****   Getters e Setters   **** */
	public String getIdentificacao_do_produto() {
		return identificacao_do_produto;
	}

	public void setIdentificacao_do_produto(String identificacao_do_produto) {
		this.identificacao_do_produto = identificacao_do_produto;
	}

	public String getIdentificacao_do_segmento() {
		return identificacao_do_segmento;
	}

	public void setIdentificacao_do_segmento(String identificacao_do_segmento) {
		this.identificacao_do_segmento = identificacao_do_segmento;
	}

	public String getIdentificacao_do_valor_referente() {
		return identificacao_do_valor_referente;
	}

	public void setIdentificacao_do_valor_referente(String identificacao_do_valor_referente) {
		this.identificacao_do_valor_referente = identificacao_do_valor_referente;
	}

	public String getValor_referente() {
		return valor_referente;
	}

	public void setValor_referente(String valor_referente) {
		this.valor_referente = valor_referente;
	}

	public String getIdentificacao_da_empresa_orgao() {
		return identificacao_da_empresa_orgao;
	}

	public void setIdentificacao_da_empresa_orgao(String identificacao_da_empresa_orgao) {
		this.identificacao_da_empresa_orgao = identificacao_da_empresa_orgao;
	}

	public String getCampo_livre_utilizacao_empresa() {
		return campo_livre_utilizacao_empresa;
	}

	public void setCampo_livre_utilizacao_empresa(String campo_livre_utilizacao_empresa) {
		this.campo_livre_utilizacao_empresa = campo_livre_utilizacao_empresa;
	}

	public String getCodigo_linha_digital() {
		return codigo_linha_digital;
	}

	public void setCodigo_linha_digital(String codigo_linha_digital) {
		this.codigo_linha_digital = codigo_linha_digital;
	}

	/* ****   Construtor   **** */
	public GuiaArrecadacaoComCodigoBarras(String identificacao_do_produto, String identificacao_do_segmento,
			String identificacao_do_valor_referente, String valor_referente, String identificacao_da_empresa_orgao,
			String data_vencimento) {

		this.identificacao_do_produto = identificacao_do_produto;
		this.identificacao_do_segmento = identificacao_do_segmento;
		this.identificacao_do_valor_referente = identificacao_do_valor_referente;

		this.valor_referente = valor_referente;
		this.valor_referente = this.valor_referente.replace(".", ""); // tira os pontos
		this.valor_referente = this.valor_referente.replace(",", ""); // tira a vírgula
		this.valor_referente = this.valor_referente.replace("R", ""); // tira o R
		this.valor_referente = this.valor_referente.replace("$", ""); // tira o cifrão
		this.valor_referente = this.valor_referente.replace(" ", ""); // tira o espaco
		this.valor_referente = org.apache.commons.lang.StringUtils.leftPad(this.valor_referente, 11, "0"); // adiciona zeros restantes

		this.identificacao_da_empresa_orgao = identificacao_da_empresa_orgao;

		SimpleDateFormat inSDF = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat outSDF = new SimpleDateFormat("yyyy-MM-dd");
		String outDate  = "";
		Date date = null;
		try {
			date = inSDF.parse(data_vencimento);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        outDate = outSDF.format(date); // padrão AAAAMMDD
        outDate = outDate.replace("-", "");
        
		this.campo_livre_utilizacao_empresa = outDate;
	}

	/* ****   Numero do Documento   **** */
	public String setNumeroDocumento(String numero_documento) {
		int count = 25 - (int) this.campo_livre_utilizacao_empresa.length();
		numero_documento = org.apache.commons.lang.StringUtils.leftPad(numero_documento, count, "0"); // adiciona zeros restantes
		this.campo_livre_utilizacao_empresa = this.campo_livre_utilizacao_empresa + numero_documento;
		return this.campo_livre_utilizacao_empresa;
	}

	/* ****   Calculo do Dac (Modulo 10)   **** */
	private String calculoDacModulo_10(String codigo) {
		this.codigo_linha_digital = codigo;
        String calculo_codigo = "";
        
        for(int i = 0; i < codigo.length(); i++) {
            if(i == 3){
                calculo_codigo = calculo_codigo + this.digitoVerificadorGeralModulo_10(codigo); // digito verificador geral
            }
            calculo_codigo = calculo_codigo + codigo.charAt(i); // concatenação do caracter
        }
        
        codigo = "";
        
        for(int i = 0; i < calculo_codigo.length(); i++) { // divisão do código em 4 partes
            if(i == 11 || i == 22 || i == 33) {
            	codigo = codigo + "-" + this.digitoVerificadorGeralModulo_10(calculo_codigo.substring(i - 11, i)) + " "; // digito verificador
            }
            codigo = codigo + calculo_codigo.charAt(i); // concatenação do caracter
        }
        
        codigo = codigo + "-" + this.digitoVerificadorGeralModulo_10(calculo_codigo.substring(33, 44)); // ultimo digito verificador
        this.codigo_linha_digital = codigo;
        return codigo;
    }
	
	/* ****   Calculo do Dac (Modulo 11)   **** */
	private String calculoDacModulo_11(String codigo) {
		this.codigo_linha_digital = codigo;
        String calculo_codigo = "";
        
        for(int i = 0; i < codigo.length(); i++) {
            if(i == 3){
                calculo_codigo = calculo_codigo + this.digitoVerificadorGeralModulo_11(codigo); // digito verificador geral
            }
            calculo_codigo = calculo_codigo + codigo.charAt(i); // concatenação do caracter
        }
        
        codigo = "";
        
        for(int i = 0; i < calculo_codigo.length(); i++) { // divisão do código em 4 partes
            if(i == 11 || i == 22 || i == 33) {
            	codigo = codigo + "-" + this.digitoVerificadorGeralModulo_11(calculo_codigo.substring(i - 11, i)) + " "; // digito verificador
            }
            codigo = codigo + calculo_codigo.charAt(i); // concatenação do caracter
        }
        
        codigo = codigo + "-" + this.digitoVerificadorGeralModulo_11(calculo_codigo.substring(33, 44)); // ultimo digito verificador
        this.codigo_linha_digital = codigo;
        return codigo;
    }
	
	/* ****   Calculo do Digito Verificador (Modulo 11)   **** */
	private String digitoVerificadorGeralModulo_11(String codigo) {
		int contador = 4;
		int calculo_codigo = 0;
		int soma = 0;

		for (int i = 0; i < codigo.length(); i++) {
			if (contador == 2) {
				calculo_codigo = calculo_codigo + (Integer.parseInt(codigo.charAt(i) + "") * contador); // Regra de negocio Febraban Versão 5 (Modulo 11)
				contador = 9;
			}
			else {
				calculo_codigo = calculo_codigo + (Integer.parseInt(codigo.charAt(i) + "") * contador); // Regra de negocio Febraban Versão 5 (Modulo 11)
				contador--;
			}
		}

		soma = calculo_codigo;
		
		if (soma % 11 == 0 || soma % 11 == 1) {
			return "0";
		}
		if (soma % 11 == 10) {
			return "1";
		}
		
		Integer digitoVerificador = (int) (11 - (soma % 11));
		return digitoVerificador.toString();
	}

	/* ****   Calculo do Digito Verificador (Modulo 10)   **** */
	private String digitoVerificadorGeralModulo_10(String codigo) {
		boolean verificador = true;
		String calculo_codigo = "";
		int soma = 0;

		for (int i = 0; i < codigo.length(); i++) {
			if (verificador == true) {
				calculo_codigo = calculo_codigo + (Integer.parseInt(codigo.charAt(i) + "") * 2); // Regra de negocio Febraban Versão 5 (Modulo 10)
				verificador = false;
			} else {
				calculo_codigo = calculo_codigo + (Integer.parseInt(codigo.charAt(i) + "") * 1); // Regra de negocio Febraban Versão 5 (Modulo 10)
				verificador = true;
			}
		}

		for (int i = 0; i < calculo_codigo.length(); i++) {
			soma = soma + Integer.parseInt(calculo_codigo.charAt(i) + ""); // Regra de negocio Febraban Versão 5 soma dos caracter (modulo 10)
		}
		
		if (soma % 10 == 0) {
			return "0";
		}

		Integer digitoVerificador = (int) (10 - (soma % 10));
		return digitoVerificador.toString();
	}

	/* ****   Get da Linha Digitavel na regra da Febraban Versão 5 (Modulo 10 ou Modulo 11)   **** */
	public String get(String id_valor_referente) {
		this.codigo_linha_digital = this.identificacao_do_produto + this.identificacao_do_segmento
				+ this.identificacao_do_valor_referente + this.valor_referente + this.identificacao_da_empresa_orgao
				+ this.campo_livre_utilizacao_empresa; // Concatenção dos dados passados no construtor
		if(id_valor_referente == "6" || id_valor_referente == "7") {
			return calculoDacModulo_10(this.codigo_linha_digital); // get da Linha Digitavel na regra da Febraban Versão 5 (Modulo 10)
		}
		
		return calculoDacModulo_11(this.codigo_linha_digital); // get da Linha Digitavel na regra da Febraban Versão 5 (Modulo 11)
	}
}