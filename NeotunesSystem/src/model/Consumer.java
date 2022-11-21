package model;

public abstract class Consumer{
	
	protected String nickname;
	protected String id;
	protected String date;
	protected PlayList[] playlists;
	protected int playlistNumber;

	public Consumer(String nickname,String id, String date) {
		this.playlists = new PlayList[100];
		this.id = id;
		this.nickname = nickname;
		this.date = date;
		this.playlistNumber = 0;
	}
	public void addPlayList(String name, Audio[] songList, String playlistId,int[][] matrix){
		playlists[playlistNumber] = new PlayList(name, songList, playlistId, matrix);
	}
	public String getNickname(){
		return nickname;
	}
	public void setPlaylistNumber(){
		playlistNumber++;
	}
	public int getPlaylistNumber(){
		return playlistNumber;
	}
	public PlayList[] getPlaylist(){
		return playlists;
	}
	public String addAudioToPlaylist(Audio newAudio,int listPos){
		String msj = "";
		msj = playlists[listPos].addAudioToPlaylist(newAudio);
		return msj;
	}
	public String removeAudioFromPlaylist(int audioPos,int listPos){
		String msj = "";
		msj = playlists[listPos].removeAudioFromPlaylist(audioPos);
		return msj;
	}

	

}