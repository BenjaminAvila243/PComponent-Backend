package PComponent_Eva3.PComponent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import PComponent_Eva3.PComponent.service.EnvioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import PComponent_Eva3.PComponent.model.Envio;

@RestController
@RequestMapping("/api/v2/envio")
@Tag(name = "Envio Management System v2")
public class EnvioControllerV2 {

	@Autowired
	private EnvioService envioService;

	@GetMapping
	@Operation(summary = "Listar todos los envios (v2)")
	public ResponseEntity<List<Envio>> getAllEnvios() {
		List<Envio> envios = envioService.findAll();
		if (envios == null || envios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(envios);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obtener un envio por Id (v2)")
	public ResponseEntity<Envio> getEnvioById(@PathVariable Integer id) {
		Envio envio = envioService.findById(id);
		if (envio == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(envio);
	}

	@PostMapping
	@Operation(summary = "Crear un nuevo envio (v2)")
	public ResponseEntity<Envio> createEnvio(@RequestBody Envio envio) {
		Envio created = envioService.save(envio);
		return ResponseEntity.status(201).body(created);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Actualizar un envio existente (v2)")
	public ResponseEntity<Envio> updateEnvio(@PathVariable Integer id, @RequestBody Envio envio) {
		envio.setId(id);
		Envio updated = envioService.save(envio);
		if (updated == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/{id}")
	@Operation(summary = "Actualizacion parcial de un envio (v2)")
	public ResponseEntity<Envio> updatePartialEnvio(@PathVariable Integer id, @RequestBody Envio envio) {
		envio.setId(id);
		Envio updated = envioService.partialUpdate(envio);
		if (updated == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar un envio (v2)")
	public ResponseEntity<Void> deleteEnvio(@PathVariable Integer id) {
		envioService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}