package starklabs.libraries.IntegrationTest;

import android.content.Context;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.EngineManager.EngineImpl;
import starklabs.libraries.Model.Mivoq.MivoqTTSSingleton;
import starklabs.libraries.Model.Voice.Effect;
import starklabs.libraries.Model.Voice.EffectImpl;
import starklabs.libraries.Model.Voice.Emotion;
import starklabs.libraries.Model.Voice.MivoqVoice;
/*
import static org.junit.Assert.assertEquals;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;
import android.test.ActivityInstrumentationTestCase2;
import android.support.test.InstrumentationRegistry;
*/

/**
 * Created by Enrico on 01/07/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest

public class TI15 {
    @Rule
    public ActivityTestRule mActivityRule = new
    MainActivity.class);


    @Test
    public void testMivoqEngine(){
        Context context = Mockito.mock(Context.class);
        context.getApplicationContext();
      //  private Context context = InstrumentationRegistry;


        Engine engine = new EngineImpl(context);

        MivoqTTSSingleton mivoqTTSSingleton = MivoqTTSSingleton.getInstance();
        mivoqTTSSingleton.setContext(context);

        MivoqVoice mivoqVoice = engine.createVoice("Fede","male","en");
        Effect rate = new EffectImpl("rate");
        rate.setValue("0.9");
        mivoqVoice.setEmotion(Emotion.Anger);
        mivoqVoice.setEffect(rate);

        byte[] array =  mivoqTTSSingleton.synthesizeText(mivoqVoice,"text"); //errore!

      //  assertEquals(false,array);

    }
}
