package starklabs.libraries.Model.EngineManager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
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
                myEngine.synthesizeToFile(myPath,myVoice,myText);
            }catch(FileNotFoundException e)
            {
                //File not found exception
            }
            return null;
        }

        protected void onPostExecute(Void Result) {
            if(myListener != null)
                myListener.onCompleteSynthesis();
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
            myEngine.speak(myVoice,myText);
            return null;
        }
    }

    public EngineImpl(Context c) {
        myContext=c;
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

    public ArrayList<MivoqVoice> getVoices() {
        return myEngine.getVoices();
    }

    public void synthesizeToFile (String path, String voiceID, String myEmotion, String text, final Listener myListener){
        ConnectivityManager cm =
                (ConnectivityManager)myContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();


        ArrayList<MivoqVoice> VoiceList= myEngine.getVoices();
        MivoqVoice VID= VoiceList.get(0); boolean found=false;

        for(int i=0; !found && i<VoiceList.size(); i++)
            if(VoiceList.get(i).getName().equals(voiceID))
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
            SynthesisTask Runner= new SynthesisTask(path,VID,text,myListener);

            Runner.execute();
        }
        else 	/*TextToSpeech di sistema*/
        {
            Locale lang= new Locale(VID.getLanguage());

            if(backupEngine.isLanguageAvailable(lang)==TextToSpeech.LANG_AVAILABLE)
                backupEngine.setLanguage(lang);

            backupEngine.setOnUtteranceCompletedListener(new TextToSpeech.OnUtteranceCompletedListener() {
                @Override
                public void onUtteranceCompleted(String utteranceId) {
                    System.out.println("Utterance ReacheD");
                    myListener.onCompleteSynthesis();
                }
            });

            HashMap<String, String> params = new HashMap<String, String>();
            params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "myUtteranceID");

            backupEngine.synthesizeToFile(text,params,path);
        }
    }

    public void speak(String voiceID, String text) {
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
            if(voiceID.equals(VoiceList.get(i).getName()))
            {
                VID= VoiceList.get(i);
                found=true;
            }

        System.out.println(VID.getName());

        if(isConnected)
        {
            SpeakTask Runner= new SpeakTask(VID,text);

            Runner.execute();
        }
        else
        {
            Locale lang= new Locale(VID.getLanguage());

            if(backupEngine.isLanguageAvailable(lang)==TextToSpeech.LANG_AVAILABLE)
                backupEngine.setLanguage(lang);
            backupEngine.speak(text,TextToSpeech.QUEUE_FLUSH,null);
        }
    }

    public MivoqVoice createVoice(String name, String gender, String myLanguage) {
        return myEngine.createVoice(name,gender,myLanguage);
    }

    public void removeVoice(int index) {
        if(index!= 0)
            myEngine.removeVoice(index);
    }

    @Override
    public void save() {
        myEngine.save();
    }

    @Override
    public MivoqVoice getVoiceByName(String s) {
        ArrayList<MivoqVoice> voiceList=myEngine.getVoices();
        for(int i=0; i<voiceList.size(); i++){
            if (voiceList.get(i).getName().equals(s))
                return voiceList.get(i);
        }
        return null;
    }
}
