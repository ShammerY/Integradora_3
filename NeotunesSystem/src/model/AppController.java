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
	public String addSong(String name,double duration,String album,String audioType,double value){
		String msj = "";
		int pos = -1;
		pos = searchAudioEmptyPos();
		if(pos!=-1){
			audio[pos] = new Song(name,duration,album,audioType,value);
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
	public String addPodcast(String name,double duration, String audioType, String description){
		String msj = "";
		int pos = -1;
		pos = searchAudioEmptyPos();
		if(pos!=-1){
			audio[pos] = new Podcast(name,duration,audioType,description);
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
				if(audio[i] instanceof Song){
					msj += audio[i].showAudio();
				}else if(audio[i] instanceof Podcast){
					msj += audio[i].showAudio();
				}
			}
		}
		if(msj.equals("")){msj = "\n NO AUDIOS REGISTERED YET.....";}
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
	public String addPlayList(String name, int consumerPos, int[] possitions, int numberSong){
		String msj = "";
		String id = "";
		int number = 0;
		int sw = 0;
		String[] playList = new String[numberSong];
		if(consumers[consumerPos] instanceof StandardUser){
			number = consumers[consumerPos].getPlaylistNumber();
			if(number>=20){msj = "\n STANDARD USER REACHED LIMIT AMOUNT OF PLAYLIST";sw = 1;}
		}
		if(sw == 0){
			for(int i = 0; i<numberSong; i++){
				playList[i] = audio[possitions[i]].getName();
				
			}
			id = setPlaylistId();
			consumers[consumerPos].addPlayList(name, playList, id);
			msj = "\n PLAYLIST CREATED SUCCESFULLY";
		}
		return msj;
	}
	/**
	 * setPlaylistId : creates a random generated ID for the Playlist
	 * @return String : Auto generated Id
	 */
	public String setPlaylistId(){
		String id = "";
		double matriz[][] = new double[6][6];
		for(int i = 0; i<6; i++){
			for(int j = 0; j<6; j++){
				matriz[i][j]=Math.floor(Math.random()*10);
			}
		}
		for(int i = 5; i>=0; i--){
			for(int j = 5; j>=0; j--){
				if(Math.floor((i+j)/2)-(i+j)/2!=0){
					id += ""+matriz[i][j];
				}
				
			}
		}
		return id;
	}
}