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
public class SintomasExceptions extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String msg;
	private HttpStatus httpCode;
	private Throwable causa;

	private static final String REGISTRONAOENCONTRADO = "Registro nao encontrado na base: ";

	private static final String INSERIRSINTOMA = "Problema ao inserir na tabela Sintoma: ";
	private static final String ATUALIZARSINTOMA = "Problema ao atualizar na tabela Sintoma: ";
	private static final String CONSULTARIDSINTOMA = "Problema ao consultar por Id na tabela Sintoma:";
	
	private static final String ERROR_INTERNO = "Ocorreu um problema interno ao executar operação: ";
	

	public SintomasExceptions registroNaoEncontrado(String parametro) {
		return new SintomasExceptions(REGISTRONAOENCONTRADO + parametro, HttpStatus.NOT_FOUND, null);
	}

	public SintomasExceptions inserirSintoma(Exception e) {
		return new SintomasExceptions(INSERIRSINTOMA, HttpStatus.BAD_REQUEST, e.getCause());
	}

	public SintomasExceptions atualizarSintoma(Exception e) {
		return new SintomasExceptions(ATUALIZARSINTOMA, HttpStatus.BAD_REQUEST, e.getCause());
	}

	public SintomasExceptions consultarIdSintoma(IllegalArgumentException e) {
		return new SintomasExceptions(CONSULTARIDSINTOMA, HttpStatus.INTERNAL_SERVER_ERROR, e.getCause());
	}
	
	public SintomasExceptions errorInterno(String operacao, Exception e) {
		return new SintomasExceptions(ERROR_INTERNO + operacao, HttpStatus.INTERNAL_SERVER_ERROR, e.getCause());
	}
}