package ar.edu.unq.tpi.persit.ball.persistencia.home;

import java.util.HashMap;
import java.util.Map;

import ar.edu.unq.tpi.persist.ball.domain.bean.Equipo;
import ar.edu.unq.tpi.persist.ball.domain.bean.Jugador;
import ar.edu.unq.tpi.persist.ball.domain.bean.PartidoCopa;
import ar.edu.unq.tpi.persist.ball.domain.bean.PartidoSimple;
import ar.edu.unq.tpi.persist.ball.domain.logica.Formacion;

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
		homes.put(PartidoSimple.class, new PartidoSimpleHome());
		homes.put(PartidoCopa.class, new PartidoCopaHome());
	}
	
	public  HomeHibernateImpl getHome(Class clazz){
		return homes.get(clazz);
	}
	
	
}
