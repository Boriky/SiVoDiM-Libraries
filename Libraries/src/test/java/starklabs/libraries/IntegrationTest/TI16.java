package starklabs.libraries.IntegrationTest;

import android.content.Context;
import android.test.InstrumentationTestCase;

import org.junit.Test;
import org.mockito.Mockito;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.EngineManager.EngineImpl;
import starklabs.libraries.Model.Voice.Effect;
import starklabs.libraries.Model.Voice.EffectImpl;
import starklabs.libraries.Model.Voice.Emotion;
import starklabs.libraries.Model.Voice.MivoqVoice;

import static org.junit.Assert.assertEquals;

/**
 * Created by AlbertoAndriolo on 01/07/2016.
 */
public class TI16 extends InstrumentationTestCase{
    @Test
    public void testVoiceEngine(){
        Context context = getInstrumentation().getContext();
        Engine engine = new EngineImpl(context);

        MivoqVoice mivoqVoice = engine.createVoice("Fede","male","en");
        Effect rate = new EffectImpl("rate");
        rate.setValue("0.9");
        mivoqVoice.setEmotion(Emotion.Anger);
        mivoqVoice.setEffect(rate);

        int index = engine.getVoices().size();

        String effects = engine.getVoices().get(index-1).getStringEffects();

        assertEquals(true,effects.equals(mivoqVoice.getStringEffects()));

        engine.removeVoice(index-1);
        assertEquals(true,engine.getVoices().size()==index-1);
    }
}
