package ar.edu.unq.tpi.persit.ball.persistencia.logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import ar.edu.unq.tpi.persist.ball.domain.bean.Equipo;
import ar.edu.unq.tpi.persist.ball.domain.utils.Par;

public class Logger {
	private static final Log LOG = LogFactory.getLog(Logger.class);
	
	public static void log(Object object){
		LOG.debug(object);
	}

	public static void log(DatosHistoricos datos) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Datos Historicos entre " + datos.getEquipo1()
				+ " y " + datos.getEquipo2() + "\n");
		if (datos.huboEmpate()) {
			logearEmpate(datos, stringBuilder);
		} else {
			logearGanador(datos, stringBuilder);
		}
		
		LOG.debug(stringBuilder);
	}
	
	public static void log(RankingPartidoCopa datos) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Ranking de equipos");
		
		for (Par<Equipo, Number> p : datos.getResultList()) {
			stringBuilder.append("\n" + p.getFirst().getNombre() + " - " + p.getSecond().toString());
		}
		LOG.debug(stringBuilder);
	}

	private static void logearGanador(DatosHistoricos datos,
			StringBuilder stringBuilder) {
		stringBuilder.append("El ganador es "+ datos.getEquipoGanador() + "\n");
		stringBuilder.append("Jugaron "+datos.partidosTotales() + " partidos\n");
		stringBuilder.append(datos.getEquipoGanador()+" gano " + datos.getPartidosGanadosDelEquipoGanador()	+ " partidos\n");
		stringBuilder.append(datos.getEquipoPerdedor()+ " gano " + datos.getPartidosGanadosDelEquipoPerdedor()+ " partidos \n");
		stringBuilder.append("Empataron  " + datos.getPartidosEmpatados()+ " partidos\n");
	}

	private static void logearEmpate(DatosHistoricos datos,
			StringBuilder stringBuilder) {
		stringBuilder.append("Hay un empate!! \n");
		stringBuilder.append("Jugaron " +datos.partidosTotales() + " partidos\n");
		stringBuilder.append("Ganaron "	+ datos.getPartidosGanadosDelEquipoGanador() + " partidos\n");
		stringBuilder.append("Empataron  " + datos.getPartidosEmpatados()+ " partidos\n");
	}

}
