package fi.tamk.tikoot.pelimoottori.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

/**
 * This class can be used to play sound files in game.
 *
 * @author Penjami Rantakangas
 * @version 1.2
 * @since 1.8
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


    /**
     * Gets the sound file as a media object.
     *
     * @return Media file that contains the sound file.
     */
    public Media getSound() {
        return sound;
    }
}
