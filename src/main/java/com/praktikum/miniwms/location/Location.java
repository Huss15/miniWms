package com.praktikum.miniwms.location;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String buisnessId;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "description", length = 100, nullable = true)
    private String description;

    @ElementCollection
    @CollectionTable(
            name = "location_equipments",
            joinColumns = @JoinColumn(name = "location_id")
    )
    @OrderColumn(name = "equipment_order", nullable = true)
    @Column(name = "equipment", length = 250, nullable = true)
    private List<String> equipments;

    public Long getId() {
        return id;
    }

    public String getBuisnessId() {
        return buisnessId;
    }

    public void setBuisnessId(String buisnessId) {
        this.buisnessId = buisnessId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<String> equipments) {
        this.equipments = equipments;
    }

    public Location(String name, String description, List<String> equipments) {
        this.name = name;
        this.description = description;
        this.equipments = equipments;
    }
}

