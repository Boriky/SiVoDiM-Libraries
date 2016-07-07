package starklabs.libraries.Model.Integration;

import android.app.ListActivity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import starklabs.libraries.Model.Mivoq.MivoqTTSSingleton;

/**
 * Created by AlbertoAndriolo on 20/06/2016.
 */
public class DownloadVoiceData extends ListActivity implements TextToSpeech.OnInitListener{

    private TextToSpeech mTts = new TextToSpeech(this, this, "starklabs.libraries");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MivoqTTSSingleton engine= MivoqTTSSingleton.getInstance();

        if(!engine.hasContext())
            engine.setContext(this);

        finish();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            mTts.speak("Hello World", TextToSpeech.QUEUE_FLUSH, null);
        }

    }
}