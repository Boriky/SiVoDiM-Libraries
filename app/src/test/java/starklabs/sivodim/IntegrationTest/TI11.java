package starklabs.sivodim.IntegrationTest;

import android.content.Context;
import android.test.InstrumentationTestCase;

import org.junit.Test;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.EngineManager.EngineImpl;
import starklabs.libraries.Model.Voice.MivoqVoice;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Character.CharacterImpl;
import starklabs.sivodim.Drama.Model.Utilities.Avatar;

/**
 * Created by Enrico on 07/07/16.
 */
public class TI11 extends InstrumentationTestCase {

    @Test
    public void testCharacter() {

        Context myContext= getInstrumentation().getContext();
        Engine mEngine = new EngineImpl(myContext);
        MivoqVoice voice=mEngine.createVoice("myVoice","male","it");
        MivoqVoice newVoice= mEngine.createVoice("fede","female","fr");

        Character mCharacter;
        CharacterImpl.CharacterBuilder builder= new CharacterImpl.CharacterBuilder();

        builder.setVoice("fede");
        builder.setName("Bella");
        builder.setAvatar(new Avatar("/avatar.png"));
        mCharacter= builder.build();

        assertNotNull(mCharacter);
        assertEquals(newVoice,mEngine.getVoiceByName(mCharacter.getVoiceID()));

    }
}
