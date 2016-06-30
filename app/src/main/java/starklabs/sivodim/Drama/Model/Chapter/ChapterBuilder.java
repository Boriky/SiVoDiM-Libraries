package starklabs.sivodim.Drama.Model.Chapter;

import starklabs.sivodim.Drama.Model.Utilities.Background;
import starklabs.sivodim.Drama.Model.Utilities.SoundFx;
import starklabs.sivodim.Drama.Model.Utilities.Soundtrack;

/**
 * Created by Riccardo Rizzo on 25/05/2016.
 */
public interface ChapterBuilder {

    // ----------------------------- SETTER ----------------------------------------------

    /**
     * Sets the title for the chapter
     * @param title Title for the chapter
     */
    void setTitle(String title);

    /**
     * Sets the image background for the chapter
     * @param background The {@link Background} for the chapter
     */
    void setBackground(Background background);

    /**
     * Sets the SoundFx for the chapter
     * @param soundFx The {@link SoundFx} for the chapter
     */
    void setSoundFx(SoundFx soundFx);

    /**
     * Sets the Soundtrack for the chapter
     * @param soundTrack The {@link Soundtrack} for the chapter
     */
    void setSoundTrack(Soundtrack soundTrack);


    // ----------------------------- UTILITIES ----------------------------------------------

    /**
     * Build the chapter
     * @return
     */
    Chapter getResult();

}
