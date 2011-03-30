package ar.edu.unq.tpi.persistencia.bean;

import static org.mockito.Mockito.mock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPartidoSimple {
	
	private Equipo equipo1, equipo2;
	private PartidoSimple partidoSimple;

	@Before
	public void setup(){
		equipo1 = mock(Equipo.class);
		equipo2 = mock(Equipo.class);
		partidoSimple = new PartidoSimple(equipo1, equipo2);		
	}
	
	@Test
	public void testGanadorEquipo1(){
		partidoSimple.simularPartido(3, 2);
		Assert.assertEquals("", equipo1, partidoSimple.getGanador());
	}
	@Test
	public void testGanadorEquipo2(){
		partidoSimple.simularPartido(1, 4);
		Assert.assertEquals("", equipo2, partidoSimple.getGanador());
	}
	@Test
	public void testEmpate(){
		partidoSimple.simularPartido(2, 2);
		Assert.assertEquals("", null, partidoSimple.getGanador());
	}
}
