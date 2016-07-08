package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;

import java.io.File;

/**
 * Created by io on 08/07/2016.
 */
public class Mp4Converter extends FfmpegConnector {

    private File input;
    private File destination;

    public Mp4Converter(Context context,File input,File destination){
        super(context);
        this.input=input;
        this.destination=destination;
    }

    @Override
    public String getCommand() {
        String cmd="-y -i "+
                input.getAbsolutePath()+" "+
                destination.getAbsolutePath();
        return cmd;
    }
}
