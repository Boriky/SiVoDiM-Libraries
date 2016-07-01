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


    void share(String where);
    void addChapter(Chapter chapter);
    void addCharacter(Character character);
    void removeCharacter(Character character);
    void importCharacters(Screenplay screenplay);
    CharacterContainer getCharacters();
    Iterator<Chapter> getChapterIterator();
    Character getCharacterByName(String name);
    String getTitle();
    String getPath(Context context);
    Chapter getChapter(String title);
    void moveUpChapter(int index);
    void moveDownChapter(int index);
    void removeChapter(int index);
    MutableInteger getNextSpeechId();
}
