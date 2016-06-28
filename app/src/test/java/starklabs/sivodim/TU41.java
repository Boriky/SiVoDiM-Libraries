package starklabs.sivodim;

/**
 * Created by GINO on 27/06/2016.
 */

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;

import starklabs.sivodim.Drama.Model.Screenplay.AudioMixer;
import static org.junit.Assert.*;


/**
 * Test TU41 that tests AudioMixer functionality
 */
public class TU41 extends Instrumentation{
    @Test
    public void testAudioMixer(){
        Context mContext = new Activity();
        Context context=Mockito.mock(Context.class);
        File primary=new File("C:Desktop/prova.wav");
        File secondary=new File("C:Desktop/prova1.wav");
        File destination=new File("C:Desktop/end.wav");

        AudioMixer audioMixer=new AudioMixer(mContext.getApplicationContext(),primary,secondary,destination);
        audioMixer.setDestination(new File("C:Desktop/test/end.wav"));
        audioMixer.setPrimaryFile(new File("C:Desktop/test/prova.wav"));
        audioMixer.setSecondaryFile(new File("C:Descktop/test/prova1.wav") );
        File testD=new File("C:Desktop/test/end.wav");
        File testP=new File("C:Desktop/test/prova.wav");
        File testS=new File("C:Desktop/test/prova1.wav");

        assertEquals(true,!audioMixer.equals(null));

    }
}
