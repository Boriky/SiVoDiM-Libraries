package starklabs.sivodim.Drama.Presenter;

import android.content.Context;

import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.View.EditCharacterInterface;
import starklabs.sivodim.Drama.View.ListCharacterInterface;

/**
 * Created by io on 25/05/2016.
 */
public interface CharacterPresenter {
    void newCharacter();
    Character getCharacter();
    void setActivity(ListCharacterInterface listCharacterInterface);
    CharacterArrayAdapter getCharacterArrayAdapter(Context context);
    void goToEditCharacterActivity(Context context,Character character);
    void setActivity(EditCharacterInterface editCharacterInterface);
}
