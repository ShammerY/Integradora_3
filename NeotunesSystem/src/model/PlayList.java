package model;

public class PlayList{
	private String name;
	private Audio[] audio;
	private String id;
	private String matrix;

	public PlayList(String aName, Audio[] playlist, String aId, int[][] aMatrix){
		audio = new Audio[1000];
		name = aName;
		audio = playlist;
		id = aId;
		setMatrix(aMatrix);
	}

	public String getName(){
		return name;
	}
	public String getId(){
		return id;
	}
	public Audio[] getAudio(){
		return audio;
	}
	public int getAudioNumber(){
		int cont = 0;
		for(int i=0;i<audio.length;i++){
			if(audio[i]!=null){cont++;}
		}
		return cont;
	}
	public String getMatrix(){
		return matrix;
	}
	public void setMatrix(int[][] aMatrix){
		matrix = "";
		for(int i=0;i<aMatrix.length;i++){
			for(int j=0;j<aMatrix.length;j++){
				matrix += "["+aMatrix[i][j]+"]";
			}
			if(i!= (aMatrix.length - 1)){
				matrix += "\n";
			}
		}
	}
	public String addAudioToPlaylist(Audio newAudio){
		String msj = "";
		boolean isFound = false;
		int pos = -1;
		for(int i = 0; i<audio.length && isFound==false;i++){
			if(audio[i]==null){
				isFound = true;
				audio[i] = newAudio;
				msj = "\n AUDIO LISTED";
			}
		}
		if(isFound==false){msj = "\n PLAYLIST IS TOO HEAVY";}
		return msj;
	}
	public String removeAudioFromPlaylist(int audioPos){
		String msj = "";
		boolean isFound = false;
		for(int i=audioPos;i<audio.length && isFound==false;i++){
			if(audio[i+1]!=null){
				audio[i] = audio[i+1];
			}else{
				audio[i] = null;
				isFound = true;
			}
		}
		msj = "\n AUDIO REMOVED SUCCESFULLY";
		return msj;
	}


}