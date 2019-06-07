package org.zerhusen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerhusen.model.Cpu;
import org.zerhusen.model.Inventariable;
import org.zerhusen.model.PlantillaCPUs;
import org.zerhusen.repository.InventariableRepository;
import org.zerhusen.repository.PlantillaCpuRepository;
import org.zerhusen.service.base.BaseService;

@Service
public class PlantillaCpuService extends BaseService<PlantillaCPUs, Long, PlantillaCpuRepository> {

}
