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

import PComponent_Eva3.PComponent.model.Producto;
import PComponent_Eva3.PComponent.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/producto")
@Tag(name = "Producto Management System v2")
public class ProductoControllerV2 {

	@Autowired
	private ProductoService productoService;

	@GetMapping
	@Operation(summary = "Listar todos los productos (v2)")
	public ResponseEntity<List<Producto>> getAllProductos() {
		List<Producto> productos = productoService.findAll();
		if (productos == null || productos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(productos);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obtener un producto por Id (v2)")
	public ResponseEntity<Producto> getProductoById(@PathVariable Integer id) {
		Producto producto = productoService.findById(id);
		if (producto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(producto);
	}

	@PostMapping
	@Operation(summary = "Crear un nuevo producto (v2)")
	public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
		Producto createdProducto = productoService.save(producto);
		return ResponseEntity.status(201).body(createdProducto);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Actualización de un producto existente (v2)")
	public ResponseEntity<Producto> updateProducto(@PathVariable Integer id, @RequestBody Producto producto) {
		producto.setId(id);
		Producto updatedProducto = productoService.save(producto);
		if (updatedProducto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedProducto);
	}

	@PatchMapping("/{id}")
	@Operation(summary = "Actualización parcial de un producto (v2)")
	public ResponseEntity<Producto> updatePartialProducto(@PathVariable Integer id, @RequestBody Producto producto) {
		producto.setId(id);
		Producto updatedProducto = productoService.partialUpdate(producto);
		if (updatedProducto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedProducto);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar un producto(v2)")
	public ResponseEntity<Void> deleteProducto(@PathVariable Integer id) {
		productoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
