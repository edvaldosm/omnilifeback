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

import com.br.omnilife.dto.ComprasDTO;
import com.br.omnilife.dto.Response;
import com.br.omnilife.exceptions.ComprasExceptions;
import com.br.omnilife.service.ComprasService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/compras")
public class ComprasRestController {

	@Autowired
	ComprasService service;

	@ApiOperation(value = "Cadastrar uma nova Compras", notes = "Essa operação salva um novo registro com as informações da Compras")
	@PostMapping
	public ResponseEntity<Response<ComprasDTO>> postCompras(@Valid @RequestBody ComprasDTO dto,
			UriComponentsBuilder ucBuilder) throws ComprasExceptions {
		Response<ComprasDTO> response = new Response<>();
		try {

			HttpHeaders headers = new HttpHeaders();
			ComprasDTO compras = service.inserir(dto);
			headers.setLocation(ucBuilder.path("/medidacorporal/{id}").buildAndExpand(compras.getId()).toUri());

			response.setCode(compras);
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Operação realizada com sucesso");

			return new ResponseEntity<Response<ComprasDTO>>(response, headers, HttpStatus.CREATED);

		} catch (ComprasExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<ComprasDTO>>(response, e.getHttpCode());
		}
	}

	@ApiOperation(value = "Alterar Compras", response = ComprasDTO.class, notes = "Essa operação altera as informações do compras cadastrado na base")
	@PutMapping(path = "/{id}")
	public ResponseEntity<Response<ComprasDTO>> updateCompras(
			@Valid @RequestBody ComprasDTO dto, @PathVariable("id") Integer id)
			throws ComprasExceptions {

		Response<ComprasDTO> response = new Response<>();
		try {
			response.setCode(service.atualizar(dto, id));
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Atualização realizada com sucesso");
			return new ResponseEntity<Response<ComprasDTO>>(response, HttpStatus.ACCEPTED);

		} catch (ComprasExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<ComprasDTO>>(response, e.getHttpCode());
		}
	}

	@ApiOperation(value = "Lista de Compras", response = ComprasDTO.class, responseContainer = "List", notes = "Essa operação consulta geral de Compras cadastrados na base")
	@GetMapping
	public ResponseEntity<Response<List<ComprasDTO>>> getCompras() throws ComprasExceptions {
		Response<List<ComprasDTO>> response = new Response<>();
		try {
			response.setCode(service.listaGeral());
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Operação realizada com sucesso");
			return new ResponseEntity<Response<List<ComprasDTO>>>(response, HttpStatus.OK);
		} catch (ComprasExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<List<ComprasDTO>>>(response, e.getHttpCode());
		}

	}

	@ApiOperation(value = "Consulta Compras por ID", response = ComprasDTO.class, notes = "Essa operação consulta por Id do Compras")
	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<ComprasDTO>> getCompraslById(@PathVariable("id") Integer id)
			throws ComprasExceptions {
		Response<ComprasDTO> response = new Response<>();
		try {

			response.setCode(service.consultaID(id));
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Consulta realizada com sucesso");

			return new ResponseEntity<Response<ComprasDTO>>(response, HttpStatus.OK);

		} catch (ComprasExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<ComprasDTO>>(response, e.getHttpCode());
		}
	}
}
