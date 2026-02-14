package com.pearl.warehouse001.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "township", uniqueConstraints = @UniqueConstraint(columnNames = {"id","region_id"}))
public class Township {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50,nullable = false)
    private String township_code;

    @Column(length = 50,nullable = false)
    private String township_name;

    @ManyToOne
    @JoinColumn(name="region_id", referencedColumnName = "id")
    private Region region;

    public Township() {}

    public Township(Long id, String township_code, String township_name, Region region) {
        this.id = id;
        this.township_code = township_code;
        this.township_name = township_name;
        this.region = region;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTownship_code() {
        return township_code;
    }

    public void setTownship_code(String township_code) {
        this.township_code = township_code;
    }

    public String getTownship_name() {
        return township_name;
    }

    public void setTownship_name(String township_name) {
        this.township_name = township_name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
