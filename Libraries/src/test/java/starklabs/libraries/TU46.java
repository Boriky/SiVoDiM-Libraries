package starklabs.libraries;

/**
 * Created by GINO on 27/06/2016.
 */

import org.junit.Test;

import starklabs.libraries.Model.Voice.Effect;
import starklabs.libraries.Model.Voice.EffectImpl;
import starklabs.libraries.Model.Voice.Emotion;
import starklabs.libraries.Model.Voice.Language;
import starklabs.libraries.Model.Voice.MivoqVoice;
import starklabs.libraries.Presenter.VoicePresenter;
import starklabs.libraries.Presenter.VoicePresenterImpl;
import static org.junit.Assert.assertEquals;

/**
 * Test TU46 that tests Mivoqvoice getter method
 */
public class TU46 {

    @Test
    public void testMivoqVoiceGetter(){
        String name="name by user";
        String realname="name by Mivoq";
        String lang="it";
        String gender="Maschio";
        String effect="effect";
        String effectString="[{F0Add:60.0,F0Flut:0.5,Rate:0.8}{effect:null}]";
        String encodedName="istc-speaker_internazionale-hsmm";
        Effect fect=new EffectImpl(effect);
        Language language=new Language(lang);
        MivoqVoice mivoqVoice=new MivoqVoice(name,realname,language);
        mivoqVoice.setGender(gender);
        mivoqVoice.setEffect(fect);
        mivoqVoice.setEmotion(Emotion.Fear);
      //  VoicePresenter voicePresenter=new VoicePresenterImpl(mivoqVoice);

      //  assertEquals(name,voicePresenter.getVoiceName());
        assertEquals(gender,mivoqVoice.getGender());
        assertEquals(lang,mivoqVoice.getLanguage());
        assertEquals(effectString, mivoqVoice.getStringEffects());
        assertEquals(effect,mivoqVoice.getEffects().get(0).getName());
        assertEquals(encodedName,mivoqVoice.getEncodedName(gender,lang));
    }
}
