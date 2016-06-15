package starklabs.sivodim.Drama.Presenter;

import android.content.Context;

import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Utilities.Avatar;
import starklabs.sivodim.Drama.View.EditCharacterInterface;
import starklabs.sivodim.Drama.View.ListCharacterInterface;
import starklabs.sivodim.Drama.View.NewCharacterInterface;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public interface CharacterPresenter {

    // ----------------------------- ACTIVITY ----------------------------------------------

    /**
     * Set up a link to a related {@link ListCharacterInterface}
     * @param listCharacterInterface
     */
    void setActivity(ListCharacterInterface listCharacterInterface);

    /**
     * Set up a link to a related {@link NewCharacterInterface}
     * @param newCharacterInterface
     */
    void setActivity(NewCharacterInterface newCharacterInterface);

    /**
     * Set up a link to a related {@link EditCharacterInterface}
     * @param editCharacterInterface
     */
    void setActivity(EditCharacterInterface editCharacterInterface);


    // ----------------------------- GETTER ----------------------------------------------

    /**
     * To obtain the current character related the presenter
     * @return
     */
    Character getCharacter();

    /**
     * Method to obtain the custom ArrayAdapter for characters which contains the current
     * characters of the screenplay
     * @param context
     * @return
     */
    CharacterArrayAdapter getCharacterArrayAdapter(Context context);

    /**
     * Gives the name of the project (the title of the screenplay)
     * @return
     */
    String getProjectName();


    // ----------------------------- UTILITIES ----------------------------------------------

    /**
     * Add a character in the screenplay
     * @param name the name of the character
     * @param voice the name (id) of the voice for the character
     * @param avatar an optional avatar for the character
     */
    void newCharacter(String name, String voice, Avatar avatar);


    // ----------------------------- MOVE ------------------------------------------------

    /**
     * Moves to the EditCharacterActivity
     * @param context
     * @param character
     */
    void goToEditCharacterActivity(Context context,Character character);

    /**
     * Moves to the NewCharacterActivity
     * @param context
     */
    void goToNewCharacterActivity(Context context);

    /**
     * Moves to the ListCharacterActivity
     * @param context
     */
    void goToListCharacterActivity(Context context);
}
