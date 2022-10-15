package com.undec.corralon.modelo;

import com.undec.corralon.modelo.audit.UserDateAudit;

import javax.persistence.*;
import java.util.Objects;
@Entity
public class Empresa extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empresa", nullable = false)
    private Integer idEmpresa;
    @Basic
    @Column(name = "cuit", nullable = false, length = 255)
    private String cuit;
    @Basic
    @Column(name = "razon_social", nullable = false, length = 255)
    private String razonSocial;
    @Basic
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    @Basic
    @Column(name = "telefono", nullable = false, length = 255)
    private String telefono;
    @Basic
    @Column(name = "domicilio", nullable = false, length = 255)
    private String domicilio;
    @Basic
    @Column(name = "habilitado", nullable = false)
    private Boolean status;

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empresa empresa = (Empresa) o;
        return Objects.equals(idEmpresa, empresa.idEmpresa) &&
                Objects.equals(razonSocial, empresa.razonSocial) &&
                Objects.equals(domicilio, empresa.domicilio) &&
                Objects.equals(email, empresa.email) &&
                Objects.equals(telefono, empresa.telefono) &&
                Objects.equals(cuit, empresa.cuit) &&
                Objects.equals(status, empresa.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmpresa, razonSocial, domicilio, email, telefono, cuit, status);
    }
}
