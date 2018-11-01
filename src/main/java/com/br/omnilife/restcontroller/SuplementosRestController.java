package com.br.omnilife.restcontroller;

import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.omnilife.dto.Response;
import com.br.omnilife.dto.SuplementosDTO;
import com.br.omnilife.exceptions.SuplementosExceptions;
import com.br.omnilife.service.SuplementosService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/suplementos")
public class SuplementosRestController {

	@Autowired
	SuplementosService service;

	@ApiOperation(value = "Cadastrar um novo suplemento", notes = "Essa operação salva um novo registro com as informações do suplemento")
	@PostMapping
	public ResponseEntity<Response<SuplementosDTO>> postSuplementos(@Valid @RequestBody SuplementosDTO dto,
			UriComponentsBuilder ucBuilder) throws SuplementosExceptions {
		Response<SuplementosDTO> response = new Response<>();
		try {

			HttpHeaders headers = new HttpHeaders();
			SuplementosDTO suplementos = service.inserir(dto);
			headers.setLocation(ucBuilder.path("/suplementos/{id}").buildAndExpand(suplementos.getId()).toUri());

			response.setCode(suplementos);
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Operação realizada com sucesso");

			return new ResponseEntity<Response<SuplementosDTO>>(response, headers, HttpStatus.CREATED);

		} catch (SuplementosExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<SuplementosDTO>>(response, e.getHttpCode());
		}
	}

	@ApiOperation(value = "Alterar Suplementos", response = SuplementosDTO.class, notes = "Essa operação altera as informações do suplemento cadastrado na base")
	@PutMapping(path = "/{id}")
	public ResponseEntity<Response<SuplementosDTO>> updateSuplementos(@Valid @RequestBody SuplementosDTO suplementos,
			@PathVariable("id") Integer id) throws SuplementosExceptions {

		Response<SuplementosDTO> response = new Response<>();
		try {
			response.setCode(service.atualizar(suplementos, id));
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Atualização realizada com sucesso");
			return new ResponseEntity<Response<SuplementosDTO>>(response, HttpStatus.ACCEPTED);

		} catch (SuplementosExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<SuplementosDTO>>(response, e.getHttpCode());
		}
	}

	@ApiOperation(value = "Lista de Suplementos", response = SuplementosDTO.class, responseContainer = "List", notes = "Essa operação consulta geral de suplementos cadastrados na base")
	@GetMapping
	public ResponseEntity<Response<List<SuplementosDTO>>> getSuplementos() throws SuplementosExceptions {
		Response<List<SuplementosDTO>> response = new Response<>();
		try {
			response.setCode(service.listaGeral());
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Operação realizada com sucesso");
			return new ResponseEntity<Response<List<SuplementosDTO>>>(response, HttpStatus.OK);
		} catch (SuplementosExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<List<SuplementosDTO>>>(response, e.getHttpCode());
		}

	}

	@ApiOperation(value = "Consulta Suplementos por ID", response = SuplementosDTO.class, notes = "Essa operação consulta por Id do Suplementos")
	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<SuplementosDTO>> getSuplementoById(@PathVariable("id") Integer id)
			throws SuplementosExceptions {
		Response<SuplementosDTO> response = new Response<>();
		try {

			response.setCode(service.consultaID(id));
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Consulta realizada com sucesso");

			return new ResponseEntity<Response<SuplementosDTO>>(response, HttpStatus.OK);

		} catch (SuplementosExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<SuplementosDTO>>(response, e.getHttpCode());
		}
	}

	@ApiOperation(value = "Consulta Suplementos por Nome", response = SuplementosDTO.class, notes = "Essa operação consulta por nome do Suplementos cadastrado na base")
	@GetMapping(path = "/nome/{nome}")
	public ResponseEntity<Response<List<SuplementosDTO>>> getSuplementoByName(@PathVariable("nome") String nome)
			throws SuplementosExceptions {
		Response<List<SuplementosDTO>> response = new Response<>();
		try {

			response.setCode(service.consultaPorNome(nome));
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Consulta realizada com sucesso");

			return new ResponseEntity<Response<List<SuplementosDTO>>>(response, HttpStatus.OK);

		} catch (SuplementosExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<List<SuplementosDTO>>>(response, e.getHttpCode());
		}
	}

}
