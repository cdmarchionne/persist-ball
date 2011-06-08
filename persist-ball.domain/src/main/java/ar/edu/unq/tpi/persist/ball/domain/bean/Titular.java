package ar.edu.unq.tpi.persist.ball.domain.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

import ar.edu.unq.tpi.persist.ball.domain.bean.enums.Posicion;

@Entity
public class Titular extends PersistentObject {
    private static final long serialVersionUID = 1L;

    @OneToOne(cascade = CascadeType.ALL)
    private Jugador jugador;

    @Enumerated(EnumType.STRING)
    private Posicion posicion;

    public Titular(final Jugador jugador, final Posicion posicion2) {
        this.jugador = jugador;
        posicion = posicion2;
    }

    public void setPosicion(final Posicion posicion) {
        this.posicion = posicion;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setJugador(final Jugador jugador) {
        this.jugador = jugador;
    }

    public Jugador getJugador() {
        return jugador;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (jugador == null ? 0 : jugador.hashCode());
        result = prime * result + (posicion == null ? 0 : posicion.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        Titular other = (Titular) obj;
        if (jugador == null) {
            if (other.jugador != null)
                return false;
        } else if (!jugador.equals(other.jugador))
            return false;
        if (posicion != other.posicion)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Jugador: " + jugador.getNombre() + " Pos: " + posicion;
    }
}
