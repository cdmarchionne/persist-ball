package ar.edu.unq.tpi.persist.ball.domain.bean;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import ar.edu.unq.tpi.persist.ball.domain.interfaces.FormacionStrategy;
import ar.edu.unq.tpi.persist.ball.domain.interfaces.Nombrable;
import ar.edu.unq.tpi.persist.ball.domain.logica.Formacion;

@Entity
public class Tecnico extends PersistentObject implements Nombrable {
    private static final long serialVersionUID = -7283264065297827705L;

    @OneToOne(cascade = CascadeType.ALL)
    private FormacionStrategy formacionStrategy;

    @Basic
    private String nombre;

    public Tecnico() {
    }

    public Tecnico(final FormacionStrategy formacionStrategy, final String unNombre) {
        nombre = unNombre;
        this.formacionStrategy = formacionStrategy;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    public Formacion armarFormacion(final Equipo equipo) {
        return this.getFormacionStrategy().armarFormacion(equipo);
    }

    public void setFormacionStrategy(final FormacionStrategy formacionStrategy) {
        this.formacionStrategy = formacionStrategy;
    }

    public FormacionStrategy getFormacionStrategy() {
        return formacionStrategy;
    }

}
