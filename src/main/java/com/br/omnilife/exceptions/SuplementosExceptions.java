package com.br.omnilife.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SuplementosExceptions extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String msg;
	private HttpStatus httpCode;
	private Throwable causa;

	private static final String REGISTRONAOENCONTRADO = "Registro nao encontrado na base: ";

	private static final String INSERIRSUPLEMENTOS = "Problema ao inserir na tabela Suplementos: ";
	private static final String ATUALIZARSUPLEMENTOS = "Problema ao atualizar na tabela Suplementos: ";
	private static final String CONSULTARIDSUPLEMENTOS = "Problema ao consultar por Id na tabela Suplementos:";
	
	private static final String ERROR_INTERNO = "Ocorreu um problema interno ao executar operação: ";
	

	public SuplementosExceptions registroNaoEncontrado(String parametro) {
		return new SuplementosExceptions(REGISTRONAOENCONTRADO + parametro, HttpStatus.NOT_FOUND, null);
	}

	public SuplementosExceptions inserirSuplementos(Exception e) {
		return new SuplementosExceptions(INSERIRSUPLEMENTOS, HttpStatus.BAD_REQUEST, e.getCause());
	}

	public SuplementosExceptions atualizarSuplementos(Exception e) {
		return new SuplementosExceptions(ATUALIZARSUPLEMENTOS, HttpStatus.BAD_REQUEST, e.getCause());
	}

	public SuplementosExceptions consultarIdSuplementos(IllegalArgumentException e) {
		return new SuplementosExceptions(CONSULTARIDSUPLEMENTOS, HttpStatus.INTERNAL_SERVER_ERROR, e.getCause());
	}
	
	public SuplementosExceptions errorInterno(String operacao, Exception e) {
		return new SuplementosExceptions(ERROR_INTERNO + operacao, HttpStatus.INTERNAL_SERVER_ERROR, e.getCause());
	}
}