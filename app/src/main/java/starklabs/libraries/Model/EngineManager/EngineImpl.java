package starklabs.libraries.Model.EngineManager;

import android.os.Bundle;

import java.io.File;
import java.util.Vector;

import starklabs.libraries.Model.Voice.Voice;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */
public class EngineImpl implements Engine{
    @Override
    public Voice getVoice() {
        return null;
    }

    @Override
    public Vector<String> getLocalesList() {
        return null;
    }

    @Override
    public Vector<String> getVoiceList() {
        return null;
    }

    @Override
    public Vector<String> getStylesList() {
        return null;
    }

    @Override
    public Vector<String> getEffectList() {
        return null;
    }

    @Override
    public int synthesizeToFile(CharSequence text, Bundle params, File file, String utteranceId) {
        return 0;
    }
}
