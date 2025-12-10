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
import PComponent_Eva3.PComponent.model.Rol;
import PComponent_Eva3.PComponent.repository.RolRepository;
import PComponent_Eva3.PComponent.service.RolService;

public class RolServiceTest {
    @Mock
    private RolRepository rolRepository;
    @InjectMocks
    private RolService rolService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testFindAll() {
        Rol rol1 = new Rol();
        rol1.setId(1);
        rol1.setNombre("Admin");
        Rol rol2 = new Rol();
        rol2.setId(2);
        rol2.setNombre("User");
        when(rolRepository.findAll()).thenReturn(Arrays.asList(rol1, rol2));
        List<Rol> result = rolService.findAll();
        assertEquals(2, result.size());
        assertEquals("Admin", result.get(0).getNombre());
    }
    @Test
    void testFindById() {
        Rol rol = new Rol();
        rol.setId(1);
        rol.setNombre("Admin");
        when(rolRepository.findById(1)).thenReturn(Optional.of(rol));
        Rol result = rolService.findById(1);
        assertNotNull(result);
        assertEquals("Admin", result.getNombre());
    }
    @Test
    void testSave() {
        Rol rol = new Rol();
        rol.setId(1);
        rol.setNombre("Admin");
        when(rolRepository.save(rol)).thenReturn(rol);
        Rol result = rolService.save(rol);
        assertNotNull(result);
        assertEquals("Admin", result.getNombre());
    }
    @Test
    void testPartialUpdate() {
        Rol existingRol = new Rol();
        existingRol.setId(1);
        existingRol.setNombre("Admin");
        Rol rolUpdate = new Rol();
        rolUpdate.setId(1);
        rolUpdate.setNombre("SuperAdmin");
        when(rolRepository.findById(1)).thenReturn(Optional.of(existingRol));
        when(rolRepository.save(existingRol)).thenReturn(existingRol);
        Rol result = rolService.partialUpdate(rolUpdate);
        assertNotNull(result);
        assertEquals("SuperAdmin", result.getNombre());
    }
    @Test
    void testDeleteById() {
        doNothing().when(rolRepository).deleteById(1);
        rolService.deleteById(1);
        verify(rolRepository, times(1)).deleteById(1);
    }
}
