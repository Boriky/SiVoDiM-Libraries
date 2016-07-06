package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.github.hiteshsondhi88.libffmpeg.FFmpegExecuteResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;

import java.io.File;

import starklabs.sivodim.Drama.Model.Utilities.SpeechSound;
import starklabs.sivodim.Drama.View.ListChapterActivity;
import starklabs.sivodim.Drama.View.ListSpeechesActivity;

/**
 * Created by io on 05/07/2016.
 */
public class ExportPreview extends AudioExport {
    private SpeechSound speechSound;

    public ExportPreview(TextView textView,SpeechSound speechSound){
        super(textView);
        this.speechSound=speechSound;
    }


    @Override
    protected void finalizeExport(final Context context){
        String name=screenplay.getTitle().replace(" ","_");
        final File file=new File(context.getFilesDir(),"concatenation"+name+".wav");
        final File destination=new File(context.getFilesDir(),name+".mp3");
        Mp3Converter mp3Converter=new Mp3Converter(context,file,destination);
        try {
            mp3Converter.exec(new FFmpegExecuteResponseHandler() {
                @Override
                public void onSuccess(String message) {

                }

                @Override
                public void onProgress(String message) {
                    System.out.println(message);
                }

                @Override
                public void onFailure(String message) {

                }

                @Override
                public void onStart() {
                    if(feedback!=null)feedback.setText("Converto in mp3..");
                }

                @Override
                public void onFinish() {
                    System.out.println("FINITO FFMPEG!!!!!!");
                    Intent intent=new Intent(context,ListSpeechesActivity.class);
                    context.startActivity(intent);
                    speechSound.play();
                }
            });
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
        }
    }
}
