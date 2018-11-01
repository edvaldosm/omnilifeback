package com.br.omnilife.restcontroller;

import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.omnilife.dto.ClienteDTO;
import com.br.omnilife.dto.Response;
import com.br.omnilife.exceptions.ClienteExceptions;
import com.br.omnilife.service.ClienteService;


import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteRestController {

	@Autowired
	ClienteService service;

	@ApiOperation(value = "Cadastrar um novo Cliente", notes = "Essa operação salva um novo registro com as informações da Cliente")
	@PostMapping
	public ResponseEntity<Response<ClienteDTO>> postCliente(@Valid @RequestBody ClienteDTO dto,
			UriComponentsBuilder ucBuilder) throws ClienteExceptions {
		Response<ClienteDTO> response = new Response<>();
		try {

			HttpHeaders headers = new HttpHeaders();
			ClienteDTO compras = service.inserir(dto);
			headers.setLocation(ucBuilder.path("/cliente/{id}").buildAndExpand(compras.getId()).toUri());

			response.setCode(compras);
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Operação realizada com sucesso");

			return new ResponseEntity<Response<ClienteDTO>>(response, headers, HttpStatus.CREATED);

		} catch (ClienteExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<ClienteDTO>>(response, e.getHttpCode());
		}
	}

	@ApiOperation(value = "Alterar Cliente", response = ClienteDTO.class, notes = "Essa operação altera as informações do cliente cadastrado na base")
	@PutMapping(path = "/{id}")
	public ResponseEntity<Response<ClienteDTO>> updateCliente(
			@Valid @RequestBody ClienteDTO dto, @PathVariable("id") Integer id)
			throws ClienteExceptions {

		Response<ClienteDTO> response = new Response<>();
		try {
			response.setCode(service.atualizar(dto, id));
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Atualização realizada com sucesso");
			return new ResponseEntity<Response<ClienteDTO>>(response, HttpStatus.ACCEPTED);

		} catch (ClienteExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<ClienteDTO>>(response, e.getHttpCode());
		}
	}

	@ApiOperation(value = "Lista de Cliente", response = ClienteDTO.class, responseContainer = "List", notes = "Essa operação consulta geral de Cliente cadastrados na base")
	@GetMapping
	public ResponseEntity<Response<List<ClienteDTO>>> getCliente() throws ClienteExceptions {
		Response<List<ClienteDTO>> response = new Response<>();
		try {
			response.setCode(service.listaGeral());
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Operação realizada com sucesso");
			return new ResponseEntity<Response<List<ClienteDTO>>>(response, HttpStatus.OK);
		} catch (ClienteExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<List<ClienteDTO>>>(response, e.getHttpCode());
		}

	}

	@ApiOperation(value = "Consulta Cliente por ID", response = ClienteDTO.class, notes = "Essa operação consulta por Id do Cliente")
	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<ClienteDTO>> getClientelById(@PathVariable("id") Integer id)
			throws ClienteExceptions {
		Response<ClienteDTO> response = new Response<>();
		try {

			response.setCode(service.consultaID(id));
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Consulta realizada com sucesso");

			return new ResponseEntity<Response<ClienteDTO>>(response, HttpStatus.OK);

		} catch (ClienteExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<ClienteDTO>>(response, e.getHttpCode());
		}
	}
}
