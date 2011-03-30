package ar.edu.unq.tpi.persistencia.bean;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.tpi.persistencia.logic.Formacion;

public class TestPartidoCopa {
	
	private PartidoCopa partidoCopa;
	private Equipo equipo1, equipo2;
	private Formacion formacion1, formacion2;
	
	@Before
	public void setup(){
		equipo1 = mock(Equipo.class);
		equipo2 = mock(Equipo.class);
		
		formacion1 = mock(Formacion.class);
		formacion2 = mock(Formacion.class);
		
		partidoCopa = new PartidoCopa(equipo1, equipo2);
		
		when(equipo1.armarFormacion()).thenReturn(formacion1);
		when(equipo2.armarFormacion()).thenReturn(formacion2);
		
	}	
	@Test
	public void testGanador(){
//		Assert.assertEquals(, partidoCopa.getGanador())
	}
	
	

}
