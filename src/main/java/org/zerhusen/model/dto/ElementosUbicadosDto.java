package org.zerhusen.model.dto;

import java.util.List;

import org.zerhusen.model.Inventariable;
import org.zerhusen.model.Ubicacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElementosUbicadosDto {

	private Ubicacion ubicacion;
	private String tipo;
	private List<Inventariable> listado;

}
