package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;

import java.util.Iterator;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Character.CharacterContainer;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public interface Screenplay {
    void export(String type, Context context);
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
}
