package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;

import java.io.File;

/**
 * Created by Francesco Bizzaro on 28/05/2016.
 */
public class AudioMixer extends FfmpegConnector {
    /**
     * File attribute that represents the main audio track (determines exported audio file length)
     */
    File primaryFile;
    /**
     * File attribute that represents the secondary audio track
     */
    File secondaryFile;
    /**
     * File attribute that represents the completed file destination path
     */
    File destination;

    /* ----- CONSTRUCTOR ----- */

    /**
     * Create an AudioMixer object
     * @param context
     * @param primaryFile
     * @param secondaryFile
     * @param destination
     */
    public AudioMixer(Context context, File primaryFile, File secondaryFile,File destination){
        super(context);
        this.primaryFile=primaryFile;
        this.secondaryFile=secondaryFile;
        this.destination=destination;
    }

    /* ----- UTILITIES METHODS ----- */
    /**
     * Generate and return command string from FFmpeg library
     * @return
     */
    @Override
    public String getCommand() {
        String cmd="-y -i " +
                primaryFile.getAbsolutePath() +
                " -i " +
                secondaryFile.getAbsolutePath() +
                " -filter_complex amix=inputs=2:duration=first:dropout_transition=3 " +
                destination.getAbsolutePath();
        return cmd;
    }

    /* ----- SETTER METHODS ----- */

    /**
     * Edit existing primary audio track or set new one
     * @param primaryFile
     */
    public void setPrimaryFile(File primaryFile){ this.primaryFile=primaryFile; }

    /**
     * Edit existing secondary audio track or set new one
     * @param secondaryFile
     */
    public void setSecondaryFile(File secondaryFile){ this.secondaryFile=secondaryFile; }

    /**
     * Edit existing file destination or set new one
     * @param destination
     */
    public void setDestination(File destination){ this.destination=destination; }
}
