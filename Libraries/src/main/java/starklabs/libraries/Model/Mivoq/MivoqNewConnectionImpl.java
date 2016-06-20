package starklabs.libraries.Model.Mivoq;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

/**
 * Created by AlbertoAndriolo on 20/06/2016.
 */


public class MivoqNewConnectionImpl implements MivoqConnection {
    private String Locale;

    private String VoiceGender;
    private String VoiceName;
    private String Effects;

    //private static final String Select_Algorithm= "ssml";

    // Some constants used for every request is submitted to the servers
    private static final String InputType="TEXT";
    private static final String OutputType="AUDIO";
    private static final String OutputFormat="WAVE_FILE";
    // new URL that Mivoq s.r.l. provided for the access to the 2 new italian voices
    private static final String Url ="http://inst0213.tts.mivoq.it/say";

    private Request request;
    private Context myContext;
    // Shared object used for the queue of requests to submit to the server
    private static RequestQueue myRequestQueue;

    //private MivoqTTSSingleton Observer;
    private byte[] Response = null;

    public MivoqNewConnectionImpl(){}

    public void setVoiceGender(String s){ VoiceGender=s; }
    public void setVoiceName(String s) { VoiceName=s; }
    public void setLocale(String s) { Locale=s; }
    public void setEffects(String s) { Effects=s; }

    public void setQueue(RequestQueue RQ) {myRequestQueue=RQ;}


    public void sendRequest(String Text)
    {

        HashMap<String, String> Params = new HashMap<String, String>();

        //Array of parameters that the request has to send to the Mivoq Service
        Params.put("input[type]",InputType);
        Params.put("input[content]",Text);
        Params.put("input[locale]",Locale);
        Params.put("output[type]",OutputType);
        Params.put("output[format]",OutputFormat);
        Params.put("voice[gender]",VoiceGender);
        Params.put("voice[name]",VoiceName);

        // Mivoq seems to have removed the algorythm selection in the new sistem;
        //Params.put("voice[selection_algorythm]",Select_Algorithm);
        Params.put("utterance[effects]",Effects);

        request = new AuthAudioRequest(Request.Method.POST, Url, Params,
                new Response.Listener<byte[]>()
                {
                    @Override
                    public void onResponse(byte[] myResponse) {

                        synchronized(this){
                            System.out.println("Consegna avvenuta con successo");

                            Response=myResponse;
                        }

                    }}	, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("c'e' stato un errore");
                System.out.println(error.getMessage());
            }
        });


        if(myRequestQueue==null)
            myRequestQueue = Volley.newRequestQueue(myContext, new HurlStack());

        myRequestQueue.add(request);
        System.out.println("New Step 1");
    }

    public byte[] getResponse(){

        return Response;

    }
}
