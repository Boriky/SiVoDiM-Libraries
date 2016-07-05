package starklabs.sivodim.Drama.Model.Utilities;

import android.media.MediaPlayer;

import java.io.File;
import java.io.IOException;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public abstract class Sound {
    /**
     * The path to the sound
     */
    private String path;
    /**
     * A File that keeps the link to the audio file
     */
    private File audio;
    /**
     * A mediaPlayer's flag
     */
    private boolean onPause=false;
    /**
     * A MediaPlayer used for playing the audio file
     */
    MediaPlayer mediaPlayer=null;


    /**
     * Constructor which initialize the file after checking if the dimension is acceptable
     * @param path
     */
    public Sound(String path){
        this.path=path;
        //initialize the file
        audio=new File(path);
        //check the dimension and eventually destroy the file
        if (audio.length()>maxSize())audio=null;
    }

    /**
     * Return the audio file if it exists (eventually null)
     * @return
     */
    public File getAudio(){
        if(!audio.exists())return null;
        return audio;
    }

    /**
     * Pause the playing of the audio
     */
    public void pause(){
        if(mediaPlayer!=null){
            mediaPlayer.pause();
            onPause=true;
        }
    }

    /**
     * Pause the playing of the audio
     */
    public void pauseSound(){
        if(mediaPlayer!=null){
            mediaPlayer.pause();
        }
    }


    /**
     * Stop the playing of the audio
     */
    public void stop(){
        if(mediaPlayer!=null){
            mediaPlayer.stop();
        }
    }

    /**
     * Return the duration in milliseconds of the audio
     * @return
     */
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

    /**
     *
     * @param completionListner
     */
    public void play(MediaPlayer.OnCompletionListener completionListner){
        if(isDefined()){
            if (mediaPlayer==null){
                mediaPlayer = new MediaPlayer();
            }
            try {
                if(!onPause){
                    mediaPlayer.setDataSource(path);
                    mediaPlayer.prepare();
                }
                mediaPlayer.setOnCompletionListener(completionListner);
                mediaPlayer.start();
                onPause=false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
