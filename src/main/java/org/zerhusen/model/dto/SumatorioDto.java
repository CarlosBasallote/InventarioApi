package org.zerhusen.model.dto;

import java.util.List;

import org.zerhusen.model.Inventariable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SumatorioDto {
	private String tipo;
	private int cantidad;
	private List<Inventariable> listado;

}
