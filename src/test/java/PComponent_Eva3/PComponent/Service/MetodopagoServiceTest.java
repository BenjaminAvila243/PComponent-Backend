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
import PComponent_Eva3.PComponent.model.Metodopago;
import PComponent_Eva3.PComponent.repository.MetodopagoRepository;
import PComponent_Eva3.PComponent.service.MetodopagoService;

public class MetodopagoServiceTest {
    @Mock
    private MetodopagoRepository metodopagoRepository;
    @InjectMocks
    private MetodopagoService metodopagoService;
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testSave() {
        Metodopago metodoPago = new Metodopago();
        metodoPago.setId(1);
        when(metodopagoRepository.save(metodoPago)).thenReturn(metodoPago);
        Metodopago result = metodopagoService.save(metodoPago);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }
    @Test
    void testFindById() {
        Metodopago metodoPago = new Metodopago();
        metodoPago.setId(1);
        when(metodopagoRepository.findById(1)).thenReturn(Optional.of(metodoPago));
        Metodopago result = metodopagoService.findById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testFindAll() {
        Metodopago m1 = new Metodopago();
        m1.setId(1);
        Metodopago m2 = new Metodopago();
        m2.setId(2);
        when(metodopagoRepository.findAll()).thenReturn(Arrays.asList(m1, m2));
        List<Metodopago> result = metodopagoService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    void testPartialUpdate_ReturnsExistingMetodo() {
        Metodopago metodoPago = new Metodopago();
        metodoPago.setId(1);
        Metodopago existingMetodo = new Metodopago();
        existingMetodo.setId(1);
        when(metodopagoRepository.findById(1)).thenReturn(Optional.of(existingMetodo));
        when(metodopagoRepository.save(existingMetodo)).thenReturn(existingMetodo);
        Metodopago result = metodopagoService.partialUpdate(metodoPago);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testPartialUpdate_ReturnsNull() {
        Metodopago metodoPago = new Metodopago();
        metodoPago.setId(10);
        when(metodopagoRepository.findById(10)).thenReturn(Optional.empty());
        Metodopago result = metodopagoService.partialUpdate(metodoPago);
        assertNull(result);
    }
    @Test
    void testDeleteById() {
        doNothing().when(metodopagoRepository).deleteById(1);

        metodopagoService.deleteById(1);

        verify(metodopagoRepository, times(1)).deleteById(1);
    }
}
