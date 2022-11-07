package model;

public abstract class Audio{
	
	protected String name;
	protected int reproductions;
	protected double duration;

	public Audio(String name, double duration) {
		this.name = name;
		this.duration = duration;
		this.reproductions = 0;
	}

	public String getName(){
		return name;
	}
	public int getReproductions(){
		return reproductions;
	}
	public double getDuration(){
		return duration;
	}
	public abstract String showAudio();


}