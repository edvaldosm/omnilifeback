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

import com.br.omnilife.dto.MedidaCorporalDTO;
import com.br.omnilife.dto.Response;
import com.br.omnilife.exceptions.MedidaCorporalExceptions;
import com.br.omnilife.service.MedidaCorporalService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/medidacorporal")
public class MedidaCorporalRestController {

	@Autowired
	MedidaCorporalService service;

	@ApiOperation(value = "Cadastrar uma nova Medida Corporal", notes = "Essa operação salva um novo registro com as informações da patologia")
	@PostMapping
	public ResponseEntity<Response<MedidaCorporalDTO>> postMedidaCorporal(@Valid @RequestBody MedidaCorporalDTO dto,
			UriComponentsBuilder ucBuilder) throws MedidaCorporalExceptions {
		Response<MedidaCorporalDTO> response = new Response<>();
		try {

			HttpHeaders headers = new HttpHeaders();
			MedidaCorporalDTO medida = service.inserir(dto);
			headers.setLocation(ucBuilder.path("/medidacorporal/{id}").buildAndExpand(medida.getId()).toUri());

			response.setCode(medida);
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Operação realizada com sucesso");

			return new ResponseEntity<Response<MedidaCorporalDTO>>(response, headers, HttpStatus.CREATED);

		} catch (MedidaCorporalExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<MedidaCorporalDTO>>(response, e.getHttpCode());
		}
	}

	@ApiOperation(value = "Alterar Medida Corporal", response = MedidaCorporalDTO.class, notes = "Essa operação altera as informações do suplemento cadastrado na base")
	@PutMapping(path = "/{id}")
	public ResponseEntity<Response<MedidaCorporalDTO>> updateMedidaCorporal(
			@Valid @RequestBody MedidaCorporalDTO medida, @PathVariable("id") Integer id)
			throws MedidaCorporalExceptions {

		Response<MedidaCorporalDTO> response = new Response<>();
		try {
			response.setCode(service.atualizar(medida, id));
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Atualização realizada com sucesso");
			return new ResponseEntity<Response<MedidaCorporalDTO>>(response, HttpStatus.ACCEPTED);

		} catch (MedidaCorporalExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<MedidaCorporalDTO>>(response, e.getHttpCode());
		}
	}

	@ApiOperation(value = "Lista de Medida Corporal", response = MedidaCorporalDTO.class, responseContainer = "List", notes = "Essa operação consulta geral de patologia cadastrados na base")
	@GetMapping
	public ResponseEntity<Response<List<MedidaCorporalDTO>>> getMedidaCorporal() throws MedidaCorporalExceptions {
		Response<List<MedidaCorporalDTO>> response = new Response<>();
		try {
			response.setCode(service.listaGeral());
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Operação realizada com sucesso");
			return new ResponseEntity<Response<List<MedidaCorporalDTO>>>(response, HttpStatus.OK);
		} catch (MedidaCorporalExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<List<MedidaCorporalDTO>>>(response, e.getHttpCode());
		}

	}

	@ApiOperation(value = "Consulta Medida Corporal por ID", response = MedidaCorporalDTO.class, notes = "Essa operação consulta por Id do Medida Corporal")
	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<MedidaCorporalDTO>> getMedidaCorporalById(@PathVariable("id") Integer id)
			throws MedidaCorporalExceptions {
		Response<MedidaCorporalDTO> response = new Response<>();
		try {

			response.setCode(service.consultaID(id));
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem("Consulta realizada com sucesso");

			return new ResponseEntity<Response<MedidaCorporalDTO>>(response, HttpStatus.OK);

		} catch (MedidaCorporalExceptions e) {
			response.setData(Calendar.getInstance().getTime());
			response.setMensagem(e.getMsg());
			return new ResponseEntity<Response<MedidaCorporalDTO>>(response, e.getHttpCode());
		}
	}
}
