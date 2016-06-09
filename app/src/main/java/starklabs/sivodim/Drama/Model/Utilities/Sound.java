package starklabs.sivodim.Drama.Model.Utilities;

import android.media.MediaPlayer;

import java.io.File;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public abstract class Sound {
    private String path;
    private File audio;
    MediaPlayer mediaPlayer=null;

    public Sound(String path){
        this.path=path;
        audio=new File(path);
        if (audio.length()>maxSize())audio=null;
    }

    public File getAudio(){
        return audio;
    }

    public void pause(){
        if(mediaPlayer!=null){
            mediaPlayer.pause();
        }
    }

    public void stop(){
        if(mediaPlayer!=null){
            mediaPlayer.stop();
        }
    }

    public void play(){
        if(isDefined()){
            if (mediaPlayer==null){
            mediaPlayer = new MediaPlayer();
            }
            try {
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract long maxSize();

    public boolean isDefined(){
        return audio.exists();
    }
}
