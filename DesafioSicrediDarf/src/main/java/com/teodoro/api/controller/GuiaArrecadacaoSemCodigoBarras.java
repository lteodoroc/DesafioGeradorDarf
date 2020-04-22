package com.teodoro.api.controller;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* ****   Classe que Gera os Dados dos Campos da DARF Aleatoriamente  **** */
public class GuiaArrecadacaoSemCodigoBarras {
	
	private String nome_telefone;
	private String numero_documento; // OBRIGATORIO
	private String periodo_apuracao;
	private String cpf_cnpj;
	private String codigo_receita;
	private String numero_referencia;
	private String data_vencimento; // OBRIGATORIO
	private String valor_principal;
	private String valor_multa;
	private String valor_juros;
	private String valor_total; // OBRIGATORIO
	private String autenticacao;
	
	/* ****   Getters e Setters   **** */
	public String getNome_telefone() {
		return nome_telefone;
	}

	public void setNome_telefone(String nome_telefone) {
		this.nome_telefone = nome_telefone;
	}

	public String getNumero_documento() {
		return numero_documento;
	}

	public void setNumero_documento(String numero_documento) {
		this.numero_documento = numero_documento;
	}

	public String getPeriodo_apuracao() {
		return periodo_apuracao;
	}

	public void setPeriodo_apuracao(String periodo_apuracao) {
		this.periodo_apuracao = periodo_apuracao;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public String getCodigo_receita() {
		return codigo_receita;
	}

	public void setCodigo_receita(String codigo_receita) {
		this.codigo_receita = codigo_receita;
	}

	public String getNumero_referencia() {
		return numero_referencia;
	}

	public void setNumero_referencia(String numero_referencia) {
		this.numero_referencia = numero_referencia;
	}

	public String getData_vencimento() {
		return data_vencimento;
	}

	public void setData_vencimento(String data_vencimento) {
		this.data_vencimento = data_vencimento;
	}

	public String getValor_principal() {
		return valor_principal;
	}

	public void setValor_principal(String valor_principal) {
		this.valor_principal = valor_principal;
	}

	public String getValor_multa() {
		return valor_multa;
	}

	public void setValor_multa(String valor_multa) {
		this.valor_multa = valor_multa;
	}

	public String getValor_juros() {
		return valor_juros;
	}

	public void setValor_juros(String valor_juros) {
		this.valor_juros = valor_juros;
	}

	public String getValor_total() {
		return valor_total;
	}

	public void setValor_total(String valor_total) {
		this.valor_total = valor_total;
	}

	public String getAutenticacao() {
		return autenticacao;
	}

	public void setAutenticacao(String autenticacao) {
		this.autenticacao = autenticacao;
	}

	/* ****   Construtor   **** */
	public GuiaArrecadacaoSemCodigoBarras() {
		
		nomeTelefone();
		numeroDocumento(); // OBRIGATORIO
		periodoApuracao();
		cpfCnpj();
		codigoReceita();
		numeroReferencia();
		dataVencimento(); // OBRIGATORIO
		valorTotal(); // OBRIGATORIO 
		this.autenticacao = "Autenticaca Bancaria";
	}
	
	/* ****   Gera Nome e Telefone Aleatoriamente   **** */
	public void nomeTelefone() {
		List<String> nome_telefoneAleatorio = new ArrayList<String>();
		nome_telefoneAleatorio.add("Joao Carlos - (51) 99999.9999");
		nome_telefoneAleatorio.add("Pedro Henrique - (51) 99888.8888");
		nome_telefoneAleatorio.add("Jose Antonio - (51) 99777.777");
		nome_telefoneAleatorio.add("Ana Luiza - (51) 99666.666");
		nome_telefoneAleatorio.add("Luis Augusto - (51) 99555.5555");
		
		int i = (int) (Math.random() * 5);
		this.nome_telefone = nome_telefoneAleatorio.get(i);
	}
	
	/* ****   Gera Numero do Documento Aleatoriamente   **** */
	public void numeroDocumento() {
		long j = (long) (100000000000l + Math.random() * 999999999999l);
		this.numero_documento = j + "";
	}
	
	/* ****   Gera a Data/Periodo de Apuracao Aleatoriamente   **** */
	public void periodoApuracao() {
		LocalDate data_atual = LocalDate.now();
		int aux = (int) (1 + Math.random() * 2);
		
		if(aux == 1) {
			int dia_atual = data_atual.getDayOfMonth();
			int dia_aleatorio_mes_atual = (int) (1 + Math.random() * dia_atual);
			if(data_atual.getMonthValue() < 10) {
				if(dia_aleatorio_mes_atual < 10) {
					this.periodo_apuracao = "0" + dia_aleatorio_mes_atual + "/0" + data_atual.getMonthValue() + 
							"/" + data_atual.getYear();
				}
				else this.periodo_apuracao = dia_aleatorio_mes_atual + "/0" + data_atual.getMonthValue() + 
						"/" + data_atual.getYear();
			}
			else {
				if(dia_aleatorio_mes_atual < 10) {
					this.periodo_apuracao = "0" + dia_aleatorio_mes_atual + "/" + data_atual.getMonthValue() + 
							"/" + data_atual.getYear();
				}
				else this.periodo_apuracao = dia_aleatorio_mes_atual + "/" + data_atual.getMonthValue() + 
						"/" + data_atual.getYear();	
			}
		}
		else if(aux == 2) {
			int dia_aleatorio_mes_anterior = (int) (1 + Math.random() * 31);
			
			if(data_atual.getMonthValue() <= 10) {
				if(dia_aleatorio_mes_anterior < 10) {
					this.periodo_apuracao = "0" + dia_aleatorio_mes_anterior + "/0" + (data_atual.getMonthValue() - 1) + 
							"/" + data_atual.getYear();
				}
				else this.periodo_apuracao = dia_aleatorio_mes_anterior + "/0" + (data_atual.getMonthValue() - 1) + 
						"/" + data_atual.getYear();
			}
			else {
				if(dia_aleatorio_mes_anterior < 10) {
					this.periodo_apuracao = "0" + dia_aleatorio_mes_anterior + "/" + (data_atual.getMonthValue() - 1) + 
							"/" + data_atual.getYear();
				}
				else this.periodo_apuracao = dia_aleatorio_mes_anterior + "/" + (data_atual.getMonthValue() - 1) + 
						"/" + data_atual.getYear();	
			}
		}
	}
	
	/* ****   Gera os Numeros de CPF ou CNPJ Aleatoriamente   **** */
	public void cpfCnpj() {
		int aux = (int) (1 + Math.random() * 2);
		
		if(aux == 1) {
			String cpf_parte_1 = (int) (Math.random() * 10) + "" + (int) (Math.random() * 10) + (int) (Math.random() * 10);
			String cpf_parte_2 = (int) (Math.random() * 10) + "" + (int) (Math.random() * 10) + (int) (Math.random() * 10);
			String cpf_parte_3 = (int) (Math.random() * 10) + "" + (int) (Math.random() * 10) + (int) (Math.random() * 10);
			String cpf_parte_4 = (int) (Math.random() * 10) + "" + (int) (Math.random() * 10);
			
			this.cpf_cnpj = cpf_parte_1 + "." + cpf_parte_2 + "." + cpf_parte_3 + "-" + cpf_parte_4;
		}
		else if(aux == 2) {
			String cpf_parte_1 = (int) (Math.random() * 10) + "" + (int) (Math.random() * 10);
			String cpf_parte_2 = (int) (Math.random() * 10) + "" + (int) (Math.random() * 10) + (int) (Math.random() * 10);
			String cpf_parte_3 = (int) (Math.random() * 10) + "" + (int) (Math.random() * 10) + (int) (Math.random() * 10);
			String cpf_parte_4 = (int) (Math.random() * 10) + "" + (int) (Math.random() * 10) + (int) (Math.random() * 10) + (int) (Math.random() * 10);
			String cpf_parte_5 = (int) (Math.random() * 10) + "" + (int) (Math.random() * 10);
			
			this.cpf_cnpj = cpf_parte_1 + "." + cpf_parte_2 + "." + cpf_parte_3 + "/" + cpf_parte_4 + "-" + cpf_parte_5;
		}
	}
	
	/* ****   Gera o Codigo da Receita Aleatoriamente   **** */
	public void codigoReceita() {
		this.codigo_receita = (int) (Math.random() * 10) + "" + (int) (Math.random() * 10) + (int) (Math.random() * 10) + (int) (Math.random() * 10);;
	}
	
	/* ****   Gera o Numero de Referencia Aleatoriamente   **** */
	public void numeroReferencia() {
		this.numero_referencia = (int) (Math.random() * 10) + "" + (int) (Math.random() * 10) + (int) (Math.random() * 10) + (int) (Math.random() * 10);;
	}
	
	/* ****   Gera a Data de Vencimento Aleatoriamente   **** */
	public void dataVencimento() {
		LocalDate data_atual = LocalDate.now();
		int data = (int) (Math.random() * 30);
		LocalDate data_vencimento = data_atual.plusDays(data);
		
		DateTimeFormatter novo_formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.data_vencimento = data_vencimento.format(novo_formato);
	}
	
	/* ****   Gera os Valores Principal, de Multa, Juros e Total Aleatoriamente   **** */
	public void valorTotal() {
		double valor_principal = 0;
		double valor_multa = 0;
		double valor_juros = 0;
		double valor_total = 0;
		
		do {
			valor_principal = (1 + Math.random() * 99999l);
			valor_multa = (1 + Math.random() * 99l);
			valor_juros = (1 + Math.random() * 99l);
			valor_total = valor_principal + valor_multa + valor_juros;
		} while(valor_total >= 99999);
		
		//this.valor_total = valor_total + "";
		NumberFormat formato_real = NumberFormat.getCurrencyInstance();
		this.valor_principal = formato_real.format(valor_principal).toString();
		this.valor_multa = formato_real.format(valor_multa).toString();
		this.valor_juros = formato_real.format(valor_juros).toString();
		this.valor_total = formato_real.format(valor_total).toString();
	}
}
