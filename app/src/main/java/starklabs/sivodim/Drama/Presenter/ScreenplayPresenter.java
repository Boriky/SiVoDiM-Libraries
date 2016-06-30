package starklabs.sivodim.Drama.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.Vector;

import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Screenplay.Screenplay;
import starklabs.sivodim.Drama.Model.Utilities.Background;
import starklabs.sivodim.Drama.Model.Utilities.Soundtrack;
import starklabs.sivodim.Drama.View.ListChapterInterface;
import starklabs.sivodim.Drama.View.NewChapterInterface;
import starklabs.sivodim.Drama.View.NewScreenplayInterface;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public interface ScreenplayPresenter {

    // ----------------------------- ACTIVITY ----------------------------------------------

    /**
     * Set up a link to a related {@link ListChapterInterface}
     * @param listChapterInterface The {@link ListChapterInterface} which uses this presenter
     */
    void setActivity(ListChapterInterface listChapterInterface);

    /**
     * Set up a link to a related {@link NewChapterInterface}
     * @param newChapterInterface The {@link NewChapterInterface} which uses this presenter
     */
    void setActivity(NewChapterInterface newChapterInterface);

    void setActivity(NewScreenplayInterface newChapterInterface);


    // ----------------------------- GETTER ----------------------------------------------

    /**
     * Give an ArrayAdapter of String with the titles of the chapters of a screenplay
     * @param context
     * @param sceenplay The screenplay to consider
     * @return
     */
    StringArrayAdapter getTitlesAdapter(Context context, String sceenplay);

    /**
     * Returns the title of the screenplay
     * @return
     */
    String getScreenplayTitle();

    /**
     * Returns the screenplay related to the ScreenplayPresenter
     * @return
     */
    Screenplay getScreenplay();

    /**
     * Gives the position of the current chapter selected
     * @return
     */
    int getChapterSelected();
    Vector<String> getStringArray();


    String getChapterSelectedName();

    // --------------------------- SETTER -------------------------------------

    void setChapterSelected(int index,String name);

    // ----------------------------- UTILITIES --------------------------------------------

    /**
     * To export the screenplay
     */
    void export();

    /**
     * To share the screenplay
     */
    void share();

    /**
     * Add a chapter in the end of the screenplay
     * @param title The title of the chapter
     */
    void newChapter(String title, Soundtrack soundtrack, Background background);

    /**
     * Create a new screenplay and save it to memory
     * @param title The title of the screenplay
     * @param context
     */
    void newScreenplay(String title,Context context);

    // check how drag and drop works for orderChapter implementation
    void moveUpChapter(int index);

    void moveDownChapter(int index);

    void removeChapter(int index);

    /**
     * Save a screenplay to memory in the default directory, with the title as name and ".scrpl" as extension
     * @param screenplay The screenplay to save
     * @param context
     * @return
     */
    boolean save(Screenplay screenplay, Context context);

    /**
     * Add a character in the screenplay
     * @param character the character to add
     */
    void addCharacter(Character character);

    /**
     * Import characters from another screenplay making a deep copy of them
     * @param screenplay The screenplay from which import characters
     * @param context
     */
    void importCharacter(String screenplay,Context context);



    // ----------------------------- MOVE -----------------------------------------------

    /**
     * Moves to a ListSpeechesActivity which shows the speeches of a selected chapter
     * @param context
     * @param selected The name of the chapter selected
     */
    void goToListSpeechesActivity(Context context,String selected);

    /**
     * Moves to a ListCharactersActivity that shows the list of characters of the screenplay
     * @param context
     */
    void goToListCharactersActivity(Context context);

    /**
     * Moves to an EditChapterActivity that permit to edit a selected chapter
     * @param context
     * @param selected The chapter to edit
     */
    void goToEditChapterActivity(Context context,String selected);

    /**
     * Moves to a NewChapterActivity in which it is possible to add a chapter in the screenplay
     * @param context
     */
    void goToNewChapterActivity(Context context);

    /**
     * Moves to a NewCharacterActivity in which it is possible to add a character in the screenplay
     * @param context
     */
    void goToNewCharacterActivity(Context context);

}