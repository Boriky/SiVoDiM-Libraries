package starklabs.libraries.IntegrationTest;

import android.content.Context;
import android.test.InstrumentationTestCase;

import org.junit.Test;

import java.util.ArrayList;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.EngineManager.EngineImpl;
import starklabs.libraries.Model.Voice.MivoqVoice;
import starklabs.libraries.Presenter.VoiceListPresenter;
import starklabs.libraries.Presenter.VoiceListPresenterImpl;
import starklabs.libraries.Presenter.VoicePresenter;
import starklabs.libraries.Presenter.VoicePresenterImpl;

/**
 * Created by AlbertoAndriolo on 06/07/2016.
 */
public class TI7 extends InstrumentationTestCase {
    @Test
    public void testPresenters(){
        Context context=getInstrumentation().getContext();
        Engine engine=new EngineImpl(context);
        MivoqVoice voice=engine.createVoice("myVoice","male","it");

        VoicePresenter voicePresenter=new VoicePresenterImpl(voice, engine);

        voicePresenter.setVoiceName("newVoiceName");
        assertEquals(voice.getName(), voicePresenter.getEngine().getVoiceByName("newVoiceName").toString());

        voicePresenter.setDefaultVoice(0);
        assertEquals(0, voicePresenter.isDefaultVoice());

        //set language in position 1: English
        voicePresenter.setLanguage(1);
        assertEquals(1, voicePresenter.getLanguage());

        //set gender in position 0: Male
        voicePresenter.setGender(0);
        assertEquals(0, voicePresenter.getGender());

        VoiceListPresenter voiceListPresenter=new VoiceListPresenterImpl(engine);
        ArrayList<MivoqVoice> listVoice = voicePresenter.getEngine().getVoices();
        assertNotNull(listVoice);
        voiceListPresenter.setVoiceSelected(0, "newVoiceName");
        assertEquals(0, voiceListPresenter.getVoiceSelected());

        voiceListPresenter.deleteVoiceSelected(context);
    }
}
