package starklabs.libraries.Presenter;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.Voice.Voice;
import starklabs.libraries.View.EditVoiceActivityInterface;
import starklabs.libraries.View.NewVoiceActivityInterface;

/**
 * Created by AlbertoAndriolo on 26/05/2016.
 */
public class VoicePresenterImpl implements VoicePresenter{

    private Voice voice;

    private Engine engine;

    private EditVoiceActivityInterface editVoiceActivityInterface;

    private NewVoiceActivityInterface newVoiceActivityInterface;

    @Override
    public Voice getVoice() {
        return null;
    }
}
