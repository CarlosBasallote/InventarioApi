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
public class Periferico extends Inventariable {

	private String marca;
	private TipoPeriferico tipo;

	public Periferico(String comentario, Ubicacion ubicacion, String marca, TipoPeriferico tipo) {
		super(comentario, ubicacion);
		this.marca = marca;
		this.tipo = tipo;
	}

}
