package com.edu.ufps.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.edu.ufps.service.*;
import com.edu.ufps.repository.*;
import com.edu.ufps.entity.*;
import com.edu.ufps.exception.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private FavoritoRepository favoritoRepository;

	@Autowired
	private MangaRepository mangaRepository;

	@GetMapping("/{username}/favoritos")
	public List<Manga> getFavoritos(@PathVariable String username) {
		Usuario usuario = usuarioRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
		return favoritoRepository.findByUsuarioUsername(username).stream().map(Favorito::getManga)
				.collect(Collectors.toList());
	}

	@PostMapping("/{username}/favoritos")
	public List<Manga> addFavorito(@PathVariable String username, @RequestBody Long mangaId) {
		Usuario usuario = usuarioRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
		Manga manga = mangaRepository.findById(mangaId)
				.orElseThrow(() -> new ResourceNotFoundException("Manga no encontrado"));
		if (favoritoRepository.existsByUsuarioAndManga(usuario, manga)) {
			throw new BadRequestException("Favorito ya se encuentra registrado");
		}
		Favorito favorito = new Favorito();
		favorito.setUsuario(usuario);
		favorito.setManga(manga);
		favoritoRepository.save(favorito);
		return getFavoritos(username);
	}

	@DeleteMapping("/{username}/favoritos/{mangaId}")
	public List<Manga> removeFavorito(@PathVariable String username, @PathVariable Long mangaId) {
		Usuario usuario = usuarioRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
		Manga manga = mangaRepository.findById(mangaId)
				.orElseThrow(() -> new ResourceNotFoundException("Manga no encontrado"));
		Favorito favorito = favoritoRepository.findByUsuarioAndManga(usuario, manga)
				.orElseThrow(() -> new ResourceNotFoundException("Favorito no encontrado"));
		favoritoRepository.delete(favorito);
		return getFavoritos(username);
	}
}
