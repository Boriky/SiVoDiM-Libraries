package starklabs.libraries.TestUnity;

import android.content.Context;
import org.junit.Test;
import org.mockito.Mockito;
import starklabs.libraries.Model.Mivoq.MivoqTTSSingleton;
import starklabs.libraries.Model.Voice.Effect;
import starklabs.libraries.Model.Voice.EffectImpl;
import starklabs.libraries.Model.Voice.Emotion;
import starklabs.libraries.Model.Voice.MivoqVoice;

import static org.junit.Assert.assertEquals;

/**
 * Created by Enrico on 27/06/16.
 */
public class TU21 {
    @Test
    public void testEditVoice(){

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
        Emotion happiness=new Emotion("happiness");
        enri.setEmotion(happiness);



        String nameModificato = "NomeModificato";
        String genderModificato = "female";
        String myLanguageModificato = "en";
        String e = "F0Add";
        Effect effettoModificato = new EffectImpl(e);
        enri.setEffect(effettoModificato);
        enri.setGender(genderModificato);
        enri.setName(nameModificato);
        enri.setGenderLanguage(genderModificato,myLanguageModificato);



        boolean check = true;

        if(enri.getEffects().get(0).getName().equals(effect)){
            check = false;
        }

        if(enri.getGender().equals(gender)){
            check = false;
        }
        if(enri.getLanguage().equals(myLanguage)){
            check = false;
        }
        if(enri.getVoiceName().equals(name)){
            check = false;
        }
        assertEquals(true, check);
    }
}
