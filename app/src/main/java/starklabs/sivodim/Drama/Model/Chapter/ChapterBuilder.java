package starklabs.sivodim.Drama.Model.Chapter;

import starklabs.sivodim.Drama.Model.Utilities.Background;
import starklabs.sivodim.Drama.Model.Utilities.SoundFx;
import starklabs.sivodim.Drama.Model.Utilities.Soundtrack;

/**
 * Created by Riccardo Rizzo on 25/05/2016.
 */
public interface ChapterBuilder {
    void setTitle(String title);
    void setBackground(Background background);
    void setSoundFx(SoundFx soundFx);
    void setSoundTrack(Soundtrack soundTrack);
    void loadSpeeches();
    Chapter getResult();

}
