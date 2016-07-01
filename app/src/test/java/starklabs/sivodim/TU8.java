package starklabs.sivodim;

import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Screenplay.CharacterContainer;
import starklabs.sivodim.Drama.Model.Screenplay.Screenplay;
import starklabs.sivodim.Drama.Model.Screenplay.ScreenplayImpl;
import starklabs.sivodim.Drama.Model.Utilities.Avatar;
import starklabs.sivodim.Drama.Presenter.CharacterPresenter;
import starklabs.sivodim.Drama.Presenter.CharacterPresenterImpl;

/**
 * Created by Francesco Bizzaro on 21/06/2016.
 */

public class TU8 {
    @Test
    public void testAddCharacter(){
        Screenplay screenplay=new ScreenplayImpl("titolo",0);
        Character character=Mockito.mock(Character.class);
        String name="NomePersonaggio";
        when(character.getName()).thenReturn(name);
        screenplay.addCharacter(character);
        assertEquals(name,screenplay.getCharacterByName(name).getName());

        String name2="NomePersonaggio2";
        String title="Titolo";
        String voice="VoiceName";
        Avatar avatar=Mockito.mock(Avatar.class);
        CharacterPresenter characterPresenter=new CharacterPresenterImpl(screenplay.getCharacters(),title);
        characterPresenter.newCharacter(name2,voice,avatar);
        assertEquals(name2,screenplay.getCharacterByName(name2).getName());
        assertEquals(voice,screenplay.getCharacterByName(name2).getVoiceID());
        assertEquals(avatar,screenplay.getCharacterByName(name2).getAvatar());
    }

}
