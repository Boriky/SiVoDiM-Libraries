package starklabs.sivodim;

import org.junit.Test;

import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Character.CharacterImpl;
import starklabs.sivodim.Drama.Model.Utilities.Avatar;

import static org.junit.Assert.*;

/**
 * Created by Enrico on 23/06/16.
 */
public class TU43 {
    @Test
    public void testEditCharacter(){
        Avatar avatar = new Avatar("/Users/Enrico/Desktop/test/prova.png");
        String nome = "nome";
        String voce = "voce";

        Character character = new CharacterImpl.CharacterBuilder()
                .setName(nome)
                .setAvatar(avatar)
                .setVoice(voce).build();

        String nomeModificato = "nomeModificato";
        Avatar avatarModificato = new Avatar("/Users/Enrico/Desktop/test/prova2.png");
        String voceModificata = "VoceModificata";

        character.setName(nomeModificato);
        character.setAvatar(avatarModificato);
        character.setVoice(nomeModificato);

        boolean check = true;

        if(character.getName().equals(nome)){
            check = false;
        }

        if(character.getAvatar().equals(avatar)){
            check = false;
        }

        if(character.getVoiceID().equals(voce)){
            check = true;
        }

        assertEquals(true, check);
    }
}
