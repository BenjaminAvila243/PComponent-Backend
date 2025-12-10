package PComponent_Eva3.PComponent.Service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import PComponent_Eva3.PComponent.model.Categoria;
import PComponent_Eva3.PComponent.repository.CategoriaRepository;
import PComponent_Eva3.PComponent.service.CategoriaService;


@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTest {

	@Mock
	private CategoriaRepository categoriaRepository;

	@InjectMocks
	private CategoriaService categoriaService;

	@Test
	void findAll_returnsList() {
		Categoria c = new Categoria(1, "Cat", "Desc", null);
		when(categoriaRepository.findAll()).thenReturn(Arrays.asList(c));

		List<Categoria> result = categoriaService.findAll();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("Cat", result.get(0).getNombreCategoria());
	}

	@Test
	void findById_found() {
		Categoria c = new Categoria(2, "Cat2", "Desc2", null);
		when(categoriaRepository.findById(2)).thenReturn(Optional.of(c));

		Categoria result = categoriaService.findById(2);

		assertNotNull(result);
		assertEquals(2, result.getId());
		assertEquals("Cat2", result.getNombreCategoria());
	}

	@Test
	void findById_notFound() {
		when(categoriaRepository.findById(999)).thenReturn(Optional.empty());

		Categoria result = categoriaService.findById(999);

		assertNull(result);
	}

	@Test
	void save_delegatesToRepository() {
		Categoria c = new Categoria(null, "New", "D", null);
		Categoria saved = new Categoria(10, "New", "D", null);
		when(categoriaRepository.save(any(Categoria.class))).thenReturn(saved);

		Categoria result = categoriaService.save(c);

		assertNotNull(result);
		assertEquals(10, result.getId());
		assertEquals("New", result.getNombreCategoria());
	}

	@Test
	void partialUpdate_updatesWhenExists() {
		Categoria existing = new Categoria(5, "OldName", "OldDesc", null);
		Categoria patch = new Categoria(5, "NewName", null, null);

		when(categoriaRepository.findById(5)).thenReturn(Optional.of(existing));
		when(categoriaRepository.save(any(Categoria.class))).thenAnswer(i -> i.getArgument(0));

		Categoria result = categoriaService.partialUpdate(patch);

		assertNotNull(result);
		assertEquals(5, result.getId());
		assertEquals("NewName", result.getNombreCategoria());
	}

	@Test
	void partialUpdate_returnsNullWhenNotFound() {
		Categoria patch = new Categoria(50, "Name", null, null);
		when(categoriaRepository.findById(50)).thenReturn(Optional.empty());

		Categoria result = categoriaService.partialUpdate(patch);

		assertNull(result);
	}

	@Test
	void deleteById_callsRepository() {
		categoriaService.deleteById(7);
		verify(categoriaRepository).deleteById(7);
	}
}
