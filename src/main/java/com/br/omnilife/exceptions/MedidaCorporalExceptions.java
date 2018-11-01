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
public class MedidaCorporalExceptions extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String msg;
	private HttpStatus httpCode;
	private Throwable causa;

	private static final String REGISTRONAOENCONTRADO = "Registro nao encontrado na base: ";

	private static final String INSERIRMEDIDACORPORAL = "Problema ao inserir na tabela Medida Corporal: ";
	private static final String ATUALIZARMEDIDACORPORAL = "Problema ao atualizar na tabela Medida Corporal: ";
	private static final String CONSULTARIDMEDIDACORPORAL = "Problema ao consultar por Id na tabela Medida Corporal:";

	private static final String ERROR_INTERNO = "Ocorreu um problema interno ao executar operação: ";

	public MedidaCorporalExceptions

			registroNaoEncontrado(String parametro) {
		return new MedidaCorporalExceptions(REGISTRONAOENCONTRADO + parametro, HttpStatus.NOT_FOUND, null);
	}

	public MedidaCorporalExceptions

			inserirMedidaCorporal(Exception e) {
		return new MedidaCorporalExceptions(INSERIRMEDIDACORPORAL, HttpStatus.BAD_REQUEST, e.getCause());
	}

	public MedidaCorporalExceptions

			atualizarMedidaCorporal(Exception e) {
		return new MedidaCorporalExceptions(ATUALIZARMEDIDACORPORAL, HttpStatus.BAD_REQUEST, e.getCause());
	}

	public MedidaCorporalExceptions

			consultarIdMedidaCorporal(IllegalArgumentException e) {
		return new MedidaCorporalExceptions(CONSULTARIDMEDIDACORPORAL, HttpStatus.INTERNAL_SERVER_ERROR, e.getCause());
	}

	public MedidaCorporalExceptions

			errorInterno(String operacao, Exception e) {
		return new MedidaCorporalExceptions(ERROR_INTERNO + operacao, HttpStatus.INTERNAL_SERVER_ERROR, e.getCause());
	}

}
