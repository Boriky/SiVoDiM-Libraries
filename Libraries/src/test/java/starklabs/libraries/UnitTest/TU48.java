package starklabs.libraries.UnitTest;

/**
 * Created by GINO on 27/06/2016.
 */

import android.content.Context;
import android.test.InstrumentationTestCase;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import starklabs.libraries.Model.Mivoq.AudioRequest;

import static org.junit.Assert.assertEquals;

/**
 * Test TU47 that tests the correct creation of an AudioRequest object
 */
public class TU48 extends InstrumentationTestCase{
    private boolean correct=false;

    @Test
    public void testAudioRequestCreation(){
        final Context context=getInstrumentation().getContext();
        Map<String, String> Params = new HashMap<String, String>();

        //Insert parameters for the Mivoq Service
        Params.put("input[type]","TEXT");
        Params.put("input[content]","testo di prova");
        Params.put("input[locale]","it");
        Params.put("output[type]","AUDIO");
        Params.put("output[format]","WAVE_FILE");
        Params.put("voice[gender]","male");
        Params.put("voice[name]","");
        Params.put("voice[selection_algorythm","ssml");
        Params.put("utterance[effects]","");

        AudioRequest audioRequest=new AudioRequest(Request.Method.POST, "http://fic2fatts.tts.mivoq.it/say", Params,
                new Response.Listener<byte[]>()
                {
                    @Override
                    public void onResponse(byte[] myResponse) {
                        //Saving of the response in the byte array
                        synchronized(this) {
                            System.out.println("Consegna avvenuta con successo");
                            assertTrue(true);
                            this.notifyAll();
                        }
                    }}
                , new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("c'e' stato un errore nell'invio della richiesta");
                System.out.println(error.getMessage());
                assertTrue(false);
            }
        } );
    }
}
