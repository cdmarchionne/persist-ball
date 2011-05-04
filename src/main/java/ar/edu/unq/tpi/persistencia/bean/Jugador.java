package ar.edu.unq.tpi.persistencia.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.edu.unq.tpi.persistencia.enums.Posicion;
import ar.edu.unq.tpi.persistencia.interfaces.Habilidad;
import ar.edu.unq.tpi.persistencia.interfaces.Nombrable;

@Entity
@Table(name = "Jugador")
public class Jugador extends PersistentObject implements Nombrable {
    private static final long serialVersionUID = 351122077442453571L;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "jugador_id")
    private List<Habilidad> habilidades = new ArrayList<Habilidad>();

    @Basic
    private String nombre;

    @Override
    public String getNombre() {
        return nombre;
    }

    public Jugador() {
    }

    public Jugador(final String nombre) {
        super();
        this.setNombre(nombre);
    }

    public void addHabilidad(final Habilidad habilidad) {
        this.getHabilidades().add(habilidad);
    }

    public int getValorHabilidad(final Posicion posicion) {
        int max = 0;
        for (Habilidad habilidad : this.getHabilidades()) {
            if (habilidad.getValor(posicion) > max) {
                max = habilidad.getValor(posicion);
            }
        }
        return max;
    }

    public void setHabilidades(final List<Habilidad> habilidades) {
        this.habilidades = habilidades;
    }

    public List<Habilidad> getHabilidades() {
        return habilidades;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

}
