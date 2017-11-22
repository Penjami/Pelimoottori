package fi.tamk.tikoot.pelimoottori;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * This class can be used to play longer sound files or music in game.
 *
 * @author Penjami Rantakangas
 * @version 1.2
 * @since 1.8
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
     * Gets the music file as a media object.
     *
     * @return Media file that contains the music file.
     */
    public Media getMusic() {
        return music;
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
