package starklabs.libraries.IntegrationTest;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.util.Log;

import org.junit.Test;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.EngineManager.EngineImpl;
import starklabs.libraries.Model.Mivoq.MivoqTTSSingleton;
import starklabs.libraries.Model.Voice.Effect;
import starklabs.libraries.Model.Voice.EffectImpl;
import starklabs.libraries.Model.Voice.Emotion;
import starklabs.libraries.Model.Voice.MivoqVoice;

/**
 * Created by Enrico on 01/07/16.
 */

public class TI15 extends InstrumentationTestCase {
    @Test
    public void testMivoqEngine() {
        Context context = getInstrumentation().getContext();
        //context.getApplicationContext();
        //private Context context = InstrumentationRegistry;

        Engine engine = new EngineImpl(context);

        MivoqTTSSingleton mivoqTTSSingleton = MivoqTTSSingleton.getInstance();
        mivoqTTSSingleton.setContext(context);

        MivoqVoice mivoqVoice = engine.createVoice("Fede","male","en");
        Effect rate = new EffectImpl("rate");
        rate.setValue("0.9");
        mivoqVoice.setEmotion(Emotion.Anger);
        mivoqVoice.setEffect(rate);

        byte[] array =  mivoqTTSSingleton.synthesizeText(mivoqVoice,"text");

        Log.v("tag","array.length = " + array.length);

        assertEquals(0,array.length);
    }
}
