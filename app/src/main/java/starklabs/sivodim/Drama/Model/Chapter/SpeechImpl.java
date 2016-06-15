package starklabs.sivodim.Drama.Model.Chapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.Iterator;
import java.util.Vector;

import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Utilities.SoundFx;
import starklabs.sivodim.Drama.Model.Utilities.SpeechSound;
import starklabs.sivodim.R;

/**
 * Created by Riccardo Rizzo on 25/05/2016.
 */
public class SpeechImpl implements Speech {
    private String text;
    private String emotionID;
    private Character character;
    private SoundFx soundFx;
    private String audioPath;
    private boolean audioStatus;

    public static class SpeechBuilder {
        // required parameters
        private String textB;

        // optional parameters
        private String emotionIDB;
        private Character characterB;
        private SoundFx soundFxB;
        private String audioPathB;
        private boolean audioStatusB;

        // setter
        public SpeechBuilder setText(String text) {
            this.textB = text;
            return this;
        }

        public SpeechBuilder setEmotion(String emotionID) {
            this.emotionIDB = emotionID;
            return this;
        }

        public SpeechBuilder setCharacter(Character character) {
            this.characterB = character;
            return this;
        }

        public SpeechBuilder setSoundFX(SoundFx soundFx) {
            this.soundFxB = soundFx;
            return this;
        }

        public SpeechBuilder setAudioPath(String audioPath) {
            this.audioPathB = audioPath;
            return this;
        }

        public SpeechBuilder setAudioStatus(boolean audioStatus) {
            this.audioStatusB = audioStatus;
            return this;
        }

        // return speech built by builder
        public SpeechImpl build() {
            if(textB!=null) {
                return new SpeechImpl(this);
            }
            return null;
        }
    }

    private SpeechImpl(SpeechBuilder builder) {
        // required parameters
        text = builder.textB;

        // optional parameters
        emotionID = builder.emotionIDB;
        character = builder.characterB;
        soundFx = builder.soundFxB;
        audioPath =builder.audioPathB;
    }

    // setter methods: edit existing parameters or set new values (text, emotionID, character, soundFx)
    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void setEmotion(String emotionID) {
        this.emotionID = emotionID;
    }

    @Override
    public void setCharacter(Character character) {
        this.character = character;
    }

    @Override
    public void setSoundFx(SoundFx soundFx) { this.soundFx = soundFx; }

    @Override
    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    @Override
    public void setAudioStatus(boolean audioStatus) {
        this.audioStatus = audioStatus;
    }


    // getter
    @Override
    public String getText() {
        return this.text;
    }


    @Override
    public String getEmotion() { return this.emotionID; }


    @Override
    public Character getCharacter() { return this.character; }

    @Override
    public void getAudio() {
        //call Libraries
    }

    @Override
    public String getAudioPath() {
        return audioPath;
    }

    @Override
    public boolean getAudioStatus() {
        return audioStatus;
    }


    //Class for speech audio preview
    public class PlaySpeech{

        private SpeechSound synthesis;

    }

    public static ArrayAdapter<String> getEmotions(Context context){
        Vector<String> emotions = new Vector<String>();
        //callback to retrieve emotions
        emotions.add("NONE");
        emotions.add("HAPPINESS");
        emotions.add("SADNESS");
        emotions.add("ANGER");
        emotions.add("FEAR");
        emotions.add("DISGUST");
        emotions.add("SURPRISE");
        return new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item,emotions);
    }

    public static ArrayAdapter<String> getVoices(Context context){
        Vector<String> emotions = new Vector<String>();
        //callback to retrieve emotions
        emotions.add("Voice1");
        emotions.add("Voice2");
        emotions.add("Voice3");
        emotions.add("Voice4");
        emotions.add("voice5");
        emotions.add("Voice6");
        return new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item,emotions);
    }


}
