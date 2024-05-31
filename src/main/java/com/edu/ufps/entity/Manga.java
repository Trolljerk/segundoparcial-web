package com.edu.ufps.entity;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Manga {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	private LocalDate fechaLanzamiento;

	private int temporadas;

	private boolean anime;

	private boolean juego;

	private boolean pelicula;

	@ManyToOne
	@JoinColumn(name = "pais_id")
	private Pais pais;

	@ManyToOne
	@JoinColumn(name = "tipo_id")
	private Tipo tipo;
	
	// Getters & Setters Lombok Generated
}
