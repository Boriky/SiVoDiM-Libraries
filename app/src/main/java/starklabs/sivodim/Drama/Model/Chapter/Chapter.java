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

    // >>>>>>>>>>>>>>>> NOT YET IMPLEMENTED <<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    void deleteBackground();
    void deleteSoundtrack();
    void moveUpSpeech(ListIterator<Speech> iterator);
    void moveDownSpeech(ListIterator<Speech> iterator);
}
