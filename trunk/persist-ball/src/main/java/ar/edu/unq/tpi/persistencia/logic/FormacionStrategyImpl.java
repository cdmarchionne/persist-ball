package ar.edu.unq.tpi.persistencia.logic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import ar.edu.unq.tpi.persistencia.bean.Equipo;
import ar.edu.unq.tpi.persistencia.bean.Jugador;
import ar.edu.unq.tpi.persistencia.bean.Titular;
import ar.edu.unq.tpi.persistencia.enums.Posicion;
import ar.edu.unq.tpi.persistencia.interfaces.FormacionStrategy;

@Entity
public class FormacionStrategyImpl extends FormacionStrategy {
    private static final long serialVersionUID = 1L;

    public FormacionStrategyImpl() {
    }

    public FormacionStrategyImpl(final List<Posicion> posiciones) {
        this.setPosiciones(posiciones);
    }

    public Jugador mejorJugador(final List<Jugador> jugadores, final Posicion posicion) {
        Jugador jugadorMax = null;

        if (!jugadores.isEmpty()) {

            jugadorMax = jugadores.get(0);

            for (Jugador jugador : jugadores) {
                if (jugador.getValorHabilidad(posicion) > jugadorMax.getValorHabilidad(posicion)) {
                    jugadorMax = jugador;
                }
            }
            jugadores.remove(jugadorMax);
        }
        return jugadorMax;
    }

    protected List<Titular> armarTitulares(final List<Jugador> jugadores) {
        List<Titular> titulares = new ArrayList<Titular>();
        for (Posicion posicion : this.getPosiciones()) {
            titulares.add(new Titular(this.mejorJugador(jugadores, posicion), posicion));
        }
        return titulares;
    }

    public void reemplazarJugadores(final List<Jugador> jugadores, final List<Jugador> reemplazo) {
        jugadores.removeAll(jugadores);
        jugadores.addAll(reemplazo);
    }

    @Override
    public Formacion armarFormacion(final Equipo equipo) {
        ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
        jugadores.addAll(equipo.getJugadores());
        Formacion formacion = new Formacion();
        formacion.setEquipo(equipo);
        formacion.setTitulares(this.armarTitulares(jugadores));
        this.reemplazarJugadores(formacion.getSuplentes(), jugadores);

        return formacion;
    }

}
