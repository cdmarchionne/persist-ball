package ar.edu.unq.tpi.persist.ball.domain.interfaces;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import ar.edu.unq.tpi.persist.ball.domain.bean.PersistentObject;
import ar.edu.unq.tpi.persist.ball.domain.bean.enums.Posicion;

@Entity
@Table(name = "Habilidad")
public abstract class Habilidad extends PersistentObject {
    private static final long serialVersionUID = 1L;

    public static final int HABILIDAD_MAXIMA = 10;

    @Enumerated(EnumType.STRING)
    private Posicion posicion;

    @Basic
    private int valor;

    public abstract int getValor(Posicion posicion);

    public void setPosicion(final Posicion posicion) {
        this.posicion = posicion;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setValor(final int valor) {
        if (valor > HABILIDAD_MAXIMA) {
            this.valor = HABILIDAD_MAXIMA;
        } else {
            this.valor = valor;
        }

    }

    protected int getValor() {
        return valor;
    }

}
