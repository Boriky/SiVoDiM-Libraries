package starklabs.sivodim;
import org.junit.Test;
import org.mockito.Mockito;

import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Screenplay.ScreenplayImpl;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

/**
 * Created by Enrico on 21/06/16.
 */
public class TU9 {
    @Test
    public void testRemoveCharacter(){
        ScreenplayImpl screenplay=new ScreenplayImpl("titolo");
        Character character=Mockito.mock(Character.class);
        String name="nome";
        when(character.getName()).thenReturn(name);

        screenplay.addCharacter(character);
        assertEquals(character,screenplay.getCharacterByName(name));
        screenplay.removeCharacter(character);
        assertEquals(null,screenplay.getCharacterByName(name));

    }
}
