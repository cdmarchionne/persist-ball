package ar.edu.unq.tpi.persistencia.bean;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.tpi.persistencia.enums.Posicion;

public class TestJugador {
    
    
    private Jugador jugador;
    private Posicion posicion;
    
    @Before
    public void setup(){
        jugador = mock(Jugador.class);
        when(jugador.getValorHabilidad(posicion)).thenReturn(5);
    }
    
    @Test
    public void getValorHabilidad(){
        Assert.assertSame("Error ", 5, jugador.getValorHabilidad(posicion));
    }

}
