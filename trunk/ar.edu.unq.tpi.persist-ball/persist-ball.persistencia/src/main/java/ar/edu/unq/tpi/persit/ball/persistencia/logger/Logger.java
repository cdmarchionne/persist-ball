package ar.edu.unq.tpi.persit.ball.persistencia.logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Logger {
	private static final Log LOG = LogFactory.getLog(Logger.class);

	
	public static void log(DatosHistoricos datos){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Datos Historicos\n");
		stringBuilder.append(datos.getEquipo1().getNombre() +" gano: "+datos.getPartidosGanadosEquipo1() + "\n");
		stringBuilder.append(datos.getEquipo2().getNombre() +" gano: "+datos.getPartidosGanadosEquipo2() + "\n");
		stringBuilder.append("emataron : "+datos.getPartidosEmpatados() + "\n");	
		LOG.debug(stringBuilder);
	}

}
