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
public class ElementoRed extends Inventariable {

	private TipoElemento tipo;
	private String marca;

	public ElementoRed(String comentario, Ubicacion ubicacion, TipoElemento tipo, String marca) {
		super(comentario, ubicacion);
		this.tipo = tipo;
		this.marca = marca;
	}

}
