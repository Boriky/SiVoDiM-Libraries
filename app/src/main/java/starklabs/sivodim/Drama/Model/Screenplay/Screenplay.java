package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;
import android.widget.TextView;

import java.util.Iterator;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Character.CharacterContainer;
import starklabs.sivodim.Drama.Model.Utilities.MutableInteger;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public interface Screenplay {

    /**
     * Export the screenplay and save it in a media file
     * @param type the type of exportation
     * @param context the application context
     */
    void export(String type, Context context,TextView feedback);

    /**
     * Add Chapter object to Screenplay
     * @param chapter
     */
    void addChapter(Chapter chapter);

    /**
     * Add Character object to Screenplay
     * @param character
     */
    void addCharacter(Character character);

    /**
     * Remove existing Character object from Screenplay
     * @param character
     */
    void removeCharacter(Character character);

    /**
     * Import Character list from an existing Screenplay object into a new Screenplay
     * @param screenplay
     */
    void importCharacters(Screenplay screenplay);

    /**
     * Return CharacterContainer from Screenplay object
     * @return
     */
    CharacterContainer getCharacters();

    /**
     * Return Chapter object's iterator
     * @return
     */
    Iterator<Chapter> getChapterIterator();

    /**
     * Return a Character object (from CharacterContainer) by its name
     * @param name
     * @return
     */
    Character getCharacterByName(String name);

    /**
     * Return Screenplay's title
     * @return
     */
    String getTitle();

    /**
     * Return Screenplay's audio path
     * @param context
     * @return
     */
    String getPath(Context context);

    /**
     * Return Chapter by its title
     * @param title
     * @return
     */
    Chapter getChapter(String title);

    /**
     * Move the chapter up from its current position
     * @param index
     */
    void moveUpChapter(int index);

    /**
     * Move the chapter down from its current position
     * @param index
     */
    void moveDownChapter(int index);

    /**
     * Remove Chapter object from Screenplay
     * @param index
     */
    void removeChapter(int index);

    /**
     * Utility Integer used to identify with an unique ID every Speech object
     * @return
     */
    MutableInteger getNextSpeechId();
}
