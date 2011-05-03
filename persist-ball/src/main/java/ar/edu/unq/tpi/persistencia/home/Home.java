package ar.edu.unq.tpi.persistencia.home;

import java.io.Serializable;
import java.util.List;

import ar.edu.unq.tpi.persistencia.enums.PersistenObject;

public interface Home<T extends PersistenObject> {


    public void delete(final T pgo);

    public void update(final T pgo);

    public void save(final T pgo);

    public void saveOrUpdate(final T pgo);

	public T getById(final Serializable id);

    public T getByName(final String name);

	public List<T> getAll();




}
