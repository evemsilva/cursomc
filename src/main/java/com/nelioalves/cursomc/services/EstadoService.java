package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.dto.EstadoDTO;
import com.nelioalves.cursomc.repositories.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository repo;
	
	public List<EstadoDTO> findAllByOrderByNome(){
		return repo.findAllByOrderByNome().stream().map(EstadoDTO::new).collect(Collectors.toList());
	}

}
