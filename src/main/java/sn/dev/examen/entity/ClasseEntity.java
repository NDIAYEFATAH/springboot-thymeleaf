package sn.dev.examen.entity;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "classes")
public class ClasseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_name")
    private String className;

    private String description;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private SectorEntity sector;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SectorEntity getSector() {
        return sector;
    }

    public void setSector(SectorEntity sector) {
        this.sector = sector;
    }
}
