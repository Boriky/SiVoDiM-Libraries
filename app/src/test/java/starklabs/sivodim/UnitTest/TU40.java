package starklabs.sivodim.UnitTest;

import android.app.Activity;
import android.content.Context;
import android.test.InstrumentationTestCase;

import com.github.hiteshsondhi88.libffmpeg.FFmpegExecuteResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import starklabs.sivodim.Drama.Model.Screenplay.AudioConcatenator;
import starklabs.sivodim.Drama.Model.Screenplay.AudioMixer;

/**
 * Created by Francesco Bizzaro on 07/07/2016.
 */
public class TU40 extends InstrumentationTestCase {
    @Test
    public void testAudioConcatenator(){
        Context context=getInstrumentation().getContext();
        final File destination=new File(context.getFilesDir(),"concat.wav");
        AudioConcatenator audioConcatenator=new AudioConcatenator(context,destination);
        assertNotNull(audioConcatenator);
        List<File> files=new ArrayList<>();
        files.add(new File(context.getFilesDir(),"parz0.wav"));
        files.add(new File(context.getFilesDir(),"parz1.wav"));
        files.add(new File(context.getFilesDir(),"parz2.wav"));
        files.add(new File(context.getFilesDir(),"parz3.wav"));
        files.add(new File(context.getFilesDir(),"parz4.wav"));
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
