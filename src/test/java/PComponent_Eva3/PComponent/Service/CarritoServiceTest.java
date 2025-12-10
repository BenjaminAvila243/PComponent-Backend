package PComponent_Eva3.PComponent.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import PComponent_Eva3.PComponent.model.Carrito;
import PComponent_Eva3.PComponent.model.CarritoItem;
import PComponent_Eva3.PComponent.model.Producto;
import PComponent_Eva3.PComponent.repository.CarritoItemRepository;
import PComponent_Eva3.PComponent.repository.CarritoRepository;
import PComponent_Eva3.PComponent.repository.ProductoRepository;
import PComponent_Eva3.PComponent.service.CarritoService;

class CarritoServiceTest {

    @Mock
    private CarritoRepository carritoRepository;

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CarritoItemRepository itemRepository;

    @InjectMocks
    private CarritoService carritoService;

    private Carrito carrito;
    private Producto producto;
    private CarritoItem item;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        carrito = new Carrito();
        carrito.setId(1);
        carrito.setItems(new ArrayList<>());

        producto = new Producto();
        producto.setId(10);

        item = new CarritoItem();
        item.setId(100);
        item.setProducto(producto);
        item.setCantidad(1);
    }
    @Test
    void testFindAll() {
        List<Carrito> lista = List.of(carrito);
        when(carritoRepository.findAll()).thenReturn(lista);

        assertEquals(1, carritoService.findAll().size());
    }
    @Test
    void testFindById() {
        when(carritoRepository.findById(1)).thenReturn(Optional.of(carrito));

        assertNotNull(carritoService.findById(1));
    }
    @Test
    void testSave() {
        when(carritoRepository.save(carrito)).thenReturn(carrito);

        assertNotNull(carritoService.save(carrito));
    }
    @Test
    void testPartialUpdate() {
        Carrito modificado = new Carrito();
        modificado.setId(1);
        modificado.setItems(List.of(item));

        when(carritoRepository.findById(1)).thenReturn(Optional.of(carrito));
        when(carritoRepository.save(any(Carrito.class))).thenReturn(carrito);

        assertNotNull(carritoService.partialUpdate(modificado));
    }
    @Test
    void testCrearCarrito() {
        when(carritoRepository.save(any(Carrito.class))).thenReturn(carrito);

        assertNotNull(carritoService.crearCarrito());
    }
    @Test
    void testObtenerCarrito() {
        when(carritoRepository.findById(1)).thenReturn(Optional.of(carrito));

        assertNotNull(carritoService.obtenerCarrito(1));
    }
    @Test
    void testAgregarProductoNuevo() {
        when(carritoRepository.findById(1)).thenReturn(Optional.of(carrito));
        when(productoRepository.findById(10)).thenReturn(Optional.of(producto));
        when(itemRepository.save(any(CarritoItem.class))).thenReturn(item);
        when(carritoRepository.save(any(Carrito.class))).thenReturn(carrito);

        Carrito result = carritoService.agregarProducto(1, 10);

        assertNotNull(result);
        assertEquals(1, result.getItems().size());
    }
    @Test
    void testAgregarProductoExistente() {
        carrito.getItems().add(item);

        when(carritoRepository.findById(1)).thenReturn(Optional.of(carrito));
        when(productoRepository.findById(10)).thenReturn(Optional.of(producto));
        when(itemRepository.save(any(CarritoItem.class))).thenReturn(item);
        when(carritoRepository.save(carrito)).thenReturn(carrito);

        Carrito result = carritoService.agregarProducto(1, 10);

        assertEquals(2, item.getCantidad());
        assertNotNull(result);
    }

    @Test
    void testActualizarCantidad() {
        carrito.getItems().add(item);

        when(carritoRepository.findById(1)).thenReturn(Optional.of(carrito));
        when(itemRepository.findById(100)).thenReturn(Optional.of(item));
        when(itemRepository.save(item)).thenReturn(item);
        when(carritoRepository.save(carrito)).thenReturn(carrito);

        Carrito result = carritoService.actualizarCantidad(1, 100, 5);

        assertNotNull(result);
        assertEquals(5, item.getCantidad());
    }

    @Test
    void testEliminarItem() {
        carrito.getItems().add(item);

        when(carritoRepository.findById(1)).thenReturn(Optional.of(carrito));
        when(carritoRepository.save(any(Carrito.class))).thenReturn(carrito);

        Carrito result = carritoService.eliminarItem(1, 100);

        assertNotNull(result);
        assertEquals(0, result.getItems().size());
    }
}
