package starklabs.sivodim.UnitTest;

/**
 * Created by GINO on 27/06/2016.
 */

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.test.InstrumentationTestCase;

import com.github.hiteshsondhi88.libffmpeg.FFmpegExecuteResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;

import starklabs.sivodim.Drama.Model.Screenplay.AudioMixer;

import static org.junit.Assert.assertEquals;


/**
 * Test TU41 that tests AudioMixer functionality
 */
public class TU41 extends InstrumentationTestCase{
    @Test
    public void testAudioMixer(){
        Context context=getInstrumentation().getContext();
        File primary=new File("C:Desktop/prova.wav");
        File secondary=new File("C:Desktop/prova1.wav");
        final File destination=new File("C:Desktop/end.wav");

        AudioMixer audioMixer=new AudioMixer(context,primary,secondary,destination);
        audioMixer.setDestination(new File("C:Desktop/test/end.wav"));
        audioMixer.setPrimaryFile(new File("C:Desktop/test/prova.wav"));
        audioMixer.setSecondaryFile(new File("C:Descktop/test/prova1.wav") );
        File testD=new File("C:Desktop/test/end.wav");
        File testP=new File("C:Desktop/test/prova.wav");
        File testS=new File("C:Desktop/test/prova1.wav");
        try {
            audioMixer.exec(new FFmpegExecuteResponseHandler() {
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
                    assertEquals(true,destination.exists());

                }
            });
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
        }



    }
}
