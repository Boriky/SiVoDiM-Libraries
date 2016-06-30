package starklabs.libraries.Model.Mivoq;

import android.annotation.TargetApi;
import android.media.AudioFormat;
import android.os.Build;
import android.speech.tts.SynthesisCallback;
import android.speech.tts.SynthesisRequest;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeechService;
import android.speech.tts.Voice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import starklabs.libraries.Model.Voice.Language;
import starklabs.libraries.Model.Voice.MivoqVoice;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */
public class MivoqTTSService extends TextToSpeechService{

    private SynthesisCallback mCallback;
    private static MivoqTTSSingleton engine=MivoqTTSSingleton.getInstance();
    private int voiceID;

    public MivoqTTSService (){
        if(!engine.hasContext())
        {
            engine.setContext(super.getBaseContext());
        }
    }

    @Override
    public String onGetDefaultVoiceNameFor (String lang, String country, String variant) {
        ArrayList<MivoqVoice> list= engine.getVoices();
        int i=0;
        Language language= new Language("ita");
        MivoqVoice voice=new MivoqVoice("ProvaEnri", "ita", language);
        if(list.size()>0) {
            for(i=0; list.size()!=0 && !voice.getLanguage().equals(lang.substring(0, 2)) && i + 1 < list.size(); i++){
            //MivoqVoice voice = list.get(i);
            //while (!voice.getLanguage().equals(lang.substring(0, 2)) && i + 1 < list.size()) {
            //  i++;
                voice = list.get(i);
            }
            if (i != list.size())
                return voice.getLanguage() + "--" + voice.getName();
            else
                return "Not Available";
        }
        return "Not Available";
    }

    @Override
    public int onIsValidVoiceName (String voiceName) {
        if(voiceName.contains("--") || voiceName.equals("Not Available"))
            return TextToSpeech.SUCCESS;
        else
            return TextToSpeech.ERROR;
    }

    @Override
    public int onLoadVoice (String voiceName) {
        String nome[]= voiceName.split("--");

        if(voiceName.equals("Not Available")) //A little problem here, think how to fix it
            return TextToSpeech.SUCCESS;

        if(nome[0]== null || nome[1]==null)
            return TextToSpeech.ERROR;

        ArrayList<MivoqVoice> list= engine.getVoices();
        int i=0;
        while(i<list.size() && !list.get(i).getName().equals(nome[1]) )    i++;

        if(i != list.size())
            voiceID=i;

        return TextToSpeech.SUCCESS;
    }

    @Override
    protected int onIsLanguageAvailable(String lang, String country, String variant) {
        if(lang.substring(0,2).equals("it"))
            return TextToSpeech.LANG_AVAILABLE;
        if(lang.substring(0,2).equals("en"))
            return TextToSpeech.LANG_AVAILABLE;
        if(lang.substring(0,2).equals("de"))
            return TextToSpeech.LANG_AVAILABLE;
        if(lang.substring(0,2).equals("fr"))
            return TextToSpeech.LANG_AVAILABLE;

        return TextToSpeech.LANG_NOT_SUPPORTED;
    }

    @Override
    protected String[] onGetLanguage() {
        String[] a={"ita", "fra", "deu", "eng"};
        return a;
    }

    @Override
    protected int onLoadLanguage(String lang, String country, String variant) {
        if(onLoadVoice(onGetDefaultVoiceNameFor(lang, country, variant))== TextToSpeech.SUCCESS)
            return TextToSpeech.LANG_AVAILABLE;
        else
            return TextToSpeech.LANG_NOT_SUPPORTED;
    }

    @Override
    protected void onStop() {

    }

    @Override
    protected void onSynthesizeText(SynthesisRequest req, SynthesisCallback call) {
        mCallback = call;
        mCallback.start(16000, AudioFormat.ENCODING_PCM_16BIT, 1);

        MivoqVoice defaultVoice = engine.getVoices().get(voiceID);
        byte[] result = engine.synthesizeText(defaultVoice, req.getText());

        int bufferSize = mCallback.getMaxBufferSize();

        int i = 0;
        while ((i + 1) * bufferSize < result.length - 44) {
            mCallback.audioAvailable(result, 44 + i * bufferSize, bufferSize);
            i++;
        }
        mCallback.audioAvailable(result, 44 + i * bufferSize, result.length - 44 - (i * bufferSize));


        mCallback.done();

    }
    @Override
    protected Set<String> onGetFeaturesForLanguage (String lang, String country, String variant) {

        Set<String> result= super.onGetFeaturesForLanguage (lang,country, variant);

        result.add(TextToSpeech.Engine.KEY_FEATURE_NETWORK_SYNTHESIS);

        return result;


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public List<Voice> onGetVoices()
    {
        List<Voice> result= new ArrayList<Voice>();
        Set<String> features= new HashSet<String>();
        Voice myV;
        ArrayList<MivoqVoice> list= engine.getVoices();

        for(int i=0; i<list.size(); i++)
        {
            MivoqVoice temp=list.get(i);
            myV= new Voice(temp.getLanguage()+"--"+temp.getName(),new Locale(temp.getLanguage()),0,0,true,features);
            result.add(myV);
        }

        return result;
    }
}
