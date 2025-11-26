package PComponent_Eva3.PComponent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PComponent_Eva3.PComponent.model.Rol;
import PComponent_Eva3.PComponent.repository.RolRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RolService {

	@Autowired
	private RolRepository rolRepository;

	public List<Rol> findAll() {
		return rolRepository.findAll();
	}

	public Rol findById(Integer id) {
		return rolRepository.findById(id).orElse(null);
	}

	public Rol save(Rol rol) {
		return rolRepository.save(rol);
	}

	public Rol partialUpdate(Rol rol) {
		Rol existing = rolRepository.findById(rol.getId()).orElse(null);
		if (existing == null) {
			return null;
		}

		if (rol.getNombre() != null) {
			existing.setNombre(rol.getNombre());
		}

		return rolRepository.save(existing);
	}

	public void deleteById(Integer id) {
		rolRepository.deleteById(id);
	}
}

