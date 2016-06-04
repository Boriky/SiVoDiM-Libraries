package starklabs.libraries.Model.Mivoq;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import starklabs.libraries.Model.Voice.Effect;
import starklabs.libraries.Model.Voice.EffectImpl;
import starklabs.libraries.Model.Voice.Language;
import starklabs.libraries.Model.Voice.MivoqVoice;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */
public class MivoqTTSSingleton {

    private MediaPlayer mediaPlayer;

    private static MivoqTTSSingleton ourInstance = new MivoqTTSSingleton();

    public static MivoqTTSSingleton getInstance() {
        return ourInstance;
    }

    private MivoqTTSSingleton() {
        VoiceList= new ArrayList<MivoqVoice>();
    }

    private static AbstractFactory myFactory= new MivoqConnectionFactory();

    private Context myContext;

    private RequestQueue Queue;

    private List<MivoqVoice> VoiceList;

    public void setContext(Context T) {
        myContext=T;
    }

    public byte[] SynthesizeText(MivoqVoice v, String Text) {
        byte[] result;

        if(Queue== null)
            Queue = Volley.newRequestQueue(myContext, new HurlStack());

        MivoqConnection request= myFactory.createConnection();

        synchronized(request)
        {
            //Set parameters
            request.setQueue(Queue);

            request.setVoiceGender(v.getGender());
            request.setVoiceName(v.getVoiceName());
            request.setLocale(v.getLanguage());
            request.setEffects(v.getStringEffects());

            // Set text here     |
            //		     |
            //		     V
            request.sendRequest(Text);
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

    public void SynthesizeToFile (String Path, MivoqVoice V, String Text) throws FileNotFoundException {
        byte[] result=SynthesizeText(V,Text);

        FileOutputStream fos = new FileOutputStream(Path);
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

    public void Speak(MivoqVoice v, String Text) {

        byte[] audio = SynthesizeText(v,Text);

        try {
            AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_VOICE_CALL, 16000,
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

    public MivoqVoice CreateVoice(String Name, String Gender, String myLanguage) {
        String VoiceName=" ";
        boolean effects=false;

        switch (myLanguage)
        {
            case "it":
                if(Gender=="female") VoiceName="istc-lucia-hsmm";
                else VoiceName="istc-speaker_internazionale-hsmm";

                break;
            case "fr":
                if(Gender=="female") VoiceName="enst-camilla-hsmm";
                else VoiceName="upmc-pierre-hsmm";
                break;
            case "de":
                if(Gender=="female")
                    effects=true;
                VoiceName="dfki-stefan-hsmm";
                break;
            case "en":
            case "en_US":
                if(Gender=="female") VoiceName="cmu-slt-hsmm";
                else VoiceName="istc-piero-hsmm";
                break;
        }

        Language L= new Language(myLanguage); // Using Locale or Language?

        MivoqVoice V = new MivoqVoice(Name, VoiceName, L);

        if(effects)
        {
            Effect E1 = new EffectImpl("HMMTractScaler");
            E1.setValue("1.3");
            Effect E2 = new EffectImpl("F0Add");
            E2.setValue("120.0");

            V.setEffect(E1);
            V.setEffect(E2);
        }

        V.setGender(Gender);

        VoiceList.add(V);

        return V;

    }

    public List<MivoqVoice> getVoices() {
        return VoiceList;
    }

    public void RemoveVoice(int index) {
        VoiceList.remove(index);
    }
}
