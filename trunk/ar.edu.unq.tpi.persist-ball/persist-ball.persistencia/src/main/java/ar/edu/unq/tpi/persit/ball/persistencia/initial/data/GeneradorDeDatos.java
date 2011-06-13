package ar.edu.unq.tpi.persit.ball.persistencia.initial.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ar.edu.unq.tpi.persist.ball.domain.bean.Equipo;
import ar.edu.unq.tpi.persist.ball.domain.bean.Jugador;
import ar.edu.unq.tpi.persist.ball.domain.bean.PartidoCopa;
import ar.edu.unq.tpi.persist.ball.domain.bean.PartidoSimple;
import ar.edu.unq.tpi.persist.ball.domain.bean.Tecnico;
import ar.edu.unq.tpi.persist.ball.domain.bean.enums.Posicion;
import ar.edu.unq.tpi.persist.ball.domain.interfaces.Habilidad;
import ar.edu.unq.tpi.persist.ball.domain.logica.Formacion;
import ar.edu.unq.tpi.persist.ball.domain.logica.FormacionStrategyImpl;
import ar.edu.unq.tpi.persist.ball.domain.utils.JugadorBuilder;
import ar.edu.unq.tpi.persist.ball.domain.utils.ListUtils;
import ar.edu.unq.tpi.persist.ball.domain.utils.Par;
import ar.edu.unq.tpi.persit.ball.persistencia.UseCase;
import ar.edu.unq.tpi.persit.ball.persistencia.UseCaseManager;
import ar.edu.unq.tpi.persit.ball.persistencia.home.HomeHibernateImpl;
import ar.edu.unq.tpi.persit.ball.persistencia.home.HomesHibernateRepository;
import ar.edu.unq.tpi.persit.ball.persistencia.home.PartidoCopaHome;
import ar.edu.unq.tpi.persit.ball.persistencia.home.PartidoSimpleHome;
import ar.edu.unq.tpi.persit.ball.persistencia.logger.DatosHistoricos;
import ar.edu.unq.tpi.persit.ball.persistencia.logger.Logger;
import ar.edu.unq.tpi.persit.ball.persistencia.logger.RankingPartidoCopa;
import ar.edu.unq.tpi.persit.ball.persistencia.logger.Reporter;

@SuppressWarnings("unchecked")
public class GeneradorDeDatos {

    public static final String CARGAR_EQUIPOS_Y_JUGAR_PARTIDO_SIMPLE = "cargarEquiposYJugarPartidoSimple";

    public static final String CARGAR_PARTIDOS_SIMPLES_Y_CREAR_PARTIDO_COPA = "cargarPartidosSimplesYCrearPartidoCopa";

    public static final String CARGAR_JUGADOR = "cargarJugador";

    public static final String GENERAR_N_PARTIDOS_SIMPLES = "generarNPartidosSimples";

    public static final String CARGAR_PARTIDOS_SIMPLES_Y_GENERAR_N_PARTIDOS_DE_COPA = "cargarPartidosSimplesYGenerarNPartidosDeCopa";

    public static final String CREAR_PARTIDOS_SIMPLES_Y_CREAR_N_PARTIDOS_DE_COPA = "crearPartidosSimplesYCrearNPartidosDeCopa";

    public static final String BOCA = "Boca";

    public static final String RIVER = "River";

    public static final String RACING = "Racing";

    public static final String INDEPENDIENTE = "Independiente";

    public static final String GIMNASIA = "Gimnasia";

    public static final String ESTUDIANTES = "Estudiantes";

    public static final String HURACAN = "Huracan";

    public static final String TIGRE = "Tigre";

    public static final String QUILMES = "Quilmes";

    public static final String GODOYCRUZ = "GodoyCruz";

    private static final String TECNICO_BOCA = "Tecnico_Boca";

    private static final String TECNICO_RIVER = "Tecnico_River";

    private static final String TECNICO_RACING = "Tecnico_Racing";

    private static final String TECNICO_INDEPENDIENTE = "Tecnico_Independiente";

    private static final String TECNICO_GIMNASIA = "Tecnico_Gimnasia";

    private static final String TECNICO_ESTUDIANTES = "Tecnico_Estudiantes";

    private static final String TECNICO_HURACAN = "Tecnico_Huracan";

    private static final String TECNICO_TIGRE = "Tecnico_Tigre";

    private static final String TECNICO_QUILMES = "Tecnico_Quilmes";

    private static final String TECNICO_GODOYCRUZ = "Tecnico_GodoyCruz";

    public static final String GENERAR_EQUIPOS_FULL = "generarEquiposFull";

    public static final int TAMANIO_DE_PAGINA = 100;;

    public void generarEquiposFull(final Integer cantidad) {
        List<String> equipos = new ArrayList<String>();
        List<String> tecnicos = new ArrayList<String>();

        for (int i = 0; i < cantidad; i++) {
            equipos.add("Equipo" + (i + 1));
            tecnicos.add("TecnicoDel" + equipos.get(i));
        }
        this.generarJugadoresPorEquipos(equipos, tecnicos);

    }

    public UseCase generar10EquiposDePrimera() {
        return new UseCase() {
            @Override
            public void run() {
                List<String> equipos = new ArrayList<String>();
                List<String> tecnicos = new ArrayList<String>();
                equipos.add(BOCA);
                tecnicos.add(TECNICO_BOCA);
                equipos.add(RIVER);
                tecnicos.add(TECNICO_RIVER);
                equipos.add(RACING);
                tecnicos.add(TECNICO_RACING);
                equipos.add(INDEPENDIENTE);
                tecnicos.add(TECNICO_INDEPENDIENTE);
                equipos.add(GIMNASIA);
                tecnicos.add(TECNICO_GIMNASIA);
                equipos.add(ESTUDIANTES);
                tecnicos.add(TECNICO_ESTUDIANTES);
                equipos.add(HURACAN);
                tecnicos.add(TECNICO_HURACAN);
                equipos.add(TIGRE);
                tecnicos.add(TECNICO_TIGRE);
                equipos.add(QUILMES);
                tecnicos.add(TECNICO_QUILMES);
                equipos.add(GODOYCRUZ);
                tecnicos.add(TECNICO_GODOYCRUZ);

                GeneradorDeDatos.this.generarJugadoresPorEquipos(equipos, tecnicos);
            }

            @Override
            public String getName() {
                return "generar4EquiposDePrimera";
            }
        };
    }

    private void generarJugadoresPorEquipos(final List<String> equipos, final List<String> tecnicos) {
        final HomeHibernateImpl<Equipo> home = HomesHibernateRepository.getInstance().getHome(Equipo.class);
        Random random = new Random();
        List<Jugador> jugadores;
        List<Posicion> posiciones;

        for (int i = 0; i < equipos.size(); i++) {
            Equipo equipo = this.createEquipo(equipos.get(i), tecnicos.get(i));
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

    public UseCase generarEquiposConJugadores() {
        return new UseCase() {
            @Override
            public void run() {
                final HomeHibernateImpl<Equipo> home = HomesHibernateRepository.getInstance().getHome(Equipo.class);

                Equipo boca = GeneradorDeDatos.this.createEquipo(BOCA, TECNICO_BOCA);
                Equipo river = GeneradorDeDatos.this.createEquipo(RIVER, TECNICO_RIVER);

                List<Jugador> jugadorsesBoca = new ArrayList<Jugador>();
                jugadorsesBoca.add(new JugadorBuilder().withHabilidad(Posicion.ARQUERO, 8).withName("Vartez").build());
                jugadorsesBoca.add(new JugadorBuilder().withHabilidad(Posicion.VOLANTE_DEFENSIVO, 8).withName("Puyol")
                        .build());
                jugadorsesBoca.add(new JugadorBuilder().withHabilidad(Posicion.VOLANTE_LATERAL, 8)
                        .withName("Roberto Carloz").build());
                jugadorsesBoca.add(new JugadorBuilder().withHabilidad(Posicion.ENGANCHE, 9).withName("Zidane").build());
                jugadorsesBoca.add(new JugadorBuilder().withHabilidad(Posicion.VOLANTE_DEFENSIVO, 7).withName("Cafu")
                        .build());
                jugadorsesBoca.add(new JugadorBuilder().withHabilidad(Posicion.DELANTERO, 9).withName("Tevez").build());
                jugadorsesBoca
                        .add(new JugadorBuilder().withHabilidad(Posicion.DELANTERO, 10).withName("Messi").build());

                List<Jugador> jugadorsesRiver = new ArrayList<Jugador>();
                jugadorsesRiver.add(new JugadorBuilder().withHabilidad(Posicion.CENTRAL, 8).withName("Masche").build());
                jugadorsesRiver.add(new JugadorBuilder().withHabilidad(Posicion.LATERAL, 8).withName("Pepe").build());
                jugadorsesRiver.add(new JugadorBuilder().withHabilidad(Posicion.MEDIA_PUNTA, 8).withName("Kaviedes")
                        .build());
                jugadorsesRiver.add(new JugadorBuilder().withHabilidad(Posicion.VOLANTE_LATERAL, 8).withName("Juan")
                        .build());

                boca.setJugadores(jugadorsesBoca);
                river.setJugadores(jugadorsesRiver);

                home.save(boca);
                home.save(river);
            }

            @Override
            public String getName() {
                return "generarEquiposConJugadores";
            }
        };
    }

    public Equipo createEquipo(final String nombreE, final String nombreDT) {
        Tecnico tecnico = new Tecnico(new FormacionStrategyImpl(Arrays.asList(Posicion.values())), nombreDT);
        Equipo equipo = new Equipo(tecnico, nombreE);
        return equipo;
    }

    public UseCase cargarEquipoYGuardarFormacion() {
        return new UseCase() {
            @Override
            public void run() {
                HomeHibernateImpl<Formacion> homeFormacion = HomesHibernateRepository.getInstance().getHome(
                        Formacion.class);
                final HomeHibernateImpl<Equipo> homeEquipo = HomesHibernateRepository.getInstance().getHome(
                        Equipo.class);

                Equipo boca = homeEquipo.getByName("Boca");
                Equipo river = homeEquipo.getByName("River");
                Formacion formacionBoca = boca.armarFormacion();
                Formacion formacionRiver = river.armarFormacion();

                homeFormacion.save(formacionBoca);
                homeFormacion.save(formacionRiver);

            }

            @Override
            public String getName() {
                return "cargarEquipoYGuardarFormacion";
            }
        };
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
        final PartidoSimpleHome home = (PartidoSimpleHome) HomesHibernateRepository.getInstance().getHome(
                PartidoSimple.class);
        PartidoSimple partido1 = home.getByNameAndDate(nombreEquipo1, nombreEquipo2, date1);
        PartidoSimple partido2 = home.getByNameAndDate(nombreEquipo1, nombreEquipo2, date2);
        PartidoCopa partidoCopa = new PartidoCopa(partido1.getEquipo1(), partido1.getEquipo2());
        partidoCopa.simularPartido(partido1, partido2, penales1, penales2);
        HomesHibernateRepository.getInstance().getHome(PartidoCopa.class).save(partidoCopa);
    }

    public UseCase jugarTorneo() {
        return new UseCase() {

            @Override
            public void run() {
                HomeHibernateImpl<Equipo> homeEquipo = HomesHibernateRepository.getInstance().getHome(Equipo.class);
                HomeHibernateImpl<PartidoCopa> homePartidoCopa = HomesHibernateRepository.getInstance().getHome(
                        PartidoCopa.class);
                List<Equipo> equipos = homeEquipo.getAll();
                Random random = new Random();
                PartidoSimple partidoSimple1;
                PartidoSimple partidoSimple2;
                PartidoCopa partidoCopa;

                for (Equipo equipo1 : equipos) {
                    for (Equipo equipo2 : equipos) {
                        if (!equipo1.equals(equipo2)) {
                            partidoSimple1 = new PartidoSimple(equipo1, equipo2);
                            partidoSimple1
                                    .simularPartido(random.nextInt(5), random.nextInt(5), new GregorianCalendar());
                            partidoSimple2 = new PartidoSimple(equipo2, equipo1);
                            partidoSimple2
                                    .simularPartido(random.nextInt(5), random.nextInt(5), new GregorianCalendar());

                            partidoCopa = new PartidoCopa(equipo1, equipo2);
                            partidoCopa.simularPartido(partidoSimple1, partidoSimple2);
                            homePartidoCopa.save(partidoCopa);
                        }
                    }
                }

            }

            @Override
            public String getName() {
                return "Jugar torneo";
            }
        };
    }

    public static void cargarPartidosSimplesYGenerarNPartidosDeCopa(final Integer n) {
        final HomeHibernateImpl<PartidoSimple> home = HomesHibernateRepository.getInstance().getHome(
                PartidoSimple.class);
        int desde = 0;

        List<PartidoSimple> currentPag = home.getPag(desde, TAMANIO_DE_PAGINA);
        List<PartidoSimple> partidosSimples = new ArrayList<PartidoSimple>();
        partidosSimples.addAll(currentPag);
        int sizePag = currentPag.size();

        while (sizePag == TAMANIO_DE_PAGINA) {
            desde += TAMANIO_DE_PAGINA;
            currentPag = home.getPag(desde, sizePag);
            partidosSimples.addAll(currentPag);
            sizePag = currentPag.size();
        }

        for (int i = 0; i < n; i++) {
            // BUSCAR 2 PARTIDOS SIMPLES
            int size = partidosSimples.size();
            int ida = (int) (Math.random() * size);
            int vuelta = (int) (Math.random() * size);
            PartidoSimple partido1 = partidosSimples.get(ida);
            PartidoSimple partido2 = partidosSimples.get(vuelta);
            while (partido1.tieneLosMismosEquipos(partido2) && partido1.getFecha().equals(partido2.getFecha())
                    || !partido1.tieneLosMismosEquipos(partido2)) {
                vuelta = (int) (Math.random() * size);
                partido2 = partidosSimples.get(vuelta);
            }

            PartidoCopa partidoCopa = new PartidoCopa(partido1.getEquipo1(), partido1.getEquipo2());

            // GENERAR PENALES
            int penales1 = (int) (Math.random() * 5);
            int penales2 = (int) (Math.random() * 5);
            while (penales2 == penales1) {
                penales2 = (int) (Math.random() * 5);
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
                    random.nextInt(1000) + 2000, random.nextInt(12), random.nextInt(28)), rivales.getFirst(),
                    rivales.getSecond());
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

    public UseCase cargarJugador() {
        return new UseCase() {

            @Override
            public void run() {
                HomesHibernateRepository.getInstance().getHome(Jugador.class).save(new Jugador("Jugador "));
            }

            @Override
            public String getName() {
                return "cargarJugador";
            }
        };
    }

    public void pruebaConcurrente(final int nThreads) {
        ExecutorService newScheduledThreadPool = Executors.newFixedThreadPool(nThreads);
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(nThreads);
        for (int i = 0; i < nThreads; i++) {
            newScheduledThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        cyclicBarrier.await();
                        UseCaseManager.execute(GeneradorDeDatos.this, CARGAR_JUGADOR);
                    } catch (Exception e) {
                        throw new RuntimeException("el cyclicBarrier tuvo problemas ", e);
                    }
                }
            });
        }
        newScheduledThreadPool.shutdown();
    }

    public void datosHistoricos(final String nombreEqipo1, final String nombreEquipo2) {
        HomeHibernateImpl<Equipo> equipoHome = HomesHibernateRepository.getInstance().getHome(Equipo.class);
        Equipo equipo1 = equipoHome.getByName(nombreEqipo1);
        Equipo equipo2 = equipoHome.getByName(nombreEquipo2);

        PartidoSimpleHome partidoHome = (PartidoSimpleHome) HomesHibernateRepository.getInstance().getHome(
                PartidoSimple.class);

        DatosHistoricos datosHistoricos = partidoHome.getDatosHistoricos(equipo1, equipo2);
        Logger.log(datosHistoricos);
    }

    public UseCase rankingPartidoCopa() {
        return new UseCase() {

            @Override
            public void run() {
                PartidoCopaHome partidoCopaHome = (PartidoCopaHome) HomesHibernateRepository.getInstance().getHome(
                        PartidoCopa.class);
                RankingPartidoCopa ranking = partidoCopaHome.getRankingPartidoCopa();
                Logger.log(ranking);
            }

            @Override
            public String getName() {
                return "rankingPartidoCopa";
            }
        };
    }

    public static void main(final String[] args) {
        final GeneradorDeDatos generadorDeDatos = new GeneradorDeDatos();

        // UseCaseManager.execute(generadorDeDatos.generar10EquiposDePrimera());
        // UseCaseManager.execute(generadorDeDatos, GENERAR_N_PARTIDOS_SIMPLES,
        // 200000);
        // UseCaseManager.execute(generadorDeDatos,
        // CARGAR_PARTIDOS_SIMPLES_Y_GENERAR_N_PARTIDOS_DE_COPA, 100000);
        // UseCaseManager.execute(generadorDeDatos, "datosHistoricos",BOCA,
        // RIVER);
        // UseCaseManager.execute(generadorDeDatos,
        // CREAR_PARTIDOS_SIMPLES_Y_CREAR_N_PARTIDOS_DE_COPA, 100000);
        for (int i = 0; i < 35000; i++) {
            UseCaseManager.execute(generadorDeDatos.jugarTorneo());
        }

        for (int i = 0; i < 5; i++) {
            UseCaseManager.execute(generadorDeDatos.rankingPartidoCopa());
            UseCaseManager.execute(generadorDeDatos.rankingPartidoCopa());
            UseCaseManager.execute(generadorDeDatos.rankingPartidoCopa());
            UseCaseManager.execute(generadorDeDatos.rankingPartidoCopa());
            UseCaseManager.execute(generadorDeDatos.rankingPartidoCopa());
            UseCaseManager.execute(generadorDeDatos.rankingPartidoCopa());
            UseCaseManager.execute(generadorDeDatos.rankingPartidoCopa());
            UseCaseManager.execute(generadorDeDatos.rankingPartidoCopa());
            UseCaseManager.execute(generadorDeDatos.rankingPartidoCopa());
            UseCaseManager.execute(generadorDeDatos.rankingPartidoCopa());
            UseCaseManager.execute(generadorDeDatos.rankingPartidoCopa());
        }
        Reporter.logAverageQueryDelay();

        // UseCaseManager.execute(generadorDeDatos.cargarEquipoYGuardarFormacion());
        //
        // UseCaseManager.execute(generadorDeDatos,
        // CARGAR_EQUIPOS_Y_JUGAR_PARTIDO_SIMPLE,BOCA, RIVER, 2, 2,
        // new GregorianCalendar(2011,5,25));
        // UseCaseManager.execute(generadorDeDatos,
        // CARGAR_EQUIPOS_Y_JUGAR_PARTIDO_SIMPLE,BOCA, RIVER, 2, 2,
        // new GregorianCalendar(2011,6,1));
        // UseCaseManager.execute(generadorDeDatos,
        // CARGAR_PARTIDOS_SIMPLES_Y_CREAR_PARTIDO_COPA,BOCA, RIVER,
        // new GregorianCalendar(2011,5,11), new GregorianCalendar(2011,6,5), 5,
        // 4);
        // UseCaseManager.execute(generadorDeDatos, GENERAR_EQUIPOS_FULL, 10);
    }

}
