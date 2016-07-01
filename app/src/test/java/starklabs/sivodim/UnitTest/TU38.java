package starklabs.sivodim.UnitTest;

/**
 * Created by GINO on 23/06/2016.
 */

import org.junit.Test;

import java.util.Iterator;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.ChapterImpl;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Character.CharacterImpl;
import starklabs.sivodim.Drama.Model.Screenplay.Screenplay;
import starklabs.sivodim.Drama.Model.Screenplay.ScreenplayImpl;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenter;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenterImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test TU38 that tests the correct screenplay getter method functionality
 */
public class TU38 {
    @Test
    public void testScreenplayGetter(){
        String title="title";
        Screenplay screenplay=new ScreenplayImpl(title,0);
        String characterName="character";
        Character character=new CharacterImpl.CharacterBuilder()
                .setName(characterName).build();
        screenplay.addCharacter(character);
        String chapterName="chapter";
        Chapter chapter=new ChapterImpl.ChapterBuilder()
                .setTitle(chapterName).build();
        screenplay.addChapter(chapter);

        ScreenplayPresenter screenplayPresenter=new ScreenplayPresenterImpl(screenplay);

        boolean check=true;

        if(!screenplayPresenter.getScreenplayTitle().equals(screenplay.getTitle())){
            check=false;
        }

        if(!screenplay.getChapter(chapterName).getTitle().equals(chapter.getTitle())){
            check=false;
        }

        if(!screenplay.getCharacterByName(characterName).getName().equals(character.getName())){
            check=false;
        }
        Iterator<Chapter> it=screenplay.getChapterIterator();
        if(!it.next().getTitle().equals(chapter.getTitle())){
            check=false;
        }

        assertEquals(true,check);
    }
}
