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

    ArrayList<File> files;
    File destination;
    File listTxt;

    public VideoConcatenator(Context context,File destination){
        super(context);
        this.destination=destination;
        this.files=new ArrayList<>();
    }

    public void add(File file){
        files.add(file);
    }

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

    @Override
    public String getCommand() {
        createFileList();
        String cmd="-y -f concat -safe 0 -i "+listTxt.getAbsolutePath()
                +" -c copy "+destination.getAbsolutePath();
        return cmd;
    }
}
