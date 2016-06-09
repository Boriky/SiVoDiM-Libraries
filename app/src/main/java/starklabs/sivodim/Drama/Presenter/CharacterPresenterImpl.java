package starklabs.sivodim.Drama.Presenter;

import android.content.Context;
import android.content.Intent;

import java.util.Iterator;

import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Screenplay.CharacterContainer;
import starklabs.sivodim.Drama.View.EditCharacterActivity;
import starklabs.sivodim.Drama.View.EditCharacterInterface;
import starklabs.sivodim.Drama.View.EditSpeechActivity;
import starklabs.sivodim.Drama.View.ListChapterInterface;
import starklabs.sivodim.Drama.View.ListCharacterInterface;
import starklabs.sivodim.Drama.View.NewCharacterActivity;
import starklabs.sivodim.Drama.View.NewCharacterInterface;
import starklabs.sivodim.R;

/**
 * Created by io on 25/05/2016.
 */
public class CharacterPresenterImpl implements CharacterPresenter {
    CharacterContainer characterContainer;
    Character character;
    NewCharacterInterface characterInterface;
    ListCharacterInterface listCharacterInterface;
    EditCharacterInterface editCharacterInterface;

    public CharacterPresenterImpl(CharacterContainer characterContainer){
        this.characterContainer=characterContainer;
    }

    public CharacterPresenterImpl(Character character){
        this.character=character;
    }

    public CharacterPresenterImpl(NewCharacterInterface characterInterface){
        this.characterInterface=characterInterface;
    }

    public CharacterPresenterImpl(ListCharacterInterface listCharacterInterface){
        this.listCharacterInterface=listCharacterInterface;
    }

    @Override
    public void setActivity(ListCharacterInterface listCharacterInterface){
        this.listCharacterInterface=listCharacterInterface;
    }

    @Override
    public void setActivity(EditCharacterInterface editCharacterInterface){
        this.editCharacterInterface=editCharacterInterface;
    }

    @Override
    public CharacterArrayAdapter getCharacterArrayAdapter(Context context){
        CharacterArrayAdapter characterArrayAdapter=
                new CharacterArrayAdapter(context, R.layout.show_character_layout);
        Iterator<Character>characterIterator=characterContainer.iterator();
        while (characterIterator.hasNext()){
            characterArrayAdapter.add(characterIterator.next());
        }
        return characterArrayAdapter;
    }

    @Override
    public void goToEditCharacterActivity(Context context,Character selected){
        Intent intent=new Intent(context,EditCharacterActivity.class);
        CharacterPresenter characterPresenter=new CharacterPresenterImpl(selected);
        EditCharacterActivity.setPresenter(characterPresenter);
        context.startActivity(intent);
    }

    @Override
    public void newCharacter() {

    }

    @Override
    public Character getCharacter() {
        return character;
    }
}
