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
	private  RolRepository rolRepository;

	public List<Rol> findAll(){
		return rolRepository.findAll();
	}

	@SuppressWarnings("null")
	public Rol findById(Integer id){
		return rolRepository.findById(id).orElse(null);
	}

	@SuppressWarnings("null")
	public Rol save(Rol rol) {
		return rolRepository.save(rol);
	}
}

