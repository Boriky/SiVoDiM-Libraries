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

    Vector<File> files;
    File destination;
    File listTxt;

    public AudioConcatenator(Context context, File destination){
        super(context);
        this.destination=destination;
    }

    public AudioConcatenator(Context context, Vector<File> files, File destination){
        this(context,destination);
        this.files=files;
    }

    public void addFile(File file){
        if(files==null){
            files=new Vector<File>();
        }
        files.add(file);
    }

    public void setDestination(File destination){
        this.destination=destination;
    }

    public void createFileList(){
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

    @Override
    public String getCommand() {
        createFileList();
        String cmd="-y -f concat -safe 0 -i " +
                listTxt.getAbsolutePath() +
                " -c copy " +
                destination.getAbsolutePath();
        return cmd;
    }
}
