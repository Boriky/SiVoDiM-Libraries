package starklabs.libraries.Model.EngineManager;

import java.util.ArrayList;

import starklabs.libraries.Model.Voice.MivoqVoice;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */
public interface Engine {
    public ArrayList<MivoqVoice> getVoices();

    public void SynthesizeToFile (String Path, String VoiceID, String myEmotion, String Text, Listener myListener);
    public void Speak(String VoiceID, String Text);
    public MivoqVoice CreateVoice(String Name, String Gender, String myLanguage);
    public void RemoveVoice(int index);
    public interface Listener {
        public void OnCompleteSynthesis();
    }
}


