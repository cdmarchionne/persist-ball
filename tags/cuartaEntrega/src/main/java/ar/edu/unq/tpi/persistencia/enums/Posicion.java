package ar.edu.unq.tpi.persistencia.enums;

import javax.persistence.Table;

@Table(name="Position")
public enum Posicion {
	ARQUERO,
	LATERAL,
	CENTRAL,
	VOLANTE_DEFENSIVO,
	VOLANTE_LATERAL,
	ENGANCHE,
	MEDIA_PUNTA,
	DELANTERO;

}
