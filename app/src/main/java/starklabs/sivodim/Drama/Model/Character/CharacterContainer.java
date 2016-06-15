package starklabs.sivodim.Drama.Model.Character;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public class CharacterContainer{
    ArrayList<Character> characters;

    public CharacterContainer(){
        characters=new ArrayList<>();
    }

    public CharacterContainer(ArrayList<Character> characters){
        this.characters=characters;
    }

    /*public void loadCharacters(){

    }*/

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

    public Character getCharacterByName(String name){
        Character character=null;
        Iterator<Character> characterIterator=iterator();
        boolean stop=false;
        while (!stop && characterIterator.hasNext()){
            Character c=characterIterator.next();
            if(c.getName().equals(name)){
                character=c;
                stop=true;
            }
        }
        return character;
    }
}
