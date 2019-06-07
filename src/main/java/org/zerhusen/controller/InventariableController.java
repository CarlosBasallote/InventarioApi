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
import org.zerhusen.model.Monitor;
import org.zerhusen.model.Periferico;
import org.zerhusen.model.Ubicacion;
import org.zerhusen.service.InventariableServicio;

@RestController
public class InventariableController {

	@Autowired
	private InventariableServicio invSer;

	@GetMapping(path = "/inventory")
	public ResponseEntity<?> getInventory() {
		List<Inventariable> listaInventario = invSer.findAll();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(listaInventario);
	}

	// @JsonView(Views.Ubicacion.class)
	@GetMapping(path = "/inventory/monitor")
	public ResponseEntity<?> getMonitors() {
		List<Inventariable> listaMonitores = invSer.listaMonitores();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(listaMonitores);
	}

	@GetMapping(path = "/inventory/peripheral")
	public ResponseEntity<?> getperipherals() {
		List<Inventariable> listaPerifericos = invSer.listaPerifericos();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(listaPerifericos);
	}

	@GetMapping(path = "/inventory/networkDevice")
	public ResponseEntity<?> getnetworkDevices() {
		List<Inventariable> listaElementosRed = invSer.listaElementosRed();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(listaElementosRed);
	}

	@GetMapping(path = "/inventory/cpu")
	public ResponseEntity<?> getCpus() {
		List<Inventariable> listaCpus = invSer.listaCpus();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(listaCpus);
	}

	@PostMapping(path = "/inventory/monitor")
	public ResponseEntity<?> AddMonitor(@RequestBody Monitor m) {
		Monitor mon = new Monitor();
		mon.setMarca(m.getMarca());
		mon.setTamanio(m.getTamanio());
		mon.setVga(m.isVga());
		mon.setAltavoces(m.isAltavoces());
		mon.setDvi(m.isDvi());
		mon.setHdmi(m.isHdmi());
		mon.setComentario(m.getComentario());
		mon.setUbicacion(m.getUbicacion());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		invSer.save(mon);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mon.getId())
				.toUri();
		return ResponseEntity.created(location).body(mon);
	}

	@PostMapping(path = "/inventory/peripheral")
	public ResponseEntity<?> AddPeriferico(@RequestBody Periferico p) {
		Periferico per = new Periferico();
		per.setComentario(p.getComentario());
		per.setTipo(p.getTipo());
		per.setMarca(p.getMarca());
		per.setUbicacion(p.getUbicacion());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		invSer.save(per);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(per.getId())
				.toUri();
		return ResponseEntity.created(location).body(per);
	}

	@PostMapping(path = "/inventory/cpu")
	public ResponseEntity<?> AddCpu(@RequestBody Cpu c) {
		Cpu cpu = new Cpu();
		cpu.setTipoRAM1(c.getTipoRAM1());
		cpu.setTipoRAM2(c.getTipoRAM2());
		cpu.setTipoRAM3(c.getTipoRAM3());
		cpu.setTipoRAM4(c.getTipoRAM4());
		cpu.setTamanioRAM1(c.getTamanioRAM1());
		cpu.setTamanioRAM2(c.getTamanioRAM2());
		cpu.setTamanioRAM3(c.getTamanioRAM3());
		cpu.setTamanioRAM4(c.getTamanioRAM4());
		cpu.setVelocidadRAM1(c.getVelocidadRAM1());
		cpu.setVelocidadRAM2(c.getVelocidadRAM2());
		cpu.setVelocidadRAM3(c.getVelocidadRAM3());
		cpu.setVelocidadRAM4(c.getVelocidadRAM4());
		cpu.setTipoHD1(c.getTipoHD1());
		cpu.setTipoHD2(c.getTipoHD2());
		cpu.setTamanioHD1(c.getTamanioHD1());
		cpu.setTamanioHD2(c.getTamanioHD2());
		cpu.setPlantillaCpus(c.getPlantillaCpus());
		cpu.setComentario(c.getComentario());
		cpu.setUbicacion(c.getUbicacion());
		invSer.save(cpu);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cpu.getId())
				.toUri();
		return ResponseEntity.created(location).body(cpu);
	}

	@PostMapping(path = "/inventory/networkDevice")
	public ResponseEntity<?> AddElemetRed(@RequestBody ElementoRed e) {
		ElementoRed ele = new ElementoRed();
		ele.setComentario(e.getComentario());
		ele.setUbicacion(e.getUbicacion());
		ele.setMarca(e.getMarca());
		ele.setTipo(e.getTipo());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		invSer.save(ele);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ele.getId())
				.toUri();
		return ResponseEntity.created(location).body(ele);
	}

	@DeleteMapping("/inventory/monitor/{id}")
	public ResponseEntity<?> borrarMonitor(@PathVariable Long id) {
		Monitor m = (Monitor) invSer.findById(id);

		invSer.delete(m);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/inventory/networkDevice/{id}")
	public ResponseEntity<?> borrarElementoRed(@PathVariable Long id) {
		ElementoRed e = (ElementoRed) invSer.findById(id);

		invSer.delete(e);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/inventory/peripheral/{id}")
	public ResponseEntity<?> borrarPeriferico(@PathVariable Long id) {
		Periferico p = (Periferico) invSer.findById(id);

		invSer.delete(p);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/inventory/cpu/{id}")
	public ResponseEntity<?> borrarCpu(@PathVariable Long id) {
		Cpu c = (Cpu) invSer.findById(id);

		invSer.delete(c);

		return ResponseEntity.noContent().build();
	}

	@GetMapping("/inventory/monitor/{id}")
	public ResponseEntity<?> getMonitor(@PathVariable Long id) {
		Monitor m = (Monitor) invSer.findById(id);
		return ResponseEntity.accepted().body(m);
	}

	@GetMapping("/inventory/peripheral/{id}")
	public ResponseEntity<?> getPeriferico(@PathVariable Long id) {
		Periferico p = (Periferico) invSer.findById(id);
		return ResponseEntity.accepted().body(p);
	}

	@GetMapping("/inventory/cpu/{id}")
	public ResponseEntity<?> getCpu(@PathVariable Long id) {
		Cpu c = (Cpu) invSer.findById(id);
		return ResponseEntity.accepted().body(c);
	}

	@GetMapping("/inventory/networkDevice/{id}")
	public ResponseEntity<?> getElementoRed(@PathVariable Long id) {
		ElementoRed e = (ElementoRed) invSer.findById(id);
		return ResponseEntity.accepted().body(e);
	}
	
	//LOS IF DEL EDIT ES PARA QUE SE PUEDA EDITAR LOS CAMPOS QUE QUIERA SIN PERDER LOS ANTIGUOS
	@PutMapping("/inventory/monitor/{id}")
	public ResponseEntity<?> editarMonitor(@PathVariable Long id, @RequestBody Monitor m) {
		Monitor mon = (Monitor) invSer.findById(id);
		if (m.getMarca() != null) {
			mon.setMarca(m.getMarca());
		}
		if (m.getTamanio() != 0) {
			mon.setTamanio(m.getTamanio());
		}
		if (!m.isVga()) {
			mon.setVga(m.isVga());
		}
		if (!m.isAltavoces()) {
			mon.setAltavoces(m.isAltavoces());
		}
		if (!m.isDvi()) {
			mon.setDvi(m.isDvi());
		}
		if (!m.isHdmi()) {
			mon.setHdmi(m.isHdmi());
		}
		if (m.getComentario() != null) {
			mon.setComentario(m.getComentario());
		}
		if (m.getUbicacion() != null) {
			mon.setUbicacion(m.getUbicacion());
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		invSer.save(mon);
		return ResponseEntity.status(HttpStatus.OK).body(mon);
	}
	
	//LOS IF DEL EDIT ES PARA QUE SE PUEDA EDITAR LOS CAMPOS QUE QUIERA SIN PERDER LOS ANTIGUOS
	@PutMapping("/inventory/peripheral/{id}")
	public ResponseEntity<?> editarPeriferico(@PathVariable Long id, @RequestBody Periferico p) {
		Periferico per = (Periferico) invSer.findById(id);
		if (p.getTipo() != null) {
			per.setTipo(p.getTipo());
		}
		if (p.getMarca() != null) {
			per.setMarca(p.getMarca());
		}
		if (p.getComentario() != null) {
			per.setComentario(p.getComentario());
		}
		if (p.getUbicacion() != null) {
			per.setUbicacion(p.getUbicacion());
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		invSer.save(per);
		return ResponseEntity.status(HttpStatus.OK).body(per);
	}

	//LOS IF DEL EDIT ES PARA QUE SE PUEDA EDITAR LOS CAMPOS QUE QUIERA SIN PERDER LOS ANTIGUOS
	@PutMapping("/inventory/cpu/{id}")
	public ResponseEntity<?> editarCpu(@PathVariable Long id, @RequestBody Cpu c) {
		Cpu cpu = (Cpu) invSer.findById(id);
		if (c.getTipoRAM1() != null) {
			cpu.setTipoRAM1(c.getTipoRAM1());
		}
		if (c.getTipoRAM2() != null) {
			cpu.setTipoRAM2(c.getTipoRAM2());
		}
		if (c.getTipoRAM3() != null) {
			cpu.setTipoRAM3(c.getTipoRAM3());
		}
		if (c.getTipoRAM4() != null) {
			cpu.setTipoRAM4(c.getTipoRAM4());
		}
		if (c.getTamanioRAM1() != 0) {
			cpu.setTamanioRAM1(c.getTamanioRAM1());
		}
		if (c.getTamanioRAM2() != 0) {
			cpu.setTamanioRAM2(c.getTamanioRAM2());
		}
		if (c.getTamanioRAM3() != 0) {
			cpu.setTamanioRAM3(c.getTamanioRAM3());
		}
		if (c.getTamanioRAM4() != 0) {
			cpu.setTamanioRAM4(c.getTamanioRAM4());
		}
		if (c.getComentario() != null) {
			cpu.setComentario(c.getComentario());
		}
		if (c.getUbicacion() != null) {
			cpu.setUbicacion(c.getUbicacion());
		}
		if (c.getVelocidadRAM1() != null) {
			cpu.setVelocidadRAM1(c.getVelocidadRAM1());
		}
		if (c.getVelocidadRAM2() != null) {
			cpu.setVelocidadRAM2(c.getVelocidadRAM2());
		}
		if (c.getVelocidadRAM3() != null) {
			cpu.setVelocidadRAM3(c.getVelocidadRAM3());
		}
		if (c.getVelocidadRAM4() != null) {
			cpu.setVelocidadRAM4(c.getVelocidadRAM4());
		}
		if (c.getTipoHD1() != null) {
			cpu.setTipoHD1(c.getTipoHD1());

		}
		if (c.getTipoHD2() != null) {
			cpu.setTipoHD2(c.getTipoHD2());

		}
		if (c.getTamanioHD1() != 0) {
			cpu.setTamanioHD1(c.getTamanioHD1());

		}
		if (c.getTamanioHD2() != 0) {
			cpu.setTamanioHD2(c.getTamanioHD2());

		}
		if (c.getPlantillaCpus() != null) {
			cpu.setPlantillaCpus(c.getPlantillaCpus());
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		invSer.save(cpu);
		return ResponseEntity.status(HttpStatus.OK).body(cpu);
	}
	
	//LOS IF DEL EDIT ES PARA QUE SE PUEDA EDITAR LOS CAMPOS QUE QUIERA SIN PERDER LOS ANTIGUOS
	@PutMapping("/inventory/networkDevice/{id}")
	public ResponseEntity<?> editarElementoRed(@PathVariable Long id, @RequestBody ElementoRed e) {
		ElementoRed ele = (ElementoRed) invSer.findById(id);
		if (e.getTipo() != null) {
			ele.setTipo(e.getTipo());
		}
		if (e.getMarca() != null) {
			ele.setMarca(e.getMarca());
		}
		if (e.getComentario() != null) {
			ele.setComentario(e.getComentario());
		}
		if (e.getUbicacion() != null) {
			ele.setUbicacion(e.getUbicacion());
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		invSer.save(ele);
		return ResponseEntity.status(HttpStatus.OK).body(ele);
	}

}
