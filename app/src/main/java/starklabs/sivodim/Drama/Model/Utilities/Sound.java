package starklabs.sivodim.Drama.Model.Utilities;

import android.media.MediaPlayer;

import java.io.File;
import java.io.IOException;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public abstract class Sound {
    private String path;
    private File audio;
    private boolean onPause=false;
    MediaPlayer mediaPlayer=null;

    public Sound(String path){
        this.path=path;
        audio=new File(path);
        if (audio.length()>maxSize())audio=null;
    }

    public File getAudio(){
        if(!audio.exists())return null;
        return audio;
    }

    public void pause(){
        if(mediaPlayer!=null){
            mediaPlayer.pause();
            onPause=true;
        }
    }

    public void pauseSound(){
        if(mediaPlayer!=null){
            mediaPlayer.pause();
        }
    }

    public void stop(){
        if(mediaPlayer!=null){
            mediaPlayer.stop();
        }
    }

    public long getDuration(){
        if(mediaPlayer==null){
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mediaPlayer.getDuration();
    }

    public void play(){
        if(isDefined()){
            if (mediaPlayer==null){
            mediaPlayer = new MediaPlayer();
            }
            try {
                if(!onPause){
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepare();
                }
                mediaPlayer.start();
                onPause=false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract long maxSize();

    public boolean isDefined(){
        if(audio==null)return false;
        return audio.exists();
    }
}
