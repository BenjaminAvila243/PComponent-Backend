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

import PComponent_Eva3.PComponent.model.Rol;
import PComponent_Eva3.PComponent.service.RolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/rol")
@Tag(name = "Rol Management System v2")
public class RolesUsuarioControllerV2 {

	@Autowired
	private RolService rolService;

	@GetMapping
	@Operation(summary = "Ver una lista de roles disponibles (v2)")
	public ResponseEntity<List<Rol>> getAllRoles() {
		List<Rol> roles = rolService.findAll();
		if (roles == null || roles.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(roles);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obtener un rol por Id (v2)")
	public ResponseEntity<Rol> getRoleById(@PathVariable Integer id) {
		Rol rol = rolService.findById(id);
		if (rol == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(rol);
	}

	@PostMapping
	@Operation(summary = "Agregar un nuevo rol (v2)")
	public ResponseEntity<Rol> createRole(@RequestBody Rol rol) {
		Rol createdRole = rolService.save(rol);
		return ResponseEntity.status(201).body(createdRole);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Actualizacion de un rol existente (v2)")
	public ResponseEntity<Rol> updateRole(@PathVariable Integer id, @RequestBody Rol rol) {
		rol.setId(id);
		Rol updatedRole = rolService.save(rol);
		if (updatedRole == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedRole);
	}

	@PatchMapping("/{id}")
	@Operation(summary = "Actualizacion parcial de un rol (v2)")
	public ResponseEntity<Rol> updatePartialRole(@PathVariable Integer id, @RequestBody Rol rol) {
		rol.setId(id);
		Rol updatedRole = rolService.partialUpdate(rol);
		if (updatedRole == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedRole);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar un rol (v2)")
	public ResponseEntity<Void> deleteRole(@PathVariable Integer id) {
		rolService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}