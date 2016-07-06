package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by Francesco Bizzaro on 28/05/2016.
 */
public class AudioConcatenator extends FfmpegConnector {
    /**
     * Vector of files which represents the concatenation of audio files
     */
    Vector<File> files;
    /**
     * File that represents the audio file obtained by the concatenation of all the audio files contained in Vector
     */
    File destination;
    /**
     * Text file that contains the list of sounds
     */
    File listTxt;

    /* ----- CONSTRUCTORS ----- */

    /**
     * Create an AudioConcatenator object: initialize audio destination file
     * @param context
     * @param destination
     */
    public AudioConcatenator(Context context, File destination){
        super(context);
        this.destination=destination;
    }

    /**
     * Create an AudioConcatenator object: initialize audio files vector and audio destination file
     * @param context
     * @param files
     * @param destination
     */
    public AudioConcatenator(Context context, Vector<File> files, File destination){
        this(context,destination);
        this.files=files;
    }

    /* ----- UTILITIES METHODS ----- */

    /**
     * Add an audio file to the file Vector
     * @param file
     */
    public void addFile(File file){
        if(files==null){
            files=new Vector<File>();
        }
        files.add(file);
    }

    /**
     * Create the listTxt object and save the text file in device memory
     */
    public void createFileList(){
        if(files==null)return;
        try {
            listTxt = new File(destination.getParentFile(),"concatList.txt");
            if(!listTxt.exists())listTxt.createNewFile();
            FileWriter writer = new FileWriter(listTxt);
            for(File file:files){
                writer.append("file '"+file.getAbsolutePath()+"'\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generate and return command string from Ffmpeg library
     * @return
     */
    @Override
    public String getCommand() {
        createFileList();
        String cmd="-y -f concat -safe 0 -i " +
                listTxt.getAbsolutePath() +
                " -c copy " +
                destination.getAbsolutePath();
        return cmd;
    }


    /* ----- SETTER METHODS ----- */

    /**
     * Edit existing file destination or set new one
     * @param destination
     */
    public void setDestination(File destination){
        this.destination=destination;
    }
}
