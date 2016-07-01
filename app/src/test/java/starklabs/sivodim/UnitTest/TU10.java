package starklabs.sivodim.UnitTest;

/**
 * Created by GINO on 27/06/2016.
 */

import org.junit.Test;

import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Character.CharacterImpl;
import starklabs.sivodim.Drama.Model.Screenplay.Screenplay;
import starklabs.sivodim.Drama.Model.Screenplay.ScreenplayImpl;

import static org.junit.Assert.assertEquals;

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
