package starklabs.sivodim.Drama.Model.Chapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.EngineManager.EngineImpl;
import starklabs.libraries.Model.Mivoq.MivoqTTSSingleton;
import starklabs.libraries.Model.Voice.MivoqVoice;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Utilities.SoundFx;
import starklabs.sivodim.Drama.Model.Utilities.SpeechSound;
import starklabs.sivodim.R;

/**
 * Created by Riccardo Rizzo on 25/05/2016.
 */
public class SpeechImpl implements Speech {
    /**
     * String that represents the text of a speech
     */
    private String text;
    /**
     * String that represents the emotion associated to a speech
     */
    private String emotionID;
    /**
     * Character object that represents the character associated to a speech
     */
    private Character character;
    /**
     * SoundFx object that represents the sound effect associated to a speech
     * The sound effect plays at the end of the speech
     */
    private SoundFx soundFx;
    /**
     * String that represents the audio path of the audio file obtained by the text synthesized through FA-TTS
     */
    private String audioPath;
    /**
     * If the boolean is true, the speech has already been synthesized and has an audio file associated
     */
    private boolean audioStatus;

    /* ----- BUILDER DESIGN PATTERN ----- */

    /**
     * SpeechBuilder: internal static class for building Speech objects (BUILDER DESIGN PATTERN)
     */
    public static class SpeechBuilder {
        // required parameters
        /**
         * Builder text attribute
         */
        private String textB;

        // optional parameters
        /**
         * Builder emotion attribute
         */
        private String emotionIDB;
        /**
         * Builder character attribute
         */
        private Character characterB;
        /**
         * Builder soundFx attribute
         */
        private SoundFx soundFxB;
        /**
         * Builder audioPath attribute
         */
        private String audioPathB;
        /**
         * Builder audioStatus attribute
         */
        private boolean audioStatusB;

        /* ----- SETTER BUILDER ----- */

        /**
         * Set the Speech's text
         * @param text
         * @return
         */
        public SpeechBuilder setText(String text) {
            this.textB = text;
            return this;
        }

        /**
         * Set the Speech's emotion
         * @param emotionID
         * @return
         */
        public SpeechBuilder setEmotion(String emotionID) {
            this.emotionIDB = emotionID;
            return this;
        }

        /**
         * Set the Speech's character
         * @param character
         * @return
         */
        public SpeechBuilder setCharacter(Character character) {
            this.characterB = character;
            return this;
        }

        /**
         * Set the Speech's SoundFx (sound effect between speeches)
         * @param soundFx
         * @return
         */
        public SpeechBuilder setSoundFX(SoundFx soundFx) {
            this.soundFxB = soundFx;
            return this;
        }

        /**
         * Set Speech's audio path (the audio path corresponds to the synthesized text returned by MIVOQ's server)
         * @param audioPath
         * @return
         */
        public SpeechBuilder setAudioPath(String audioPath) {
            this.audioPathB = audioPath;
            return this;
        }

        /**
         * Set Speech's audio status (audio status tells if the speech object has been already synthesized)
         * @param audioStatus
         * @return
         */
        public SpeechBuilder setAudioStatus(boolean audioStatus) {
            this.audioStatusB = audioStatus;
            return this;
        }

        /**
         * Build the Speech
         * @return
         */
        public SpeechImpl build() {
            if(textB!=null) {
                return new SpeechImpl(this);
            }
            return null;
        }
    }

    /* ----- CONSTRUCTOR ----- */

    /**
     * Create object with Builder
     * @param builder
     */
    private SpeechImpl(SpeechBuilder builder) {
        // required parameters
        text = builder.textB;

        // optional parameters
        emotionID = builder.emotionIDB;
        character = builder.characterB;
        soundFx = builder.soundFxB;
        audioPath = builder.audioPathB;
        audioStatus = false;
    }

    /* ----- UTILITIES METHODS ----- */

    /**
     * Synthesize the text of a Speech object and create an audio file in device memory
     * @param context the application context
     * @param path the path where save
     */
    @Override
    public void synthesizeAudio(Context context, final String path) {
        setAudioStatus(false);
        Engine engine=new EngineImpl(context);
        File file=new File(path);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdir();
        }
        engine.synthesizeToFile(path,
                getCharacter().getVoiceID(),
                getEmotion(),
                getText(),
                new Engine.Listener() {
                    @Override
                    public void onCompleteSynthesis() {
                        setAudioPath(path);
                        setAudioStatus(true);
                        System.out.println("Sintesi finita");
                    }
                });
    }

    /* ----- SETTER METHODS ----- */

    /**
     * Edit existing text or set new one
     * @param text Text to the speech
     */
    @Override
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Edit existing emotion or set new one
     * @param emotionID
     */
    @Override
    public void setEmotion(String emotionID) {
        this.emotionID = emotionID;
    }

    /**
     * Edit existing character or set new one
     * @param character
     */
    @Override
    public void setCharacter(Character character) {
        this.character = character;
    }

    /**
     * Edit existing sound effect or set new one
     * @param soundFx
     */
    @Override
    public void setSoundFx(SoundFx soundFx) { this.soundFx = soundFx; }

    /**
     * Edit existing audio path or set new one
     * @param audioPath
     */
    @Override
    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    /**
     * Edit existing audio status or set new one
     * @param audioStatus
     */
    @Override
    public void setAudioStatus(boolean audioStatus) {
        this.audioStatus = audioStatus;
    }

    /* ----- GETTER METHODS ----- */

    /**
     * Return the Speech's text
     * @return
     */
    @Override
    public String getText() {
        return this.text;
    }

    /**
     * Return the Speech's emotion
     * @return
     */
    @Override
    public String getEmotion() { return this.emotionID; }

    /**
     * Return the Speech's duration
     * @return
     */
    @Override
    public long getDuration(){
        if(audioStatus==false)return 0L;
        if(audioPath==null || !(new File(audioPath).exists()))return 0L;
        return new SpeechSound(audioPath).getDuration();
    }

    /**
     * Return the Speech's character
     * @return
     */
    @Override
    public Character getCharacter() { return this.character; }

    @Override
    public void synthesizeAudio(Context context, final String path) {
        setAudioStatus(false);
        this.audioPath=path;
        Engine engine=new EngineImpl(context);
        File file=new File(path);
        if(!file.getParentFile().exists()){
                file.getParentFile().mkdir();
        }
        engine.synthesizeToFile(path,
                getCharacter().getVoiceID(),
                getEmotion(),
                getText(),
                new Engine.Listener() {
            @Override
            public void onCompleteSynthesis() {
                setAudioPath(path);
                setAudioStatus(true);
                System.out.println("Sintesi finita");
            }
        });
    }

    @Override
    public String getAudioPath() {
        /*int i=0;
        while (!audioStatus){
            i++;
            try {
                Thread.sleep(10);
                System.out.println("Sto scaricando..");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        if(i==500){
            synthesizeAudio();
        }
        }*/
        return audioPath;
    }

    /**
     * Return the Speech's audio status (audio status tells if the speech object has been already synthesized)
     * @return
     */
    @Override
    public boolean getAudioStatus() {
        return audioStatus;
    }

    /**
     * Return the emotions list (NONE, HAPPINESS, SADNESS, ANGER, FEAR, DISGUST, SURPRISE)
     * @return
     */
    public static ArrayList<String> getEmotions(){
        ArrayList<String> emotions = new ArrayList<String>();
        emotions.add("NONE");
        emotions.add("HAPPINESS");
        emotions.add("SADNESS");
        emotions.add("ANGER");
        emotions.add("FEAR");
        emotions.add("DISGUST");
        emotions.add("SURPRISE");
        return emotions;
    }


    /**
     * Return the voices contained in ArrayAdapter
     * @param context
     * @return
     */
    public static ArrayAdapter<String> getVoices(Context context){
        Vector<String> emotions = new Vector<String>();
        //callback to retrieve voices
        MivoqTTSSingleton mivoqTTSSingleton=MivoqTTSSingleton.getInstance();
        ArrayList<MivoqVoice>voices=mivoqTTSSingleton.getVoices();
        Iterator<MivoqVoice>iterator=voices.iterator();
        while (iterator.hasNext()){
            MivoqVoice voice=iterator.next();
            if(voice!=null)emotions.add(voice.getName());
        }
        return new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item,emotions);
    }
}
