package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;
import android.widget.TextView;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public class VideoExport extends ExportAlgorithm {


    private TextView feedback;
    private AudioExport audioExport;

    public VideoExport(TextView feedback){
        this.feedback=feedback;
        audioExport=new AudioExport(feedback);
    }

    private void exportAudio(Context context){
        audioExport.export(context);
    }

    private void concatenateImages(){

    }


    private void finalizeExport(){

    }

    @Override
    public void export(Context context) {

    }
}
