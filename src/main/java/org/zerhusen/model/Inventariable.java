package org.zerhusen.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "Inventariable")
@Inheritance(strategy = InheritanceType.JOINED)
public class Inventariable {

	@Id
	@GeneratedValue
	private long id;
	private String comentario;
	@ManyToOne
	private Ubicacion ubicacion;

	public Inventariable(String comentario, Ubicacion ubicacion) {
		this.comentario = comentario;
		this.ubicacion = ubicacion;
	}

}
