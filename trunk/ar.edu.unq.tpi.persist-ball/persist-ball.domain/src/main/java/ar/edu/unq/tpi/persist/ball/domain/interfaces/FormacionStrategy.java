package ar.edu.unq.tpi.persist.ball.domain.interfaces;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import ar.edu.unq.tpi.persist.ball.domain.bean.Equipo;
import ar.edu.unq.tpi.persist.ball.domain.bean.PersistentObject;
import ar.edu.unq.tpi.persist.ball.domain.bean.enums.Posicion;
import ar.edu.unq.tpi.persist.ball.domain.logica.Formacion;

@Entity
@Table(name = "FormacionStrategy")
@Cache(region = "FormacionStrategy", usage = CacheConcurrencyStrategy.READ_ONLY)
public abstract class FormacionStrategy extends PersistentObject {
    private static final long serialVersionUID = 1L;

    @Cascade(CascadeType.ALL)
    @Enumerated(EnumType.STRING)
    @Column(name = "posicion")
    @ElementCollection
    @OrderColumn(name = "order_index")
    private List<Posicion> posiciones = new ArrayList<Posicion>();

    public abstract Formacion armarFormacion(Equipo equipo);

    public void setPosiciones(final List<Posicion> posiciones) {
        this.posiciones = posiciones;
    }

    public List<Posicion> getPosiciones() {
        return posiciones;
    }

}
