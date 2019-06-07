package org.zerhusen.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class PlantillaCPUs {

	@Id
	@GeneratedValue
	private Long id;
	private String marca;
	private String modelo;
	private String tipoRam;
	private String velocidadMaxRam;

	public PlantillaCPUs(String marca, String modelo, String tipoRam, String velocidadMaxRam) {
		this.marca = marca;
		this.modelo = modelo;
		this.tipoRam = tipoRam;
		this.velocidadMaxRam = velocidadMaxRam;
	}

}
