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
    private String Locale;
    private String VoiceGender;
    private String VoiceName;
    //private String VoiceAge;
    private String Effects;

    private static final String Select_Algorithm= "ssml";

    private static final String InputType="TEXT";
    private static final String OutputType="AUDIO";
    private static final String OutputFormat="WAVE_FILE";
    private static final String Url ="http://fic2fatts.tts.mivoq.it/say";

    private Request request;
    private Context myContext;
    private static RequestQueue myRequestQueue;

    private byte[] Response = null;

    public MivoqConnectionImpl(){}

    public void setVoiceGender(String s){ VoiceGender=s; }
    public void setVoiceName(String s) { VoiceName=s; }
    public void setLocale(String s) { Locale=s; }
    public void setEffects(String s) { Effects=s; }
    public void setQueue(RequestQueue rq) {myRequestQueue=rq;}

    public void sendRequest(String text)
    {
    //http://www.techstricks.com/download-file-using-android-volley/
    // Guide to handle file download with volley

        Map<String, String> Params = new HashMap<String, String>();

    //Insert parameters for the Mivoq Service
        Params.put("input[type]",InputType);
        Params.put("input[content]",text);
        Params.put("input[locale]",Locale);
        Params.put("output[type]",OutputType);
        Params.put("output[format]",OutputFormat);
        Params.put("voice[gender]",VoiceGender);
        Params.put("voice[name]",VoiceName);
        Params.put("voice[selection_algorythm",Select_Algorithm);
        Params.put("utterance[effects]",Effects);

        request = new AudioRequest(Request.Method.POST, Url, Params,
                new Response.Listener<byte[]>()
                {
                    @Override
                    public void onResponse(byte[] myResponse) {
                    //Saving of the response in the byte array
                        synchronized(this) {
                            System.out.println("Consegna avvenuta con successo");
                            Response = myResponse;
                            this.notifyAll();
                        }
                    }}
                , new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("c'e' stato un errore");
                System.out.println(error.getMessage());
            }
        } );


        myRequestQueue.add(request);
        System.out.println("Step 1");
    }

    public byte[] getResponse(){
        return Response;
    }
}
