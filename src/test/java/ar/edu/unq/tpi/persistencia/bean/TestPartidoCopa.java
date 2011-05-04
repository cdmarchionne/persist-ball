package ar.edu.unq.tpi.persistencia.bean;

import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.tpi.persistencia.logic.Formacion;

public class TestPartidoCopa {

    private PartidoCopa partidoCopa;

    private Equipo equipo1, equipo2;

    private Formacion formacion1, formacion2;

    private PartidoSimple partidoSimple1, partidoSimple2;

    @Before
    public void setup() {
        equipo1 = mock(Equipo.class);
        equipo2 = mock(Equipo.class);
        when(equipo1.armarFormacion()).thenReturn(formacion1);
        when(equipo2.armarFormacion()).thenReturn(formacion2);

        partidoSimple1 = mock(PartidoSimple.class);
        partidoSimple2 = mock(PartidoSimple.class);

        formacion1 = mock(Formacion.class);
        formacion2 = mock(Formacion.class);

        partidoCopa = new PartidoCopa(equipo1, equipo2);
    }

    @Test
    public void testGanadorConPenales() {
        when(partidoSimple1.getGolesEquipo(equipo1)).thenReturn(5);
        when(partidoSimple1.getGolesEquipo(equipo2)).thenReturn(2);

        when(partidoSimple2.getGolesEquipo(equipo1)).thenReturn(2);
        when(partidoSimple2.getGolesEquipo(equipo2)).thenReturn(5);

        partidoCopa.simularPartido(partidoSimple1, partidoSimple2, 5, 4);

        Assert.assertEquals("No calcula bien el ganador por penales", equipo1, partidoCopa.getGanador());
    }

    @Test
    public void testGanadorSinPenales() {
        when(partidoSimple1.getGolesEquipo(equipo1)).thenReturn(5);
        when(partidoSimple1.getGolesEquipo(equipo2)).thenReturn(2);

        when(partidoSimple2.getGolesEquipo(equipo1)).thenReturn(2);
        when(partidoSimple2.getGolesEquipo(equipo2)).thenReturn(2);

        partidoCopa.simularPartido(partidoSimple1, partidoSimple2);

        Assert.assertEquals("No calcula bien el ganador sin penales", equipo1, partidoCopa.getGanador());
    }

}
