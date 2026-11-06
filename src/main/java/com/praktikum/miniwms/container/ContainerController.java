package com.praktikum.miniwms.container;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/containers")
public class ContainerController {
    private final ContainerService containerService;

    public ContainerController(ContainerService containerService) {
        this.containerService = containerService;
    }
    @GetMapping
    public List<Container> getAllContainers() {
        return containerService.getAllContainers();
    }
    @GetMapping("/{id}")
    public Container getContainerById(@PathVariable Long id) {
        return containerService.getContainerById(id);
    }
    @PostMapping
    public Container createContainer(@RequestBody ContainerRequest request) {
        return containerService.createContainer(request);
    }
    @PutMapping("/{id}")
    public Container updateContainer(@PathVariable Long id, @RequestBody ContainerRequest request) {
        return containerService.updateContainer(id, request);
    }
    @DeleteMapping("/{id}")
    public void deleteContainer(@PathVariable Long id) {
        containerService.deleteContainer(id);
    }
}