package starklabs.libraries.Model.Mivoq;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */
public class MivoqTTSSingleton {

    private MediaPlayer mediaPlayer;

    private static MivoqTTSSingleton ourInstance = new MivoqTTSSingleton();

    public static MivoqTTSSingleton getInstance() {
        return ourInstance;
    }

    private MivoqTTSSingleton() {}

    private static AbstractFactory myFactory= new MivoqConnectionFactory();

    private Context myContext;

    private RequestQueue Queue;

    //private List<MivoqVoice> VoiceList;

    //public  List<MivoqVoice> getVoice(){

    //}
    public void setContext(Context T)
    {
        myContext=T;
    }

    public byte[] SynthesizeText(/*MivoqVoice v,*/ String Text)
    {
        byte[] audio;

        if(Queue== null)
            Queue = Volley.newRequestQueue(myContext, new HurlStack());

        MivoqConnection request= myFactory.createConnection();

        synchronized(request) {
            //set parameters
            request.setQueue(Queue);

            request.setVoiceGender("female");
            request.setVoiceName("itsc-lucia-hsmm");
            request.setLocale("it");
            request.setEffects("");

            // Set text here     |
            //					 |
            //					 V
            request.sendRequest(Text);

            try {
                while (request.getResponse() == null) {

                    System.out.println("Waiting");

                    //request.wait();
                    Thread.sleep(100);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Consume response
            audio = request.getResponse();

            try {
                AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_VOICE_CALL, 16000,
                        AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT,
                        audio.length, AudioTrack.MODE_STATIC);

                audioTrack.write(audio, 0, audio.length);
                audioTrack.play();


            } catch (Throwable t) {
                String ab=t.getMessage();
                System.out.println(ab);
                // /Log.d("Audio", "Playback Failed");
            }

        }
        return audio;
    }

    public  String getVoice(){

        JSONObject result;

        if(Queue== null)
            Queue = Volley.newRequestQueue(myContext, new HurlStack());

        MivoqInfo request= myFactory.createInfoConnection();

        synchronized(request)
        {
            //Set parameters
            request.setQueue(Queue);

            request.sendRequest("http://fic2fatts.tts.mivoq.it/info/locales/all");
            try{
                while(request.getResponse()==null)
                {
                    //request.wait();
                    Thread.sleep(50);
                }
            }catch(InterruptedException e)
            {
                e.printStackTrace();
            }

            //Consume response

            result= request.getResponse();

            System.out.println(result.toString());
        }

        return result.toString();

    }
}
