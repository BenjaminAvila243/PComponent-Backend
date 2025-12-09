package PComponent_Eva3.PComponent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import PComponent_Eva3.PComponent.model.Usuario;
import PComponent_Eva3.PComponent.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; 

    public List<Usuario> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

    public Usuario findById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setContraseña(null);
        }
        return usuario;
    }

    public Usuario login(Usuario usuario) {
        Usuario foundUsuario = usuarioRepository.findByEmail(usuario.getEmail());
        if (foundUsuario != null &&  passwordEncoder.matches(usuario.getContraseña(), foundUsuario.getContraseña())) {
            return foundUsuario;
        }
        return null;
    }

    public Usuario updateUsuario(Usuario usuario) {
        return save(usuario);
    }

    public Usuario save(Usuario usuario) {
        String passwordHasheada = passwordEncoder.encode(usuario.getContraseña());
        usuario.setContraseña(passwordHasheada);
        return usuarioRepository.save(usuario);
    }

    public Usuario partialUpdate(Usuario usuario){
        Usuario existingUsuario = usuarioRepository.findById(usuario.getId()).orElse(null);
        if (existingUsuario != null) {
            if (usuario.getNombre() != null) {
                existingUsuario.setNombre(usuario.getNombre());
            }
            if (usuario.getEmail() != null) {
                existingUsuario.setEmail(usuario.getEmail());
            }

            if(usuario.getContraseña() != null) {
                existingUsuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
            }

            if(usuario.getRol() != null) {
                existingUsuario.setRol(usuario.getRol());
            }

            return usuarioRepository.save(existingUsuario);
        }
        return null;
    }

    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }
}