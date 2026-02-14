package com.pearl.warehouse001.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bin")
public class Bin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="zone_id", referencedColumnName = "id")
    private Zone zone;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private String code;

    public Bin(){}

    public Bin(Long id, Zone zone, int capacity, String code) {
        this.id = id;
        this.zone = zone;
        this.capacity = capacity;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
