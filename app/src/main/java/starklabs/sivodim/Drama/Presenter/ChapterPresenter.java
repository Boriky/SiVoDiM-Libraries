package starklabs.sivodim.Drama.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.View.EditChapterActivity;
import starklabs.sivodim.Drama.View.EditChapterInterface;
import starklabs.sivodim.Drama.View.ListSpeechesInterface;
import starklabs.sivodim.Drama.View.NewCharacterActivity;
import starklabs.sivodim.Drama.View.NewSpeechActivity;
import starklabs.sivodim.Drama.View.NewSpeechInterface;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public interface ChapterPresenter {

    void orderSpeech();//>>>>>>>>>>>>>>>>>>>>NOT YET IMPLEMENTED <<<<<<<<<<<<<<

    // ----------------------------- ACTIVITY ----------------------------------------------

    /**
     * Set up a link to a related {@link ListSpeechesInterface}
     * @param listSpeechesInterface The {@link ListSpeechesInterface} which uses the {@link ChapterPresenter}
     */
    void setActivity(ListSpeechesInterface listSpeechesInterface);

    /**
     * Set up a link to a related {@link EditChapterInterface}
     * @param editChapterInterface The {@link EditChapterInterface} which uses the {@link ChapterPresenter}
     */
    void setActivity(EditChapterInterface editChapterInterface);

    /**
     * Set up a link to a related {@link NewSpeechInterface}
     * @param newSpeechInterface The {@link NewSpeechInterface} which uses the {@link ChapterPresenter}
     */
    void setActivity(NewSpeechInterface newSpeechInterface);


    // ----------------------------- GETTER -----------------------------------------------

    /**
     * Method to obtain the custom ArrayAdapter for speeches which contains the current speeches of the chapter
     * @param context
     * @return
     */
    SpeechArrayAdapter getSpeeches(Context context);

    /**
     * Gives an ArrayAdapter of String with the name of the current characters of the screenplay
     * @param context
     * @return
     */
    ArrayAdapter<String> getCharactersAdapter(Context context);

    /**
     * Returns the title of the {@link Chapter} memorized in the presenter, or a null reference
     * if it is not initialized
     * @return
     */
    String getChapterTitle();

    /**
     * Gives the last selected speech in ListSpeechesActvity
     * @return
     */
    int getSpeechSelected();

    // ----------------------------- SETTER ------------------------------------------------

    /**
     * Set the chapter's title
     * @param title The title for the chapter
     */
    void setChapterTitle(String title);

    /**
     * select the speech in ListSpeechesActivity
     * @param index the index in SpeechArrayAdapter
     */
    void setSpeechSelected(int index);


    // ----------------------------- UTILITIES ------------------------------------------------

    /**
     * Add a speech in the chapter
     * @param text The text of the speech
     * @param chatacterName The name of the character who says the speech
     * @param emotion the emotion that determine the synthesis parameters
     */
    void newSpeech(String text,String chatacterName,String emotion);

    /**
     * Delete a speech from the chapter if it is inside
     * @param speech
     */
    void deleteSpeech(Speech speech);

    // ----------------------------- MOVE ------------------------------------------------

    /**
     * Moves to the EditSpeechActivity
     * @param context
     * @param selected The speech selected to be changed
     */
    void goToEditSpeechActivity(Context context,Speech selected);

    /**
     *Moves to the activity with the list of the characters
     * @param context
     */
    void goToListCharactersActivity(Context context);

    /**
     * Moves to {@link EditChapterActivity}
     * @param context
     */
    void goToEditChapterActivity(Context context);

    /**
     * Moves to {@link NewCharacterActivity}
     * @param context
     */
    void goToNewCharacterActivity(Context context);

    /**
     * Moves to {@link NewSpeechActivity}
     * @param context
     */
    void goToNewSpeechActivity(Context context);
}
