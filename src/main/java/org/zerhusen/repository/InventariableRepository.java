package org.zerhusen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerhusen.model.Cpu;
import org.zerhusen.model.Inventariable;
import org.zerhusen.model.PlantillaCPUs;
import org.zerhusen.model.Ubicacion;

public interface InventariableRepository extends JpaRepository<Inventariable, Long> {

	@Query("select i from Inventariable i where TYPE(i) = Monitor")
	List<Inventariable> listaMonitores();

	@Query("select i from Inventariable i where TYPE(i) = Periferico")
	List<Inventariable> listaPerifericos();

	@Query("select i from Inventariable i where TYPE(i) = ElementoRed")
	List<Inventariable> listaElementosRed();

	@Query("select i from Inventariable i where TYPE(i) = Cpu")
	List<Inventariable> listaCpus();

	@Query("select i from Inventariable i where i.ubicacion = ?1")
	List<Inventariable> todosDeUnaUbicacion(Ubicacion u);

	@Query("select c from Cpu c where c.plantillaCpus.id=?1")
	List<Cpu> cpusDeUnaPlantilla(Long id);

	@Query("select count(i) from Inventariable i where TYPE(i) = Monitor")
	Integer sumaM();

	@Query("select count(i) from Inventariable i where TYPE(i) = Periferico")
	Integer sumaP();

	@Query("select count(i) from Inventariable i where TYPE(i) = Cpu")
	Integer sumaC();

	@Query("select count(i) from Inventariable i where TYPE(i) = ElementoRed")
	Integer sumaE();

}
