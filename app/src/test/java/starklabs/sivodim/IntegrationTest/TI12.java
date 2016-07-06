package starklabs.sivodim.IntegrationTest;

import android.content.Context;
import android.test.InstrumentationTestCase;

import org.junit.Test;

import java.io.File;

import starklabs.sivodim.Drama.Model.Utilities.Avatar;
import starklabs.sivodim.Drama.Model.Utilities.Background;
import starklabs.sivodim.Drama.Model.Utilities.Soundtrack;
import starklabs.sivodim.Drama.Model.Utilities.SpeechSound;

/**
 * Created by AlbertoAndriolo on 06/07/2016.
 */
public class TI12 extends InstrumentationTestCase {
    @Test
    public void testUtilites() {
        Context context=getInstrumentation().getContext();
        File backgroundFile=new File(context.getFilesDir(), "background.png");
        Background background=new Background(backgroundFile.getAbsolutePath());

        File soundtrackFile=new File(context.getFilesDir(), "soundtrack.mp3");
        Soundtrack soundtrack=new Soundtrack(soundtrackFile.getAbsolutePath());

        File avatarFile = new File(context.getFilesDir(), "avatar.png");
        Avatar avatar=new Avatar(avatarFile.getAbsolutePath());

        File speechSoundFile = new File(context.getFilesDir(), "speechSound.png");
        SpeechSound speechSound= new SpeechSound(speechSoundFile.getAbsolutePath());

    }
}
