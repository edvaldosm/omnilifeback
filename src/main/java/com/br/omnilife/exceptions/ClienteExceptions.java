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
public class ClienteExceptions extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	private HttpStatus httpCode;
	private Throwable causa;
	
	private static final String REGISTRONAOENCONTRADO = "Registro nao encontrado na base: ";
	private static final String REGISTROSNAOENCONTRADO = "Registros da lista nao encontrado na base: ";

	private static final String INSERIRCLIENTE = "Problema ao inserir na tabela Cliente: ";
	private static final String ATUALIZARCLIENTE = "Problema ao atualizar na tabela Cliente: ";
	private static final String CONSULTARIDCLIENTE = "Problema ao consultar por Id na tabela Cliente:";

	private static final String ERROR_INTERNO = "Ocorreu um problema interno ao executar operação: ";

	public ClienteExceptions registroNaoEncontrado(String parametro) {
		return new ClienteExceptions(REGISTRONAOENCONTRADO + parametro, HttpStatus.NOT_FOUND, null);
	}
	
	public ClienteExceptions registrosNaoEncontrado(String parametro) {
		return new ClienteExceptions(REGISTROSNAOENCONTRADO + parametro, HttpStatus.NOT_FOUND, null);
	}


	public ClienteExceptions inserirCliente(Exception e) {
		return new ClienteExceptions(INSERIRCLIENTE, HttpStatus.BAD_REQUEST, e.getCause());
	}

	public ClienteExceptions atualizarCliente(Exception e) {
		return new ClienteExceptions(ATUALIZARCLIENTE, HttpStatus.BAD_REQUEST, e.getCause());
	}

	public ClienteExceptions consultarIdCliente(IllegalArgumentException e) {
		return new ClienteExceptions(CONSULTARIDCLIENTE, HttpStatus.INTERNAL_SERVER_ERROR, e.getCause());
	}

	public ClienteExceptions errorInterno(String operacao, Exception e) {
		return new ClienteExceptions(ERROR_INTERNO + operacao, HttpStatus.INTERNAL_SERVER_ERROR, e.getCause());
	}

}
