package ar.edu.unq.tpi.persistencia.bean;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;


@MappedSuperclass
public class Entity implements Serializable{
	private static final long serialVersionUID = 1L;
	
    @Version
    @GeneratedValue
    private Long stateVersion;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    // @IgnoreProperty
    public Long getStateVersion() {
        return stateVersion;
    }

    public boolean isPersisted() {
        return stateVersion != null;
    }

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}	
}
