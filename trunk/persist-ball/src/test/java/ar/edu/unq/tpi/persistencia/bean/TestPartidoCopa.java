package ar.edu.unq.tpi.persistencia.bean;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TestPartidoCopa {
	
	private PartidoSimple partidoIda, partidoVuelta;
	private PartidoCopa partidoCopa;
	
	@Before
	public void setup(){
		partidoVuelta = mock(PartidoSimple.class);
		partidoIda = mock(PartidoSimple.class);
//		partidoCopa = new PartidoCopa(partidoIda, partidoVuelta, 5, 4);
		when(partidoIda.getGolesEquipo1()).thenReturn(5);
		when(partidoIda.getGolesEquipo2()).thenReturn(3);
		when(partidoVuelta.getGolesEquipo1()).thenReturn(1);
		when(partidoVuelta.getGolesEquipo2()).thenReturn(3);
	}
	
	@Test
	public void testGanador(){
//		Assert.assertEquals(, partidoCopa.getGanador())
	}
	
	

}
