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
import PComponent_Eva3.PComponent.model.Envio;
import PComponent_Eva3.PComponent.repository.EnvioRepository;
import PComponent_Eva3.PComponent.service.EnvioService;

class EnvioServiceTest {

    @Mock
    private EnvioRepository envioRepository;
    @InjectMocks
    private EnvioService envioService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void findAll_retornaLista() {
        Envio e1 = new Envio();
        e1.setId(1);
        Envio e2 = new Envio();
        e2.setId(2);
        when(envioRepository.findAll()).thenReturn(Arrays.asList(e1, e2));
        List<Envio> result = envioService.findAll();
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
    }
    @Test
    void findById_existente_retornaEnvio() {
        Envio e = new Envio();
        e.setId(5);
        when(envioRepository.findById(5)).thenReturn(Optional.of(e));
        Envio result = envioService.findById(5);
        assertNotNull(result);
        assertEquals(5, result.getId());
    }
    @Test
    void findById_noExistente_retornaNull() {
        when(envioRepository.findById(50)).thenReturn(Optional.empty());
        Envio result = envioService.findById(50);
        assertNull(result);
    }
    @Test
    void save_guardaCorrectamente() {
        Envio envio = new Envio();
        envio.setId(7);
        when(envioRepository.save(envio)).thenReturn(envio);
        Envio result = envioService.save(envio);
        assertNotNull(result);
        assertEquals(7, result.getId());
    }
    @Test
    void partialUpdate_envioExiste_actualiza() {
        Envio envio = new Envio();
        envio.setId(10);
        Envio existing = new Envio();
        existing.setId(10);
        when(envioRepository.findById(10)).thenReturn(Optional.of(existing));
        when(envioRepository.save(existing)).thenReturn(existing);
        Envio result = envioService.partialUpdate(envio);
        assertNotNull(result);
        assertEquals(10, result.getId());
    }
    @Test
    void partialUpdate_envioNoExiste_retornaNull() {
        Envio envio = new Envio();
        envio.setId(100);
        when(envioRepository.findById(100)).thenReturn(Optional.empty());
        Envio result = envioService.partialUpdate(envio);
        assertNull(result);
    }
    @Test
    void deleteById_eliminaCorrectamente() {
        envioService.deleteById(9);

        verify(envioRepository, times(1)).deleteById(9);
    }
}
