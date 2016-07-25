package starklabs.sivodim.Drama.Model.Chapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import starklabs.sivodim.Drama.Model.Utilities.Background;
import starklabs.sivodim.Drama.Model.Utilities.SoundFx;
import starklabs.sivodim.Drama.Model.Utilities.Soundtrack;

/**
 * Created by Riccardo Rizzo on 25/05/2016.
 */
public class ChapterImpl implements Chapter{
    /**
     * String that represents the title of the chapter
     */
    private String title;
    /**
     * Background object that represents the background image of the chapter
     */
    private Background background;
    /**
     * Soundtrack object that represents the soundtrack audio of the chapter
     */
    private Soundtrack soundtrack;
    /**
     * SoundFx object that represents the sound effect positioned at the end of the chapter
     */
    private SoundFx soundFx;
    /**
     * List container that contains all the speeches created inside a chapter
     */
    private List<Speech> speeches=null;

    /* ----- BUILDER DESIGN PATTERN ----- */

    /**
     * ChapterBuilder: internal static class for building Chapter objects (BUILDER DESIGN PATTERN)
     */
    // internal static class for building Chapter objects
    public static class ChapterBuilder {
        // required parameters
        /**
         * Builder title attribute
         */
        private String titleB;

        // optional parameters
        /**
         * Builder background attribute
         */
        private Background backgroundB = null;
        /**
         * Builder soundtrack attribute
         */
        private Soundtrack soundtrackB = null;
        /**
         * Builder soundFx attribute
         */
        private SoundFx soundFxB = null;
        /**
         * Builder ArrayList of Speech objects attribute
         */
        private ArrayList<Speech> speechesB= new ArrayList<>();

        /* ------ SETTER BUILDER ------ */

        /**
         * Set the title for the chapter
         * @param title the title to set
         * @return
         */
        public ChapterBuilder setTitle(String title) {
            this.titleB = title;
            return this;
        }

        /**
         * Set the chapter's background
         * @param background the background to set
         * @return
         */
        public ChapterBuilder setBackground(Background background) {
            this.backgroundB = background;
            return this;
        }

        /**
         * Set the soundtrack to the chapter
         * @param soundtrack the soundtrack to set
         * @return
         */
        public ChapterBuilder setSoundtrack(Soundtrack soundtrack) {
            this.soundtrackB = soundtrack;
            return this;
        }

        /**
         * Set the SoundFx to the chapter
         * @param soundFx the sound effect to set
         * @return
         */
        public ChapterBuilder setSoundFx(SoundFx soundFx) {
            this.soundFxB = soundFx;
            return this;
        }


        /**
         * Build the chapter
         * @return
         */
        public ChapterImpl build() {
            if(titleB!=null) {
                return new ChapterImpl(this);
            }
            return null;
        }
    }

    /* ----- CONSTRUCTOR ----- */

    /**
     * Create object with Builder
     * @param builder
     */
    private ChapterImpl(ChapterBuilder builder) {
        // required parameters
        title = builder.titleB;

        // optional parameters
        background = builder.backgroundB;
        soundtrack = builder.soundtrackB;
        soundFx = builder.soundFxB;
        speeches = builder.speechesB;
    }

    /* ----- UTILITIES METHODS ----- */

    /**
     * Get the iterator of the speech list container (necessary to iterate through the list)
     * @return
     */
    @Override
    public ListIterator<Speech> getSpeechIterator(){
        return speeches.listIterator();
    }

    /**
     * Return the duration of the chapter in milliseconds
     * @return
     */
    @Override
    public long getDuration(){
        Iterator<Speech> iterator=getSpeechIterator();
        int dur=0;
        while (iterator.hasNext()){
            Speech speech=iterator.next();
            dur+=speech.getDuration();
        }
        return dur;
    }

    @Override
    public int getSpeechNumber() {
        return speeches.size();
    }

    /**
     * Move the selected speech (position) down in the list
     * @param position the position of the speech to move up
     */
    @Override
    public void moveUpSpeech(int position) {
        if(position>0){
            Speech speech=speeches.remove(position);
            speeches.add(position-1,speech);
        }
    }


    /**
     * Move the selected speech (position) up in the list
     * @param position the position of the speech to move down
     */
    @Override
    public void moveDownSpeech(int position) {
        Speech speech=speeches.remove(position);
        if(position<speeches.size())
            speeches.add(position+1,speech);
        else
            speeches.add(speech);
    }

    /**
     * Add new speech: push back new speech to ArrayList containing existing speeches
     * @param speech The speech to add
     */
    @Override
    public void addSpeech(Speech speech) {
        speeches.add(speech);
    }

    /* ----- GETTER METHODS ----- */

    /**
     * Return the title of the chapter
     * @return
     */
    @Override
    public String getTitle(){ return title; }

    /**
     * Return the soundtrack of the chapter (audio)
     * @return
     */
    @Override
    public Soundtrack getSoundtrack(){
        return soundtrack;
    }

    /**
     * Return the background of the chapter (image)
     * @return
     */
    @Override
    public Background getBackground(){
        return this.background;
    }

    /* ----- SETTER METHODS ----- */

    /**
     * Edit existing title or set new one
     * @param title The title to set
     */
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Edit existing background or set new one
     * @param background
     */
    @Override
    public void setBackground(Background background) {
        this.background = background;
    }

    /**
     * Edit existing soundtrack or set new one
     * @param soundtrack The soundtrack to set
     */
    @Override
    public void setSoundtrack(Soundtrack soundtrack) {
        this.soundtrack = soundtrack;
    }

    /* ----- DELETE METHODS ----- */

    /**
     * Delete existing speech
     * @param speech a reference of the speech to delete
     */
    @Override
    public void deleteSpeech(Speech speech) {
        speeches.remove(speech);
    }

    /**
     * Delete existing background
     */
    @Override
    public void deleteBackground() {
        background=null;
    }

    /**
     * Delete existing soundtrack
     */
    @Override
    public void deleteSoundtrack() {
        soundtrack=null;
    }
}
