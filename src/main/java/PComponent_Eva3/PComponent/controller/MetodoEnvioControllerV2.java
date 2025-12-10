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

import PComponent_Eva3.PComponent.model.Metodoenvio;
import PComponent_Eva3.PComponent.service.MetodoenvioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/metodoEnvio")
@Tag(name = "Metodo Envio Management System v2")
public class MetodoEnvioControllerV2 {

	@Autowired
	private MetodoenvioService metodoenvioService;

	@GetMapping
	@Operation(summary = "Listar todos los metodos de envio (v2)")
	public ResponseEntity<List<Metodoenvio>> getAllMetodosEnvio() {
		List<Metodoenvio> metodoenvio = metodoenvioService.findAll();
		if (metodoenvio == null || metodoenvio.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(metodoenvio);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obtener un metodo de envio por Id (v2)")
	public ResponseEntity<Metodoenvio> getMetodoEnvioById(@PathVariable Integer id) {
		Metodoenvio metodoenvio = metodoenvioService.findById(id);
		if (metodoenvio == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(metodoenvio);
	}

	@PostMapping
	@Operation(summary = "Crear un nuevo metodo de envio (v2)")
	public ResponseEntity<Metodoenvio> createMetodoEnvio(@RequestBody Metodoenvio metodoenvio) {
		Metodoenvio createdMetodoEnvio = metodoenvioService.save(metodoenvio);
		return ResponseEntity.status(201).body(createdMetodoEnvio);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Actualizar un metodo de envio existente (v2)")
	public ResponseEntity<Metodoenvio> updateMetodoEnvio(@PathVariable Integer id, @RequestBody Metodoenvio metodoenvio) {
		metodoenvio.setId(id);
		Metodoenvio updatedMetodoEnvio = metodoenvioService.save(metodoenvio);
		if (updatedMetodoEnvio == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedMetodoEnvio);
	}

	@PatchMapping("/{id}")
	@Operation(summary = "Actualizacion parcial de un metodo de envio (v2)")
	public ResponseEntity<Metodoenvio> patchMetodoEnvio(@PathVariable Integer id, @RequestBody Metodoenvio metodoenvio) {
		metodoenvio.setId(id);
		Metodoenvio updatedMetodoEnvio = metodoenvioService.partialUpdate(metodoenvio);
		if (updatedMetodoEnvio == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedMetodoEnvio);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar un metodo de envio(v2)")
	public ResponseEntity<Void> deleteMetodoEnvio(@PathVariable Integer id) {
		metodoenvioService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
