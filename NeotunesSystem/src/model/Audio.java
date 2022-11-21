package model;

public abstract class Audio{
	
	protected String name;
	protected int reproductions;
	protected double duration;
	protected String producer;

	public Audio(String name, double duration, String producer) {
		this.name = name;
		this.duration = duration;
		this.producer = producer;
		this.reproductions = 0;
	}

	public String getName(){
		return name;
	}
	public int getReproductions(){
		return reproductions;
	}
	public void setReproductions(){
		reproductions++;
	}
	public double getDuration(){
		return duration;
	}
	public String getProducer(){
		return producer;
	}
	public abstract String showAudio();


}