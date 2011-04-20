package ar.edu.unq.tpi.persistencia.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ar.edu.unq.tpi.persistencia.bean.Equipo;
import ar.edu.unq.tpi.persistencia.bean.Jugador;
import ar.edu.unq.tpi.persistencia.bean.Tecnico;
import ar.edu.unq.tpi.persistencia.enums.Posicion;
import ar.edu.unq.tpi.persistencia.home.Home;
import ar.edu.unq.tpi.persistencia.logic.Formacion;
import ar.edu.unq.tpi.persistencia.logic.FormacionStrategyImpl;

public class Main {

    private static List<Jugador> generatedJugadores(final Equipo equipo) {
        List<Jugador> jugadorses = new ArrayList<Jugador>();
        jugadorses.add(new JugadorBuilder().withHabilidad(Posicion.ARQUERO, 8).withName("Vartez").build());
        jugadorses.add(new JugadorBuilder().withHabilidad(Posicion.VOLANTE_DEFENSIVO, 8).withName("Puyol").build());
        jugadorses.add(new JugadorBuilder().withHabilidad(Posicion.VOLANTE_LATERAL, 8).withName("Roberto Carloz")
                .build());
        jugadorses.add(new JugadorBuilder().withHabilidad(Posicion.VOLANTE_DEFENSIVO, 7).withName("Cafu").build());
        jugadorses.add(new JugadorBuilder().withHabilidad(Posicion.DELANTERO, 9).withName("Tevez").build());
        jugadorses.add(new JugadorBuilder().withHabilidad(Posicion.DELANTERO, 10).withName("Messi").build());
        jugadorses.add(new JugadorBuilder().withHabilidad(Posicion.ENGANCHE, 9).withName("Zidane").build());
        jugadorses.add(new JugadorBuilder().withHabilidad(Posicion.CENTRAL, 8).withName("Masche").build());
        jugadorses.add(new JugadorBuilder().withHabilidad(Posicion.LATERAL, 8).withName("Pepe").build());
        jugadorses.add(new JugadorBuilder().withHabilidad(Posicion.MEDIA_PUNTA, 8).withName("Kaviedes").build());
        jugadorses.add(new JugadorBuilder().withHabilidad(Posicion.VOLANTE_LATERAL, 8).withName("Juan").build());

        return jugadorses;
    }

    protected static void mainConcurrente() {
        final Home<Jugador> home = new Home<Jugador>(Jugador.class);
        ExecutorService newScheduledThreadPool = Executors.newFixedThreadPool(10);
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
        for (int i = 0; i < 10; i++) {
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

    protected static void crearEquipoConJugadores() {
        final Home<Equipo> home = new Home<Equipo>(Equipo.class);

        Tecnico tecnico = new Tecnico(new FormacionStrategyImpl(Arrays.asList(Posicion.values())), "DT");
        Equipo equipo = new Equipo(tecnico, "Estrellas");

        List<Jugador> jugadores = generatedJugadores(equipo);

        equipo.setJugadores(jugadores);

        home.save(equipo);
    }

    public static void cargarEquipoYGuardarFormacion() {
        final Home<Equipo> home = new Home<Equipo>(Equipo.class);
        Equipo byName = home.getByName("Estrellas");
        Formacion armarFormacion = byName.armarFormacion();
        System.out.println(byName.getNombre());
        new Home<Formacion>(Formacion.class).save(armarFormacion);
    }

    public static void main(final String[] args) {
        crearEquipoConJugadores();
        cargarEquipoYGuardarFormacion();
        // mainConcurrente();
        PersistenceManager.getInstance().close();
    }

}
