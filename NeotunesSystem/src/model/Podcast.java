package model;
public class Podcast extends Audio{

	private String description;
	private Category category;
	public Podcast(String name, double duration,String producer, String description, String type){
		super(name,duration, producer);
		this.description = description;
		setCategory(type);
	}
	public Category getCategory(){
		return category;
	}
	public String getDescription(){
		return description;
	}
	public void setCategory(String audioType){
		switch(audioType){
			case "1":
				category = Category.POLITICS;
				break;
			case "2":
				category = Category.ENTERTAINMENT;
				break;
			case "3":
				category = Category.VIDEOGAMES;
				break;
			case "4":
				category = Category.FASHION;
				break;
		}
	}
	@Override
	public String showAudio(){
		String msj = "\n"+
		"\n PODCAST NAME : "+name+
		"\n     CATEGORY : "+category+
		"\n  DESCRIPTION : "+description;
		return msj;
	}

}