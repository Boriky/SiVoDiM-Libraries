package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;

import java.io.File;

/**
 * Created by Francesco Bizzaro on 28/05/2016.
 */
public class Mp3Converter extends FfmpegConnector{
    File file;
    File destination;

    public Mp3Converter(Context context,File fileToConvert, File destination){
        super(context);
        this.file=fileToConvert;
        this.destination=destination;
    }

    public void setFile(File fileToConvert){
        this.file=fileToConvert;
    }

    public void setDestination(File destination){
        this.destination=destination;
    }

    @Override
    public String getCommand() {
        /** conversion wav -> mp3 **/
        String cmd="-y -i "+file.getAbsolutePath()+
               " -vn -ar 44100 -ac 2 -ab 192k -f mp3 " +
                destination.getAbsolutePath();
        return cmd;
    }
}
