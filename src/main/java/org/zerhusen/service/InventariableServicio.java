package org.zerhusen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerhusen.model.Cpu;
import org.zerhusen.model.Inventariable;
import org.zerhusen.model.PlantillaCPUs;
import org.zerhusen.repository.InventariableRepository;
import org.zerhusen.service.base.BaseService;

@Service
public class InventariableServicio extends BaseService<Inventariable, Long, InventariableRepository> {
	@Autowired
	InventariableRepository invRep;

	public List<Inventariable> listaMonitores() {
		return invRep.listaMonitores();
	}

	public List<Inventariable> listaPerifericos() {
		return invRep.listaPerifericos();
	}

	public List<Inventariable> listaElementosRed() {
		return invRep.listaElementosRed();
	}

	public List<Inventariable> listaCpus() {
		return invRep.listaCpus();
	}

	public List<Cpu> findCpusDeConPlantilla(Long id) {
		return invRep.cpusDeUnaPlantilla(id);

	}

}
