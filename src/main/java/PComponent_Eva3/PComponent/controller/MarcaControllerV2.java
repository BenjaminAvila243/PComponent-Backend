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

import PComponent_Eva3.PComponent.model.Marca;
import PComponent_Eva3.PComponent.service.MarcaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/marca")
@Tag(name = "Marca Management System v2")
public class MarcaControllerV2 {

	@Autowired
	private MarcaService marcaService;

	@GetMapping
	@Operation(summary = "Listar todas las marcas (v2)")
	public ResponseEntity<List<Marca>> getAllMarcas() {
		List<Marca> marcas = marcaService.findAll();
		if (marcas == null || marcas.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(marcas);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obtener una marca por Id (v2)")
	public ResponseEntity<Marca> getMarcaById(@PathVariable Integer id) {
		Marca marca = marcaService.findById(id);
		if (marca == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(marca);
	}

	@PostMapping
	@Operation(summary = "Crear una nueva marca (v2)")
	public ResponseEntity<Marca> createMarca(@RequestBody Marca marca) {
		Marca created = marcaService.save(marca);
		return ResponseEntity.status(201).body(created);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Actualizar una marca existente (v2)")
	public ResponseEntity<Marca> updateMarca(@PathVariable Integer id, @RequestBody Marca marca) {
		marca.setId(id);
		Marca updated = marcaService.save(marca);
		if (updated == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/{id}")
	@Operation(summary = "Actualizacion parcial de una marca (v2)")
	public ResponseEntity<Marca> updatePartialMarca(@PathVariable Integer id, @RequestBody Marca marca) {
		marca.setId(id);
		Marca updated = marcaService.partialUpdate(marca);
		if (updated == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updated);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar una marca (v2)")
	public ResponseEntity<Void> deleteMarca(@PathVariable Integer id) {
		marcaService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}