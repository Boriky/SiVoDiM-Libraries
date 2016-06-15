package starklabs.sivodim.Drama.Presenter;

import android.content.Context;
import android.content.Intent;

import java.util.Iterator;

import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Character.CharacterImpl;
import starklabs.sivodim.Drama.Model.Character.CharacterContainer;
import starklabs.sivodim.Drama.Model.Utilities.Avatar;
import starklabs.sivodim.Drama.View.EditCharacterActivity;
import starklabs.sivodim.Drama.View.EditCharacterInterface;
import starklabs.sivodim.Drama.View.ListCharacterActivity;
import starklabs.sivodim.Drama.View.ListCharacterInterface;
import starklabs.sivodim.Drama.View.NewCharacterActivity;
import starklabs.sivodim.Drama.View.NewCharacterInterface;
import starklabs.sivodim.R;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public class CharacterPresenterImpl implements CharacterPresenter {
    String projectName;
    CharacterContainer characterContainer;
    Character character;
    NewCharacterInterface characterInterface;
    ListCharacterInterface listCharacterInterface;
    EditCharacterInterface editCharacterInterface;

    // ----------------------------- CONSTRUCTORS -------------------------------------------

    public CharacterPresenterImpl(CharacterContainer characterContainer,String projectName){
        this.characterContainer=characterContainer;
        this.projectName=projectName.replace(" ","_");
    }

    public CharacterPresenterImpl(Character character,String projectName){
        this.character=character;
        this.projectName=projectName;
    }

    public CharacterPresenterImpl(NewCharacterInterface characterInterface){
        this.characterInterface=characterInterface;
    }

    public CharacterPresenterImpl(ListCharacterInterface listCharacterInterface){
        this.listCharacterInterface=listCharacterInterface;
    }


    // ----------------------------- ACTIVITY ----------------------------------------------

    @Override
    public void setActivity(ListCharacterInterface listCharacterInterface){
        this.listCharacterInterface=listCharacterInterface;
    }

    @Override
    public void setActivity(EditCharacterInterface editCharacterInterface){
        this.editCharacterInterface=editCharacterInterface;
    }

    @Override
    public void setActivity(NewCharacterInterface newCharacterInterface){
        this.characterInterface=newCharacterInterface;
    }


    // ----------------------------- GETTER ----------------------------------------------

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
    public Character getCharacter() {
        return character;
    }

    @Override
    public String getProjectName(){
        return projectName.replace(" ","_");
    }


    // ----------------------------- UTILITIES ----------------------------------------------

    @Override
    public void newCharacter(String name, String voice, Avatar avatar) {
        Character character=new CharacterImpl.CharacterBuilder()
                .setName(name)
                .setVoice(voice)
                .build();
        if(avatar!=null)//check if the avatar exists
            character.setAvatar(avatar);
        characterContainer.addCharacter(character);
    }


    // ----------------------------- MOVE ----------------------------------------------

    @Override
    public void goToEditCharacterActivity(Context context,Character selected){
        Intent intent=new Intent(context,EditCharacterActivity.class);
        CharacterPresenter characterPresenter=new CharacterPresenterImpl(selected,projectName);
        EditCharacterActivity.setPresenter(characterPresenter);
        context.startActivity(intent);
    }

    @Override
    public void goToNewCharacterActivity(Context context){
        Intent intent=new Intent(context,NewCharacterActivity.class);
        NewCharacterActivity.setPresenter(this);
        context.startActivity(intent);
    }

    @Override
    public void goToListCharacterActivity(Context context){
        Intent intent=new Intent(context,ListCharacterActivity.class);
        ListCharacterActivity.setPresenter(this);
        context.startActivity(intent);
    }

}
