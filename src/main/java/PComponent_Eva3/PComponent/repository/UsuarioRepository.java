package PComponent_Eva3.PComponent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import PComponent_Eva3.PComponent.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
        Usuario findByEmail(String email);
}
