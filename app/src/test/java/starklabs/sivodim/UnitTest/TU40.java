package starklabs.sivodim.UnitTest;

import android.app.Activity;
import android.content.Context;
import android.test.InstrumentationTestCase;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;

import starklabs.sivodim.Drama.Model.Screenplay.AudioConcatenator;
import starklabs.sivodim.Drama.Model.Screenplay.AudioMixer;

/**
 * Created by io on 07/07/2016.
 */
public class TU40 extends InstrumentationTestCase {
    @Test
    public void testAudioConcatenator(){
        Context context=getInstrumentation().getContext();
        File destination=new File(context.getFilesDir(),"");
        AudioConcatenator audioConcatenator=new AudioConcatenator(context,destination);

    }
}
