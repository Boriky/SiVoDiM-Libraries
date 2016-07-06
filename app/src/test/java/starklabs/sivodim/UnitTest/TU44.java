package starklabs.sivodim.UnitTest;

/**
 * Created by GINO on 23/06/2016.
 */

import org.junit.Test;

import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Character.CharacterImpl;
import starklabs.sivodim.Drama.Model.Utilities.Avatar;
import static org.junit.Assert.assertEquals;
/**
 * Test TU44 that tests character getter methods functionality.
 */
public class TU44 {
    @Test
    public void testCharacterGetter() {
        String name = "character";
        String path = "C:/Desktop/prova.png";
        String voice = "id";
        Avatar avatar = new Avatar(path);
        Character character = new CharacterImpl.CharacterBuilder()
                .setName(name)
                .setAvatar(avatar)
                .setVoice(voice).build();

        boolean check = true;

        if (!character.getName().equals(name)) {
            check = false;
        }

        if (!character.getAvatar().getPath().equals(path)) {
            check = false;
        }

        if (!character.getVoiceID().equals(voice)) {
            check = false;
        }



        assertEquals(true, check);
    }
}
