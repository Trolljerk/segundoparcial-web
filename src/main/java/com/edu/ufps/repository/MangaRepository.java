package com.edu.ufps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.ufps.entity.Manga;

public interface MangaRepository extends JpaRepository<Manga, Long> {}
