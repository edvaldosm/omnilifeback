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
import com.br.omnilife.dto.PatologiaDTO;
import com.br.omnilife.exceptions.PatologiaExceptions;
import com.br.omnilife.service.PatologiaService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/patologia")
public class PatologiaRestController {

	@Autowired
	PatologiaService service;

	@ApiOperation(value = "Cadastrar uma nova Patologia", notes = "Essa operação salva um novo registro com as informações da patologia")
	@PostMapping
	public ResponseEntity<Response<PatologiaDTO>> postPatologia(@Valid @RequestBody PatologiaDTO dto,
			UriComponentsBuilder ucBuilder) throws PatologiaExceptions {
		Response<PatologiaDTO> response = new Response<>();
		try {

			HttpHeaders headers = new HttpHeaders();
			PatologiaDTO patologia = service.inserir(dto);
			headers.setLocation(ucBuilder.path("/patologia/{id}").buildAndExpand(patologia.getId()).toUri());

			response.setCode(patologia);
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Operação realizada com sucesso");

			return new ResponseEntity<Response<PatologiaDTO>>(response, headers, HttpStatus.CREATED);

		} catch (PatologiaExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<PatologiaDTO>>(response, e.getHttpCode());
		}
	}

	@ApiOperation(value = "Alterar Patologia", response = PatologiaDTO.class, notes = "Essa operação altera as informações do suplemento cadastrado na base")
	@PutMapping(path = "/{id}")
	public ResponseEntity<Response<PatologiaDTO>> updatePatologia(@Valid @RequestBody PatologiaDTO patologia,
			@PathVariable("id") Integer id) throws PatologiaExceptions {

		Response<PatologiaDTO> response = new Response<>();
		try {
			response.setCode(service.atualizar(patologia, id));
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Atualização realizada com sucesso");
			return new ResponseEntity<Response<PatologiaDTO>>(response, HttpStatus.ACCEPTED);

		} catch (PatologiaExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<PatologiaDTO>>(response, e.getHttpCode());
		}
	}

	@ApiOperation(value = "Lista de Patologia", response = PatologiaDTO.class, responseContainer = "List", notes = "Essa operação consulta geral de patologia cadastrados na base")
	@GetMapping
	public ResponseEntity<Response<List<PatologiaDTO>>> getPatologia() throws PatologiaExceptions {
		Response<List<PatologiaDTO>> response = new Response<>();
		try {
			response.setCode(service.listaGeral());
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Operação realizada com sucesso");
			return new ResponseEntity<Response<List<PatologiaDTO>>>(response, HttpStatus.OK);
		} catch (PatologiaExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<List<PatologiaDTO>>>(response, e.getHttpCode());
		}

	}

	@ApiOperation(value = "Consulta Patologia por ID", response = PatologiaDTO.class, notes = "Essa operação consulta por Id do Patologia")
	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<PatologiaDTO>> getPatologiaById(@PathVariable("id") Integer id)
			throws PatologiaExceptions {
		Response<PatologiaDTO> response = new Response<>();
		try {

			response.setCode(service.consultaID(id));
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Consulta realizada com sucesso");

			return new ResponseEntity<Response<PatologiaDTO>>(response, HttpStatus.OK);

		} catch (PatologiaExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<PatologiaDTO>>(response, e.getHttpCode());
		}
	}

	@ApiOperation(value = "Consulta Patologia por Nome", response = PatologiaDTO.class, notes = "Essa operação consulta por nome do Patologia cadastrado na base")
	@GetMapping(path = "/nome/{nome}")
	public ResponseEntity<Response<List<PatologiaDTO>>> getPatologiaByName(@PathVariable("nome") String nome)
			throws PatologiaExceptions {
		Response<List<PatologiaDTO>> response = new Response<>();
		try {

			response.setCode(service.consultaPorNome(nome));
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Consulta realizada com sucesso");

			return new ResponseEntity<Response<List<PatologiaDTO>>>(response, HttpStatus.OK);

		} catch (PatologiaExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<List<PatologiaDTO>>>(response, e.getHttpCode());
		}
	}

}
