package model;
public class AppController{

	public static final int TOTAL_CONSUMERS = 100;
	public static final int TOTAL_PRODUCERS = 100;
	public static final int TOTAL_AUDIO = 100;
	private Consumer[] consumers;
	private Producer[] producers;
	private Audio[] audio;

	public AppController(){
		consumers = new Consumer[TOTAL_CONSUMERS];
		producers = new Producer[TOTAL_PRODUCERS];
		audio = new Audio[TOTAL_AUDIO];
	}
	public Consumer[] getConsumers(){
		return consumers;
	}
	/**
	 * searchConsumerUser : Method tos earch the possition of an specific consumer user
	 * @param nickname : identifier
	 * @return pos : possition of the consumer
	 */
	public int searchConsumerUser(String nickname){
		int pos = -1;
		boolean isFound = false;
		for(int i = 0; i<TOTAL_CONSUMERS && isFound == false; i++){
			if(consumers[i]!=null){
				if(consumers[i].getNickname().equals(nickname)){
					pos = i;
					isFound = true;
				}
			}
		}
		return pos;
	}
	/**
	 * searchProducerUser : Method to search the possition in array of an specific producer
	 * @param name : identifier of the producer
	 * @return pos : Possition of the searched producer
	 */
	public int searchProducerUser(String name){
		int pos = -1;
		boolean isFound = false;
		for(int i = 0; i<TOTAL_PRODUCERS && isFound == false; i++){
			if(producers[i]!=null){
				if(producers[i].getName().equals(name)){
					pos = i;
					isFound = true;
				}
			}
		}
		return pos;
	}
	/**
	 * addConsumer : add a consumer on the next empty possition of the Consumer Array
	 * @param nickname : nickname of the consumer
	 * @param id : id of the consumer
	 * @param date : date when the consumer was registered
	 * @param type : variable that hold what type of consumer is being registered
	 * @return msj : Confirmation message
	 */
	public String addConsumer(String nickname,String id,String date,String type){
		String msj = "";
		int pos = -1;
		pos = searchConsumerEmptyPos();
		if(pos!= -1){
			switch(type){
				case "1":
					consumers[pos] = new StandardUser(nickname, id, date);
					msj = "\n STANDARD USER REGISTERED SUCCESFULLY";
					break;
				case "2":
					consumers[pos] = new PremiumUser(nickname, id, date);
					msj = "\n PREMIUM USER REGISTERED SUCCESFULLY";
					break;
			}
		}else{msj = "\n LIMIT OF CONSUMERS REACHED";}
		return msj;
	}
	/**
	 * addProducer : adds a producer on the next empty possition of the producer array
	 * @param name : name of the producer
	 * @param date : Date which producer is registered
	 * @param type : Type of the producer
	 * @return msj : Confirmation message
	 */
	public String addProducer(String name, String date, String type){
		String msj = "";
		int pos = -1;
		pos = searchProducerEmptyPos();
		if(pos!=-1){
			switch(type){
				case "1":
					producers[pos] = new Artist(name, date);
					msj = "\n ARTIST REGISTERED SUCCESFULLY";
					break;
				case "2":
					producers[pos] = new Creator(name, date);
					msj = "\n CREATOR REGISTERED SUCCESFULLY";
					break;
			}
		}else{msj = "\n LIMIT OF PRODUCERS REACHED";}
		return msj;
	}
	/**
	 * searchConsumerEmptyPos : identifies the next empty possition of the consumer array
	 * @return pos : possition of the empty spot in the array
	 */
	public int searchConsumerEmptyPos(){
		int pos = -1;
		boolean isFound = false;
		for(int i = 0; i<TOTAL_CONSUMERS && isFound==false; i++){
			if(consumers[i]==null){
				pos = i;
				isFound=true;
			}
		}
		return pos;
	}
	/**
	 * searchProducerEmptyPos : identifies the next empty possition of the producer array
	 * @return pos : possition of the empty spot in the array
	 */
	public int searchProducerEmptyPos(){
		int pos = -1;
		boolean isFound = false;
		for(int i = 0; i<TOTAL_PRODUCERS && isFound==false; i++){
			if(producers[i]==null){
				pos = i;
				isFound=true;
			}
		}
		return pos;
	}
	/**
	 * validateProducerType : identifies what type of producer is being worked with
	 * @param pos : possition of the producer being identified
	 * @return int : hold the producer type
	 */
	public int validateProducerType(int pos){
		int userType;
		if(producers[pos] instanceof Artist){
			userType = 1;
		}else{userType = 2;}
		return userType;
	}
	/**
	 * searchAudio : searchs for an specific audio
	 * @param name : identifier of the Audio
	 * @return int : possition of the audio being searched
	 */
	public int searchAudio(String name){
		int pos = -1;
		boolean isFound = false;
		for(int i = 0; i<TOTAL_AUDIO && isFound == false; i++){
			if(audio[i]!=null){
				if(audio[i].getName().equals(name)){
					pos = i;
					isFound = true;
				}
			}
		}
		return pos;
	}
	/**
	 * addSong : adds a song in the next empty possition of the array
	 * @param name : name of the song
	 * @param duration : duration of the song
	 * @param album : Album of the song
	 * @param audioType : Genre of the song
	 * @param value : monetary value of the song
	 * @return String : confirmation message
	 */
	public String addSong(String name,double duration,String producerName,String album,String audioType,double value){
		String msj = "";
		int pos = -1;
		pos = searchAudioEmptyPos();
		if(pos!=-1){
			audio[pos] = new Song(name,duration,producerName,album,audioType,value);
			msj = "\n SONG REGISTERED SUCCESFULLY";
		}else{msj = "\n LIMIT OF AUDIO REACHED";}
		return msj;
	}
	/**
	 * addPodcast : adds a podcast to the next empty possition of the array
	 * @param name : name of the podcast
	 * @param duration : duration of the podcast
	 * @param audioType : Category of the podcast
	 * @param description : description of the podcast
	 * @return String : confirmation Message
	 */
	public String addPodcast(String name,double duration,String producerName, String audioType, String description){
		String msj = "";
		int pos = -1;
		pos = searchAudioEmptyPos();
		if(pos!=-1){
			audio[pos] = new Podcast(name,duration,producerName,audioType,description);
			msj = "\n PODCAST REGISTERED SUCCESFULLY";
		}
		return msj;
	}
	/**
	 * searchAudioEmptyPos : searh for the next empty possition in the audio array
	 * @return int : empty possition of the audio array
	 */
	public int searchAudioEmptyPos(){
		int pos = -1;
		boolean isFound = false;
		for(int i = 0; i<TOTAL_AUDIO && isFound==false; i++){
			if(audio[i]==null){
				pos = i;
				isFound=true;
			}
		}
		return pos;
	}
	/**
	 * showAudioList : Shows a list of the registered audio
	 * @return String : List of the registered audio
	 */
	public String showAudioList(){
		String msj = "";

		for(int i = 0; i<TOTAL_AUDIO; i++){
			if(audio[i]!=null){
				msj += audio[i].showAudio();
			}
		}
		if(msj.equals("")){msj = "\n NO AUDIOS REGISTERED YET....";}
		return msj;
	}

	public String songList(){
		String msj = "";
		Song song = null;
		for(int i =0; i<TOTAL_AUDIO; i++){
			if(audio[i]!=null && audio[i] instanceof Song){
				song = (Song)audio[i];
				msj += "\n"+
				"  NAME : "+song.getName()+"\n"+
				" GENRE : "+song.getGenre()+"\n"+
				" VALUE : $"+song.getValue()+"\n";
			}
		}
		return msj;
	}

	/**
	 * addPlayList : adds a playlist in the next possition of the array
	 * @param name : name of the playlist
	 * @param consumerPos : Possition of the consumer creating the playlist
	 * @param possitions : possitions of the songs added by the consumer
	 * @param numberSong : number of songs added by the consumer
	 * @return String : confirmation message
	 */
	public String addPlayList(String name, int consumerPos, int[] possitions, int numberAudio){
		String msj = "";
		String id = "";
		int cont = 0;
		int sw = 0;
		int[][] mtx = new int[6][6];
		Audio[] list = new Audio[1000];
		if(consumers[consumerPos] instanceof StandardUser){
			if(consumers[consumerPos].getPlaylistNumber()>=20){sw = 1;}
		}
		if(sw == 0){
			for(int i = 0; i<numberAudio; i++){
				list[i] = audio[possitions[i]];
				if(audio[possitions[i]] instanceof Song){cont++;}
			}
			if(cont == numberAudio){
				sw = 1;
			}else if(cont == 0){
				sw = 2;
			}else{sw = 3;}
			mtx = generateMatrix(6,6);
			id = setPlaylistId(sw,mtx);
			consumers[consumerPos].addPlayList(name, list, id, mtx);
			consumers[consumerPos].setPlaylistNumber();
			msj = "\n PLAYLIST CREATED SUCCESFULLY";
		}else{msj = "\n STANDARD USER REACHED LIMIT AMOUNT OF PLAYLIST";}
		return msj;
	}
	public int[][] generateMatrix(int rows, int collumns){
		int[][] matrix = new int[rows][collumns];
		for(int i = 0; i<rows; i++){
			for(int j = 0; j<collumns; j++){
				matrix[i][j]=(int)Math.floor(Math.random()*10);
			}
		}
		return matrix;
	}
	/**
	 * setPlaylistId : creates a random generated ID for the Playlist
	 * @return String : Auto generated Id
	 */
	public String setPlaylistId(int listCase,int[][] mtx){
		String id = "";
		
		switch(listCase){
			case 1:
				for(int i = 5; i>0; i--){
					id += ""+mtx[i][0];
				}
				for(int i=0;i<6;i++){
					id+=""+mtx[i][i];
				}
				for(int i = 4; i>=0; i--){
					id += ""+mtx[i][5];
				}
				break;
			case 2:
				for(int i=0;i<3;i++){
					id+=""+mtx[0][i];
				}
				for(int i=1;i<6;i++){
					id+=""+mtx[i][2];
				}
				for(int i=5;i>0;i--){
					id+=""+mtx[i][3];
				}
				for(int i=3;i<6;i++){
					id+=""+mtx[0][i];
				}

				break;
			case 3:
				for(int i = 5; i>=0; i--){
					for(int j = 5; j>=0; j--){
						if( (i+j)%2 !=0 && i+j > 1){
							id += ""+mtx[i][j];
						}
					}
				}
				break;
		}
				
		return id;
	}
	public String showPlaylistName(int consumerPos){
		String msj = "";
		PlayList[] playlist = consumers[consumerPos].getPlaylist();
		for(int i=0; i<consumers[consumerPos].getPlaylistNumber();i++){
			msj += "\n PLAYLIST : "+playlist[i].getName()+
				   "\n     CODE : "+playlist[i].getId()+"\n";
		}
		return msj;
	}
	public String showPlaylistMatrix(int consumerPos){
		String msj = "";
		PlayList[] playlist = consumers[consumerPos].getPlaylist();
		for(int i=0; i<consumers[consumerPos].getPlaylistNumber();i++){
			msj += "\n PLAYLIST : "+playlist[i].getName()+
				   "\n     CODE : "+playlist[i].getId()+
				   "\n   MATRIX : \n"+
				   playlist[i].getMatrix()+"\n";
		}
		return msj;
	}
	public int searchPlaylist(int consumerPos, String name){
		int pos = -1;
		boolean isFound = false;
		PlayList[] playlist = consumers[consumerPos].getPlaylist();
		for(int i=0; i<consumers[consumerPos].getPlaylistNumber() && isFound==false;i++){
			if(playlist[i].getName().equals(name)){
				isFound=true;
				pos = i;
			}
		}
		return pos;
	}
	public String showPlaylist(int consumerPos,int listPos){
		String msj = "";
		PlayList[] playlist = consumers[consumerPos].getPlaylist();
		Audio[] audioList = playlist[listPos].getAudio();
		for(int i=0; i<playlist[listPos].getAudioNumber();i++){
			msj += audioList[i].showAudio();
		}
		return msj;
	}
	public int searchAudioInList(String name, int consumerPos, int listPos){
		PlayList[] playlist = consumers[consumerPos].getPlaylist();
		Audio[] audioList = playlist[listPos].getAudio();
		int pos = -1;
		boolean isFound = false;
		for(int i = 0; i<playlist[listPos].getAudioNumber() && isFound==false;i++){
			if(audioList[i].getName().equals(name)){
				pos = i;
				isFound = true;
			}
		}
		return pos;
	}
	public String addAudioToPlaylist(int audioPos,int consumerPos,int listPos){
		String msj = "";
		Audio newAudio = audio[audioPos];
		msj = consumers[consumerPos].addAudioToPlaylist(newAudio, listPos);
		return msj;
	}
	public String removeAudioFromPlaylist(int audioPos,int consumerPos,int listPos){
		String msj = "";
		msj = consumers[consumerPos].removeAudioFromPlaylist(audioPos,listPos);
		return msj;
	}
	public String buySong(int audioPos){
		String msj = "";
		Song song = null;
		if(audio[audioPos] instanceof Song){
			song = (Song)audio[audioPos];
			song.setSells();
			song.setSellValue();
			msj = "\n SOLD SUCCESFULLY";
		}else{msj = "\n THIS IS NOT A SONG YOU BULB";}
		return msj;
	}
	public void setReproductions(String[] names, int cont){
		int pos = -1;
		int producerPos=-1;
		String producerName = "";
		for(int i=0;i<cont;i++){
			pos = searchAudio(names[i]);
			audio[pos].setReproductions();
			producerPos = searchProducerUser(audio[pos].getProducer());
			producers[producerPos].setReproductions();
		}
	}
	public String audioTypeReproductions(){
		String msj = "";
		int podcastRep = 0;
		int songRep = 0;
		for(int i=0; i<audio.length;i++){
			if(audio[i]!=null){
				if(audio[i] instanceof Song){
					songRep+=audio[i].getReproductions();
				}else if(audio[i] instanceof Podcast){
					podcastRep+=audio[i].getReproductions();
				}
			}
		}
		msj = "\n    TOTAL SONG REPRODUCTIONS : "+songRep+
			  "\n TOTAL PODCAST REPRODUCTIONS : "+podcastRep+"\n";
		return msj;
	}
	public String mostHeardGenre(){
		String msj = "";
		Genre[] genre = {Genre.ROCK,Genre.POP,Genre.TRAP,Genre.HOUSE};
		Song song = null;
		int contReps = 0;
		int maxCount = 0;

		for(int j=0;j<genre.length;j++){
			contReps=0;
			for(int i=0;i<audio.length;i++){
				if(audio[i]!=null && audio[i] instanceof Song){
					song = (Song)audio[i];
					if(song.getGenre().equals(genre[j])){
						contReps += song.getReproductions();
					}
				}
			}
			if(contReps>maxCount){
				maxCount = contReps;
				msj = "\n MOST REPRODUCED GENRE :\n"+
					  "\n         GENRE :"+genre[j]+
					  "\n REPRODUCTIONS : "+contReps;
			}
		}
		if(maxCount==0){msj = "\n NO SONGS REPRODUCED YET....";}
		return msj;
	}
	public String mostHeardCategory(){
		String msj = "";
		Category[] category = {Category.POLITICS,Category.ENTERTAINMENT,Category.VIDEOGAMES,Category.FASHION};
		Podcast podcast = null;
		int cont = 0;
		int maxCount = 0;

		for(int j=0;j<category.length;j++){
			cont=0;
			for(int i=0;i<audio.length;i++){
				if(audio[i]!=null && audio[i] instanceof Podcast){
					podcast = (Podcast)audio[i];
					if(podcast.getCategory().equals(category[j])){
						cont += podcast.getReproductions();
					}
				}
			}
			if(cont>maxCount){
				maxCount = cont;
				msj = "\n MOST REPRODUCED Category :\n"+
					  "\n      CATEGORY :"+category[j]+
					  "\n REPRODUCTIONS : "+cont;
			}
		}
		if(maxCount==0){msj = "\n NO PODCAST REPRODUCED YET....";}
		return msj;
	}
	public String topArtists(){
		String msj = "\n  TOP 5 ARTISTS \n";
		int artistNum = 0;
		int maxCount = -1;
		int pos = -1;
		int[] possitions = new int[1000];

		for(int i=0;i<producers.length;i++){
			if(producers[i]!=null && producers[i] instanceof Artist){
				possitions[artistNum]= i;
				artistNum++;
			}
		}
		if(artistNum>0){
			for(int j =0;j<5 && j<artistNum ;j++){
				maxCount = -1;
				for(int i =0;i<artistNum;i++){
					if(possitions[i]!=-1 && producers[possitions[i]].getReproductions()>=maxCount){
						pos = possitions[i];
						maxCount = producers[pos].getReproductions();
					}
				}
				msj += "\n ("+(j+1)+")      NAME : "+producers[pos].getName()+
					   "\n REPRODUCTIONS : "+producers[pos].getReproductions()+"\n";
				possitions[pos] = -1;
			}
		}else{msj += "\n NO ARTIST REGISTERED YET....";}

		return msj;
	}
	public String topCreators(){
		String msj = "\n  TOP 5 CREATORS \n";
		int creatorNum = 0;
		int maxCount = -1;
		int pos = -1;
		int[] possitions = new int[1000];

		for(int i=0;i<producers.length;i++){
			if(producers[i]!=null && producers[i] instanceof Creator){
				possitions[creatorNum]= i;
				creatorNum++;
			}
		}
		if(creatorNum>0){
			for(int j =0;j<5 && j<creatorNum;j++){
				maxCount = -1;
				for(int i =0;i<creatorNum;i++){
					if(possitions[i]!=-1 && producers[possitions[i]].getReproductions()>=maxCount){
						pos = possitions[i];
						maxCount = producers[pos].getReproductions();
					}
				}
				msj += "\n ("+(j+1)+")      NAME : "+producers[pos].getName()+
					   "\n REPRODUCTIONS : "+producers[pos].getReproductions();
				possitions[pos] = -1;
			}
		}else{msj += "\n NO CREATOR REGISTERED YET....";}

		return msj;
	}
	public String topSongs(){
		String msj = "\n  TOP 10 SONGS \n";
		int[] possitions = new int[1000];
		Song song = null;
		int cont = 0;
		int pos = -1;
		int maxCont = -1;

		for(int i=0;i<audio.length;i++){
			if(audio[i]!=null && audio[i] instanceof Song){
				possitions[cont]= i;
				cont++;
			}
		}

		if(cont>0){
			for(int i=0;i<10 && i<cont;i++){
				maxCont = -1;
				for(int j=0;j<cont;j++){
					if(possitions[j]!=-1 && audio[possitions[j]].getReproductions() >= maxCont){
						maxCont = audio[possitions[j]].getReproductions();
						pos = possitions[j];
					}
				}
				song = (Song)audio[pos];
				msj += "\n ("+(i+1)+")      NAME : "+song.getName()+
					   "\n         GENRE : "+song.getGenre()+
					   "\n REPRODUCTIONS : "+song.getReproductions()+"\n";
				possitions[pos] = -1;
			}
		}else{msj += "\n NO SONGS REGISTERED YET...";}
			
		return msj;
		
	}
	public String topPodcasts(){
		String msj = "\n  TOP 10 PODCAST \n";
		int[] possitions = new int[1000];
		Podcast podcast = null;
		int cont = 0;
		int pos = -1;
		int maxCont = -1;
		for(int i=0;i<audio.length;i++){
			if(audio[i]!=null && audio[i] instanceof Podcast){
				possitions[cont]= i;
				cont++;
			}
		}
		if(cont>0){
			for(int i=0;i<10 && i<cont;i++){
				maxCont = -1;
				for(int j=0;j<cont;j++){
					if(possitions[j]!=-1 && audio[possitions[j]].getReproductions() >= maxCont){
						maxCont = audio[possitions[j]].getReproductions();
						pos = possitions[j];
					}
				}
				podcast = (Podcast)audio[pos];
				msj += "\n ("+(i+1)+")      NAME : "+podcast.getName()+
					   "\n      CATEGORY : "+podcast.getCategory()+
					   "\n REPRODUCTIONS : "+podcast.getReproductions()+"\n";
				possitions[pos] = -1;
			}
		}else{msj += "\n NO PODCASTS REGISTERED YET...";}	
		return msj;
	}
	public String genreSells(){
		String msj = "";
		Genre[] genre = {Genre.ROCK,Genre.POP,Genre.TRAP,Genre.HOUSE};
		Song song = null;
		int cont = 0;
		int sells = 0;
		int sellValue = 0;

		for(int i=0;i<genre.length;i++){
			sells = 0;
			sellValue = 0;
			cont =0;
			for(int j=0; j<audio.length;j++){
				if(audio[j]!=null && audio[j] instanceof Song){
					song = (Song)audio[j];
					if(song.getGenre().equals(genre[i])){
						cont++;
						sells += song.getSells();
						sellValue += song.getSellValue();
					}
				}
			}
			msj += "\n      "+genre[i]+"\n"+
				   "\n     "+genre[i]+" SONGS : "+cont+
				   "\n            SELLS : "+sells+
				   "\n TOTAL SELL VALUE : $"+sellValue+"\n";
				
		}
		return msj;
	}
	public String mostSoldSong(){
		String msj = "\n MOST SOLD SONG \n";
		Song song = null;
		int maxSells = -1;
		int pos = -1;
		for(int i=0; i<audio.length;i++){
			if(audio[i]!=null && audio[i] instanceof Song){
				song = (Song)audio[i];
				if(song.getSells()>maxSells){
					maxSells = song.getSells();
					pos = i;
				}
			}
		}
		if(maxSells>0){
			song = (Song)audio[pos];
			msj += "\n        NAME : "+song.getName()+
				   "\n       SELLS : "+song.getSells()+
				   "\n TOTAL VALUE : $"+song.getSellValue()+"\n";
		}else{msj = "\n NO SONGS SOLD YET...";}
		return msj;
	}


}