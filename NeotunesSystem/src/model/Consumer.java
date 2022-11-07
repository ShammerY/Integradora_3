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
	public void addPlayList(String name, String[] playlist, String playlistId){
		playlists[playlistNumber] = new PlayList(name, playlist, playlistId);
		playlistNumber++;
	}

	public String getNickname(){
		return nickname;
	}
	public int getPlaylistNumber(){
		return playlistNumber;
	}

	

}