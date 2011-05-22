package ar.edu.unq.tpi.persist.ball.domain.bean;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.tpi.persist.ball.domain.bean.enums.Posicion;
import ar.edu.unq.tpi.persist.ball.domain.logica.HabilidadImpl;

public class TestHabilidad {
	
	private HabilidadImpl habilidad;
	
	@Before
	public void setUp(){
		this.habilidad = new HabilidadImpl(Posicion.DELANTERO, 6);
	}
	
	@Test
	public void testDelantero(){
		Assert.assertSame("Error el la habilidad no devolvio el valor correcto ", 6, habilidad.getValor(Posicion.DELANTERO));
	}
	@Test
	public void testCentral(){
		Assert.assertSame("Error el la habilidad no devolvio el valor correcto ", 0, habilidad.getValor(Posicion.CENTRAL));
	}
	@Test
	public void testLateral(){
		Assert.assertSame("Error el la habilidad no devolvio el valor correcto", 0, habilidad.getValor(Posicion.LATERAL));
	}
	
	@Test
	public void testEnganche(){
		Assert.assertSame("Error el la habilidad no devolvio el valor correcto ", 0, habilidad.getValor(Posicion.ENGANCHE));
	}
	@Test
	public void testVolanteDefensivo(){
		Assert.assertSame("Error el la habilidad no devolvio el valor correcto ", 0, habilidad.getValor(Posicion.VOLANTE_DEFENSIVO));
	}
	@Test
	public void testMediaPunta(){
		Assert.assertSame("Error el la habilidad no devolvio el valor correcto ", 0, habilidad.getValor(Posicion.MEDIA_PUNTA));
	}
	

}
