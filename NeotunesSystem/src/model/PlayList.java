package model;
public class PlayList{
	private String name;
	private String[] audio;
	private String id;

	public PlayList(String name, String[] playlist, String id){
		audio = new String[1000];
		name = name;
		audio = playlist;
		id = id;
	}


}