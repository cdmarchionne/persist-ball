package ar.edu.unq.tpi.persist.ball.domain.utils;

import ar.edu.unq.tpi.persist.ball.domain.bean.Jugador;
import ar.edu.unq.tpi.persist.ball.domain.bean.enums.Posicion;
import ar.edu.unq.tpi.persist.ball.domain.logica.HabilidadImpl;

/**
 */
public class JugadorBuilder {

    private Jugador jugador;

    public JugadorBuilder() {
        jugador = new Jugador();
    }

    public JugadorBuilder withName(final String name) {
        jugador.setNombre(name);
        return this;
    }

    public JugadorBuilder withHabilidad(final Posicion posicion, final int value) {
        jugador.addHabilidad(new HabilidadImpl(posicion, value));
        return this;
    }

    public Jugador build() {
        return jugador;
    }

}
