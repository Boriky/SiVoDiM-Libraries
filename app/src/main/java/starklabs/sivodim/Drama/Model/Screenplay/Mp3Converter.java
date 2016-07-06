package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;

import java.io.File;

/**
 * Created by Francesco Bizzaro on 28/05/2016.
 */
public class Mp3Converter extends FfmpegConnector{
    /**
     * File attribute that represents the original file that is going to be converted in MP3
     */
    private File file;
    /**
     * File attribute that represents the completed MP3 file
     */
    private File destination;

    /* ----- CONSTRUCTOR ----- */

    /**
     * Create Mp3Converter object
     * @param context
     * @param fileToConvert
     * @param destination
     */
    public Mp3Converter(Context context,File fileToConvert, File destination){
        super(context);
        this.file=fileToConvert;
        this.destination=destination;
    }

    /* ----- UTILITIES METHODS ----- */

    /**
     * Generate and return command string from FFmpeg library
     * @return
     */
    @Override
    public String getCommand() {
        /** conversion wav -> mp3 **/
        String cmd="-y -i "+file.getAbsolutePath()+
                " -vn -ar 44100 -ac 2 -ab 192k -f mp3 " +
                destination.getAbsolutePath();
        return cmd;
    }

    /* ----- SETTER METHODS ----- */

    /**
     * Edit existing file ("file to convert") or set new one
     * @param fileToConvert
     */
    public void setFile(File fileToConvert){
        this.file=fileToConvert;
    }

    /**
     * Edit existing destination MP3 file or set new one
      * @param destination
     */
    public void setDestination(File destination){
        this.destination=destination;
    }
}
