package org.zerhusen.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.zerhusen.model.dto.Views;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Ubicacion {

	@Id
	@GeneratedValue
	private long id;
	@JsonView(Views.Ubicacion.class)
	private String nombre;
	@JsonView(Views.Ubicacion.class)
	private String tipo;

	public Ubicacion(String nombre, String tipo) {
		this.nombre = nombre;
		this.tipo = tipo;
	}

}
