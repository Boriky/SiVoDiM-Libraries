package starklabs.libraries.Model.Mivoq;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */
public class MivoqConnectionImpl implements MivoqConnection{
    private String locale;
    private String voiceGender;
    private String voiceName;
    //private String VoiceAge;
    private String effects;

    private static final String selectAlgorithm= "ssml";

    private static final String inputType="TEXT";
    private static final String outputType="AUDIO";
    private static final String outputFormat="WAVE_FILE";
    private static final String url ="http://fic2fatts.tts.mivoq.it/say";

    private Request request;
    private Context myContext;
    private static RequestQueue myRequestQueue;

    private byte[] response = null;

    public MivoqConnectionImpl(){}

    public void setVoiceGender(String s){ voiceGender=s; }
    public void setVoiceName(String s) { voiceName=s; }
    public void setLocale(String s) { locale=s; }
    public void setEffects(String s) { effects=s; }
    public void setQueue(RequestQueue rq) {myRequestQueue=rq;}

    public void sendRequest(String text)
    {
    //http://www.techstricks.com/download-file-using-android-volley/
    // Guide to handle file download with volley

        Map<String, String> Params = new HashMap<String, String>();

    //Insert parameters for the Mivoq Service
        Params.put("input[type]",inputType);
        Params.put("input[content]",text);
        Params.put("input[locale]",locale.substring(0,2));
        Params.put("output[type]",outputType);
        Params.put("output[format]",outputFormat);
        Params.put("voice[gender]",voiceGender);
        Params.put("voice[name]",voiceName);
        Params.put("voice[selection_algorythm",selectAlgorithm);
        Params.put("utterance[effects]",effects);

        request = new AudioRequest(Request.Method.POST, url, Params,
                new Response.Listener<byte[]>()
                {
                    @Override
                    public void onResponse(byte[] myResponse) {
                    //Saving of the response in the byte array
                        synchronized(this) {
                            System.out.println("Consegna avvenuta con successo");
                            response = myResponse;
                            this.notifyAll();
                        }
                    }}
                , new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("c'e' stato un errore nell'invio della richiesta");
                System.out.println(error.getMessage());
            }
        } );


        myRequestQueue.add(request);
        System.out.println("Aggiunta la richiesta alla coda");
    }

    public byte[] getResponse(){
        return response;
    }
}
