package starklabs.libraries.TestUnity;

import android.content.Context;

import org.junit.Test;
import org.mockito.Mockito;

import starklabs.libraries.Model.Mivoq.MivoqTTSSingleton;
import starklabs.libraries.Model.Voice.Effect;
import starklabs.libraries.Model.Voice.EffectImpl;
import starklabs.libraries.Model.Voice.MivoqVoice;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Enrico on 24/06/16.
 */
public class TU16 {
    @Test
    public void testSetEffect() {
        String name = "name";
        String gender = "male";
        String myLanguage = "de";

        String de = "dfki-stefan-hsmm";

        //Mock effect
        //MivoqVoice mivoqVoiceFintoDe = Mockito.mock(MivoqVoice.class);
        Effect effectFinto = Mockito.mock(EffectImpl.class);
        String rate = "Rate";
        String value = "1.5";
        when(effectFinto.getName()).thenReturn(rate);
        when(effectFinto.getValue()).thenReturn(value);

        MivoqTTSSingleton mivoqTTSSingleton = MivoqTTSSingleton.getInstance();
        Context context = Mockito.mock(Context.class);
        mivoqTTSSingleton.setContext(context);

        //Create real voice
        MivoqVoice enri = mivoqTTSSingleton.createVoice(name, gender, myLanguage);
        Effect effect = new EffectImpl("Rate");

        effect.setValue("1.5");
        enri.setEffect(effect);

        assertEquals(true,enri.getEffects().get(0).getName().equals(effectFinto.getName()));
        assertEquals(true,enri.getEffects().get(0).getValue().equals(effectFinto.getValue()));

    }
}
