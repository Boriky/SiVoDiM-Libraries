package starklabs.libraries.Model.Mivoq;

import android.speech.tts.SynthesisCallback;
import android.speech.tts.SynthesisRequest;
import android.speech.tts.TextToSpeechService;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */
public class MivoqTTSService extends TextToSpeechService{

    private SynthesisCallback mCallback;

    @Override
    protected int onIsLanguageAvailable(String lang, String country, String variant) {
        return 0;
    }

    @Override
    protected String[] onGetLanguage() {
        return new String[0];
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
       /* MivoqTTSSingleton engine= MivoqTTSSingleton.getInstance();

        mCallback=call;

        mCallback.start(16000, AudioFormat.ENCODING_PCM_16BIT, 1);

        byte[] result= engine.SynthesizeText(req.getText());

        mCallback.audioAvailable(result,24,result.length-24);

        mCallback.done();
        */
    }

}
