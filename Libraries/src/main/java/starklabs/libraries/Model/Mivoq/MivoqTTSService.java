package starklabs.libraries.Model.Mivoq;

import android.media.AudioFormat;
import android.speech.tts.SynthesisCallback;
import android.speech.tts.SynthesisRequest;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeechService;

import java.util.Set;

import starklabs.libraries.Model.Voice.MivoqVoice;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */
public class MivoqTTSService extends TextToSpeechService{

    private SynthesisCallback mCallback;

    @Override
    protected int onIsLanguageAvailable(String lang, String country, String variant) {
        return TextToSpeech.LANG_COUNTRY_VAR_AVAILABLE;
    }

    @Override
    protected String[] onGetLanguage() {
        String[] a={"it", "italia"};
        return a;
    }

    @Override
    protected int onLoadLanguage(String lang, String country, String variant) {
        return 0;
    }

    @Override
    protected void onStop() {

    }

    @Override
    protected void onSynthesizeText(SynthesisRequest req, SynthesisCallback call) {
        MivoqTTSSingleton engine= MivoqTTSSingleton.getInstance();

        mCallback=call;

        mCallback.start(16000, AudioFormat.ENCODING_PCM_16BIT, 1);

        MivoqVoice Fede= engine.createVoice("Fede", "male", "it");
        byte[] result= engine.synthesizeText(Fede, req.getText());

        System.out.println("buffer"+ mCallback.getMaxBufferSize());

        int i=0;
        while((i+1)*8000<result.length-44){
            mCallback.audioAvailable(result,44+i*8000, 8000/*result.length-44*/);
            i++;
        }
        mCallback.audioAvailable(result,44+i*8000, result.length-44-(i*8000));


        mCallback.done();

    }
    @Override
    protected Set<String> onGetFeaturesForLanguage (String lang, String country, String variant) {

        Set<String> result= super.onGetFeaturesForLanguage (lang,country, variant);

        result.add(TextToSpeech.Engine.KEY_FEATURE_NETWORK_SYNTHESIS);

        return result;


    }
}
