package starklabs.libraries.Model.Mivoq;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import starklabs.libraries.Model.Voice.Language;
import starklabs.libraries.Model.Voice.MivoqVoice;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */
public class MivoqTTSSingleton {

    private Context myContext;
    private RequestQueue queue;
    private ArrayList<MivoqVoice> voiceList;

    private static MivoqTTSSingleton ourInstance = new MivoqTTSSingleton();
    private static AbstractFactory myFactory= new MivoqConnectionFactory();
    public static MivoqTTSSingleton getInstance() {
        return ourInstance;
    }

    private MivoqTTSSingleton() {
        voiceList= new ArrayList<MivoqVoice>();
    }

    public boolean hasContext() {
        return (myContext!=null);
    }

    public void setContext(Context T) {
        myContext=T;
    }

    public byte[] synthesizeText(MivoqVoice v, String text) {
        byte[] result;

        if(queue== null)
            queue = Volley.newRequestQueue(myContext, new HurlStack());

        MivoqConnection request= myFactory.createConnection();

        synchronized(request)
        {
            //Set parameters
            request.setQueue(queue);

            request.setVoiceGender(v.getGender());
            request.setVoiceName(v.getVoiceName());
            request.setLocale(v.getLanguage());
            request.setEffects(v.getStringEffects());

            // Workaround to fix ' and . problems
            String FixText= text;

            FixText=FixText.replace("'","' ");
            FixText+= ".";

            // Set text here     |
            //		     |
            //		     V
            request.sendRequest(FixText);
            try{
                while(request.getResponse()==null)
                {
                    //request.wait();
                    Thread.sleep(100);
                }
            }catch(InterruptedException e)
            {
                e.printStackTrace();
            }

            //Consume response

            result= request.getResponse();
        }

        return result;

    }

    public void synthesizeToFile (String path, MivoqVoice v, String text) throws FileNotFoundException {
        byte[] result=synthesizeText(v, text);

        FileOutputStream fos = new FileOutputStream(path);
        try {
            fos.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void speak(MivoqVoice v, String text) {

        byte[] audio = synthesizeText(v,text);

        try {
            AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 16000,
                    AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT,
                    audio.length, AudioTrack.MODE_STATIC);

            audioTrack.write(audio, 0, audio.length);
            audioTrack.play();


        } catch (Throwable t) {
            String ab=t.getMessage();
            System.out.println(ab);
            // Log.d("Audio", "Playback Failed");
        }
    }

    public MivoqVoice createVoice(String name, String gender, String myLanguage) {
        String VoiceName="istc-speaker_internazionale-hsmm";

        Language L= new Language(myLanguage); // Using Locale or Language?
        MivoqVoice V=  new MivoqVoice(name,VoiceName,L);
        V.setGenderLanguage(gender,myLanguage);

        V.setGender(gender);

        voiceList.add(V);

        return V;

    }

    public ArrayList<MivoqVoice> getVoices() {
        return voiceList;
    }

    public void removeVoice(int index) {
        voiceList.remove(index);
    }
}
