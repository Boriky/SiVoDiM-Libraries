package starklabs.libraries.Presenter;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.View.HomeActivityInterface;

/**
 * Created by AlbertoAndriolo on 26/05/2016.
 */
public class HomePresenterImpl implements HomePresenter {

    private Engine engine;

    private VoicePresenter voicePresenter;

    private VoiceListPresenter voiceListPresenter;

    private HomeActivityInterface homeActivityInterface;

    public HomePresenterImpl(Engine engine) {
        this.engine = engine;
    }
}
