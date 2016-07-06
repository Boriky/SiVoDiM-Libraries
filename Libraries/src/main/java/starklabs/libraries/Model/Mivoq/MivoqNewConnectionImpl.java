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
    private String locale;

    private String voiceGender;
    private String voiceName;
    private String effects;

    //private static final String Select_Algorithm= "ssml";

    // Some constants used for every request is submitted to the servers
    private static final String inputType ="TEXT";
    private static final String outputType ="AUDIO";
    private static final String outputFormat ="WAVE_FILE";
    // new URL that Mivoq s.r.l. provided for the access to the 2 new italian voices
    private static final String url ="http://inst0213.tts.mivoq.it/say";

    private Request request;
    private Context myContext;
    // Shared object used for the queue of requests to submit to the server
    private static RequestQueue myRequestQueue;

    //private MivoqTTSSingleton Observer;
    private byte[] response = null;

    public MivoqNewConnectionImpl(){}

    public void setVoiceGender(String s){ voiceGender =s; }
    public void setVoiceName(String s) { voiceName =s; }
    public void setLocale(String s) { locale =s; }
    public void setEffects(String s) { effects =s; }

    public void setQueue(RequestQueue RQ) {myRequestQueue=RQ;}


    public void sendRequest(String Text)
    {

        HashMap<String, String> Params = new HashMap<String, String>();

        //Array of parameters that the request has to send to the Mivoq Service
        Params.put("input[type]", inputType);
        Params.put("input[content]",Text);
        Params.put("input[locale]", locale.substring(0,2));
        Params.put("output[type]", outputType);
        Params.put("output[format]", outputFormat);
        Params.put("voice[gender]", voiceGender);
        Params.put("voice[name]", voiceName);

        // Mivoq seems to have removed the algorythm selection in the new sistem;
        //Params.put("voice[selection_algorythm]",Select_Algorithm);
        Params.put("utterance[effects]", effects);

        request = new AuthAudioRequest(Request.Method.POST, url, Params,
                new Response.Listener<byte[]>()
                {
                    @Override
                    public void onResponse(byte[] myResponse) {

                        synchronized(this){
                            System.out.println("Consegna avvenuta con successo");

                            response =myResponse;
                        }

                    }}	, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("c'e' stato un errore nell'invio della richiesta");
                System.out.println(error.getMessage());
            }
        });


        if(myRequestQueue==null)
            myRequestQueue = Volley.newRequestQueue(myContext, new HurlStack());

        myRequestQueue.add(request);
    }

    public byte[] getResponse(){

        return response;

    }
}
