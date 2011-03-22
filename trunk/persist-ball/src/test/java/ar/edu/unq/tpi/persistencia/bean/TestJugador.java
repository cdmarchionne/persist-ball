package ar.edu.unq.tpi.persistencia.bean;

import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.tpi.persistencia.enums.Posicion;
import ar.edu.unq.tpi.persistencia.interfaces.Habilidad;

/**
 * @author Ronny
 *
 */
public class TestJugador {
    
    
    private Jugador jugador;
    private Posicion posicion;
    private Habilidad habilidad;
    
    @Before
    public void setup(){
        jugador = new Jugador();
        habilidad = mock(Habilidad.class);
        when(habilidad.getValor(posicion)).thenReturn(4);
    }
    
    @Test
    public void getValorHabilidad(){
    	Assert.assertSame("Error ", 0, jugador.getValorHabilidad(posicion));
    	jugador.addHabilidad(habilidad);
        Assert.assertSame("Error ", 4, jugador.getValorHabilidad(posicion));
    }
    
    @Test
    public void addHabilidad(){
    	Assert.assertFalse("", jugador.getHabilidades().contains(habilidad));
    	jugador.addHabilidad(habilidad);
    	Assert.assertTrue("", jugador.getHabilidades().contains(habilidad));
    }

}
