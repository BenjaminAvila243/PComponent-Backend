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
import PComponent_Eva3.PComponent.model.Venta;
import PComponent_Eva3.PComponent.model.Metodoenvio;
import PComponent_Eva3.PComponent.repository.VentaRepository;
import PComponent_Eva3.PComponent.service.VentaService;

public class VentaServiceTest {

    @Mock
    private VentaRepository ventaRepository;
    @InjectMocks
    private VentaService ventaService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testSave() {
        Venta venta = new Venta();
        venta.setId(1);
        when(ventaRepository.save(venta)).thenReturn(venta);
        Venta result = ventaService.save(venta);
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(ventaRepository).save(venta);
    }
    @Test
    void testFindById() {
        Venta venta = new Venta();
        venta.setId(1);
        when(ventaRepository.findById(1)).thenReturn(Optional.of(venta));
        Venta result = ventaService.findById(1);
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(ventaRepository).findById(1);
    }
    @Test
    void testFindAll() {
        Venta v1 = new Venta();
        Venta v2 = new Venta();
        when(ventaRepository.findAll()).thenReturn(Arrays.asList(v1, v2));
        List<Venta> result = ventaService.findAll();
        assertEquals(2, result.size());
        verify(ventaRepository).findAll();
    }
    @Test
    void testPartialUpdate() {
        Venta existingVenta = new Venta();
        existingVenta.setId(1);
        Metodoenvio metodo = new Metodoenvio();
        metodo.setId(10);
        Venta updateData = new Venta();
        updateData.setId(1);
        updateData.setMetodoEnvio(metodo);
        when(ventaRepository.findById(1)).thenReturn(Optional.of(existingVenta));
        when(ventaRepository.save(existingVenta)).thenReturn(existingVenta);
        Venta result = ventaService.partialUpdate(updateData);
        assertNotNull(result);
        assertEquals(10, result.getMetodoEnvio().getId());
        verify(ventaRepository).save(existingVenta);
    }
    @Test
    void testDeleteById() {
        ventaService.deleteById(1);
        verify(ventaRepository).deleteById(1);
    }
}
