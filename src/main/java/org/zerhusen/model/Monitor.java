package org.zerhusen.model;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
public class Monitor extends Inventariable {

	private String marca;
	private float tamanio;
	private boolean vga;
	private boolean dvi;
	private boolean hdmi;
	private boolean altavoces;

	public Monitor(String comentario, Ubicacion ubicacion, String marca, float tamanio, boolean vga, boolean dvi,
			boolean hdmi, boolean altavoces) {
		super(comentario, ubicacion);
		this.marca = marca;
		this.tamanio = tamanio;
		this.vga = vga;
		this.dvi = dvi;
		this.hdmi = hdmi;
		this.altavoces = altavoces;
	}

}
