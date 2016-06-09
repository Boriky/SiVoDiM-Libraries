package starklabs.sivodim.Drama.Model.Screenplay;

import java.util.Iterator;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Character.Character;

/**
 * Created by io on 25/05/2016.
 */
public interface Screenplay {
    void export();
    void share();
    void addChapter(Chapter chapter);
    void addCharacter(Character character);
    void removeCharacter(Character character);
    void importCharacters(Screenplay screenplay);
    CharacterContainer getCharacters();
    Iterator<Chapter> getChapterIterator();
    Character getCharacterByName(String name);
    String getTitle();
    Chapter getChapter(String title);
}
