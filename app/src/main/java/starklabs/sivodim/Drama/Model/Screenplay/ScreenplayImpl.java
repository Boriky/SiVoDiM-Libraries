package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Utilities.XMLParser;

/**
 * Created by io on 25/05/2016.
 */
public class ScreenplayImpl implements Screenplay {
    private String title;
    private CharacterContainer characterContainer;
    private ArrayList<Chapter> chapters=new ArrayList<>();
    private ExportAlgorithm exportAlgorithm;
    private ShareAlgorithm shareAlgorithm;

    public ScreenplayImpl(String title){
        this.title = title;
        characterContainer=new CharacterContainer();
    }

    public static Screenplay loadScreenplay(String screenplayTitle, Context context){
        File dir = context.getFilesDir();
        File screenplayFile = new File(dir,screenplayTitle);

        //debug
        System.out.println(screenplayFile.getAbsolutePath());

        XMLParser xmlParser = new XMLParser();
        xmlParser.parseXml(screenplayFile);
        return xmlParser.getParsedData();
    }

    public static void saveScreenplay(Screenplay screenplay, Context context) {
        File dir = context.getFilesDir();
        File screenplayFile = new File(dir,screenplay.getTitle()+".scrpl");

        //debug
        System.out.println(screenplayFile.getAbsolutePath());

        XMLParser xmlParser = new XMLParser();
        xmlParser.saveXML(screenplayFile, screenplay);
    }

    @Override
    public Iterator<Chapter> getChapterIterator(){
        return chapters.iterator();
    }

    @Override
    public void export() {

    }

    @Override
    public String getTitle(){
        return title;
    }

    @Override
    public void share() {

    }

    @Override
    public CharacterContainer getCharacters(){
        return characterContainer;
    }

    @Override
    public void importCharacters(Screenplay screenplay){
        CharacterContainer characters=screenplay.getCharacters();
        this.characterContainer=characters.clone();
    }

    @Override
    public void addChapter(Chapter chapter) {
        chapters.add(chapter);
    }

    @Override
    public void addCharacter(Character character) {
        characterContainer.addCharacter(character);
    }

    @Override
    public void removeCharacter(Character character){
        characterContainer.removeCharacter(character);
    }

    @Override
    public Chapter getChapter(String title){
        Iterator<Chapter> chapterIterator=chapters.iterator();
        while (chapterIterator.hasNext()){
            Chapter chapter=chapterIterator.next();
            if(chapter.getTitle().equals(title))
                return chapter;
        }
        return null;
    }

    @Override
    public Character getCharacterByName(String name) {
        Iterator<Character> iterator = characterContainer.iterator();
        while(iterator.hasNext()) {
            Character character = iterator.next();
            if(character.getName() == name)
                return character;
        }
        return null;
    }
}
