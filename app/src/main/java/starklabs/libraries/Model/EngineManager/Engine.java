package starklabs.libraries.Model.EngineManager;

import android.os.Bundle;

import java.io.File;
import java.util.Vector;

import starklabs.libraries.Model.Voice.Voice;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */
public interface Engine {
    Voice getVoice();

    Vector<String> getLocalesList();

    Vector<String> getVoiceList();

    Vector<String> getStylesList();

    Vector<String> getEffectList();

    int synthesizeToFile(CharSequence text, Bundle params, File file, String utteranceId);
}


