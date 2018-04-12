package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.dto.CidadeDTO;
import com.nelioalves.cursomc.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;

	public List<CidadeDTO> findCidades(Integer estadoId) {
		return repo.findCidades(estadoId).stream().map(CidadeDTO::new).collect(Collectors.toList());
	}

}
