package starklabs.libraries.UnitTest;

import android.content.Context;
import android.test.InstrumentationTestCase;

import org.junit.Test;

import starklabs.libraries.Model.Mivoq.MivoqTTSSingleton;
import starklabs.libraries.Model.Voice.MivoqVoice;


/**
 * Created by Enrico on 28/06/16.
 */
public class TU14 extends InstrumentationTestCase{


    @Test
    public void testSyntesizeText(){
        MivoqTTSSingleton mivoqTTSSingleton = MivoqTTSSingleton.getInstance();
        Context context = getInstrumentation().getContext();
        mivoqTTSSingleton.setContext(context);

        String name = "name";
        String gender = "male";
        String myLanguage = "de";

        //Create real voice
        MivoqVoice enri = mivoqTTSSingleton.createVoice(name, gender, myLanguage);

        String text = "Benvenuti nel mondo della sintesi vocale";

        Byte[] result =  null;

        mivoqTTSSingleton.synthesizeText(enri,text);

        boolean test = result.equals(null);

        assertEquals(false,test);

    }
}
