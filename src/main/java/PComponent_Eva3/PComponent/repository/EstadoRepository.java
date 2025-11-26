package PComponent_Eva3.PComponent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import PComponent_Eva3.PComponent.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado,Long>{
    
}
