package fi.tamk.tikoot.pelimoottori;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

/**
 * Created by Penjami on 13.11.2017.
 */
public class Sound {

    private Media sound;

    /**
     * Constructor for a sound object.
     *
     * @param fileLocation String that contains the location to the sound file.
     */
    public Sound(String fileLocation) {
        setSound(fileLocation);
    }

    /**
     * Plays the sound once.
     */
    public void play() {
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    /**
     * sets the sound file and creates a mediaplayer to play the sound.
     *
     * @param fileLocation String that contains the location to the sound file.
     */
    public void setSound(String fileLocation) {
        sound = new Media(new File(fileLocation).toURI().toString());
    }
}
