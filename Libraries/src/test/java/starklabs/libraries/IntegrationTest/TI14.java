package starklabs.libraries.IntegrationTest;

import android.content.Context;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.EngineManager.EngineImpl;
import starklabs.libraries.Model.Voice.MivoqVoice;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Enrico on 01/07/16.
 */
public class TI14 {
    @Test
    public void testMivoqEngine(){
        Context context = Mockito.mock(Context.class);
        context.getApplicationContext();
        //  private Context context = InstrumentationRegistry;

        Engine engine = new EngineImpl(context);

        final File test = Mockito.mock(File.class);
        when(test.getAbsolutePath()).thenReturn("/path/file");

        engine.synthesizeToFile(test.getAbsolutePath(),"","NONE", MivoqVoice.getSampleText("it"),
                new Engine.Listener() {
                    public void onCompleteSynthesis() {
                        assertNotEquals(0,test.length());
                    }
                });

    }
}
