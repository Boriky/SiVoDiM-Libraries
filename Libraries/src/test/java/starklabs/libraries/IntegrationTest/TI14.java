package starklabs.libraries.IntegrationTest;

import android.content.Context;
import android.test.InstrumentationTestCase;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.EngineManager.EngineImpl;
import starklabs.libraries.Model.Voice.MivoqVoice;

/**
 * Created by Enrico on 07/07/16.
 */
public class TI14 extends InstrumentationTestCase {
    @Test
    public void testEngineManager() {

        Context myContext = getInstrumentation().getContext();
        Engine mEngine = new EngineImpl(myContext);
        MivoqVoice voice=mEngine.createVoice("myVoice","male","it");

        final String path = "/Desktop/test";
        String text = "prova";

        mEngine.synthesizeToFile(path, "myVoice", "NONE", text, new Engine.Listener() {
            @Override
            public void onCompleteSynthesis() {
                File file=new File(path);
                assertEquals(true,file.exists());
            }
        });

    }
}
