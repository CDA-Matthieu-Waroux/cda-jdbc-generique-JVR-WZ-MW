package com.jdbc.dao;

import java.util.List;

public interface Dao<T> {

	public void create(T pObject);

	public List<T> readAll();

	public void update(T pObject);

	public void delete(T pObject);
}
