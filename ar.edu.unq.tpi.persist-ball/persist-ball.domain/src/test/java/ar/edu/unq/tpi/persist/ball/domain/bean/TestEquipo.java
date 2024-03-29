package ar.edu.unq.tpi.persist.ball.domain.bean;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class TestEquipo {
	
	private Equipo equipo;
	private Tecnico tecnico;
	
	@Before
	public void setUp(){
		tecnico = mock(Tecnico.class);
		equipo = new Equipo(tecnico, "Equipo1");
	}
	
	@Test
	public void armarFormacion(){
		equipo.armarFormacion();
		verify(tecnico).armarFormacion(equipo);
	}
	

}
