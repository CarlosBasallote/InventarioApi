package org.zerhusen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerhusen.model.Inventariable;
import org.zerhusen.model.Ubicacion;
import org.zerhusen.repository.InventariableRepository;
import org.zerhusen.repository.UbicacionRepository;
import org.zerhusen.service.base.BaseService;

@Service
public class UbicacionServicio extends BaseService<Ubicacion, Long, UbicacionRepository> {

	@Autowired
	InventariableRepository inventariableRepository;

	public List<Inventariable> inventariablesDeUnaUbicacion(Ubicacion u) {
		return inventariableRepository.todosDeUnaUbicacion(u);
	}

}
