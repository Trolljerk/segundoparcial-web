package com.edu.ufps.service;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.ufps.repository.*;
import com.edu.ufps.entity.*;

@Service
public class MangaService {
	@Autowired
	private MangaRepository mangaRepository;

	@Autowired
	private PaisRepository paisRepository;

	@Autowired
	private TipoRepository tipoRepository;

	public List<Manga> findAll() {
		return mangaRepository.findAll();
	}

	public Manga findById(Long id) throws Exception {
		return mangaRepository.findById(id).orElseThrow(() -> new Exception("Manga not found"));
	}

	public Manga create(Manga manga) throws BadRequestException {
		if (!paisRepository.existsById(manga.getPais().getId())) {
			throw new BadRequestException("Pais no existe");
		}
		if (!tipoRepository.existsById(manga.getTipo().getId())) {
			throw new BadRequestException("Tipo no existe");
		}
		return mangaRepository.save(manga);
	}

	public Manga update(Long id, Manga manga) throws Exception {
		if (!mangaRepository.existsById(id)) {
			throw new Exception("Manga not found");
		}
		return create(manga);
	}

	public void delete(Long id) throws Exception {
		Manga manga = findById(id);
		mangaRepository.delete(manga);
	}
}
