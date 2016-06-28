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

import starklabs.libraries.Model.Voice.MivoqVoice;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */
public class MivoqTTSService extends TextToSpeechService{

    private SynthesisCallback mCallback;

    @Override
    public String onGetDefaultVoiceNameFor (String lang, String country, String variant) {
        if(lang.equals("ita"))
            return "Fede";
        else
            return "Gino";
    }

    @Override
    public int onIsValidVoiceName (String voiceName) {
        if(voiceName.equals("Fede"))
            return TextToSpeech.SUCCESS;
        else
            return TextToSpeech.ERROR;
    }

    @Override
    public int onLoadVoice (String voiceName) {
        if(voiceName.equals("Fede"))
            return TextToSpeech.SUCCESS;
        else
            return TextToSpeech.ERROR;
    }

    @Override
    protected int onIsLanguageAvailable(String lang, String country, String variant) {
        return TextToSpeech.LANG_COUNTRY_VAR_AVAILABLE;
    }

    @Override
    protected String[] onGetLanguage() {
        String[] a={"ita", "italia"};
        return a;
    }

    @Override
    protected int onLoadLanguage(String lang, String country, String variant) {
        return TextToSpeech.LANG_AVAILABLE;
    }

    @Override
    protected void onStop() {

    }

    @Override
    protected void onSynthesizeText(SynthesisRequest req, SynthesisCallback call) {
        MivoqTTSSingleton engine = MivoqTTSSingleton.getInstance();

        mCallback = call;

        mCallback.start(16000, AudioFormat.ENCODING_PCM_16BIT, 1);

        MivoqVoice defaultVoice = engine.getVoices().get(0);
        byte[] result = engine.synthesizeText(defaultVoice, req.getText());

        System.out.println("buffer" + mCallback.getMaxBufferSize());

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
        Voice Fede= new Voice("Fede",new Locale("ita"),0,0,true,features);

        result.add(Fede);
        return result;
    }
}
