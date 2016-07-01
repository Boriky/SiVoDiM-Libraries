package starklabs.libraries.UnitTest;

import android.content.Context;

import org.junit.Test;
import org.mockito.Mockito;

import starklabs.libraries.Model.Mivoq.MivoqTTSSingleton;
import starklabs.libraries.Model.Voice.Effect;
import starklabs.libraries.Model.Voice.EffectImpl;
import starklabs.libraries.Model.Voice.MivoqVoice;

import static org.junit.Assert.assertEquals;

/**
 * Created by Enrico on 24/06/16.
 */
public class TU18 {
    @Test
    public void testEditEffect(){

        MivoqTTSSingleton mivoqTTSSingleton = MivoqTTSSingleton.getInstance();
        Context context = Mockito.mock(Context.class);
        mivoqTTSSingleton.setContext(context);

        String name = "name";
        String gender = "male";
        String myLanguage = "de";

        //Create real voice
        MivoqVoice enri = mivoqTTSSingleton.createVoice(name, gender, myLanguage);
        Effect effect = new EffectImpl("Rate");
        effect.setValue("1.5");
        enri.setEffect(effect);

        String e = "F0Add";
        Effect effettoModificato = new EffectImpl(e);
        enri.setEffect(effettoModificato);


        boolean check = true;

        if(enri.getEffects().get(0).getName().equals(effect)){
            check = false;
        }

        assertEquals(true, check);
    }
}
