package ar.edu.unq.tpi.persistencia.bean;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import ar.edu.unq.tpi.persistencia.enums.PersistenObject;

@MappedSuperclass
public class PersistentObject implements Serializable, PersistenObject {
    private static final long serialVersionUID = 1L;

    @Version
    @GeneratedValue
    private Long stateVersion;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Override
    public Long getStateVersion() {
        return stateVersion;
    }

    public boolean isPersisted() {
        return stateVersion != null;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
