package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by io on 02/07/2016.
 */
public class VideoConcatenator extends FfmpegConnector {
    /**
     *  Vector of files which represents the concatenation of video files
     */
    ArrayList<File> files;
    /**
     *  File that represents the video file obtained by the concatenation of all the video files contained in Vector
     */
    File destination;
    /**
     * Text file that contains the list of videos
     */
    File listTxt;

    /* ----- CONSTRUCTOR ----- */

    /**
     * Create a VideoConcatenator object: initialize audio destination file
     * @param context
     * @param destination
     */
    public VideoConcatenator(Context context,File destination){
        super(context);
        this.destination=destination;
        this.files=new ArrayList<>();
    }

    /* ----- UTILITIES METHODS ----- */

    /**
     * Add a video file to the file Vector
     * @param file
     */
    public void add(File file){
        files.add(file);
    }

    /**
     * Create the listTxt object and save the text file in device memory
     */
    public void createFileList(){
        try {
            listTxt = new File(destination.getParentFile(),"concatVideoList.txt");
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
        String cmd="-y -f concat -safe 0 -i "+listTxt.getAbsolutePath()
                +" -c copy "+destination.getAbsolutePath();
        return cmd;
    }
}
