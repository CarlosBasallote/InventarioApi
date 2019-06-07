package org.zerhusen.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
public class Cpu extends Inventariable {

	private String tipoRAM1;
	private String tipoRAM2;
	private String tipoRAM3;
	private String tipoRAM4;
	private float tamanioRAM1;
	private float tamanioRAM2;
	private float tamanioRAM3;
	private float tamanioRAM4;
	private String velocidadRAM1;
	private String velocidadRAM2;
	private String velocidadRAM3;
	private String velocidadRAM4;
	private TipoHd tipoHD1;
	private TipoHd tipoHD2;
	private float tamanioHD1;
	private float tamanioHD2;
	@ManyToOne
	private PlantillaCPUs plantillaCpus;

	@Builder
	public Cpu(String comentario, Ubicacion ubicacion, String tipoRAM1, String tipoRAM2, String tipoRAM3,
			String tipoRAM4, float tamanioRAM1, float tamanioRAM2, float tamanioRAM3, float tamanioRAM4,
			String velocidadRAM1, String velocidadRAM2, String velocidadRAM3, String velocidadRAM4, TipoHd tipoHD1,
			TipoHd tipoHD2, float tamanioHD1, float tamanioHD2, PlantillaCPUs plantillaCpus) {
		super(comentario, ubicacion);
		this.tipoRAM1 = tipoRAM1;
		this.tipoRAM2 = tipoRAM2;
		this.tipoRAM3 = tipoRAM3;
		this.tipoRAM4 = tipoRAM4;
		this.tamanioRAM1 = tamanioRAM1;
		this.tamanioRAM2 = tamanioRAM2;
		this.tamanioRAM3 = tamanioRAM3;
		this.tamanioRAM4 = tamanioRAM4;
		this.velocidadRAM1 = velocidadRAM1;
		this.velocidadRAM2 = velocidadRAM2;
		this.velocidadRAM3 = velocidadRAM3;
		this.velocidadRAM4 = velocidadRAM4;
		this.tipoHD1 = tipoHD1;
		this.tipoHD2 = tipoHD2;
		this.tamanioHD1 = tamanioHD1;
		this.tamanioHD2 = tamanioHD2;
		this.plantillaCpus = plantillaCpus;
	}

}
