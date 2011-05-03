package ar.edu.unq.tpi.persistencia.home;

import java.util.HashMap;
import java.util.Map;

import ar.edu.unq.tpi.persistencia.bean.Equipo;
import ar.edu.unq.tpi.persistencia.bean.Jugador;
import ar.edu.unq.tpi.persistencia.bean.Partido;
import ar.edu.unq.tpi.persistencia.bean.PartidoCopa;
import ar.edu.unq.tpi.persistencia.bean.PartidoSimple;
import ar.edu.unq.tpi.persistencia.logic.Formacion;

@SuppressWarnings("rawtypes")
public class HomesHibernateRepository {
	private final Map<Class, HomeHibernateImpl> homes = new HashMap<Class, HomeHibernateImpl>();
	private static HomesHibernateRepository INSTANCE;
	
	public synchronized static HomesHibernateRepository getInstance(){
		if(INSTANCE == null){
			INSTANCE = new HomesHibernateRepository();
		}
		return INSTANCE;
	}
	
	private HomesHibernateRepository() {
		homes.put(Equipo.class, new HomeHibernateImpl<Equipo>(Equipo.class));
		homes.put(Jugador.class, new HomeHibernateImpl<Jugador>(Jugador.class));
		homes.put(Formacion.class, new HomeHibernateImpl<Formacion>(Formacion.class));
		homes.put(PartidoSimple.class, new HomeHibernateImpl<PartidoSimple>(PartidoSimple.class));
		homes.put(PartidoCopa.class, new HomeHibernateImpl<PartidoCopa>(PartidoCopa.class));
	}
	
	public  HomeHibernateImpl getHome(Class clazz){
		return homes.get(clazz);
	}
	
	
}
