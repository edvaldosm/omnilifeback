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
public class ComprasExceptions extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String msg;
	private HttpStatus httpCode;
	private Throwable causa;

	private static final String REGISTRONAOENCONTRADO = "Registro nao encontrado na base: ";

	private static final String INSERIRCOMPRAS = "Problema ao inserir na tabela Compras: ";
	private static final String ATUALIZARCOMPRAS = "Problema ao atualizar na tabela Compras: ";
	private static final String CONSULTARIDCOMPRAS = "Problema ao consultar por Id na tabela Compras:";

	private static final String ERROR_INTERNO = "Ocorreu um problema interno ao executar operação: ";

	public ComprasExceptions registroNaoEncontrado(String parametro) {
		return new ComprasExceptions(REGISTRONAOENCONTRADO + parametro, HttpStatus.NOT_FOUND, null);
	}

	public ComprasExceptions inserirCompras(Exception e) {
		return new ComprasExceptions(INSERIRCOMPRAS, HttpStatus.BAD_REQUEST, e.getCause());
	}

	public ComprasExceptions atualizarCompras(Exception e) {
		return new ComprasExceptions(ATUALIZARCOMPRAS, HttpStatus.BAD_REQUEST, e.getCause());
	}

	public ComprasExceptions consultarIdCompras(IllegalArgumentException e) {
		return new ComprasExceptions(CONSULTARIDCOMPRAS, HttpStatus.INTERNAL_SERVER_ERROR, e.getCause());
	}

	public ComprasExceptions errorInterno(String operacao, Exception e) {
		return new ComprasExceptions(ERROR_INTERNO + operacao, HttpStatus.INTERNAL_SERVER_ERROR, e.getCause());
	}

}
