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
    private String title;
    private Background background;
    private Soundtrack soundtrack;
    private SoundFx soundFx;
    private List<Speech> speeches=null;

    public static class ChapterBuilder {
        // required parameters
        private String titleB;

        // optional parameters
        private Background backgroundB = null;
        private Soundtrack soundtrackB = null;
        private SoundFx soundFxB = null;
        private ArrayList<Speech> speechesB= new ArrayList<>();

        //-------------------- setter --------------------

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
         * set the SoundFx to the chapter
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

    private ChapterImpl(ChapterBuilder builder) {
        // required parameters
        title = builder.titleB;

        // optional parameters
        background = builder.backgroundB;
        soundtrack = builder.soundtrackB;
        soundFx = builder.soundFxB;
        speeches = builder.speechesB;
    }

    // methods for chapter management
    @Override
    public ListIterator<Speech> getSpeechIterator(){
        return speeches.listIterator();
    }


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
    public void moveUpSpeech(int position) {
        if(position>0){
            Speech speech=speeches.remove(position);
            speeches.add(position-1,speech);
        }
    }

    @Override
    public void moveDownSpeech(int position) {
        Speech speech=speeches.remove(position);
        if(position<speeches.size())
            speeches.add(position+1,speech);
        else
            speeches.add(speech);
    }

    @Override
    public String getTitle(){
        return title;
    }

    @Override
    public Soundtrack getSoundtrack(){
        return soundtrack;
    }

    @Override
    public Background getBackground(){
        return this.background;
    }

    // add new speech: push back new speech to ArrayList containing existing speeches
    @Override
    public void addSpeech(Speech speech) {
        speeches.add(speech);
    }

    // setter methods: edit existing parameters or set new values (title, background, soundtrack)
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setBackground(Background background) {
        this.background = background;
    }

    @Override
    public void setSoundtrack(Soundtrack soundtrack) {
        this.soundtrack = soundtrack;
    }

    // delete methods: delete existing parameters (speech, background, soundtrack)
    @Override
    public void deleteSpeech(Speech speech) {
        speeches.remove(speech);
    }

    @Override
    public void deleteBackground() {
        background=null;
    }

    @Override
    public void deleteSoundtrack() {
        soundtrack=null;
    }
}
