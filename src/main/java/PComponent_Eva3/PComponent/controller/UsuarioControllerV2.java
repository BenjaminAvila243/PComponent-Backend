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

import PComponent_Eva3.PComponent.model.Usuario;
import PComponent_Eva3.PComponent.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/usuario")
@Tag(name = "Usuario Management System v2")
public class UsuarioControllerV2 {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	@Operation(summary = "Ver una lista de usuarios disponibles (v2)")
	public ResponseEntity<List<Usuario>> getAllUsuarios() {
		List<Usuario> usuarios = usuarioService.findAll();
		if (usuarios == null || usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarios);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obtener un usuario por Id (v2)")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
		Usuario usuario = usuarioService.findById(id);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}

	@PostMapping
	@Operation(summary = "Agregar un nuevo usuario (v2)")
	public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
		Usuario createdUsuario = usuarioService.save(usuario);
		return ResponseEntity.status(201).body(createdUsuario);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Actualización de un usuario existente (v2)")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
		usuario.setId(id);
		Usuario updatedUsuario = usuarioService.save(usuario);
		if (updatedUsuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedUsuario);
	}

	@PatchMapping("/{id}")
	@Operation(summary = "Actualización parcial de un usuario (v2)")
	public ResponseEntity<Usuario> updatePartialUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
		usuario.setId(id);
		Usuario updatedUsuario = usuarioService.partialUpdate(usuario);
		if (updatedUsuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedUsuario);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar un usuario (v2)")
	public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
		usuarioService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}