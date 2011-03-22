package ar.edu.unq.tpi.persistencia.logic;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unq.tpi.persistencia.bean.Equipo;
import ar.edu.unq.tpi.persistencia.bean.Jugador;
import ar.edu.unq.tpi.persistencia.bean.Tecnico;
import ar.edu.unq.tpi.persistencia.bean.Titular;
import ar.edu.unq.tpi.persistencia.enums.Posicion;
import ar.edu.unq.tpi.persistencia.interfaces.Habilidad;

public class TestFormacionStrategyImpl {
	
	private Tecnico tecnico;
	private Equipo equipo;
	private FormacionStrategyImpl formacionStrategy;
	private Jugador jugador1, jugador2, jugador3, jugador4, jugador5, jugador6;
	private HabilidadImpl habA1, habC1, habD1;
	private HabilidadImpl habA2, habC2, habD2;
	private HabilidadImpl habA3, habC3, habD3;
	private HabilidadImpl habA4, habC4, habD4;
	private HabilidadImpl habA5, habC5, habD5;
	private HabilidadImpl habA6, habC6, habD6;
	
	
	@Before
	public void setUp(){
		List<Posicion> strategyPosiciones = new ArrayList<Posicion>();
		strategyPosiciones.add(Posicion.ARQUERO);
		strategyPosiciones.add(Posicion.CENTRAL);
		strategyPosiciones.add(Posicion.CENTRAL);
		strategyPosiciones.add(Posicion.DELANTERO);
		strategyPosiciones.add(Posicion.DELANTERO);
		
		formacionStrategy = new FormacionStrategyImpl(strategyPosiciones);
		
		tecnico = new Tecnico(formacionStrategy);
		
		List<Habilidad> habs1 = new ArrayList<Habilidad>();
		habA1 = new HabilidadImpl(Posicion.ARQUERO, 10); //
		habC1 = new HabilidadImpl(Posicion.CENTRAL, 1);
		habD1 = new HabilidadImpl(Posicion.DELANTERO, 2);
		habs1.add(habA1);
		habs1.add(habC1);
		habs1.add(habD1);
		jugador1 = new Jugador();
		jugador1.setHabilidades(habs1);
		
		List<Habilidad> habs2 = new ArrayList<Habilidad>();
		habA2 = new HabilidadImpl(Posicion.ARQUERO, 3);
		habC2 = new HabilidadImpl(Posicion.CENTRAL, 8); //
		habD2 = new HabilidadImpl(Posicion.DELANTERO, 3);
		habs2.add(habA2);
		habs2.add(habC2);
		habs2.add(habD2);
		jugador2 = new Jugador();
		jugador2.setHabilidades(habs2);
		
		List<Habilidad> habs3 = new ArrayList<Habilidad>();
		habA3 = new HabilidadImpl(Posicion.ARQUERO, 7);
		habC3 = new HabilidadImpl(Posicion.CENTRAL, 7); //
		habD3 = new HabilidadImpl(Posicion.DELANTERO, 8); 
		habs3.add(habA3);
		habs3.add(habC3);
		habs3.add(habD3);
		jugador3 = new Jugador();
		jugador3.setHabilidades(habs3);
		
		List<Habilidad> habs4 = new ArrayList<Habilidad>();
		habA4 = new HabilidadImpl(Posicion.ARQUERO, 1);
		habC4 = new HabilidadImpl(Posicion.CENTRAL, 3);
		habD4 = new HabilidadImpl(Posicion.DELANTERO, 5);
		habs4.add(habA4);
		habs4.add(habC4);
		habs4.add(habD4);
		jugador4 = new Jugador();
		jugador4.setHabilidades(habs4);
		
		List<Habilidad> habs5 = new ArrayList<Habilidad>();
		habA5 = new HabilidadImpl(Posicion.ARQUERO, 6);
		habC5 = new HabilidadImpl(Posicion.CENTRAL, 6);
		habD5 = new HabilidadImpl(Posicion.DELANTERO, 9); //
		habs5.add(habA5);
		habs5.add(habC5);
		habs5.add(habD5);
		jugador5 = new Jugador();
		jugador5.setHabilidades(habs5);
		
		List<Habilidad> habs6 = new ArrayList<Habilidad>();
		habA6 = new HabilidadImpl(Posicion.ARQUERO, 4);
		habC6 = new HabilidadImpl(Posicion.CENTRAL, 3);
		habD6 = new HabilidadImpl(Posicion.DELANTERO, 10); //
		habs6.add(habA6);
		habs6.add(habC6);
		habs6.add(habD6);
		jugador6 = new Jugador();
		jugador6.setHabilidades(habs6);
		
		List<Jugador> jugadoresDelEquipo = new ArrayList<Jugador>();
		jugadoresDelEquipo.add(jugador1);
		jugadoresDelEquipo.add(jugador2);
		jugadoresDelEquipo.add(jugador3);
		jugadoresDelEquipo.add(jugador4);
		jugadoresDelEquipo.add(jugador5);
		jugadoresDelEquipo.add(jugador6);

		equipo = new Equipo();
		equipo.setJugadores(jugadoresDelEquipo);
	}
	
	@Test
	public void testArmarFormacion(){
		Formacion mejorFormacion = tecnico.armarFormacion(equipo);
		
		List<Titular> titularesEsperados = new ArrayList<Titular>();
		titularesEsperados.add(new Titular(jugador1, Posicion.ARQUERO));
		titularesEsperados.add(new Titular(jugador2, Posicion.CENTRAL));
		titularesEsperados.add(new Titular(jugador3, Posicion.CENTRAL));
		titularesEsperados.add(new Titular(jugador5, Posicion.DELANTERO));
		titularesEsperados.add(new Titular(jugador6, Posicion.DELANTERO));
		
		List<Jugador> suplentesEsperados = new ArrayList<Jugador>();
		suplentesEsperados.add(jugador4);
		
		Formacion formacionEsperada = new Formacion();
		formacionEsperada.setTitulares(titularesEsperados);
		formacionEsperada.setSuplentes(suplentesEsperados);
		formacionEsperada.setEquipo(equipo);
		
		Assert.assertEquals(mejorFormacion, formacionEsperada);
		
	}
}
