package com.pearl.warehouse001.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="region")
public class Region {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length=50, nullable=false)
    private String region_code;

    @Column(name = "region_name", length=50, nullable=false)
    private String region_name;

    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private List<Township> townships = new ArrayList<>();

    public Region() {}

    public Region(Long id, String region_code, String region_name, List<Township> townships) {
        this.id = id;
        this.region_code = region_code;
        this.region_name = region_name;
        this.townships = townships;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public List<Township> getTownships() {
        return townships;
    }

    public void setTownships(List<Township> townships) {
        this.townships = townships;
    }
}
