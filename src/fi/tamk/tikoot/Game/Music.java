package fi.tamk.tikoot.Game;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * Created by Penjami on 13.11.2017.
 */
public class Music {

    private Media music;
    private MediaPlayer mediaPlayer;

    public Music(String fileLocation) {
        setMusic(fileLocation);
    }

    public void play() {
        mediaPlayer.play();
    }

    public void setMusic(String fileLocation) {
        music = new Media(new File(fileLocation).toURI().toString());
        mediaPlayer = new MediaPlayer(music);
    }

    public boolean isPlaying() {
        if(mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            return true;
        } else {
            return false;
        }
    }


}
