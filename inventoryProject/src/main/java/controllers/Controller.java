package controllers;

import java.util.List;

public interface Controller<T>{
	String singular();
	String plural();
	T create();
	
	boolean delete();
	
	List<T> readAll();
	
	T read();
	
	T update();
	
	boolean cancelAll();
	public boolean validID(int ID);
}