package ar.edu.unq.tpi.persistencia.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.log4j.BasicConfigurator;
import org.hibernate.Session;

import ar.edu.unq.tpi.persistencia.bean.Jugador;
import ar.edu.unq.tpi.persistencia.interfaces.Habilidad;
import ar.edu.unq.tpi.persistencia.logic.HabilidadImpl;

public class Main {
    
    public static void main(String[] args) {
        BasicConfigurator.configure();
        Logger.getAnonymousLogger().setLevel(Level.INFO);
//        Jugador pepe = new Jugador("Pepe");
        PersistenceManager.getInstance().getConfiguration().addAnnotatedClass(Jugador.class);
        PersistenceManager.getInstance().getConfiguration().addAnnotatedClass(Habilidad.class);
        PersistenceManager.getInstance().getConfiguration().addAnnotatedClass(HabilidadImpl.class);
        Session session = PersistenceManager.getInstance().getCurrentSession();
//        session.beginTransaction();
//        session.save(pepe);
//        session.getTransaction().commit();
        System.out.println(session.get(Jugador.class.getCanonicalName(), 1));
    }

}
