package org.zerhusen.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.zerhusen.model.Inventariable;
import org.zerhusen.model.PlantillaCPUs;
import org.zerhusen.model.Ubicacion;
import org.zerhusen.model.dto.AddUbicacionDto;
import org.zerhusen.service.InventariableServicio;
import org.zerhusen.service.UbicacionServicio;

@RestController
public class UbicacionController {

	@Autowired
	UbicacionServicio ubiSer;

	@Autowired
	InventariableServicio invSer;

	@RequestMapping(path = "/location", method = RequestMethod.GET)
	public ResponseEntity<?> getLocations() {
		List<Ubicacion> listaUbicaciones = ubiSer.findAll();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(listaUbicaciones);
	}

	@PostMapping("/location")
	public ResponseEntity<?> AddUbicacion(@RequestBody AddUbicacionDto u) {
		Ubicacion ubi = new Ubicacion();
		ubi.setNombre(u.getNombre());
		ubi.setTipo(u.getTipo());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		ubiSer.save(ubi);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ubi.getId())
				.toUri();
		return ResponseEntity.created(location).body(ubi);
	}

	@DeleteMapping("/location/{id}")
	public ResponseEntity<?> borrarUbicacion(@PathVariable Long id) {
		Ubicacion u = ubiSer.findById(id);

		ubiSer.inventariablesDeUnaUbicacion(ubiSer.findById(id))
				.forEach(inventariable -> inventariable.setUbicacion(null));

		ubiSer.delete(u);

		return ResponseEntity.noContent().build();
	}
	
	//LOS IF DEL EDIT ES PARA QUE SE PUEDA EDITAR LOS CAMPOS QUE QUIERA SIN PERDER LOS ANTIGUOS
	@PutMapping("/location/{id}")
	public ResponseEntity<?> editarUbicacion(@PathVariable Long id, @RequestBody Ubicacion u) {
		Ubicacion ubi = ubiSer.findById(id);
		if (u.getNombre() != null) {
			ubi.setNombre(u.getNombre());
		}
		if (u.getTipo() != null) {
			ubi.setTipo(u.getTipo());
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ubiSer.save(ubi);
		return ResponseEntity.status(HttpStatus.OK).body(ubi);
	}

}
