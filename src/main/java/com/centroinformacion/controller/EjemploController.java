package com.centroinformacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centroinformacion.entity.Ejemplo;
import com.centroinformacion.kafka.service.EjemploEventService;
import com.centroinformacion.service.EjemploService;


@RestController
@RequestMapping("/url/ejemplo")
public class EjemploController {

	@Autowired
	private EjemploService ejemploService;
	
	@Autowired
	private EjemploEventService ejemploEventService;
	
	@GetMapping("/lista")
	public List<Ejemplo> listaEjemplo() {
		return ejemploService.listaEjemplo();
	}
	
	@PostMapping("/inserta")
	public Ejemplo insertaEjemplo(@RequestBody Ejemplo obj) {
		ejemploEventService.publish(obj);
		return ejemploService.insertaActualizaEjemplo(obj);
	}
	
}
