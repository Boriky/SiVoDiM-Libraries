package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;

import java.io.File;

/**
 * Created by io on 02/07/2016.
 */
public class AudioVideoMixer extends FfmpegConnector {

    private File video;
    private File audio;
    private File destination;

    public AudioVideoMixer(Context context, File video,File audio,File destination){
        super(context);
        this.video=video;
        this.audio=audio;
        this.destination=destination;
    }


    @Override
    public String getCommand() {
        /** create a loop video from an image **/
        String cmd="-y -i "+video.getAbsolutePath()
                +" -i "+audio.getAbsolutePath()
                +" -codec copy -shortest "+destination.getAbsolutePath();
        return cmd;
    }
}
