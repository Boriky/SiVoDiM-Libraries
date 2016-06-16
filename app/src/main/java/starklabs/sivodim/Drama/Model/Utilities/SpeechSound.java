package starklabs.sivodim.Drama.Model.Utilities;

import android.widget.MediaController;

import java.io.File;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public class SpeechSound extends Sound implements MediaController.MediaPlayerControl {
    private static final long maxSize=15728640L;

    public SpeechSound(String path){
        super(path);
    }

    @Override
    protected long maxSize() {
        return maxSize;
    }

    @Override
    public void start() {
        play();
    }

    @Override
    public int getDuration() {
        return 0;
    }

    @Override
    public int getCurrentPosition() {
        return 0;
    }

    @Override
    public void seekTo(int pos) {

    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return false;
    }

    @Override
    public boolean canSeekBackward() {
        return false;
    }

    @Override
    public boolean canSeekForward() {
        return false;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }
}
