package ar.edu.unq.tpi.persist.ball.domain.logica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import ar.edu.unq.tpi.persist.ball.domain.bean.Equipo;
import ar.edu.unq.tpi.persist.ball.domain.bean.Jugador;
import ar.edu.unq.tpi.persist.ball.domain.bean.PersistentObject;
import ar.edu.unq.tpi.persist.ball.domain.bean.Titular;

@Entity
public class Formacion extends PersistentObject {
    private static final long serialVersionUID = 1L;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "formacion_id")
    private List<Titular> titulares = new ArrayList<Titular>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "formacion_suplente")
    @JoinColumn(columnDefinition = "suplente_id")
    private List<Jugador> suplentes = new ArrayList<Jugador>();

    @OneToOne(cascade = CascadeType.ALL)
    private Equipo equipo;

    public List<Titular> getTitulares() {
        return titulares;
    }

    public void setTitulares(final List<Titular> titulares) {
        this.titulares = titulares;
    }

    public List<Jugador> getSuplentes() {
        return suplentes;
    }

    public void setSuplentes(final List<Jugador> suplentes) {
        this.suplentes = suplentes;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(final Equipo equipo) {
        this.equipo = equipo;
    }

}
