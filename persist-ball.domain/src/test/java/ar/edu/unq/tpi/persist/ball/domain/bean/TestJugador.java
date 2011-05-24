package ar.edu.unq.tpi.persist.ball.domain.bean;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.tpi.persist.ball.domain.bean.enums.Posicion;
import ar.edu.unq.tpi.persist.ball.domain.interfaces.Habilidad;

/**
 *
 */
public class TestJugador {

	private Jugador jugador;
    private Posicion posicionArquero;
    private Posicion posicionDelantero;
    private Habilidad habilidadArquero;
    private Habilidad habilidadDelantero;
    
    @Before
    public void setup(){
    	posicionArquero = Posicion.ARQUERO;
    	posicionDelantero = Posicion.DELANTERO;
        jugador = new Jugador();
        habilidadArquero = mock(Habilidad.class);
        habilidadDelantero = mock(Habilidad.class);
        when(habilidadArquero.getValor(posicionArquero)).thenReturn(4);
        when(habilidadArquero.getValor(posicionDelantero)).thenReturn(0);
        when(habilidadDelantero.getValor(posicionArquero)).thenReturn(0);
        when(habilidadDelantero.getValor(posicionDelantero)).thenReturn(9);
        
    }
    
    @Test
    public void getValorHabilidad(){
    	Assert.assertSame("Error el No tiene la habilidad arquero", 0, jugador.getValorHabilidad(posicionArquero));
    	jugador.addHabilidad(habilidadArquero);
        Assert.assertSame("Error El jugador tiene la habilidad Arquero", 4, jugador.getValorHabilidad(posicionArquero));
        Assert.assertSame("Error El jugador no tiene la habilidad Delantero ", 0, jugador.getValorHabilidad(posicionDelantero));
        Assert.assertSame("Error El jugador no tiene la habilidad Delantero ", 0, jugador.getValorHabilidad(posicionDelantero));
        jugador.addHabilidad(habilidadDelantero);        
        Assert.assertSame("Error El jugador tiene la habilidad Delantero  ", 9, jugador.getValorHabilidad(posicionDelantero));
    }
    
    @Test
    public void addHabilidad(){
    	Assert.assertFalse("Error: El jugador no tiene esa habilidad ", jugador.getHabilidades().contains(habilidadArquero));
    	jugador.addHabilidad(habilidadArquero);
    	Assert.assertTrue("Error: el jugador tiene esa habilidad", jugador.getHabilidades().contains(habilidadArquero));
    }

}
