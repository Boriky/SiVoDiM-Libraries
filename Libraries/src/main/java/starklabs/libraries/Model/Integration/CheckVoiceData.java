package starklabs.libraries.Model.Integration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import java.util.ArrayList;

import starklabs.libraries.Model.Mivoq.MivoqTTSSingleton;
import starklabs.libraries.Model.Voice.MivoqVoice;

/**
 * Created by AlbertoAndriolo on 20/06/2016.
 */
public class CheckVoiceData extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        int result = TextToSpeech.Engine.CHECK_VOICE_DATA_PASS;
        Intent returnData = new Intent();

        ArrayList<MivoqVoice> voiceList = getVoices();
        if (voiceList== null || voiceList.isEmpty()) {

            result = TextToSpeech.Engine.CHECK_VOICE_DATA_FAIL;
            setResult(result, returnData);
        }
        else
        {

            ArrayList<String> available = new ArrayList<String>();

            for(MivoqVoice vox:voiceList) {
                available.add(vox.getLanguage()+"-"+vox.getState()+"-"+vox.getName());
            }

            returnData.putStringArrayListExtra("availableVoices", available);
            setResult(result, returnData);
        }
        finish();
    }

    public ArrayList<MivoqVoice> getVoices() {

        MivoqTTSSingleton engine= MivoqTTSSingleton.getInstance();

        if(!engine.hasContext())
            engine.setContext(this);

        ArrayList<MivoqVoice> voices= engine.getVoices();

        return voices;
    }


}