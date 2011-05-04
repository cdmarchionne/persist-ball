package ar.edu.unq.tpi.persistencia.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Index;

import ar.edu.unq.tpi.persistencia.interfaces.Nombrable;
import ar.edu.unq.tpi.persistencia.logic.Formacion;

@Entity
public class Equipo extends PersistentObject implements Nombrable {
    private static final long serialVersionUID = 1L;

    @OneToOne(cascade = CascadeType.ALL)
    private Tecnico tecnico;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipo_id")
    private List<Jugador> jugadores = new ArrayList<Jugador>();

    @Basic
    @Column(unique = true)
    @Index(name = "nombre")
    private String nombre;

    public Equipo() {
    }

    public Equipo(final Tecnico tecnico, final String nombre) {
        this();
        this.nombre = nombre;
        this.tecnico = tecnico;
    }

    @Override
    public boolean equals(final Object obj) {
        return nombre == ((Equipo) obj).getNombre();
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    public Formacion armarFormacion() {
        return tecnico.armarFormacion(this);
    }

    public void setJugadores(final List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

}
