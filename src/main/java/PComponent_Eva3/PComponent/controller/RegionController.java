package PComponent_Eva3.PComponent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import PComponent_Eva3.PComponent.model.Region;
import PComponent_Eva3.PComponent.service.RegionService;

@RestController
@RequestMapping("/api/region")
@CrossOrigin("*")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping
    public ResponseEntity<List<Region>> findAll() {
        return ResponseEntity.ok(regionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> findById(@PathVariable Integer id) {
        Region region = regionService.findById(id);
        if (region == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(region);
    }

    @PostMapping
    public ResponseEntity<Region> save(@RequestBody Region region) {
        Region nueva = regionService.save(region);
        return ResponseEntity.status(201).body(nueva);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Region> partialUpdate(
            @PathVariable Integer id,
            @RequestBody Region data) {

        // Forzamos a que el ID exista dentro del objeto
        data.setId(id);

        Region updated = regionService.partialUpdate(id, data);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Region region = regionService.findById(id);
        if (region == null) {
            return ResponseEntity.notFound().build();
        }

        regionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
