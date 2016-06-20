package starklabs.libraries.Model.Integration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import starklabs.libraries.Model.Voice.MivoqVoice;

/**
 * Created by AlbertoAndriolo on 20/06/2016.
 */
public class GetSampleText extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        String language="";
        String myText= "";
        int result;
        Intent returnData = new Intent();
        Intent intent = getIntent();

        if (intent != null)
            language = intent.getStringExtra("language");

        if (language != null)
            myText= MivoqVoice.getSampleText(language);

        if (myText == null)
        {
            result = TextToSpeech.LANG_NOT_SUPPORTED;
            returnData.putExtra("sampleText", "");
        }
        else
        {
            result = TextToSpeech.LANG_AVAILABLE;
            returnData.putExtra("sampleText", myText);
        }

        setResult(result, returnData);
        finish();
    }
}