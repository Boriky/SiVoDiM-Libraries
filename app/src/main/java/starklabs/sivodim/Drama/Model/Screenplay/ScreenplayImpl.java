package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Character.CharacterContainer;
import starklabs.sivodim.Drama.Model.Utilities.XMLParser;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
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
    public void export(String type,Context context) {
        if(type.equals("Video"))
            exportAlgorithm=new VideoExport();
        else
            exportAlgorithm=new AudioExport();
        exportAlgorithm.setScreenplay(this);
        exportAlgorithm.export(context);
    }

    @Override
    public String getTitle(){
        return title;
    }

    @Override
    public String getPath(Context context) {
        File file=new File(context.getFilesDir(),getTitle().replace(" ","_"));
        return file.getAbsolutePath();
    }

    @Override
    public void share(String where) {

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
        if(characterContainer==null)
            characterContainer=new CharacterContainer();
        characterContainer.addCharacter(character);
    }

    @Override
    public void removeCharacter(Character character){
        if(characterContainer!=null)
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
            if(character.getName().equals(name))
                return character;
        }
        return null;
    }
}
