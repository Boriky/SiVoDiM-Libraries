package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;

import java.io.File;

/**
 * Created by io on 02/07/2016.
 */
public class AudioVideoMixer extends FfmpegConnector {
    /**
     * File attribute that represents the video file
     */
    private File video;
    /**
     * File attribute that represents the audio file
     */
    private File audio;
    /**
     * File attribute that represents the completed video file
     */
    private File destination;

    /* ----- CONSTRUCTOR ----- */

    /**
     * Create an AudioVideoMixer object
     * @param context
     * @param video
     * @param audio
     * @param destination
     */
    public AudioVideoMixer(Context context, File video,File audio,File destination){
        super(context);
        this.video=video;
        this.audio=audio;
        this.destination=destination;
    }

    /* ----- UTILITIES METHODS ----- */

    /**
     * Generate and return command string from FFmpeg library
     * @return
     */
    @Override
    public String getCommand() {
        /** create a loop video from an image **/
        String cmd="-y -i "+video.getAbsolutePath()
                +" -i "+audio.getAbsolutePath()
                +" -codec copy -shortest "+destination.getAbsolutePath();
        return cmd;
    }
}
