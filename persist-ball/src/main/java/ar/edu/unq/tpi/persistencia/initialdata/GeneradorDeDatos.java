package ar.edu.unq.tpi.persistencia.initialdata;

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
import ar.edu.unq.tpi.persistencia.home.HomeHibernateImpl;
import ar.edu.unq.tpi.persistencia.home.HomesHibernateRepository;
import ar.edu.unq.tpi.persistencia.logic.Formacion;
import ar.edu.unq.tpi.persistencia.logic.FormacionStrategyImpl;
import ar.edu.unq.tpi.persistencia.persistence.UseCase;
import ar.edu.unq.tpi.persistencia.utils.JugadorBuilder;

@SuppressWarnings("unchecked")
public class GeneradorDeDatos {
	
	public static final String GENERAR_EQUPOS_CON_JUGADORES = "generarEquiposConJugadores";
	public static final String CARGAR_EQUIPO_Y_GUARDAR_FORMACION = "cargarEquipoYGuardarFormacion";
	public static final String CARGAR_EQUIPOS_Y_JUGAR_PARTIDO_SIMPLE = "cargarEquiposYJugarPartidoSimple";
	public static final String CARGAR_PARTIDOS_SIMPLES_Y_CREAR_PARTIDO_COPA = "cargarPartidosSimplesYCrearPartidoCopa";
	public static final String CARGAR_JUGADOR = "cargarJugador";

    public  void generarEquiposConJugadores() {
        final HomeHibernateImpl<Equipo> home = HomesHibernateRepository.getInstance().getHome(Equipo.class);
        
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
    	HomeHibernateImpl<Formacion> homeFormacion = HomesHibernateRepository.getInstance().getHome(Formacion.class);
        final HomeHibernateImpl<Equipo> homeEquipo = HomesHibernateRepository.getInstance().getHome(Equipo.class);

        Equipo boca = homeEquipo.getByName("Boca");
        Equipo river = homeEquipo.getByName("River");
        Formacion formacionBoca = boca.armarFormacion();
        Formacion formacionRiver = river.armarFormacion();
        
		homeFormacion.save(formacionBoca);
		homeFormacion.save(formacionRiver);
    }
    
    public static void cargarEquiposYJugarPartidoSimple(String nombreEquipo1, String nombreEquipo2, Integer golesEquipo1, Integer golesEquipo2, Date date) {
        final HomeHibernateImpl<Equipo> home = HomesHibernateRepository.getInstance().getHome(Equipo.class);
        Equipo equipo1 = home.getByName(nombreEquipo1);
        Equipo equipo2 = home.getByName(nombreEquipo2);
        PartidoSimple partido = new PartidoSimple(equipo1, equipo2);
        partido.simularPartido(golesEquipo1, golesEquipo2, date);
        HomesHibernateRepository.getInstance().getHome(PartidoSimple.class).save(partido);
    }
    
    public static void cargarPartidosSimplesYCrearPartidoCopa(String nombreEquipo1, String nombreEquipo2, Date date1, Date date2) {
        final HomeHibernateImpl<PartidoSimple> home = HomesHibernateRepository.getInstance().getHome(PartidoSimple.class);
        PartidoSimple partido1 = home.getByNameAndDate(nombreEquipo1, nombreEquipo2, date1);
        PartidoSimple partido2 = home.getByNameAndDate(nombreEquipo1, nombreEquipo2, date2);
        PartidoCopa partidoCopa = new PartidoCopa(partido1.getEquipo1(), partido1.getEquipo2());
        partidoCopa.simularPartido(partido1, partido2);
        HomesHibernateRepository.getInstance().getHome(PartidoCopa.class).save(partidoCopa);
    }
    
    

	public  void cargarJugador() {
    	HomesHibernateRepository.getInstance().getHome(Jugador.class).save(new Jugador("Jugador "));
    }

	@SuppressWarnings("deprecation")
	public static void main(final String[] args) {
    	final GeneradorDeDatos generadorDeDatos = new GeneradorDeDatos();
    	
    	UseCase.execute(generadorDeDatos, GENERAR_EQUPOS_CON_JUGADORES);
    	UseCase.execute(generadorDeDatos, CARGAR_EQUIPO_Y_GUARDAR_FORMACION);
    	UseCase.execute(generadorDeDatos, CARGAR_EQUIPOS_Y_JUGAR_PARTIDO_SIMPLE,"Boca", "River", 3, 2, new Date("2011/5/5"));
    	UseCase.execute(generadorDeDatos, CARGAR_EQUIPOS_Y_JUGAR_PARTIDO_SIMPLE,"Boca", "River", 1, 1,new Date("2011/6/5"));
    	UseCase.execute(generadorDeDatos, CARGAR_PARTIDOS_SIMPLES_Y_CREAR_PARTIDO_COPA,"Boca", "River", new Date("2011/5/5"), new Date("2011/6/5"));
    	
//    	int nThreads = 10;
//		ExecutorService newScheduledThreadPool = Executors.newFixedThreadPool(nThreads);
//        final CyclicBarrier cyclicBarrier = new CyclicBarrier(nThreads);
//        for (int i = 0; i < nThreads; i++) {
//            newScheduledThreadPool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        cyclicBarrier.await();
//                        UseCase.execute(generadorDeDatos, CARGAR_JUGADOR);
//                    } catch (Exception e) {
//                        throw new RuntimeException("el cyclicBarrier tuvo problemas ", e);
//                    }
//                }
//            });
//        } 
	}

}
