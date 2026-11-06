package com.praktikum.miniwms.container;

import com.praktikum.miniwms.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import com.praktikum.miniwms.material.MaterialRepository;
import java.util.List;
import com.praktikum.miniwms.location.LocationRepository;

@Service
public class ContainerService {
    private final ContainerRepository containerRepository;
    private final MaterialRepository materialRepository;
    private final LocationRepository locationRepository;

    public ContainerService(ContainerRepository containerRepository, MaterialRepository materialRepository, LocationRepository locationRepository) {
        this.containerRepository = containerRepository;
        this.materialRepository = materialRepository;
        this.locationRepository = locationRepository;
    }
    public List<Container> getAllContainers() {
        return containerRepository.findAll();
    }
    public Container getContainerById(Long id) {
        return containerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Container with ID " + id + " not found!"));
    }
    public Container createContainer(ContainerRequest request) {
        Long materialId = request.getMaterialId();

        var material = materialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Material with ID " + materialId + " not found!"));
        Long locationId = request.getLocationId();

        var location = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Location with ID " + locationId + " not found!"));

        Container container = new Container();
        container.setMaterial(material);
        container.setLocation(location);

        return containerRepository.save(container);
    }
    public Container updateContainer(Long id, ContainerRequest request) {
        Container existingContainer = containerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Container with ID " + id + " not found!"));

        Long materialId = request.getMaterialId();

        var material = materialRepository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Material with ID " + materialId + " not found!"));
        Long locationId = request.getLocationId();

        var location = locationRepository.findById(locationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Location with ID " + locationId + " not found!"));

        existingContainer.setMaterial(material);
        existingContainer.setLocation(location);

        return containerRepository.save(existingContainer);
    }
    public void deleteContainer(Long id) {
        containerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Container with ID " + id + " not found!"));

        containerRepository.deleteById(id);
    }
}