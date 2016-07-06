package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Character.CharacterContainer;
import starklabs.sivodim.Drama.Model.Utilities.MutableInteger;
import starklabs.sivodim.Drama.Model.Utilities.XMLParser;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public class ScreenplayImpl implements Screenplay {
    /**
     * MutableInteger attribute to identify every Speech with an unique ID
     */
    private MutableInteger nextSpeechId;
    /**
     * String attribute that represents the title of the Screenplay
     */
    private String title;
    /**
     * CharacterContainer that contains all the Characters created inside the Screenplay
     */
    private CharacterContainer characterContainer;
    /**
     * ArrayList of Chapter objects that contains all the Chapters created inside the Screenplay
     */
    private ArrayList<Chapter> chapters=new ArrayList<>();
    /**
     * ExportAlgorithm attribute that implements AudioExport or VideoExport
     */
    private ExportAlgorithm exportAlgorithm;

    /* ----- CONSTRUCTOR ----- */

    /**
     * Create ScreenplayImpl object
     * @param title
     * @param nextSpeechId
     */
    public ScreenplayImpl(String title,int nextSpeechId){
        this.title = title;
        this.nextSpeechId=new MutableInteger(nextSpeechId);
        characterContainer=new CharacterContainer();
    }

    /* ----- UTILITIES METHODS ----- */

    /**
     * Load and return Screenplay object from device memory calling parseXML(String screenplayTitle) on XMLParser object
     * @param screenplayTitle
     * @param context
     * @return
     */
    public static Screenplay loadScreenplay(String screenplayTitle, Context context){
        File dir = context.getFilesDir();
        File screenplayFile = new File(dir,screenplayTitle);

        //debug
        System.out.println(screenplayFile.getAbsolutePath());

        XMLParser xmlParser = new XMLParser();
        xmlParser.parseXml(screenplayFile);
        return xmlParser.getParsedData();
    }

    @Override
    public MutableInteger getNextSpeechId(){
        return nextSpeechId;
    }

    /**
     * Save Screenplay object into device memory calling
     * saveXML(File screenplayFile, Screenplay screenplay) on XMLParser object
     * @param screenplay
     * @param context
     */
    public static void saveScreenplay(Screenplay screenplay, Context context) {
        File dir = context.getFilesDir();
        System.out.println("PATH="+dir.getAbsolutePath());

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
    public void export(String type, Context context, TextView feedback) {
        if(type.equals("Video"))
            exportAlgorithm=new VideoExport(feedback);
        else
            exportAlgorithm=new AudioExport(feedback);
        exportAlgorithm.setScreenplay(this);
        exportAlgorithm.export(context);
    }

    @Override
    public void importCharacters(Screenplay screenplay){
        CharacterContainer characters=screenplay.getCharacters();
        ListIterator<Character> iterator = characters.iterator();
        while(iterator.hasNext()) {
            this.characterContainer.addCharacter(iterator.next());
        }
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
    public void moveUpChapter(int index){
        if(index>0){
            Chapter chapter=chapters.remove(index);
            chapters.add(index-1,chapter);
        }
    }

    @Override
    public void moveDownChapter(int index) {
        Chapter chapter=chapters.remove(index);
        if(index<chapters.size())
            chapters.add(index+1,chapter);
        else
            chapters.add(chapter);
    }

    @Override
    public void removeChapter(int index) {
        chapters.remove(index);
    }

    /* ----- GETTER METHODS ----- */

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
    public CharacterContainer getCharacters(){
        return characterContainer;
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
