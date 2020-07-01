package com.jdbc.dao;

import java.util.List;

public interface Dao<T> {
	
	public void create(T pObbject);

	public List<T> readAll();

	public void update(T pObject);

	public void delete(T pObject);
}
