package service;

import java.util.ArrayList;

public interface Querry<T> {
	public ArrayList<T> selectAll();

	public T selectById(int id);

	public int insert(T obj);

	public int update(T obj);

	public int delete(int id);

	public ArrayList<T> selectByCondition(String condition);

	int getAutoIncrement();
}
