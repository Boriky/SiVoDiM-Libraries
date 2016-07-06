package starklabs.libraries.IntegrationTest;

import android.content.Context;
import android.test.InstrumentationTestCase;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.EngineManager.EngineImpl;
import starklabs.libraries.Model.Voice.MivoqVoice;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Enrico on 01/07/16.
 */
public class TI14 extends InstrumentationTestCase {
    @Test
    public void testMivoqEngine(){

        Context context = getInstrumentation().getContext();
        Engine engine = new EngineImpl(context);

        final File test = Mockito.mock(File.class);
        when(test.getAbsolutePath()).thenReturn("C:/Desktop.prova.wav");

        engine.synthesizeToFile(test.getAbsolutePath(),"","NONE", MivoqVoice.getSampleText("it"),
                new Engine.Listener() {
                    public void onCompleteSynthesis() {
                        assertNotEquals(0,test.length());
                    }
                });

    }
}
