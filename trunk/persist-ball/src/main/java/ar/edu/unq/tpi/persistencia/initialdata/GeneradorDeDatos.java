package ar.edu.unq.tpi.persistencia.initialdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import ar.edu.unq.tpi.persistencia.bean.Equipo;
import ar.edu.unq.tpi.persistencia.bean.Jugador;
import ar.edu.unq.tpi.persistencia.bean.PartidoCopa;
import ar.edu.unq.tpi.persistencia.bean.PartidoSimple;
import ar.edu.unq.tpi.persistencia.bean.Tecnico;
import ar.edu.unq.tpi.persistencia.enums.Posicion;
import ar.edu.unq.tpi.persistencia.home.HomeHibernateImpl;
import ar.edu.unq.tpi.persistencia.home.HomesHibernateRepository;
import ar.edu.unq.tpi.persistencia.interfaces.Habilidad;
import ar.edu.unq.tpi.persistencia.logic.Formacion;
import ar.edu.unq.tpi.persistencia.logic.FormacionStrategyImpl;
import ar.edu.unq.tpi.persistencia.persistence.UseCase;
import ar.edu.unq.tpi.persistencia.utils.JugadorBuilder;
import ar.edu.unq.tpi.persistencia.utils.ListUtils;
import ar.edu.unq.tpi.persistencia.utils.Par;

@SuppressWarnings("unchecked")
public class GeneradorDeDatos {

    public static final String BOCA = "Boca";

    public static final String RIVER = "River";

    public static final String RACING = "Racing";

    public static final String INDEPENDIENTE = "Independiente";

    private static final String TECNICO_BOCA = "Bilardo";

    private static final String TECNICO_RIVER = "Pasarella";

    private static final String TECNICO_RACING = "Mostazo Merlo";

    private static final String TECNICO_INDEPENDIENTE = "Bochinni";

    public static final String GENERAR_EQUPOS_FULL = "generarEquiposFull";

    public static final String GENERAR_EQUPOS_CON_JUGADORES = "generarEquiposConJugadores";

    public static final String CARGAR_EQUIPO_Y_GUARDAR_FORMACION = "cargarEquipoYGuardarFormacion";

    public static final String CARGAR_EQUIPOS_Y_JUGAR_PARTIDO_SIMPLE = "cargarEquiposYJugarPartidoSimple";

    public static final String CARGAR_PARTIDOS_SIMPLES_Y_CREAR_PARTIDO_COPA = "cargarPartidosSimplesYCrearPartidoCopa";

    public static final String CARGAR_JUGADOR = "cargarJugador";

    public static final String GENERAR_N_PARTIDOS_SIMPLES = "generarNPartidosSimples";

    public void generarEquiposFull() {
        final HomeHibernateImpl<Equipo> home = HomesHibernateRepository.getInstance().getHome(Equipo.class);
        Random random = new Random();

        List<String> equipos = new ArrayList<String>();
        List<String> tecnicos = new ArrayList<String>();
        List<Jugador> jugadores;
        List<Posicion> posiciones;
        equipos.add(BOCA);
        tecnicos.add(TECNICO_BOCA);
        equipos.add(RIVER);
        tecnicos.add(TECNICO_RIVER);
        equipos.add(RACING);
        tecnicos.add(TECNICO_RACING);
        equipos.add(INDEPENDIENTE);
        tecnicos.add(TECNICO_INDEPENDIENTE);

        for (int i = 0; i < equipos.size(); i++) {
            Equipo equipo = createEquipo(equipos.get(i), tecnicos.get(i));
            posiciones = ListUtils.posicionRandom();
            jugadores = new ArrayList<Jugador>();

            for (int j = 0; j < posiciones.size(); j++) {
                Posicion pos = (Posicion) ListUtils.random(posiciones);
                jugadores.add(new JugadorBuilder().withHabilidad(pos, random.nextInt(Habilidad.HABILIDAD_MAXIMA))
                        .withName("Jugador" + equipo.getNombre() + (j + 1)).build());
            }
            equipo.setJugadores(jugadores);
            home.save(equipo);
        }
    }

    public void generarEquiposConJugadores() {
        final HomeHibernateImpl<Equipo> home = HomesHibernateRepository.getInstance().getHome(Equipo.class);

        Equipo boca = createEquipo(BOCA, TECNICO_BOCA);
        Equipo river = createEquipo(RIVER, TECNICO_RIVER);

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

    public Equipo createEquipo(final String nombreE, final String nombreDT) {
        Tecnico tecnico = new Tecnico(new FormacionStrategyImpl(Arrays.asList(Posicion.values())), nombreDT);
        Equipo equipo = new Equipo(tecnico, nombreE);
        return equipo;
    }

    public void cargarEquipoYGuardarFormacion() {
        HomeHibernateImpl<Formacion> homeFormacion = HomesHibernateRepository.getInstance().getHome(Formacion.class);
        final HomeHibernateImpl<Equipo> homeEquipo = HomesHibernateRepository.getInstance().getHome(Equipo.class);

        Equipo boca = homeEquipo.getByName(BOCA);
        Equipo river = homeEquipo.getByName(RIVER);
        Formacion formacionBoca = boca.armarFormacion();
        Formacion formacionRiver = river.armarFormacion();

        homeFormacion.save(formacionBoca);
        homeFormacion.save(formacionRiver);
    }

    public static void cargarEquiposYJugarPartidoSimple(final String nombreEquipo1, final String nombreEquipo2,
            final Integer golesEquipo1, final Integer golesEquipo2, final GregorianCalendar date) {
        final HomeHibernateImpl<Equipo> home = HomesHibernateRepository.getInstance().getHome(Equipo.class);
        Equipo equipo1 = home.getByName(nombreEquipo1);
        Equipo equipo2 = home.getByName(nombreEquipo2);
        persistirPartidoSimple(golesEquipo1, golesEquipo2, date, equipo1, equipo2);
    }

    private static void persistirPartidoSimple(final Integer golesEquipo1, final Integer golesEquipo2,
            final GregorianCalendar date, final Equipo equipo1, final Equipo equipo2) {
        PartidoSimple partido = new PartidoSimple(equipo1, equipo2);
        partido.simularPartido(golesEquipo1, golesEquipo2, date);
        HomesHibernateRepository.getInstance().getHome(PartidoSimple.class).save(partido);
    }

    public static void cargarPartidosSimplesYCrearPartidoCopa(final String nombreEquipo1, final String nombreEquipo2,
            final GregorianCalendar date1, final GregorianCalendar date2, final Integer penales1, final Integer penales2) {
        final HomeHibernateImpl<PartidoSimple> home = HomesHibernateRepository.getInstance().getHome(
                PartidoSimple.class);
        PartidoSimple partido1 = home.getByNameAndDate(nombreEquipo1, nombreEquipo2, date1);
        PartidoSimple partido2 = home.getByNameAndDate(nombreEquipo1, nombreEquipo2, date2);
        PartidoCopa partidoCopa = new PartidoCopa(partido1.getEquipo1(), partido1.getEquipo2());
        partidoCopa.simularPartido(partido1, partido2, penales1, penales2);
        HomesHibernateRepository.getInstance().getHome(PartidoCopa.class).save(partidoCopa);
    }

    public static List<PartidoSimple> cargarTodosLosPartidosSimples() {
        final HomeHibernateImpl<PartidoSimple> home = HomesHibernateRepository.getInstance().getHome(
                PartidoSimple.class);
        return home.getAll();
    }

    public static void cargarPartidosSimplesYGenerarNPartidosDeCopa(final int n) {
        List<PartidoSimple> partidosSimples = cargarTodosLosPartidosSimples();

        for (int i = 0; i < n; i++) {
            // BUSCAR 2 PARTIDOS SIMPLES
            int size = partidosSimples.size();
            int ida = (int) Math.random() * size;
            int vuelta = (int) Math.random() * size;
            PartidoSimple partido1 = partidosSimples.get(ida);
            PartidoSimple partido2 = partidosSimples.get(vuelta);
            while (partido1.tieneLosMismosEquipos(partido2) && partido1.getFecha().equals(partido2.getFecha())
                    || !partido1.tieneLosMismosEquipos(partido2)) {
                vuelta = (int) Math.random() * size;
                partido2 = partidosSimples.get(vuelta);
            }

            PartidoCopa partidoCopa = new PartidoCopa(partido1.getEquipo1(), partido1.getEquipo2());

            // GENERAR PENALES
            int penales1 = (int) Math.random() * 5 + 1;
            int penales2 = (int) Math.random() * 5 + 1;
            while (penales2 == penales1) {
                penales2 = (int) Math.random() * 5 + 1;
            }

            // SIMULAR PARTIDO
            partidoCopa.simularPartido(partido1, partido2, penales1, penales2);

            // GUARDARLO
            HomesHibernateRepository.getInstance().getHome(PartidoCopa.class).save(partidoCopa);
        }
    }

    public static void generarNPartidosSimples(final Integer n) {
        final HomeHibernateImpl<Equipo> home = HomesHibernateRepository.getInstance().getHome(Equipo.class);

        List<Equipo> equipos = home.getAll();

        Random random = new Random();

        Par<Equipo, Equipo> rivales;
        for (int i = 0; i < n; i++) {
            rivales = getRamdonRivales(equipos);
            persistirPartidoSimple(random.nextInt(5), random.nextInt(5), new GregorianCalendar(
                    random.nextInt(1000) + 2000, random.nextInt(12), random.nextInt(28)), rivales.getX(),
                    rivales.getY());
        }

    }

    private static Par<Equipo, Equipo> getRamdonRivales(final List<Equipo> equipos) {
        Random random = new Random();
        int cantEquipos = equipos.size();
        int random1 = random.nextInt(cantEquipos);
        int random2 = random.nextInt(cantEquipos);
        Equipo equipo1 = equipos.get(random1);
        while (random1 == random2) {
            random2 = random.nextInt(cantEquipos);
        }
        Equipo equipo2 = equipos.get(random2);

        return new Par<Equipo, Equipo>(equipo1, equipo2);
    }

    public void cargarJugador() {
        HomesHibernateRepository.getInstance().getHome(Jugador.class).save(new Jugador("Jugador "));
    }

    public static void main(final String[] args) {
        final GeneradorDeDatos generadorDeDatos = new GeneradorDeDatos();

        // UseCase.execute(generadorDeDatos, GENERAR_EQUPOS_CON_JUGADORES);
        // UseCase.execute(generadorDeDatos, CARGAR_EQUIPO_Y_GUARDAR_FORMACION);
        //
        // UseCase.execute(generadorDeDatos,
        // CARGAR_EQUIPOS_Y_JUGAR_PARTIDO_SIMPLE,BOCA, RIVER, 3, 2, new
        // GregorianCalendar(2011,5,5));
        // UseCase.execute(generadorDeDatos,
        // CARGAR_EQUIPOS_Y_JUGAR_PARTIDO_SIMPLE,BOCA, RIVER, 1, 2,new
        // GregorianCalendar(2011,6,5));
        // UseCase.execute(generadorDeDatos,
        // CARGAR_PARTIDOS_SIMPLES_Y_CREAR_PARTIDO_COPA,BOCA, RIVER, new
        // GregorianCalendar(2011,5,5), new GregorianCalendar(2011,6,5), 5, 4);

        UseCase.execute(generadorDeDatos, GENERAR_EQUPOS_FULL);
        UseCase.execute(generadorDeDatos, GENERAR_N_PARTIDOS_SIMPLES, 30000);

        // int nThreads = 50;
        // ExecutorService newScheduledThreadPool =
        // Executors.newFixedThreadPool(nThreads);
        // final CyclicBarrier cyclicBarrier = new CyclicBarrier(nThreads);
        // for (int i = 0; i < nThreads; i++) {
        // newScheduledThreadPool.execute(new Runnable() {
        // @Override
        // public void run() {
        // try {
        // cyclicBarrier.await();
        // UseCase.execute(generadorDeDatos, CARGAR_JUGADOR);
        // } catch (Exception e) {
        // throw new RuntimeException("el cyclicBarrier tuvo problemas ", e);
        // }
        // }
        // });
        // }
        // newScheduledThreadPool.shutdown();
    }

}
