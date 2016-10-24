package com.softserve.edu.resources.dao;

import java.util.List;

public interface IDao<TEntity> {

	// Create
	boolean insert(TEntity entity);

	// Read
	TEntity getById(Long id);

	List<TEntity> getByFieldName(String fieldName, String text);

	// TEntity getByFieldName(String fieldName, Long value);

	List<TEntity> getAll();

	// Update
	boolean updateByFieldName(String fieldName, String text);

	// Delete
	boolean deleteById(Long id);

	boolean delete(TEntity entity);

}
