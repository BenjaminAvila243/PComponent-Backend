package PComponent_Eva3.PComponent.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import PComponent_Eva3.PComponent.model.Region;
import PComponent_Eva3.PComponent.repository.RegionRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    public Region findById(Integer id) {
        return regionRepository.findById(id).orElse(null);
    }

    public Region save(Region region) {
        return regionRepository.save(region);
    }

    public Region partialUpdate(Integer id, Region data) {

        Optional<Region> optional = regionRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }

        Region existing = optional.get();

        if (data.getNombreRegion() != null) {
            existing.setNombreRegion(data.getNombreRegion());
        }

        if (data.getNumeroRegion() != null) {
            existing.setNumeroRegion(data.getNumeroRegion());
        }

        return regionRepository.save(existing);
    }

    public void deleteById(Integer id) {
        regionRepository.deleteById(id);
    }
}
