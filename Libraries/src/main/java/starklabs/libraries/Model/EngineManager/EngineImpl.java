package starklabs.libraries.Model.EngineManager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;

import starklabs.libraries.Model.Mivoq.MivoqTTSSingleton;
import starklabs.libraries.Model.Voice.Emotion;
import starklabs.libraries.Model.Voice.MivoqVoice;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */
public class EngineImpl implements Engine{
    private static MivoqTTSSingleton myEngine= MivoqTTSSingleton.getInstance();
    private static TextToSpeech backupEngine;
    private Context myContext;

    private class SynthesisTask extends AsyncTask<Void,Void,Void> {
        private String myPath;
        private MivoqVoice myVoice;
        private String myText;
        private Listener myListener;

        SynthesisTask(String p,MivoqVoice v,String t,Listener l) {
            myPath=p; myVoice=v; myText=t; myListener=l;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try{
                myEngine.SynthesizeToFile(myPath,myVoice,myText);
            }catch(FileNotFoundException e)
            {
                //File not found exception
            }
            return null;
        }

        protected void onPostExecute( ) {
            if(myListener != null)
                myListener.OnCompleteSynthesis();
        }
    }

    private class SpeakTask extends AsyncTask<Void,Void,Void> {
        private MivoqVoice myVoice;
        private String myText;

        SpeakTask(MivoqVoice v, String t) {
            myVoice=v; myText=t;
        }
        @Override
        protected Void doInBackground(Void... params) {
            myEngine.Speak(myVoice,myText);
            return null;
        }
    }

    public EngineImpl(Context c) {
        myContext=c;
        if(!myEngine.hasContext())
            myEngine.setContext(c);
        if(backupEngine==null)
            backupEngine= new TextToSpeech(c,
                    new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            //Initialization TTS
                        }
                    });
    }

    public void SynthesizeToFile (String Path, String VoiceID, String myEmotion, String Text, Listener myListener){
        ConnectivityManager cm =
                (ConnectivityManager)myContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();


        ArrayList<MivoqVoice> VoiceList= myEngine.getVoices();
        MivoqVoice VID= VoiceList.get(0); boolean found=false;

        for(int i=0; !found && i<VoiceList.size(); i++)
            if(VoiceList.get(i).getName() == VoiceID)
            {
                VID= VoiceList.get(i);
                found=true;
            }

        Emotion myEmot= null;

        switch(myEmotion)
        {
            case "HAPPINESS":	myEmot= Emotion.Happiness;
                break;
            case "DISGUST":		myEmot= Emotion.Disgust;
                break;
            case "FEAR":		myEmot= Emotion.Fear;
                break;
            case "SADNESS":		myEmot= Emotion.Sadness;
                break;
            case "SURPRISE":	myEmot= Emotion.Surprise;
                break;
            case "ANGER":		myEmot= Emotion.Anger;
                break;
            case "NONE":		myEmot= null;
                break;
        }

        VID.setEmotion(myEmot);

        if(isConnected)
        {
            SynthesisTask Runner= new SynthesisTask(Path,VID,Text,myListener);

            Runner.execute();
        }
        else 	/*TextToSpeech di sistema*/
        {
            Locale lang= new Locale(VID.getLanguage());

            if(backupEngine.isLanguageAvailable(lang)==TextToSpeech.LANG_AVAILABLE)
                backupEngine.setLanguage(lang);
            File f= new File(Path);

            backupEngine.synthesizeToFile(Text,null,Path);
        }
    }

    public void Speak(String VoiceID, String Text) {
        ConnectivityManager cm =
                (ConnectivityManager)myContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();
        if (isConnected)
            System.out.println("e' connesso");
        else
            System.out.println("non connesso");

        ArrayList<MivoqVoice> VoiceList= myEngine.getVoices();
        MivoqVoice VID= VoiceList.get(0); boolean found=false;

        for(int i=0; !found && i<VoiceList.size(); i++)
            if(VoiceList.get(i).getName() == VoiceID)
            {
                VID= VoiceList.get(i);
                found=true;
            }

        System.out.println(VID.getName());

        if(isConnected)
        {
            SpeakTask Runner= new SpeakTask(VID,Text);

            Runner.execute();
        }
        else
        {
            Locale lang= new Locale(VID.getLanguage());

            if(backupEngine.isLanguageAvailable(lang)==TextToSpeech.LANG_AVAILABLE)
                backupEngine.setLanguage(lang);
            backupEngine.speak(Text,TextToSpeech.QUEUE_FLUSH,null);
        }
    }

    public ArrayList<MivoqVoice> getVoices() {
        return myEngine.getVoices();
    }

    public MivoqVoice CreateVoice(String Name, String Gender, String myLanguage) {
        return myEngine.CreateVoice(Name,Gender,myLanguage);
    }

    public void RemoveVoice(int index) {
        if(index!= 0)
            myEngine.RemoveVoice(index);
    }
}
