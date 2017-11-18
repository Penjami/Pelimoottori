package fi.tamk.tikoot.pelimoottori;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * Created by Penjami on 13.11.2017.
 */
public class Music {

    private Media music;
    private MediaPlayer mediaPlayer;

    /**
     * Constructor for a music object.
     *
     * @param fileLocation String that contains the location to the music file.
     */
    public Music(String fileLocation) {
        setMusic(fileLocation);
    }

    /**
     * Plays the music.
     */
    public void play() {
        mediaPlayer.play();
    }

    /**
     * sets the music file and creates a mediaplayer to play the music.
     *
     * @param fileLocation String that contains the location to the music file.
     */
    public void setMusic(String fileLocation) {
        music = new Media(new File(fileLocation).toURI().toString());
        mediaPlayer = new MediaPlayer(music);
    }

    /**
     * Used to check if the music is playing or not.
     *
     * @return true or false depending on the current state of the music object.
     */
    public boolean isPlaying() {
        return mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING;
    }
}
