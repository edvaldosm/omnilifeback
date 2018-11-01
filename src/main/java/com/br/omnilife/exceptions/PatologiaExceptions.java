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
public class PatologiaExceptions extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String msg;
	private HttpStatus httpCode;
	private Throwable causa;

	private static final String REGISTRONAOENCONTRADO = "Registro nao encontrado na base: ";

	private static final String INSERIRPATOLOGIA = "Problema ao inserir na tabela Patologia: ";
	private static final String ATUALIZARPATOLOGIA = "Problema ao atualizar na tabela Patologia: ";
	private static final String CONSULTARIDPATOLOGIA = "Problema ao consultar por Id na tabela Patologia:";

	private static final String ERROR_INTERNO = "Ocorreu um problema interno ao executar operação: ";

	public PatologiaExceptions registroNaoEncontrado(String parametro) {
		return new PatologiaExceptions(REGISTRONAOENCONTRADO + parametro, HttpStatus.NOT_FOUND, null);
	}

	public PatologiaExceptions inserirPatologia(Exception e) {
		return new PatologiaExceptions(INSERIRPATOLOGIA, HttpStatus.BAD_REQUEST, e.getCause());
	}

	public PatologiaExceptions atualizarPatologia(Exception e) {
		return new PatologiaExceptions(ATUALIZARPATOLOGIA, HttpStatus.BAD_REQUEST, e.getCause());
	}

	public PatologiaExceptions consultarIdPatologia(IllegalArgumentException e) {
		return new PatologiaExceptions(CONSULTARIDPATOLOGIA, HttpStatus.INTERNAL_SERVER_ERROR, e.getCause());
	}

	public PatologiaExceptions errorInterno(String operacao, Exception e) {
		return new PatologiaExceptions(ERROR_INTERNO + operacao, HttpStatus.INTERNAL_SERVER_ERROR, e.getCause());
	}
}