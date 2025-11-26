package PComponent_Eva3.PComponent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PComponent_Eva3.PComponent.model.Usuario;
import PComponent_Eva3.PComponent.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario partialUpdate(Usuario usuario) {
        Usuario existing = usuarioRepository.findById(usuario.getId()).orElse(null);

        if (existing == null) {
            return null;
        }

        if (usuario.getNombre() != null) {
            existing.setNombre(usuario.getNombre());
        }
        if (usuario.getApellido() != null) {
            existing.setApellido(usuario.getApellido());
        }
        if (usuario.getRut() != null) {
            existing.setRut(usuario.getRut());
        }
        if (usuario.getTelefono() != null) {
            existing.setTelefono(usuario.getTelefono());
        }
        if (usuario.getEmail() != null) {
            existing.setEmail(usuario.getEmail());
        }
        if (usuario.getContraseña() != null) {
            existing.setContraseña(usuario.getContraseña());
        }
        if (usuario.getComuna() != null) {
            existing.setComuna(usuario.getComuna());
        }
        if (usuario.getRoles() != null) {
            existing.setRoles(usuario.getRoles());
        }

        return usuarioRepository.save(existing);
    }

    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }
}
