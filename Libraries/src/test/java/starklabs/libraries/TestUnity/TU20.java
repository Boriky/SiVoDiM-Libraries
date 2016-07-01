package starklabs.libraries.TestUnity;

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
public class TU20 {
    @Test
    public void testEditLanguage(){

        MivoqTTSSingleton mivoqTTSSingleton = MivoqTTSSingleton.getInstance();
        Context context = Mockito.mock(Context.class);
        mivoqTTSSingleton.setContext(context);

        String name = "name";
        String gender = "male";
        String myLanguage = "de";

        //Create real voice
        MivoqVoice enri = mivoqTTSSingleton.createVoice(name, gender, myLanguage);

        String linguaModificata= "en";
        enri.setGenderLanguage(gender,linguaModificata);


        boolean check = true;

        if(enri.getLanguage().equals(myLanguage)){
            check = false;
        }

        assertEquals(true, check);
    }
}
