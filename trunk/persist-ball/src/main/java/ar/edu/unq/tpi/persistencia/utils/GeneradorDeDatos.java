package ar.edu.unq.tpi.persistencia.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ar.edu.unq.tpi.persistencia.bean.Equipo;
import ar.edu.unq.tpi.persistencia.bean.Jugador;
import ar.edu.unq.tpi.persistencia.bean.PartidoCopa;
import ar.edu.unq.tpi.persistencia.bean.PartidoSimple;
import ar.edu.unq.tpi.persistencia.bean.Tecnico;
import ar.edu.unq.tpi.persistencia.enums.Posicion;
import ar.edu.unq.tpi.persistencia.home.Home;
import ar.edu.unq.tpi.persistencia.logic.Formacion;
import ar.edu.unq.tpi.persistencia.logic.FormacionStrategyImpl;

public class GeneradorDeDatos {
	
	public static final String GENERAR_EQUPOS_CON_JUGADORES = "generarEquiposConJugadores";
	public static final String CARGAR_EQUIPO_Y_GUARDAR_FORMACION = "cargarEquipoYGuardarFormacion";
	public static final String CARGAR_EQUIPOS_Y_JUGAR_PARTIDO_SIMPLE = "cargarEquiposYJugarPartidoSimple";
	public static final String CARGAR_PARTIDOS_SIMPLES_Y_CREAR_PARTIDO_COPA = "cargarPartidosSimplesYCrearPartidoCopa";
	public static final String CARGAR_JUGADORES_CONCURRENTEMENTE = "cargarJugadoresConcurrentemente";

    public  void generarEquiposConJugadores() {
        final Home<Equipo> home = new Home<Equipo>(Equipo.class);
        
        Equipo boca = createEquipo("Boca", "Bilardo");
        Equipo river = createEquipo("River", "Franchela");
		
        List<Jugador> jugadorsesBoca = new ArrayList<Jugador>();
        jugadorsesBoca.add(new JugadorBuilder().withHabilidad(Posicion.ARQUERO, 8).withName("Vartez").build());
        jugadorsesBoca.add(new JugadorBuilder().withHabilidad(Posicion.VOLANTE_DEFENSIVO, 8).withName("Puyol").build());
        jugadorsesBoca.add(new JugadorBuilder().withHabilidad(Posicion.VOLANTE_LATERAL, 8).withName("Roberto Carloz")
                .build());
        jugadorsesBoca.add(new JugadorBuilder().withHabilidad(Posicion.ENGANCHE, 9).withName("Zidane").build());
        jugadorsesBoca.add(new JugadorBuilder().withHabilidad(Posicion.VOLANTE_DEFENSIVO, 7).withName("Cafu").build());
        jugadorsesBoca.add(new JugadorBuilder().withHabilidad(Posicion.DELANTERO, 9).withName("Tevez").build());
        jugadorsesBoca.add(new JugadorBuilder().withHabilidad(Posicion.DELANTERO, 10).withName("Messi").build());
        
        
        List<Jugador> jugadorsesRiver = new ArrayList<Jugador>();
        jugadorsesRiver.add(new JugadorBuilder().withHabilidad(Posicion.CENTRAL, 8).withName("Masche").build());
        jugadorsesRiver.add(new JugadorBuilder().withHabilidad(Posicion.LATERAL, 8).withName("Pepe").build());
        jugadorsesRiver.add(new JugadorBuilder().withHabilidad(Posicion.MEDIA_PUNTA, 8).withName("Kaviedes").build());
        jugadorsesRiver.add(new JugadorBuilder().withHabilidad(Posicion.VOLANTE_LATERAL, 8).withName("Juan").build());

        boca.setJugadores(jugadorsesBoca);
        river.setJugadores(jugadorsesRiver);
        
        home.save(boca);
        home.save(river);
    }

    
    public  Equipo createEquipo(String nombreE, String nombreDT){
    	Tecnico tecnico = new Tecnico(new FormacionStrategyImpl(Arrays.asList(Posicion.values())), nombreDT);
        Equipo equipo = new Equipo(tecnico, nombreE);
        return equipo;
    }

    public  void cargarEquipoYGuardarFormacion() {
    	Home<Formacion> homeFormacion = new Home<Formacion>(Formacion.class);
        final Home<Equipo> homeEquipo = new Home<Equipo>(Equipo.class);

        Equipo boca = homeEquipo.getByName("Boca");
        Equipo river = homeEquipo.getByName("River");
        Formacion formacionBoca = boca.armarFormacion();
        Formacion formacionRiver = river.armarFormacion();
        
		homeFormacion.save(formacionBoca);
		homeFormacion.save(formacionRiver);
    }
    
    public static void cargarEquiposYJugarPartidoSimple(String nombreEquipo1, String nombreEquipo2, Integer golesEquipo1, Integer golesEquipo2, Date date) {
        final Home<Equipo> home = new Home<Equipo>(Equipo.class);
        Equipo equipo1 = home.getByName(nombreEquipo1);
        Equipo equipo2 = home.getByName(nombreEquipo2);
        PartidoSimple partido = new PartidoSimple(equipo1, equipo2);
        partido.simularPartido(golesEquipo1, golesEquipo2, date);
        new Home<PartidoSimple>(PartidoSimple.class).save(partido);
    }
    
    public static void cargarPartidosSimplesYCrearPartidoCopa(String nombreEquipo1, String nombreEquipo2, Date date1, Date date2) {
        final Home<PartidoSimple> home = new Home<PartidoSimple>(PartidoSimple.class);
        PartidoSimple partido1 = home.getByNameAndDate(nombreEquipo1, nombreEquipo2, date1);
        PartidoSimple partido2 = home.getByNameAndDate(nombreEquipo1, nombreEquipo2, date2);
        PartidoCopa partidoCopa = new PartidoCopa(partido1.getEquipo1(), partido1.getEquipo2());
        partidoCopa.simularPartido(partido1, partido2);
        new Home<PartidoCopa>(PartidoCopa.class).save(partidoCopa);
    }

    public  void cargarJugadoresConcurrentemente() {
        final Home<Jugador> home = new Home<Jugador>(Jugador.class);
        ExecutorService newScheduledThreadPool = Executors.newFixedThreadPool(90);
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(90);
        for (int i = 0; i < 90; i++) {
            newScheduledThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        cyclicBarrier.await();
                        home.save(new Jugador("Jugador "));
                    } catch (Exception e) {
                        throw new RuntimeException("el cyclicBarrier tuvo problemas ", e);
                    }
                }
            });
        }
    }

    @SuppressWarnings("deprecation")
	public static void main(final String[] args) {
    	GeneradorDeDatos generadorDeDatos = new GeneradorDeDatos();
    	
    	CasoDeUso.ejecutar(generadorDeDatos, GENERAR_EQUPOS_CON_JUGADORES);
    	CasoDeUso.ejecutar(generadorDeDatos, CARGAR_EQUIPO_Y_GUARDAR_FORMACION);
    	CasoDeUso.ejecutar(generadorDeDatos, CARGAR_EQUIPOS_Y_JUGAR_PARTIDO_SIMPLE,"Boca", "River", 3, 2, new Date("2011/5/5"));
    	CasoDeUso.ejecutar(generadorDeDatos, CARGAR_EQUIPOS_Y_JUGAR_PARTIDO_SIMPLE,"Boca", "River", 1, 1,new Date("2011/6/5"));
    	CasoDeUso.ejecutar(generadorDeDatos, CARGAR_PARTIDOS_SIMPLES_Y_CREAR_PARTIDO_COPA,"Boca", "River", new Date("2011/5/5"), new Date("2011/6/5"));
//    	CasoDeUso.ejecutar(generadorDeDatos, CARGAR_JUGADORES_CONCURRENTEMENTE); //probar concurrencia
	}

}
