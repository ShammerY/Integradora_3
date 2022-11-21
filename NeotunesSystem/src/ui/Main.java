package ui; 

import model.*; 
import java.util.Scanner;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Main{

	private Scanner reader;
	private AppController appController;
	private Timer timer;
	private int timerFlag;

	public Main(){
		appController = new AppController();
		reader = new Scanner(System.in);
		timer = new Timer();
		timerFlag = 0;
	}

	public static void main(String[] args) {
		Main main = new Main();
		String option = "";
		do{
			option = main.showMenu();
			main.executeOption(option);
		}while(option.equals("0")==false);
		main.timer.cancel();
	}
	public void print(String text){
		System.out.println(text);
	}
	public void wait(double time){
		timerFlag = 0;
		time *= 1000;
		TimerTask task = new TimerTask(){
			public void run(){
				timerFlag = 1;
			}
		};
		timer.schedule(task,(long)time);
		do{
			if(timerFlag==1){System.out.println("");}
		}while(timerFlag==0);
	}
	public String showMenu(){
		String option = "";
		print("\n----------------------------------------------\n"+
			"             Welcome to NEOTUNES              \n"+
			"----------------------------------------------\n"+
			"(1)  Register Consumer User \n"+
			"(2)  Register Producer User\n"+
			"(3)  Register Song and Podcast\n"+
			"(4)  Create Play List\n"+
			"(5)  Edit Play List \n"+
			"(6)  Share Playlist \n"+
			"(7)  Reproduce Playlist \n"+
			"(8)  Buy a Song \n"+
			"(9)  Generate Reports \n"+
			"(0)  Exit Program");
		option = reader.next();
		return option;
	}
	public void executeOption(String option){
		switch(option){
			case "1":
				registerConsumer();
				break;
			case "2":
				registerProducer();
				break;
			case "3":
				registerAudio();
				break;
			case "4":
				registerPlaylist();	
				break;
			case "5":
				editPlaylist();
				break;
			case "6":
				sharePlaylist();
				break;
			case "7":
				reproducePlaylist();
				break;
			case "8":
				buySong();
				break;
			case "9":
				generateReport();
				break;
			case "0":
				print("\n SEE YOU NEXT TIME :)");
				break;
			default:
				print("\n INVALID OPTION");
		}
	}
	public void registerConsumer(){
		String msj = "";
		String nickname = "";
		String id = "";
		String type = "";
		String date = "";
		int pos = -1;
		Calendar calendar = Calendar.getInstance();

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
	}
	public void registerProducer(){
		String msj = "";
		String name = "";
		String type = "";
		String date = "";
		int pos = -1;
		Calendar calendar = Calendar.getInstance();

		print("\n USER REGISTRATION \n \n Enter NAME : ");
		name = reader.next();
		pos = appController.searchProducerUser(name);
		if(pos==-1){
			print("\n Select PRODUCER TYPE :");
			msj = showProducerType();
			print(msj);
			do{
				type = reader.next();
				if(!type.equals("1") && !type.equals("2")){print("\n INVALID USER TYPE");}
			}while(!type.equals("1") && !type.equals("2"));
			date = calendar.get(Calendar.DATE)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR);
			msj = appController.addProducer(name, date, type);
		}else{msj="\n PRODUCER ALREADY REGISTERED";}
		print(msj);
	}
	public void registerAudio(){
		String msj = "";
		String name = "";
		int userType = -1;
		int pos = -1;
		print("\n AUDIO REGISTRATION \n \n Enter PRODUCER NAME :");
		name = reader.next();
		pos = appController.searchProducerUser(name);
		if(pos!=-1){
			userType = appController.validateProducerType(pos);
			switch(userType){
				case 1:
					msj = registerSong(name);
					break;
				case 2:
					msj = registerPodcast(name);
					break;
			}
		}else{msj = "\n PRODUCER NOT FOUND";}
		print(msj);
	}
	public String registerSong(String producerName){
		String msj = "";
		String name = "";
		String album = "";
		String audioType = "";
		int pos = -1;
		int sw = 0;
		double value = 0;
		double duration = 0;

		print("\n SONG REGISTRATION");
		do{
			print("\n Enter song NAME :");
			name = reader.next();
			pos = appController.searchAudio(name);
			if(pos != -1){print("\n AUDIO WITH THIS NAME ALREADY EXISTS");}
		}while(pos!=-1);
		print("\n Enter SONG DURATION (minutes) :");
		do{
			sw = 0;
			if(reader.hasNextDouble()){
				duration = reader.nextDouble();
				if(duration<0){print("\n INVALID TIME DURATION");}
			}else{print("\n INVALID TIME DURATION");reader.next();sw=1;}
		}while(duration<0 || sw == 1);
		print("\n Enter SONG ALBUM : ");
		reader.nextLine();
		album = reader.nextLine();
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
		msj = appController.addSong(name,duration,producerName,album,audioType,value);
		return msj;
	}
	public String registerPodcast(String producerName){
		String msj = "";
		String name = "";
		String audioType = "";
		String description = "";
		int pos = -1;
		int sw = 0;
		double duration = 0;

		print("\n PODCAST REGISTRATION");
		do{
			print("\n Enter PODCAST NAME :");
			name = reader.next();
			pos = appController.searchAudio(name);
			if(pos != -1){print("\n AUDIO WITH THIS NAME ALREADY EXISTS");}
		}while(pos!=-1);
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
		reader.nextLine();
		description = reader.nextLine();
		msj = appController.addPodcast(name,duration,producerName,description,audioType);
		return msj;
	}
	public void registerPlaylist(){
		String msj = "";
		String nickname = "";
		String name = "";
		String audioName = "";
		String list = "";
		String type = "";
		int sw = 0;
		int pos = -1;
		int consumerPos = -1;
		int[] possitions = new int[10000];

		print("\n PLAYLIST CREATION \n \n Enter CONSUMER NICKNAME :");
		nickname = reader.next();
		consumerPos = appController.searchConsumerUser(nickname);
		if(consumerPos !=-1){
			print("Enter PLAYLIST NAME :");
			name = reader.next();
			sw = 0;
			list = appController.showAudioList();
			print(list);
			do{
				msj = showPlayListOptions();
				print(msj);
				type = reader.next();
				switch(type){
					case "1":
						print("\n Enter AUDIO NAME :");
						audioName = reader.next();
						pos = appController.searchAudio(audioName);
						if(pos!=-1){
							if(searchSongPos(pos,possitions,sw)==false){
								possitions[sw] = pos;
								sw++;
								print("\n AUDIO LISTED");
							}else{print("\n SONG ALREADY LISTED");}
						}else{print("\n AUDIO NOT FOUND");}
						break;
					case "0":
						if(sw>0){
							msj =appController.addPlayList(name,consumerPos,possitions, sw);
						}else{msj = "\n PLAYLIST DISCARTED";}
						break;
					case "2":
						print(list);
						break;
					default:
						print("\n INVALID OPTION");
				}
			}while(!type.equals("0"));
		}else{msj = "\n CONSUMER NOT FOUND";}
		print(msj);
	}
	public boolean searchSongPos(int pos, int[] possitions, int cant){
		boolean isFound = false;
		for(int i = 0; i<cant && isFound==false; i++){
			if(possitions[i]==pos){
				isFound = true;
			}
		}
		return isFound;
	}
	public void editPlaylist(){
		String msj = "";
		String name = "";
		String nickname = "";
		String list = "";
		String option = "";
		int consumerPos = -1;
		int pos = -1;
		print("PLAYLIST EDITOR \n\n Enter Consumer User Name :");
		nickname = reader.next();
		consumerPos = appController.searchConsumerUser(nickname);
		if(consumerPos!=-1){
			print("\n Select PLAYLIST to EDIT :");
			list = appController.showPlaylistName(consumerPos);
			if(!list.equals("")){
				print(list);
				name = reader.next();
				pos = appController.searchPlaylist(consumerPos, name);
				if(pos!=-1){
					do{
						msj = showEditOptions();
						print(msj);
						option = reader.next();
						executeEditOption(option, consumerPos, pos);
					}while(option.equals("0")==false);
					msj = "\n PLAYLIST SAVED";
				}else{msj = "\n PLAYLIST NOT FOUND";}
			}else{msj = "\n NO PLAYLIST REGISTERED YET......";}
		}else{msj = "\n USER NOT FOUND";}
		print(msj);
	}
	public void executeEditOption(String option, int consumerPos, int listPos){
		String msj = "";
		String name = "";
		int pos = -1;
		switch(option){
			case "1":
				print("\n Enter AUDIO NAME to add :");
				name = reader.next();
				pos = appController.searchAudioInList(name,consumerPos,listPos);
				if(pos!=-1){print("\n AUDIO ALREADY IN LIST");break;}
				pos = appController.searchAudio(name);
				if(pos==-1){print("\n AUDIO NOT FOUND");break;}
				msj = appController.addAudioToPlaylist(pos, consumerPos, listPos);
				print(msj);
				break;
			case "2":
				print("\n Enter AUDIO NAME to remove :");
				name = reader.next();
				pos = appController.searchAudioInList(name, consumerPos, listPos);
				if(pos!=-1){
					msj = appController.removeAudioFromPlaylist(pos, consumerPos, listPos);
				}else{msj = "\n AUDIO NOT FOUND IN PLAYLIST";}
				print(msj);
				break;
			case "3":
				msj = appController.showPlaylist(consumerPos,listPos);
				print(msj);
				break;
			case "4":
				msj = appController.showAudioList();
				print(msj);
				break;
			case "0":
				break;
			default:
				print("\n INVALID OPTION");
		}
	}
	public void buySong(){
		String msj = "";
		String nickname = "";
		String name = "";
		String list = "";
		int consumerPos = -1;
		int pos = -1;
		Calendar calendar = Calendar.getInstance();

		print("\n Enter Consumer USER NAME :");
		nickname = reader.next();
		consumerPos = appController.searchConsumerUser(nickname);
		if(consumerPos!=-1){
			list = appController.songList();
			if(!list.equals("")){
				print(list);
				print(" Enter SONG to buy : ");
				name = reader.next();
				pos = appController.searchAudio(name);
				if(pos!=-1){
					msj = appController.buySong(pos);
					msj += "\n DATE : "+calendar.get(Calendar.DATE)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR);
				}else{msj = "\n SONG NOT FOUND";}
			}else{msj = "\n NO SONGS REGISTERED YET.......";}	
		}else{msj = "\n USER NOT FOUND";}
		print(msj);
	}
	public void sharePlaylist(){
		String msj = "";
		String nickname = "";
		String name = "";
		String list = "";
		int consumerPos = -1;
		int pos = -1;

		print("\n Enter Consumer USER NAME :");
		nickname = reader.next();
		consumerPos = appController.searchConsumerUser(nickname);
		if(consumerPos!=-1){
			print("\n Select PLAYLIST to SHARE :");
			list = appController.showPlaylistMatrix(consumerPos);
			if(!list.equals("")){
				print(list);
				name = reader.next();
				pos = appController.searchPlaylist(consumerPos,name);
				if(pos!=-1){
					msj = "\n SHARING PLAYLIST WITH FOLLOWING AUDIO ";
					msj += appController.showPlaylist(consumerPos,pos)+"\n\n PLAYLIST SHARED SUCCESFULLY";
				}else{msj = "\n PLAYLIST NOT FOUND";}
			}else{msj = "\n NO PLAYLIST REGISTERED YET.....";}
		}else{msj = "\n USER NOT FOUND";}
		print(msj);
	}
	public void reproducePlaylist(){
		String msj = "";
		String nickname = "";
		String name = "";
		String list = "";
		int consumerPos = -1;
		int pos = -1;

		print("\n Enter Consumer USER NAME :");
		nickname = reader.next();
		consumerPos = appController.searchConsumerUser(nickname);
		if(consumerPos!=-1){
			print("\n Select PLAYLIST to REPRODUCE :");
			list = appController.showPlaylistName(consumerPos);
			if(!list.equals("")){
				print(list);
				name = reader.next();
				pos = appController.searchPlaylist(consumerPos,name);
				if(pos!=-1){
					playPlaylist(consumerPos,pos);
				}else{msj = "\n PLAYLIST NOT FOUND";}
			}else{msj = "\n NO PLAYLIST REGISTERED YET.....";}
		}else{msj = "\n USER NOT FOUND";}
		print(msj);
	}
	public void playPlaylist(int consumerPos,int listPos){
		String msj = "\n";
		double t = 0;
		int cont = 0;
		String[] names = new String[1000];
		Consumer[] consumers = appController.getConsumers();
		PlayList[] playlist = consumers[consumerPos].getPlaylist();
		Audio[] audio = playlist[listPos].getAudio();

		for(int i=0; i<playlist[listPos].getAudioNumber();i++){
			if(consumers[consumerPos] instanceof StandardUser && ((i%2)==0 && i!=0) ){
				reproduceAdd();
			}

			if(audio[i] instanceof Song){
				msj = "\n Playing Song :\n\n";
			}else{msj = "\n Playing Podcast :\n\n";}
			msj +=  "     NAME : "+audio[i].getName()+"\n"+
					" PRODUCER : "+audio[i].getProducer()+"\n"+
					" DURATION : "+(int)audio[i].getDuration()+" min";
			print(msj);
			cont++;
			names[i]=audio[i].getName();
			wait(t=4);
		}
		appController.setReproductions(names,cont);
		print(" PLAYLIST FINISHED :");
	}
	public void reproduceAdd(){
		AddCases addCase = new AddCases();
		String[] add = addCase.getAdd();
		print(add[(int)Math.floor(Math.random()*3)]);
	}
	public void generateReport(){
		String option = "";
		do{
			option = showReportOptions();
			print(option);
			option = reader.next();
			executeReportOption(option);
		}while(!option.equals("0"));
	}
	public void executeReportOption(String option){
		String msj = "";
		String name = "";
		int pos = -1;
		switch(option){
			case "1":
				msj = appController.audioTypeReproductions();
				print(msj);
				break;
			case "2":
				msj = appController.mostHeardGenre();
				print(msj);
				break;
			case "3":
				msj = appController.mostHeardCategory();
				print(msj);
				break;
			case "4":
				msj = appController.topArtists();
				print(msj);
				msj = appController.topCreators();
				print(msj);
				break;
			case "5":
				msj = appController.topSongs();
				print(msj);
				msj = appController.topPodcasts();
				print(msj);
				break;
			case "6":
				msj = appController.genreSells();
				print(msj);
				break;
			case "7":
				msj = appController.mostSoldSong();
				print(msj);
				break;
			case "0":
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
		"(1) STANDARD /   20 Play Lists      | Buy 100 Songs \n"+
		"(2) PREMIUM  / Unlimited Play Lists | Buy Unlimited Songs";
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
		"(2) SHOW AVAILABLE AUDIO \n"+
		"(0) FINISH EDITING PLAYLIST";
		return msj;
	}
	public String showEditOptions(){
		String msj = "\n EDIT OPTIONS \n"+
		"(1) ADD AUDIO\n"+
		"(2) REMOVE AUDIO \n"+
		"(3) DISPLAY PLAYLIST AUDIO \n"+
		"(4) SHOW AVAILABLE AUDIO \n"+
		"(0) FINISH EDITING PLAYLIST ";
		return msj;
	}
	public String showReportOptions(){
		String msj = "\n Report Options :\n\n"+
		"(1) Total reproductions per Audio Type\n"+
		"(2) Song Genre most reproduced \n"+
		"(3) Podcast Category most reproduced \n"+
		"(4) TOP 5 Artists and Creators \n"+
		"(5) TOP 10 Songs and Podcasts \n"+
		"(6) Total Sells per Song Genre \n"+
		"(7) Most Sold Song \n"+
		"(0) Finish Reports ";
		return msj;
	}
	

}