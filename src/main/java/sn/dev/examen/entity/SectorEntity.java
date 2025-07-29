package sn.dev.examen.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sectors")
public class SectorEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClasseEntity> classes = new ArrayList<>();

    // --- Constructeurs ---
    public SectorEntity() {}

    public SectorEntity(String name) {
        this.name = name;
    }

    // --- Getters / Setters ---
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ClasseEntity> getClasses() {
        return classes;
    }

    public void setClasses(List<ClasseEntity> classes) {
        this.classes = classes;
    }

    // --- Helpers pour la relation bidirectionnelle ---
    public void addClasse(ClasseEntity classe) {
        classes.add(classe);
        classe.setSector(this);
    }

    public void removeClasse(ClasseEntity classe) {
        classes.remove(classe);
        classe.setSector(null);
    }
}
