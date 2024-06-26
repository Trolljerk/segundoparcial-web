package com.edu.ufps.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.edu.ufps.entity.*;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {

	List<Favorito> findByUsuarioUsername(String username);

	boolean existsByUsuarioAndManga(Usuario usuario, Manga manga);

	void deleteByUsuarioAndManga(Usuario usuario, Manga manga);

	Optional<Favorito> findByUsuarioAndManga(Usuario usuario, Manga manga);
}
