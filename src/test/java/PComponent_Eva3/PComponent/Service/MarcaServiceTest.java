package PComponent_Eva3.PComponent.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import PComponent_Eva3.PComponent.model.Marca;
import PComponent_Eva3.PComponent.repository.MarcaRepository;
import PComponent_Eva3.PComponent.service.MarcaService;

class MarcaServiceTest {

    @Mock
    private MarcaRepository marcaRepository;
    @InjectMocks
    private MarcaService marcaService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void findAll_retornaLista() {
        Marca m1 = new Marca();
        m1.setId(1);
        Marca m2 = new Marca();
        m2.setId(2);
        when(marcaRepository.findAll()).thenReturn(Arrays.asList(m1, m2));
        List<Marca> result = marcaService.findAll();
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
    }
    @Test
    void findById_existente_retornaMarca() {
        Marca marca = new Marca();
        marca.setId(10);
        when(marcaRepository.findById(10)).thenReturn(Optional.of(marca));
        Marca result = marcaService.findById(10);
        assertNotNull(result);
        assertEquals(10, result.getId());
    }
    @Test
    void findById_noExistente_retornaNull() {
        when(marcaRepository.findById(99)).thenReturn(Optional.empty());
        Marca result = marcaService.findById(99);
        assertNull(result);
    }
    @Test
    void save_guardaCorrectamente() {
        Marca marca = new Marca();
        marca.setId(5);
        when(marcaRepository.save(marca)).thenReturn(marca);
        Marca result = marcaService.save(marca);
        assertNotNull(result);
        assertEquals(5, result.getId());
    }
    @Test
    void partialUpdate_existente_actualizaNombre() {
        Marca marcaUpdate = new Marca();
        marcaUpdate.setId(1);
        marcaUpdate.setNombreMarca("Nueva Marca");
        Marca existing = new Marca();
        existing.setId(1);
        existing.setNombreMarca("Marca Vieja");
        when(marcaRepository.findById(1)).thenReturn(Optional.of(existing));
        when(marcaRepository.save(existing)).thenReturn(existing);
        Marca result = marcaService.partialUpdate(marcaUpdate);
        assertNotNull(result);
        assertEquals("Nueva Marca", result.getNombreMarca());
    }
    @Test
    void partialUpdate_noExistente_retornaNull() {
        Marca marca = new Marca();
        marca.setId(50);
        when(marcaRepository.findById(50)).thenReturn(Optional.empty());
        Marca result = marcaService.partialUpdate(marca);
        assertNull(result);
    }
    @Test
    void deleteById_eliminaCorrectamente() {
        marcaService.deleteById(7);
        verify(marcaRepository, times(1)).deleteById(7);
    }
}
