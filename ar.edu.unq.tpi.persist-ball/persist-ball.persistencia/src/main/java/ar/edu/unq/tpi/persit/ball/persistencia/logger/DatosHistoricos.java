package ar.edu.unq.tpi.persit.ball.persistencia.logger;

import java.util.List;

import ar.edu.unq.tpi.persist.ball.domain.bean.Equipo;

public class DatosHistoricos {
	private Equipo equipo1;
	private Equipo equipo2;
	private Long partidosGanadosEquipo1;
	private Long partidosGanadosEquipo2;
	private Long partidosEmpatados;

	public DatosHistoricos(List<Object[]> list) {
		Object[] tupla = list.get(0);
		int n = 0;
		
		if(tupla[n]==null){
			partidosEmpatados = (Long) tupla[1];
			n++;
		}
		
		tupla = list.get(n);
		
		setearDatosEquipo1(tupla);
		n++;
		if(tupla[0]==null){
			partidosEmpatados = (Long) tupla[1];
			n++;
		}
		setearDatosEquipo2(list.get(n));
	}

	private void setearDatosEquipo1(Object[] tupla) {
		equipo1 = (Equipo) tupla[0];
		partidosGanadosEquipo1 = (Long) tupla[1];
	}
	private void setearDatosEquipo2(Object[] tupla) {
		equipo2 = (Equipo) tupla[0];
		partidosGanadosEquipo2 = (Long) tupla[1];
	}
	
	public Equipo getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}

	public Equipo getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}

	public Long getPartidosGanadosEquipo1() {
		return partidosGanadosEquipo1;
	}

	public void setPartidosGanadosEquipo1(Long partidosGanadosEquipo1) {
		this.partidosGanadosEquipo1 = partidosGanadosEquipo1;
	}

	public Long getPartidosGanadosEquipo2() {
		return partidosGanadosEquipo2;
	}

	public void setPartidosGanadosEquipo2(Long partidosGanadosEquipo2) {
		this.partidosGanadosEquipo2 = partidosGanadosEquipo2;
	}

	public Long getPartidosEmpatados() {
		return partidosEmpatados;
	}

	public void setPartidosEmpatados(Long partidosEmpatados) {
		this.partidosEmpatados = partidosEmpatados;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Datos Historicos\n");
		stringBuilder.append(equipo1.getNombre() +" gano: "+partidosGanadosEquipo1 + "\n");
		stringBuilder.append(equipo2.getNombre() +" gano: "+partidosGanadosEquipo2 + "\n");
		stringBuilder.append("emataron : "+partidosEmpatados + "\n");
		return stringBuilder.toString();
	}

}
