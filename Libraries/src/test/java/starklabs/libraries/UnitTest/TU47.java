package starklabs.libraries.UnitTest;

/**
 * Created by GINO on 27/06/2016.
 */

import org.junit.Test;

import starklabs.libraries.Model.Voice.Effect;
import starklabs.libraries.Model.Voice.EffectImpl;
import starklabs.libraries.Model.Voice.Language;
import starklabs.libraries.Model.Voice.MivoqVoice;
import static org.junit.Assert.assertEquals;

/**
 * Test TU47 that test if it is possible to remove an effect from a MivoqVoice instance
 */
public class TU47 {
    @Test
    public void testRemoveEffect(){
        String name="name by user";
        String realname="name by Mivoq";
        String lang="it";
        String gender="Maschio";
        String effect="effect";
        Effect fect=new EffectImpl(effect);
        Language language=new Language(lang);
        MivoqVoice mivoqVoice=new MivoqVoice(name,realname,language);
        mivoqVoice.setGender(gender);
        mivoqVoice.setEffect(fect);
        mivoqVoice.removeEffect(0);
        assertEquals(true,mivoqVoice.getEffects().isEmpty());
    }
}
