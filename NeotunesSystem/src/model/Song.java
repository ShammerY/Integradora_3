package model;
public class Song extends Audio{

	private String album;
	private Genre genre;
	private double value;
	private int sells;
	public Song(String name, double duration,String album,String type,double value){
		super(name,duration);
		this.album = album;
		this.value = value;
		this.sells = 0;
		setGenre(type);
	}
	public String getAlbum(){
		return album;
	}
	public Genre getGenre(){
		return genre;
	}
	public double getValue(){
		return value;
	}

	public void setGenre(String audioType){
		switch(audioType){
			case "1":
				genre = Genre.ROCK;
				break;
			case "2":
				genre = Genre.POP;
				break;
			case "3":
				genre = Genre.TRAP;
				break;
			case "4":
				genre = Genre.HOUSE;
				break;
		}
		
	}
	@Override
	public String showAudio(){
		String msj = "\n"+
		"\n    SONG NAME : "+name+
		"\n        ALBUM : "+album+
		"\n        GENRE : "+genre+
		"\n        VALUE : "+value;
		return msj;
	}
}