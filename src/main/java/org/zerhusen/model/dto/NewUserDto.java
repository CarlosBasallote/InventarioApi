package org.zerhusen.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDto {

	private Long id;
	private String nombre;
	private String email;
	private String token;

}
