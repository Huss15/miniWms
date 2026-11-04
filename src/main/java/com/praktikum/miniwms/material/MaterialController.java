package com.praktikum.miniwms.material;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materials")
public class MaterialController {
    private MaterialService materialService;
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public List<Material> getAllMaterials() {
        return materialService.getAllMaterials();
    }
    @GetMapping("/{id}")
    public Material getMaterialById(@PathVariable Long id){
        return materialService.getMaterialById(id);
    }
    @PostMapping
    public Material createMaterial(@RequestBody Material material){
        return materialService.createMaterial(material);
    }
    @PutMapping("/{id}")
    public Material updateMaterial(@PathVariable Long id,@RequestBody Material updatedMaterial){
        return materialService.updateMaterial(id, updatedMaterial);
    }
    @DeleteMapping("/{id}")
    void deleteMaterial(@PathVariable Long id){
        materialService.deleteMaterial(id);
    }

}
