package starklabs.sivodim.Drama.Model.Character;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 * Custom Container for Character objects
 */
public class CharacterContainer{
    /**
     * CharacterContainer is a custom ArrayList
     */
    ArrayList<Character> characters;

    /* ----- CONSTRUCTORS ----- */

    /**
     * Create a CharacterContainer object initializing an empty characters ArrayList
     */
    public CharacterContainer(){
        characters=new ArrayList<>();
    }

    /**
     * Create a CharacterContainer object from an existing ArrayList of Character objects
     * @param characters
     */
    public CharacterContainer(ArrayList<Character> characters){
        this.characters=characters;
    }

    /* ----- UTILITIES METHODS ----- */

    /**
     * Clone the entire ArrayList invoking the clone() method on every character inside the container (DEEP COPY)
     * @return
     */
    public ArrayList<Character> cloneList() {
        ArrayList<Character> clone = new ArrayList<Character> (characters.size());
        for(Character item: characters) clone.add(item.clone());
        return clone;
    }

    /**
     * Clone the CharacterContainer invoking the cloneList() method (DEEP COPY)
     * @return
     */
    public CharacterContainer clone(){
        return new CharacterContainer(this.cloneList());
    }

    /**
     * Return the container's iterator
     * @return
     */
    public ListIterator<Character> iterator(){
        return characters.listIterator();
    }

    /**
     * Add an existing Character object inside the CharacterContainer
     * @param character
     */
    public void addCharacter(Character character){
        characters.add(character);
    }

    /**
     * Remove an existing Character object from the CharacterContainer
     * @param character
     */
    public void removeCharacter(Character character){
        characters.remove(character);
    }

    /* ----- GETTER METHODS ----- */

    /**
     * Return a Character objects from the container by its name
     * @param name
     * @return
     */
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
