package starklabs.sivodim.Drama.Model.Screenplay;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import starklabs.sivodim.Drama.Model.Character.Character;

/**
 * Created by io on 25/05/2016.
 */
public class CharacterContainer{
    ArrayList<Character> characters;

    public CharacterContainer(){
        characters=new ArrayList<>();
    }

    public CharacterContainer(ArrayList<Character> characters){
        this.characters=characters;
    }

    public void loadCharacters(){

    }

    public ArrayList<Character> cloneList() {
        ArrayList<Character> clone = new ArrayList<Character> (characters.size());
        for(Character item: characters) clone.add(item.clone());
        return clone;
    }

    public CharacterContainer clone(){
        return new CharacterContainer(this.cloneList());
    }

    public ListIterator<Character> iterator(){
        return characters.listIterator();
    }

    public void addCharacter(Character character){
        characters.add(character);
    }

    public void removeCharacter(Character character){
        characters.remove(character);
    }
}
