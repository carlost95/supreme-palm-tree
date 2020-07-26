package com.undec.corralon.modelo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Pantalla {
    private Integer id;
    private Rol rolByRolId;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pantalla pantalla = (Pantalla) o;
        return Objects.equals(id, pantalla.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "rol_id", referencedColumnName = "id")
    public Rol getRolByRolId() {
        return rolByRolId;
    }

    public void setRolByRolId(Rol rolByRolId) {
        this.rolByRolId = rolByRolId;
    }
}
