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

import com.br.omnilife.dto.Response;
import com.br.omnilife.dto.SintomaDTO;
import com.br.omnilife.exceptions.SintomasExceptions;
import com.br.omnilife.service.SintomasService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/sintoma")
public class SintomasRestController {

	@Autowired
	SintomasService service;

	@ApiOperation(value = "Cadastrar um novo suplemento", notes = "Essa operação salva um novo registro com as informações do suplemento")
	@PostMapping
	public ResponseEntity<Response<SintomaDTO>> postSuplementos(@Valid @RequestBody SintomaDTO dto,
			UriComponentsBuilder ucBuilder) throws SintomasExceptions {
		Response<SintomaDTO> response = new Response<>();
		try {

			HttpHeaders headers = new HttpHeaders();
			SintomaDTO sintoma = service.inserir(dto);
			headers.setLocation(ucBuilder.path("/sintoma/{id}").buildAndExpand(sintoma.getId()).toUri());

			response.setCode(sintoma);
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Operação realizada com sucesso");

			return new ResponseEntity<Response<SintomaDTO>>(response, headers, HttpStatus.CREATED);

		} catch (SintomasExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<SintomaDTO>>(response, e.getHttpCode());
		}
	}

	@ApiOperation(value = "Alterar Suplementos", response = SintomaDTO.class, notes = "Essa operação altera as informações do suplemento cadastrado na base")
	@PutMapping(path = "/{id}")
	public ResponseEntity<Response<SintomaDTO>> updateSuplementos(@Valid @RequestBody SintomaDTO sintoma,
			@PathVariable("id") Integer id) throws SintomasExceptions {

		Response<SintomaDTO> response = new Response<>();
		try {
			response.setCode(service.atualizar(sintoma, id));
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Atualização realizada com sucesso");
			return new ResponseEntity<Response<SintomaDTO>>(response, HttpStatus.ACCEPTED);

		} catch (SintomasExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<SintomaDTO>>(response, e.getHttpCode());
		}
	}

	@ApiOperation(value = "Lista de Suplementos", response = SintomaDTO.class, responseContainer = "List", notes = "Essa operação consulta geral de sintoma cadastrados na base")
	@GetMapping
	public ResponseEntity<Response<List<SintomaDTO>>> getSuplementos() throws SintomasExceptions {
		Response<List<SintomaDTO>> response = new Response<>();
		try {
			response.setCode(service.listaGeral());
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Operação realizada com sucesso");
			return new ResponseEntity<Response<List<SintomaDTO>>>(response, HttpStatus.OK);
		} catch (SintomasExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<List<SintomaDTO>>>(response, e.getHttpCode());
		}

	}

	@ApiOperation(value = "Consulta Suplementos por ID", response = SintomaDTO.class, notes = "Essa operação consulta por Id do Suplementos")
	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<SintomaDTO>> getSuplementoById(@PathVariable("id") Integer id)
			throws SintomasExceptions {
		Response<SintomaDTO> response = new Response<>();
		try {

			response.setCode(service.consultaID(id));
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Consulta realizada com sucesso");

			return new ResponseEntity<Response<SintomaDTO>>(response, HttpStatus.OK);

		} catch (SintomasExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<SintomaDTO>>(response, e.getHttpCode());
		}
	}
}
