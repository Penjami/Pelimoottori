package fi.tamk.tikoot.Game;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

/**
 * Created by Penjami on 13.11.2017.
 */
public class Sound {

    private Media sound;

    public Sound(String fileLocation) {
        setSound(fileLocation);
    }

    public void play() {
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    public void setSound(String fileLocation) {
        sound = new Media(new File(fileLocation).toURI().toString());
    }
}
