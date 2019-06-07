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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.zerhusen.model.Cpu;
import org.zerhusen.model.ElementoRed;
import org.zerhusen.model.Inventariable;
import org.zerhusen.model.PlantillaCPUs;
import org.zerhusen.model.Ubicacion;
import org.zerhusen.service.InventariableServicio;
import org.zerhusen.service.PlantillaCpuService;

@RestController
public class PlantillaCPUsController {

	@Autowired
	InventariableServicio invSer;

	@Autowired
	private PlantillaCpuService pcSer;

	@GetMapping(path = "/cpuTemplates")
	public ResponseEntity<?> getPlantillas() {
		List<PlantillaCPUs> lista = pcSer.findAll();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(lista);
	}

	@PostMapping(path = "/cpuTemplates")
	public ResponseEntity<?> AddPlantillaCPU(@RequestBody PlantillaCPUs p) {
		PlantillaCPUs pla = new PlantillaCPUs();
		pla.setMarca(p.getMarca());
		pla.setModelo(p.getModelo());
		pla.setTipoRam(p.getTipoRam());
		pla.setVelocidadMaxRam(p.getVelocidadMaxRam());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		pcSer.save(pla);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pla.getId())
				.toUri();
		return ResponseEntity.created(location).body(pla);
	}

	@DeleteMapping("/cpuTemplates/{id}")
	public ResponseEntity<?> borrarPlantillaCPUs(@PathVariable Long id) {
		PlantillaCPUs p = pcSer.findById(id);

		invSer.findCpusDeConPlantilla(id).forEach(cpu -> cpu.setPlantillaCpus(null));

		pcSer.delete(p);

		return ResponseEntity.noContent().build();
	}

	//LOS IF DEL EDIT ES PARA QUE SE PUEDA EDITAR LOS CAMPOS QUE QUIERA SIN PERDER LOS ANTIGUOS
	@PutMapping("/cpuTemplates/{id}")
	public ResponseEntity<?> editarPlantillaCPUs(@PathVariable Long id, @RequestBody PlantillaCPUs p) {
		PlantillaCPUs planti = pcSer.findById(id);
		if (p.getMarca() != null) {
			planti.setMarca(p.getMarca());
		}
		if (p.getModelo() != null) {
			planti.setModelo(p.getModelo());
		}
		if (p.getTipoRam() != null) {
			planti.setTipoRam(p.getTipoRam());
		}
		if (p.getVelocidadMaxRam() != null) {
			planti.setVelocidadMaxRam(p.getVelocidadMaxRam());
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		pcSer.save(planti);
		return ResponseEntity.status(HttpStatus.OK).body(planti);
	}

}
