package model;

public abstract class Producer{
	
	protected String name;
	protected String date;
	protected int reproductions;

	public Producer(String name, String date) {
		this.name = name;
		this.date = date;
		this.reproductions = 0;
	}

	public String getName(){
		return name;
	}
	public void setReproductions(){
		reproductions++;
	}
	public int getReproductions(){
		return reproductions;
	}
	


}