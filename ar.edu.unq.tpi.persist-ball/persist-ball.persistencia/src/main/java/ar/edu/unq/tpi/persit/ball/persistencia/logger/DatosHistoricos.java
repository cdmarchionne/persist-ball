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

		if (tupla[n] == null) {
			partidosEmpatados = (Long) tupla[1];
			n++;
		}

		tupla = list.get(n);

		setearDatosEquipo1(tupla);
		n++;
		if (tupla[0] == null) {
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
	
	public Long partidosTotales(){
		return partidosGanadosEquipo1+partidosGanadosEquipo2+partidosEmpatados;
	}
	
	public boolean huboEmpate(){
		return partidosGanadosEquipo1 == partidosGanadosEquipo2;
	}

	public Equipo getEquipoGanador() {
		if (partidosGanadosEquipo1 > partidosGanadosEquipo2) {
			return equipo1;
		} else if (partidosGanadosEquipo1 < partidosGanadosEquipo2) {
			return equipo2;
		} else {
			return null;
		}
	}

	public Equipo getEquipoPerdedor() {
		if (partidosGanadosEquipo1 > partidosGanadosEquipo2) {
			return equipo2;
		} else if (partidosGanadosEquipo1 < partidosGanadosEquipo2) {
			return equipo1;
		} else {
			return null;
		}
	}

	public Long getPartidosGanadosDelEquipoGanador() {
		return Math.max(partidosGanadosEquipo1, partidosGanadosEquipo2);
	}

	public Long getPartidosGanadosDelEquipoPerdedor() {
		return Math.min(partidosGanadosEquipo1, partidosGanadosEquipo2);
	}

	public Long getPartidosEmpatados() {
		return partidosEmpatados;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Datos Historicos\n");
		stringBuilder.append(equipo1.getNombre() + " gano: "
				+ partidosGanadosEquipo1 + "\n");
		stringBuilder.append(equipo2.getNombre() + " gano: "
				+ partidosGanadosEquipo2 + "\n");
		stringBuilder.append("emataron : " + partidosEmpatados + "\n");
		return stringBuilder.toString();
	}

	public Equipo getEquipo1() {
		return equipo1;
	}

	public Equipo getEquipo2() {
		return equipo2;
	}

}
