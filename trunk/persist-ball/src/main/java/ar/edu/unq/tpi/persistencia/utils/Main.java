package ar.edu.unq.tpi.persistencia.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.log4j.BasicConfigurator;
import org.hibernate.Session;

import ar.edu.unq.tpi.persistencia.bean.Jugador;
import ar.edu.unq.tpi.persistencia.logic.HabilidadImpl;

public class Main {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		Logger.getAnonymousLogger().setLevel(Level.INFO);
		 Jugador pepe = new Jugador("Pepe");
		 pepe.addHabilidad(new HabilidadImpl());
		PersistenceManager.getInstance().build();
		Session session = PersistenceManager.getInstance().getCurrentSession();
		 session.beginTransaction();
		 session.save(pepe);
		 session.getTransaction().commit();

		PersistenceManager.getInstance().close();
	}

}
