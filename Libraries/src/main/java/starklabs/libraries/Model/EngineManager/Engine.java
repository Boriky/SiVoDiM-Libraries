package starklabs.libraries.Model.EngineManager;

import java.util.ArrayList;

import starklabs.libraries.Model.Voice.MivoqVoice;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */
public interface Engine {
    ArrayList<MivoqVoice> getVoices();

    /**
     *
     * @param path where to save the synthesized file
     * @param voiceID name of the voice
     * @param myEmotion {NONE, HAPPINESS, DISGUST, ANGER, FEAR, SURPRISE, SADNESS}
     * @param text synthesized text
     * @param myListener wrapper for the callback at the end of the synthesis
     */
    void synthesizeToFile (String path, String voiceID, String myEmotion, String text, Listener myListener);

    /**
     *
     * @param voiceID name of the voice
     * @param text synthesized text
     */
    void speak(String voiceID, String text);
    MivoqVoice createVoice(String name, String gender, String myLanguage);
    void removeVoice(int index);
    interface Listener {
        void onCompleteSynthesis();
    }
    void save();
    MivoqVoice getVoiceByName(String s);
}


