package starklabs.sivodim.Drama.Model.Chapter;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import starklabs.sivodim.Drama.Model.Utilities.Background;
import starklabs.sivodim.Drama.Model.Utilities.Soundtrack;

/**
 * Created by Riccardo Rizzo on 25/05/2016.
 */
public interface Chapter {

    // ----------------------------- GETTER ----------------------------------------------

    /**
     * Gives an Iterator to iterate among the speeches of the chapter
     * @return
     */
    ListIterator<Speech> getSpeechIterator();

    /**
     * Gives the title of the chapter
     * @return
     */
    String getTitle();

    /**
     * Gives the soundtrack of the chapter
     * @return
     */
    Soundtrack getSoundtrack();

    /**
     * Gives the image background of the chapter
     * @return
     */
    Background getBackground();


    long getDuration();

    int getSpeechNumber();

    // ----------------------------- SETTER ----------------------------------------------

    /**
     * Sets the soundtrack of the chapter
     * @param soundtrack The soundtrack to set
     */
    void setSoundtrack(Soundtrack soundtrack);

    /**
     * Sets the title of the chapter
     * @param title The title to set
     */
    void setTitle(String title);

    /**
     * Sets the image background of the chapter
     * @param background
     */
    void setBackground(Background background);


    // ----------------------------- UTILITIES ----------------------------------------------

    /**
     * Adds a speech in the end of the chapter
     * @param speech The speech to add
     */
    void addSpeech(Speech speech);//push_back

    /**
     * delete a Speech from the chapter
     * @param speech a reference of the speech to delete
     */
    void deleteSpeech(Speech speech);

    /**
     * Delete the background of the chapter
     */
    void deleteBackground();

    /**
     * Delete the soundtrack of the chapter
     */
    void deleteSoundtrack();

    /**
     * Move up a speech in the chapter's order
     * @param position the position of the speech to move up
     */
    void moveUpSpeech(int position);

    /**
     * Move down a speech in the chapter's order
     * @param position the position of the speech to move down
     */
    void moveDownSpeech(int position);
}
