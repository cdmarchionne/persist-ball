package ar.edu.unq.tpi.persist.ball.domain.utils;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.tpi.persist.ball.domain.bean.Titular;
import ar.edu.unq.tpi.persist.ball.domain.bean.enums.Posicion;
import ar.edu.unq.tpi.persist.ball.domain.logica.Formacion;

public class ListUtils {

    public static boolean compareLists(final List<?> l1, final List<?> l2) {
        return l1.containsAll(l2) && l1.size() == l2.size();
    }

    public static Object random(final List<?> list) {
        return random(list.toArray());
    }

    public static Object random(final Object... objects) {
        return objects[(int) (Math.random() * objects.length)];
    }

    public static List<Posicion> posicionRandom() {
        List<Posicion> posiciones = new ArrayList<Posicion>();

        List<Posicion> posicionesBasicas = new ArrayList<Posicion>();
        // Agrego las posiciones minimas que debo cubrir para formar un equipo
        posicionesBasicas.add(Posicion.ARQUERO);
        posicionesBasicas.add(Posicion.LATERAL);
        posicionesBasicas.add(Posicion.CENTRAL);
        posicionesBasicas.add(Posicion.VOLANTE_DEFENSIVO);
        posicionesBasicas.add(Posicion.VOLANTE_LATERAL);
        posicionesBasicas.add(Posicion.ENGANCHE);
        posicionesBasicas.add(Posicion.MEDIA_PUNTA);
        posicionesBasicas.add(Posicion.DELANTERO);

        posiciones.addAll(posicionesBasicas);

        // Completo el equipo aleatoreamente
        Posicion posicionesAleatoreas;
        while (posiciones.size() < 11) {
            posicionesAleatoreas = (Posicion) random(posicionesBasicas);
            if (posicionesAleatoreas != Posicion.ARQUERO) {
                posiciones.add(posicionesAleatoreas);
            }
        }

        return posiciones;
    }

    public static boolean titularesNoEnSuplentes(final Formacion aFormation) {

        for (Titular t : aFormation.getTitulares()) {
            if (aFormation.getSuplentes().contains(t)) {
                return false;
            }
        }

        return true;
    }
}
