package com.praktikum.miniwms.material;

import org.springframework.stereotype.Service;
import java.util.List;
import com.praktikum.miniwms.exceptions.ResourceNotFoundException;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    public Material getMaterialById(Long id) {
        return materialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Material with ID " + id + " not found!"));
    }

    public Material createMaterial(Material material) {
        return materialRepository.save(material);
    }

    public Material updateMaterial(Long id, Material updatedMaterial) {
        Material existingMaterial = materialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Material with ID " + id + " not found!"));
        existingMaterial.setName(updatedMaterial.getName());
        existingMaterial.setDescription(updatedMaterial.getDescription());
        existingMaterial.setQuantity(updatedMaterial.getQuantity());
        existingMaterial.setUom(updatedMaterial.getUom());
        existingMaterial.setBusinessid(updatedMaterial.getBusinessid());
        return materialRepository.save(existingMaterial);
    }

    public void deleteMaterial(Long id) {
        materialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Material with ID " + id + " not found!"));

        materialRepository.deleteById(id);
    }
}