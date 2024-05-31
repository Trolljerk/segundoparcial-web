package com.edu.ufps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.ufps.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}
