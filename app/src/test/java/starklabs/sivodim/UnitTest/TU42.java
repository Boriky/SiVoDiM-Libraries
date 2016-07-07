package starklabs.sivodim.UnitTest;

/**
 * Created by GINO on 27/06/2016.
 */

import android.content.Context;
import android.test.InstrumentationTestCase;

import com.github.hiteshsondhi88.libffmpeg.FFmpegExecuteResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import starklabs.sivodim.Drama.Model.Screenplay.AudioConcatenator;
import starklabs.sivodim.Drama.Model.Screenplay.ExportAlgorithm;
import starklabs.sivodim.Drama.Model.Screenplay.VideoExport;

/**
 * Test TU42 that tests concatenateImages() method
 */
public class TU42 extends InstrumentationTestCase{
    @Test
    public void testConcatenateImages(){
        Context context=getInstrumentation().getContext();
        final File destination=new File(context.getFilesDir(),"concat.mp4");
        AudioConcatenator audioConcatenator=new AudioConcatenator(context,destination);
        assertNotNull(audioConcatenator);
        List<File> files=new ArrayList<>();
        files.add(new File(context.getFilesDir(),"parz0.jpg"));
        files.add(new File(context.getFilesDir(),"parz1.jpg"));
        files.add(new File(context.getFilesDir(),"parz2.jpg"));
        files.add(new File(context.getFilesDir(),"parz3.jpg"));
        files.add(new File(context.getFilesDir(),"parz4.jpg"));
        for (File file:files){
            audioConcatenator.addFile(file);
        }
        try {
            audioConcatenator.exec(new FFmpegExecuteResponseHandler() {
                @Override
                public void onSuccess(String message) {

                }

                @Override
                public void onProgress(String message) {

                }

                @Override
                public void onFailure(String message) {

                }

                @Override
                public void onStart() {

                }

                @Override
                public void onFinish() {
                    assertTrue(destination.exists());
                }
            });
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
        }

    }
}
