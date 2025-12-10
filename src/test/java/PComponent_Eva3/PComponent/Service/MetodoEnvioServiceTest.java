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
import PComponent_Eva3.PComponent.model.Metodoenvio;
import PComponent_Eva3.PComponent.repository.MetodoEnvioRepository;
import PComponent_Eva3.PComponent.service.MetodoenvioService;

class MetodoenvioServiceTest {

    @Mock
    private MetodoEnvioRepository metodoEnvioRepository;
    @InjectMocks
    private MetodoenvioService metodoEnvioService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void findAll_retornaListaDeMetodos() {
        Metodoenvio m1 = new Metodoenvio();
        m1.setId(1);
        Metodoenvio m2 = new Metodoenvio();
        m2.setId(2);
        when(metodoEnvioRepository.findAll())
                .thenReturn(Arrays.asList(m1, m2));
        List<Metodoenvio> result = metodoEnvioService.findAll();
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
    }

    @Test
    void findById_existente_retornaMetodo() {
        Metodoenvio metodo = new Metodoenvio();
        metodo.setId(10);
        when(metodoEnvioRepository.findById(10))
                .thenReturn(Optional.of(metodo));
        Metodoenvio result = metodoEnvioService.findById(10);
        assertNotNull(result);
        assertEquals(10, result.getId());
    }
    @Test
    void findById_noExistente_retornaNull() {
        when(metodoEnvioRepository.findById(99))
                .thenReturn(Optional.empty());
        Metodoenvio result = metodoEnvioService.findById(99);
        assertNull(result);
    }
    @Test
    void save_guardaCorrectamente() {
        Metodoenvio metodo = new Metodoenvio();
        metodo.setId(5);
        when(metodoEnvioRepository.save(metodo))
                .thenReturn(metodo);
        Metodoenvio result = metodoEnvioService.save(metodo);
        assertNotNull(result);
        assertEquals(5, result.getId());
    }
    @Test
    void partialUpdate_existente_actualizaCampos() {
        Metodoenvio updateData = new Metodoenvio();
        updateData.setId(1);
        updateData.setNombre("Express");
        updateData.setTipoEnvio("Rapido");
        updateData.setCosto(4990);
        Metodoenvio existing = new Metodoenvio();
        existing.setId(1);
        existing.setNombre("Normal");
        existing.setTipoEnvio("Lento");
        existing.setCosto(2990);
        when(metodoEnvioRepository.findById(1))
                .thenReturn(Optional.of(existing));
        when(metodoEnvioRepository.save(existing))
                .thenReturn(existing);
        Metodoenvio result = metodoEnvioService.partialUpdate(updateData);
        assertNotNull(result);
        assertEquals("Express", result.getNombre());
        assertEquals("Rapido", result.getTipoEnvio());
        assertEquals(4990, result.getCosto());
    }
    @Test
    void partialUpdate_noExistente_retornaNull() {
        Metodoenvio metodo = new Metodoenvio();
        metodo.setId(100);
        when(metodoEnvioRepository.findById(100))
                .thenReturn(Optional.empty());
        Metodoenvio result = metodoEnvioService.partialUpdate(metodo);
        assertNull(result);
    }
    @Test
    void deleteById_eliminaCorrectamente() {
        metodoEnvioService.deleteById(15);
        verify(metodoEnvioRepository, times(1)).deleteById(15);
    }
}
