package starklabs.sivodim;

/**
 * Created by GINO on 27/06/2016.
 */

import android.app.Activity;
import android.content.Context;

import org.junit.Test;
import org.mockito.Mockito;

import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Character.CharacterImpl;
import starklabs.sivodim.Drama.Model.Screenplay.Screenplay;
import starklabs.sivodim.Drama.Model.Screenplay.ScreenplayImpl;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenter;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenterImpl;
import starklabs.sivodim.Drama.View.NewScreenplayActivity;
import starklabs.sivodim.Drama.View.NewScreenplayInterface;
import static org.junit.Assert.*;

/**
 * Test TU10 that tests character import
 */
public class TU10 {

    @Test
    public void testImport(){
        Screenplay screenplay=new ScreenplayImpl("screenplay",0);
        Screenplay screenplay1=new ScreenplayImpl("screenplay1",0);
        String name="name";
        Character character=new CharacterImpl.CharacterBuilder()
                .setName(name).build();
        screenplay.addCharacter(character);
        screenplay1.importCharacters(screenplay);
        assertEquals(name,screenplay1.getCharacters().iterator().next().getName());


    }
}
