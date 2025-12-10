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
import org.springframework.security.crypto.password.PasswordEncoder;
import PComponent_Eva3.PComponent.model.Usuario;
import PComponent_Eva3.PComponent.repository.UsuarioRepository;
import PComponent_Eva3.PComponent.service.UsuarioService;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UsuarioService usuarioService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testFindAll() {
        Usuario u1 = new Usuario();
        Usuario u2 = new Usuario();

        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(u1, u2));

        List<Usuario> result = usuarioService.findAll();

        assertEquals(2, result.size());
        verify(usuarioRepository, times(1)).findAll();
    }
    @Test
    void testFindById() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setContraseña("pass123");
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        Usuario result = usuarioService.findById(1);
        assertNotNull(result);
        assertNull(result.getContraseña()); 
        verify(usuarioRepository).findById(1);
    }
    @Test
    void testLoginCorrecto() {
        Usuario login = new Usuario();
        login.setEmail("test@mail.com");
        login.setContraseña("1234");
        Usuario dbUser = new Usuario();
        dbUser.setEmail("test@mail.com");
        dbUser.setContraseña("hashedPass");
        when(usuarioRepository.findByEmail("test@mail.com")).thenReturn(dbUser);
        when(passwordEncoder.matches("1234", "hashedPass")).thenReturn(true);
        Usuario result = usuarioService.login(login);
        assertNotNull(result);
        assertEquals("test@mail.com", result.getEmail());
        verify(usuarioRepository).findByEmail("test@mail.com");
    }
    @Test
    void testLoginIncorrecto() {
        Usuario login = new Usuario();
        login.setEmail("test@mail.com");
        login.setContraseña("wrong");
        Usuario dbUser = new Usuario();
        dbUser.setEmail("test@mail.com");
        dbUser.setContraseña("hashedPass");
        when(usuarioRepository.findByEmail("test@mail.com")).thenReturn(dbUser);
        when(passwordEncoder.matches("wrong", "hashedPass")).thenReturn(false);
        Usuario result = usuarioService.login(login);
        assertNull(result);
    }
    @Test
    void testSave() {
        Usuario usuario = new Usuario();
        usuario.setContraseña("1234");
        when(passwordEncoder.encode("1234")).thenReturn("hashed1234");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        Usuario result = usuarioService.save(usuario);
        assertNotNull(result);
        assertEquals("hashed1234", usuario.getContraseña());
        verify(usuarioRepository).save(usuario);
    }
    @Test
    void testPartialUpdate() {
        Usuario existente = new Usuario();
        existente.setId(1);
        existente.setNombre("Antiguo");
        existente.setEmail("old@mail.com");
        Usuario cambios = new Usuario();
        cambios.setId(1);
        cambios.setNombre("Nuevo");
        cambios.setContraseña("abc");
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(existente));
        when(passwordEncoder.encode("abc")).thenReturn("hashedABC");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(existente);
        Usuario resultado = usuarioService.partialUpdate(cambios);
        assertNotNull(resultado);
        assertEquals("Nuevo", existente.getNombre());
        assertEquals("hashedABC", existente.getContraseña());
        verify(usuarioRepository).save(existente);
    }
    @Test
    void testDeleteById() {
        usuarioService.deleteById(5);
        verify(usuarioRepository).deleteById(5);
    }
}
