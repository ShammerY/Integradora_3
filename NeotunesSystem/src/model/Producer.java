package model;

public abstract class Producer{
	
	protected String name;
	protected String date;

	public Producer(String name, String date) {
		this.name = name;
		this.date = date;
	}

	public String getName(){
		return name;
	}
	


}