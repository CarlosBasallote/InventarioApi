package org.zerhusen;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.zerhusen.model.Cpu;
import org.zerhusen.model.ElementoRed;
import org.zerhusen.model.Monitor;
import org.zerhusen.model.Periferico;
import org.zerhusen.model.PlantillaCPUs;
import org.zerhusen.model.TipoElemento;
import org.zerhusen.model.TipoHd;
import org.zerhusen.model.TipoPeriferico;
import org.zerhusen.model.Ubicacion;
import org.zerhusen.service.InventariableServicio;
import org.zerhusen.service.PlantillaCpuService;
import org.zerhusen.service.UbicacionServicio;

@SpringBootApplication
public class JwtDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(InventariableServicio inventariableServicio, UbicacionServicio ubicacionServicio,
			PlantillaCpuService plantillaSer) {
		return args -> {
			Ubicacion u = ubicacionServicio.save(new Ubicacion("Trastero 4", "Trastero"));
			Ubicacion u2 = ubicacionServicio.save(new Ubicacion("Aula 2", "Aula"));
			Ubicacion u3 = ubicacionServicio.save(new Ubicacion("Despacho de Angel", "Despacho"));
			Ubicacion u4 = ubicacionServicio.save(new Ubicacion("Aula 7", "Aula"));
			PlantillaCPUs p = plantillaSer.save(new PlantillaCPUs("Smart", "V2", "Drr4", "1300"));
			PlantillaCPUs p1 = plantillaSer.save(new PlantillaCPUs("Xradian", "Z4", "Drr3", "600"));
			PlantillaCPUs p2 = plantillaSer.save(new PlantillaCPUs("Zenon", "Pro", "Drr4", "1200"));
			inventariableServicio
					.save(new Monitor("Funciona correctamente", u, "Samsung", 22, false, true, false, true));
			inventariableServicio.save(new Monitor("Un boton no funcion", u2, "Fujistsu", 22, true, true, false, true));
			inventariableServicio.save(new Monitor("Todo correcto", u3, "Fujistsu", 22, true, true, true, true));
			inventariableServicio.save(new ElementoRed("Funciona bien", u3, TipoElemento.SWITCH, "Woxter"));
			inventariableServicio.save(new ElementoRed("A veces se corta la señal", u, TipoElemento.ROUTER, "Hifi"));
			inventariableServicio.save(new ElementoRed("Poco rango", u4, TipoElemento.SWITCH, "VMR"));
			inventariableServicio.save(new Periferico("Poco rango", u4, "Lenovo", TipoPeriferico.KEYBOARD));
			inventariableServicio.save(new Periferico("Poco rango", u2, "Logitech", TipoPeriferico.MOUSE));
			inventariableServicio.save(new Periferico("Poco rango", u, "JBL", TipoPeriferico.SPEAKERS));
			inventariableServicio.save(new Cpu("Se recalienta a las 2 horas", u, "Drr4", "Drr4", null, null, 4.0f, 4.0f,
					0.0f, 0.0f, "1200 Mhz", "1330 Mhz", null, null, TipoHd.SDD, TipoHd.SDD, 200.0f, 500.0f, p2));
			inventariableServicio.save(new Cpu("Funciona correctamente", u2, "Drr4", "Drr4", null, null, 4.0f, 0.0f,
					0.0f, 0.0f, "800 Mhz", null, null, null, TipoHd.HDD, TipoHd.HDD, 200.0f, 230.0f, p));
			inventariableServicio.save(new Cpu("Cuidado,es caro", u3, "Drr4", "Drr4", null, null, 8.0f, 4.0f, 0.0f,
					0.0f, "1600 Mhz", "1330 Mhz", null, null, TipoHd.SDD, TipoHd.SDD, 500.0f, 1000.0f, p1));

		};
	}
}
