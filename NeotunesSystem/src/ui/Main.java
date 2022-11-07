package ui; 

import model.AppController; 
import java.util.Scanner;
import java.util.Calendar;

public class Main{

	private Scanner reader;
	private AppController appController;	
	public Main(){
		appController = new AppController();
		reader = new Scanner(System.in);
	}

	public static void main(String[] args) {
		Main main = new Main();
		String option = "";
		do{
			option = main.showMenu();
			main.executeOption(option);
		}while(option.equals("0")==false);

	}
	public void print(String text){
		System.out.println(text);
	}
	public String showMenu(){
		String option = "";
		print("\n----------------------------------------------\n"+
			"             Welcome to NEOTUNES              \n"+
			"----------------------------------------------\n"+
			"(1)  Register Consumer User \n"+
			"(2)  Register Producer User  \n"+
			"(3)  Register Song and Podcast\n"+
			"(4)  Create Play List\n"+
			"(5)  Edit Play List \n"+
			"(0)  Exit Program");
		option = reader.next();
		return option;
	}
	public void executeOption(String option){
		String msj = "";
		int sw = 0;
		int pos = -1;
		int pos2 = -1;
		int userType = -1;
		String type = "";

		Calendar calendar = Calendar.getInstance();
		String date = "";
		String nickname = "";
		String id = "";

		int[] possitions = new int[1000];
		String album = "";
		String name = "";
		String audioName = "";
		double value = 0;
		double duration = 0;
		String audioType = "";
		String description = "";

		switch(option){
			case "1":
				print("\n USER REGISTRATION \n \n Enter NICKNAME : ");
				nickname = reader.next();
				pos = appController.searchConsumerUser(nickname);
				if(pos==-1){
					print("\n Enter ID :");
					id = reader.next();
					print("\n Select CONSUMER USER TYPE :");
					msj = showUserType();
					print(msj);
					do{
						type = reader.next();
						if(!type.equals("1") && !type.equals("2")){print("\n INVALID USER TYPE");}
					}while(!type.equals("1") && !type.equals("2"));
					date = calendar.get(Calendar.DATE)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR);
					msj = appController.addConsumer(nickname, id, date, type);
				}else{msj = "\n NICKNAME ALREADY TAKEN";}
				print(msj);
				break;
			case "2":
				print("\n USER REGISTRATION \n \n Enter NAME : ");
				nickname = reader.next();
				pos = appController.searchProducerUser(nickname);
				if(pos==-1){
					print("\n Select PRODUCER TYPE :");
					msj = showProducerType();
					print(msj);
					do{
						type = reader.next();
						if(!type.equals("1") && !type.equals("2")){print("\n INVALID USER TYPE");}
					}while(!type.equals("1") && !type.equals("2"));
					date = calendar.get(Calendar.DATE)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR);
					msj = appController.addProducer(nickname, date, type);
				}else{msj="\n PRODUCER ALREADY REGISTERED";}
				print(msj);
				break;
			case "3":
				print("\n AUDIO REGISTRATION \n \n Enter PRODUCER NAME :");
				nickname = reader.next();
				pos = appController.searchProducerUser(nickname);
				if(pos!=-1){
					userType = appController.validateProducerType(pos);
					switch(userType){
						case 1:
							print("\n SONG REGISTRATION\n");
							do{
								print("\n Enter song NAME :");
								nickname = reader.next();
								pos2 = appController.searchAudio(nickname);
								if(pos2 != -1){print("\n AUDIO WITH THIS NAME ALREADY EXISTS");}
							}while(pos2!=-1);
							print("\n Enter SONG DURATION (minutes) :");
							do{
								sw = 0;
								if(reader.hasNextDouble()){
									duration = reader.nextDouble();
									if(duration<0){print("\n INVALID TIME DURATION");}
								}else{print("\n INVALID TIME DURATION");reader.next();sw=1;}
							}while(duration<0 || sw == 1);
							print("\n Enter SONG ALBUM : ");
							album = reader.next();
							msj = showGenre();
							print(msj);
							do{
								sw = 0;
								audioType = reader.next();
								if(!audioType.equals("1") && !audioType.equals("2") && !audioType.equals("3") && !audioType.equals("4")){
									print("\n INVALID SONG GENRE"); sw = 1;
								}
							}while(sw == 1);
							do{
								sw = 0;
								print("\n Enter SONG VALUE ($) :");
								if(reader.hasNextDouble()){
									value = reader.nextDouble();
									if(value<0){print("\n INVALID VALUE");sw =1;}
								}else{print("\n INVALID VALUE");reader.next();sw = 1;}
							}while(sw == 1);
							msj = appController.addSong(nickname,duration,album,audioType,value);
							break;
						case 2:
							print("\n PODCAST REGISTRATION");
							do{
								print("\n Enter PODCAST NAME :");
								nickname = reader.next();
								pos2 = appController.searchAudio(nickname);
								if(pos2 != -1){print("\n AUDIO WITH THIS NAME ALREADY EXISTS");}
							}while(pos2!=-1);
							print("\n Enter PODCAST DURATION (minutes) :");
							do{
								sw = 0;
								if(reader.hasNextDouble()){
									duration = reader.nextDouble();
									if(duration<0){print("\n INVALID TIME DURATION");}
								}else{print("\n INVALID TIME DURATION");reader.next();sw=1;}
							}while(duration<0 || sw == 1);
							print("\n Enter PODCAST CATEGORY");
							msj = showCategory();
							print(msj);
							do{
								sw = 0;
								audioType = reader.next();
								if(!audioType.equals("1") && !audioType.equals("2") && !audioType.equals("3") && !audioType.equals("4")){
									print("\n INVALID PODCAST CATEGORY"); sw = 1;
								}
							}while(sw == 1);
							print("\n Enter Podcast DESCRIPTION :");
							description = reader.nextLine();
							msj = appController.addPodcast(nickname,duration,description,audioType);
							reader.next();
							break;
					}
				}else{msj = "\n PRODUCER NOT FOUND";}
				print(msj);
				break;
			case "4":
				print("\n PLAY LIST CREATION \n \n Enter CONSUMER NICKNAME :");
				nickname = reader.next();
				pos = appController.searchConsumerUser(nickname);
				if(pos !=-1){
					print("Enter PLAYLIST NAME :");
					name = reader.next();
					sw = 0;
					msj = appController.showAudioList();
					print(msj);
					do{
						msj = showPlayListOptions();
						print(msj);
						type = reader.next();
						switch(type){
							case "1":
								print("\n Enter AUDIO NAME :");
								audioName = reader.next();
								pos2 = appController.searchAudio(audioName);
								if(pos2!=-1){
									possitions[sw] = pos2;
									sw++;
									print("\n SONG ADDED");
								}else{print("\n AUDIO NOT FOUND");}
								break;
							case "2":
								if(sw>0){
									msj =appController.addPlayList(name,pos,possitions, sw);
									print(msj);
								}
								break;
							case "3":
								msj = appController.showAudioList();
								print(msj);
								break;
							default:
								print("\n INVALID OPTION");
						}
					}while(!type.equals("2"));
				}else{msj = "\n CONSUMER NOT FOUND";}
					
				print(msj);
				break;
			case "5":
				
				break;
			case "0":
				print("\n SEE YOU NEXT TIME :)");
				break;
			default:
				print("\n INVALID OPTION");
		}
	}
	/**
	 * showUserType : Visual Method to show the user type options
	 * @return String : slist of the user Type options;
	 */
	public String showUserType(){
		String msj = "\n"+
		"(1) STANDARD /   20 Play Lists      |  100 Songs \n"+
		"(2) PREMIUM  / Unlimited Play Lists | Unlimited Songs";
		return msj;
	}
	/**
	 * showProducerType : Visual Method to show the producer type options
	 * @return String : slist of the producer type options;
	 */
	public String showProducerType(){
		String msj = "\n"+
		"(1) ARTIST   /   PRODUCE SONGS \n"+
		"(2) CREATOR  /   PRODUCE PODCASTS ";
		return msj;
	}
	/**
	 * showGenre : Visual Method to show the genre options
	 * @return String : slist of the genre options;
	 */
	public String showGenre(){
		String msj = "\n"+
		"(1) ROCK \n"+
		"(2) POP \n"+
		"(3) TRAP \n"+
		"(4) HOUSE ";
		return msj;
	}
	/**
	 * showCategory : Visual Method to show the category options
	 * @return String : slist of the category options;
	 */
	public String showCategory(){
		String msj = "\n"+
		"(1) POLITICS \n"+
		"(2) ENTERTAINMENT \n"+
		"(3) VIDEOGAMES \n"+
		"(4) FASHION ";
		return msj;
	}
	/**
	 * showPlayListOptions : Visual Method to show the play list options
	 * @return String : slist of the play list options;
	 */
	public String showPlayListOptions(){
		String msj = "\n PLAY LIST OPTIONS \n"+
		"(1) ADD AUDIO\n"+
		"(2) FINISH EDITING PLAYLIST \n"+
		"(3) SHOW AVAILABLE SONGS ";
		return msj;
	}
	

}