package com.praktikum.miniwms.material;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
@Table(name="materials")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Business ID cannot be empty!")
    @Column(unique = true)
    private String businessid;

    @NotBlank(message = "Name cannot be empty!")
    @Column(name = "name",length = 50)
    @Size(max = 50, message = "Name cannot exceed 50 characters!")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters!")
    @Column(name = "description", length = 500)
    private String description;

    @NotNull(message = "Quantity cannot be null!")
    @PositiveOrZero(message = "Quantity cannot be negative!")
    @Column(name = "quantity")
    private Integer quantity;

    @NotBlank(message = "Unit of measurement cannot be empty!")
    @Column(name = "uom", length = 20)
    private String uom;

    //getters & setters
    //for name:
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //for description:
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    //for quantity:
    public Integer getQuantity(){
        return quantity;
    }
    public void setQuantity(Integer quantity){
        this.quantity = quantity;
    }
    //for uom:
    public String getUom(){
        return uom;
    }
    public void setUom(String uom){
        this.uom = uom;
    }
    //for id
    public Long getId() {
        return id;
    }
    //for businessid
    public String getBusinessid() {
        return businessid;
    }

    public void setBusinessid(String businessid) {
        this.businessid = businessid;
    }
    public Material(){
    }
    public Material(String name, String description, Integer quantity, String uom,String businessid){
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.uom = uom;
        this.businessid = businessid;
    }

}
