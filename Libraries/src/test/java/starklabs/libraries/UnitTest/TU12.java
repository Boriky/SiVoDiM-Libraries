package starklabs.libraries.UnitTest;

/**
 * Created by GINO on 28/06/2016.
 */

import android.content.Context;
import android.test.InstrumentationTestCase;

import org.junit.Test;

import java.io.File;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.EngineManager.EngineImpl;
import starklabs.libraries.Model.Mivoq.MivoqTTSSingleton;
import starklabs.libraries.Model.Voice.Language;
import starklabs.libraries.Model.Voice.MivoqVoice;

/**
 * Test TU12 that tests the tts creation
 */
public class TU12 extends InstrumentationTestCase {
    @Test
    public void testTTS(){
        Context context=getInstrumentation().getContext();
        Engine engine= new EngineImpl(context);
        MivoqVoice voice=engine.createVoice("voice","male","it");
        MivoqTTSSingleton mivoqTTSSingleton=MivoqTTSSingleton.getInstance();
        try {
            mivoqTTSSingleton.synthesizeToFile("C:/Desktop/synth.wav",voice,"testo di prova");

        }catch (Exception e){

        }
        assertEquals(true,new File("C:/Desktop/synth.wav").exists());
    }
}
