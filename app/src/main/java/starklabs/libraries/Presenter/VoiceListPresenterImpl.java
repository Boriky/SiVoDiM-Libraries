package starklabs.libraries.Presenter;

import java.util.Vector;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.Voice.Voice;
import starklabs.libraries.View.VoiceListActivityInterface;

/**
 * Created by AlbertoAndriolo on 26/05/2016.
 */
public class VoiceListPresenterImpl implements VoiceListPresenter{

    private Engine engine;

    private VoiceListActivityInterface voiceListActivityInterface;

    @Override
    public Vector<Voice> getVoices() {
        return null;
    }
}
