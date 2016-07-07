package starklabs.libraries.IntegrationTest;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.test.mock.MockContext;

import org.junit.Test;

import java.io.File;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.EngineManager.EngineImpl;
import starklabs.libraries.Model.Voice.MivoqVoice;

/**
 * Created by Enrico on 07/07/16.
 */
public class TI9 extends InstrumentationTestCase {

    Context myContext;

    public void setUp() throws Exception {
        super.setUp();
        myContext = new MockContext();

        assertNotNull(myContext);

    }

    @Test
    public void testEngineManager() {

        Engine mEngine = new EngineImpl(myContext);
        MivoqVoice voice=mEngine.createVoice("myVoice","male","it");
        MivoqVoice newVoice= mEngine.createVoice("fede","female","fr");

        assertEquals(voice,mEngine.getVoiceByName("myVoice"));
        assertEquals(2,mEngine.getVoices().size());

        mEngine.removeVoice(1);
        assertEquals(1,mEngine.getVoices().size());
        assertNull(mEngine.getVoiceByName("fede"));

        final String path = "/Desktop/test";
        String text = "prova";

        mEngine.synthesizeToFile(path, "myVoice", "NONE", text, new Engine.Listener() {
            @Override
            public void onCompleteSynthesis() {
                File file=new File(path);
                assertEquals(true,file.exists());
            }
        });
        //trying to save the voices and load the voices;
        mEngine.save();

        mEngine.createVoice("enrico","male","de");
        assertEquals(2,mEngine.getVoices().size());

        mEngine.load();
        assertEquals(1,mEngine.getVoices().size());
    }
}
