package com.nelioalves.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.dto.ClienteNewDTO;
import com.nelioalves.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		 Cliente obj = service.find(id);
		 return ResponseEntity.ok(obj);
	}
	
	@GetMapping(value = "/email")
	public ResponseEntity<Cliente> findByEmail(@RequestParam("value") String email) {
		 Cliente obj = service.findByEmail(email);
		 return ResponseEntity.ok(obj);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody @Valid ClienteNewDTO objDto) {
		Cliente obj = service.insert(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody @Valid ClienteDTO objDto, @PathVariable Integer id) {
		Cliente obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDTO = list.stream().map(ClienteDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok(listDTO);
	}
	
	@GetMapping(value="/page")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(name="page", defaultValue="0") Integer page,
			@RequestParam(name="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(name="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(name="direction", defaultValue="ASC") String direction) {
		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDTO = list.map(ClienteDTO::new);
		return ResponseEntity.ok(listDTO);
	}
	
	@PostMapping(value="/picture")
	public ResponseEntity<Void> insert(@RequestParam(name="file") MultipartFile file) {
		URI uri = service.uploadProfilePicture(file);
		return ResponseEntity.created(uri).build();
	}

}
