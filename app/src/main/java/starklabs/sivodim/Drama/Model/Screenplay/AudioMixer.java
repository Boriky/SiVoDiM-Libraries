package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;

import java.io.File;

/**
 * Created by Francesco Bizzaro on 28/05/2016.
 */
public class AudioMixer extends FfmpegConnector {
    File primaryFile;
    File secondaryFile;
    File destination;

    public AudioMixer(Context context, File primaryFile, File secondaryFile,File destination){
        super(context);
        this.primaryFile=primaryFile;
        this.secondaryFile=secondaryFile;
        this.destination=destination;
    }

    public void setPrimaryFile(File primaryFile){
        this.primaryFile=primaryFile;
    }

    public void setSecondaryFile(File secondaryFile){
        this.secondaryFile=secondaryFile;
    }

    public void setDestination(File destination){
        this.destination=destination;
    }

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
}
