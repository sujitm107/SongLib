package sample;

public class SongDetail {

    String artist;
    String album;
    String year;
    String song;

    public SongDetail(String artist, String album, String year, String song){
        this.artist = artist;
        this.album = album;
        this.year = year;
        this.song = song;
    }

    public String toString(){

        return "Song: "+artist+", Artist: "+artist+", Album: "+album+", Year: "+year;
    }
}
