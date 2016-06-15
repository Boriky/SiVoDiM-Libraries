package starklabs.sivodim.Drama.Presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.Model.Chapter.SpeechImpl;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Character.CharacterContainer;
import starklabs.sivodim.Drama.View.EditChapterActivity;
import starklabs.sivodim.Drama.View.EditChapterInterface;
import starklabs.sivodim.Drama.View.EditSpeechActivity;
import starklabs.sivodim.Drama.View.ListCharacterActivity;
import starklabs.sivodim.Drama.View.ListSpeechesInterface;
import starklabs.sivodim.Drama.View.NewChapterInterface;
import starklabs.sivodim.Drama.View.NewCharacterActivity;
import starklabs.sivodim.Drama.View.NewSpeechActivity;
import starklabs.sivodim.Drama.View.NewSpeechInterface;
import starklabs.sivodim.R;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 * @author Francesco Bizzaro
 */
public class ChapterPresenterImpl implements ChapterPresenter {
    //The Chapter and CharacterContainer of the presenter
    Chapter chapter;
    CharacterContainer characterContainer;

    // name of the speech.. to pick up the project directory
    String projectName;

    //For election of speech in ListSpeechesActivity
    int speechSelected=-1;

    /**
     *  custom ArrayAdapter which contain the list of speeches of the Chapter
     */
    SpeechArrayAdapter speechArrayAdapter;

    //References of related activity's interfaces
    ListSpeechesInterface listSpeechesInterface;
    NewChapterInterface newChapterInterface;
    EditChapterInterface editChapterInterface;
    NewSpeechInterface newSpeechInterface;

    // ----------------------------- CONSTRUCTORS ----------------------------------------------

    /**
     * Main constructor of {@link ChapterPresenterImpl}
     * @param chapter the chapter to edit
     * @param characterContainer connection to the list of {@link Character} of the {@link starklabs.sivodim.Drama.Model.Screenplay.Screenplay}
     * @param projectName The name of the screenplay
     */
    public ChapterPresenterImpl(Chapter chapter,CharacterContainer characterContainer,String projectName){
        this.chapter=chapter;
        this.characterContainer=characterContainer;
        this.projectName=projectName;
    }

    public ChapterPresenterImpl(ListSpeechesInterface listSpeechesInterface){
        this.listSpeechesInterface=listSpeechesInterface;
    }

    public ChapterPresenterImpl(EditChapterInterface editChapterInterface){
        this.editChapterInterface=editChapterInterface;
    }

    public ChapterPresenterImpl(NewChapterInterface newChapterInterface){
        this.newChapterInterface=newChapterInterface;
    }


    // ----------------------------- ACTIVITY ----------------------------------------------

    /**
     * Set up a link to a related {@link ListSpeechesInterface}
     * @param listSpeechesInterface The {@link ListSpeechesInterface} which uses the {@link ChapterPresenter}
     */
    @Override
    public void setActivity(ListSpeechesInterface listSpeechesInterface){
        this.listSpeechesInterface=listSpeechesInterface;
    }

    /**
     * Set up a link to a related {@link EditChapterInterface}
     * @param editChapterInterface The {@link EditChapterInterface} which uses the {@link ChapterPresenter}
     */
    @Override
    public void setActivity(EditChapterInterface editChapterInterface){
        this.editChapterInterface=editChapterInterface;
    }

    /**
     * Set up a link to a related {@link NewSpeechInterface}
     * @param newSpeechInterface The {@link NewSpeechInterface} which uses the {@link ChapterPresenter}
     */
    @Override
    public void setActivity(NewSpeechInterface newSpeechInterface){
        this.newSpeechInterface=newSpeechInterface;
    }


    // ----------------------------- GETTER ----------------------------------------------

    /**
     * Returns the title of the {@link Chapter} memorized in the presenter, or a null reference
     * if it is not initialized
     * @return
     */
    @Override
    public String getChapterTitle(){
        if(chapter!=null)return chapter.getTitle();
        return null;
    }

    /**
     * Method to obtain the custom ArrayAdapter for speeches which contains the current speeches of the chapter
     * @param context
     * @return
     */
    @Override
    public SpeechArrayAdapter getSpeeches(Context context){
        // load to refresh data
        loadSpeeches(context);
        return speechArrayAdapter;
    }

    /**
     * Gives an ArrayAdapter of String with the name of the current characters of the screenplay
     * @param context
     * @return
     */
    @Override
    public ArrayAdapter<String> getCharactersAdapter(Context context){
        Vector<String> charactersName = new Vector<String>();
        Iterator<Character> characterIterator=characterContainer.iterator();
        while (characterIterator.hasNext()){
            charactersName.add(characterIterator.next().getName());
        }
        return new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item,charactersName);
    }

    /**
     * Gives the last selected speech in ListSpeechesActvity
     * @return
     */
    @Override
    public int getSpeechSelected(){
        return speechSelected;
    }


    // ----------------------------- SETTER ----------------------------------------------

    /**
     * Set the chapter's title
     * @param title The title for the chapter
     */
    @Override
    public void setChapterTitle(String title){
        chapter.setTitle(title);
    }

    /**
     * select the speech in ListSpeechesActivity
     * @param index the index in SpeechArrayAdapter
     */
    @Override
    public void setSpeechSelected(int index){
        speechSelected=index;
    }


    // ----------------------------- UTILITIES ----------------------------------------------

    /**
     * Add a speech in the chapter
     * @param text The text of the speech
     * @param chatacterName The name of the character who says the speech
     * @param emotion the emotion that determine the synthesis parameters
     */
    @Override
    public void newSpeech(String text,String chatacterName,String emotion) {
        Speech speech=new SpeechImpl.SpeechBuilder()
                .setText(text)
                //get character by name
                .setCharacter(characterContainer.getCharacterByName(chatacterName))
                .setEmotion(emotion)
                .build();
        //add SoundFx
        chapter.addSpeech(speech);
    }

    /**
     * Load from Chapter the speeches and memorize them in speechArrayAdapter attribute
     * @param context
     */
    public void loadSpeeches(Context context){
        speechArrayAdapter=new SpeechArrayAdapter(context, R.layout.speech_layout);
        speechArrayAdapter.setSpeechSelected(speechSelected);
        //load speeches
        ListIterator<Speech> speechListIterator=chapter.getSpeechIterator();
        while (speechListIterator.hasNext()){
            speechArrayAdapter.add(speechListIterator.next());
        }
    }

    @Override
    public void deleteSpeech(Speech speech){
        chapter.deleteSpeech(speech);
    }



    // ----------------------------- MOVE ----------------------------------------------

    /**
     * Moves to the EditSpeechActivity
     * @param context
     * @param selected The speech selected to be changed
     */
    @Override
    public void goToEditSpeechActivity(Context context,Speech selected){
        Intent intent=new Intent(context,EditSpeechActivity.class);
        SpeechPresenter speechPresenter=new SpeechPresenterImpl(selected,characterContainer);
        EditSpeechActivity.setPresenter(speechPresenter);
        context.startActivity(intent);
    }

    /**
     *Moves to the activity with the list of the {@link Character}
     * @param context
     */
    @Override
    public void goToListCharactersActivity(Context context){
        Intent listCharacterIntent=new Intent(context,ListCharacterActivity.class);
        CharacterPresenter characterPresenter=new CharacterPresenterImpl(characterContainer,projectName);
        ListCharacterActivity.setPresenter(characterPresenter);
        context.startActivity(listCharacterIntent);
    }

    /**
     * Moves to {@link EditChapterActivity}
     * @param context
     */
    @Override
    public void goToEditChapterActivity(Context context){
        Intent editChapterIntent=new Intent(context,EditChapterActivity.class);
        EditChapterActivity.setPresenter(this);
        context.startActivity(editChapterIntent);
    }

    /**
     * Moves to {@link NewCharacterActivity}
     * @param context
     */
    @Override
    public void goToNewCharacterActivity(Context context){
        Intent intent=new Intent(context, NewCharacterActivity.class);
        CharacterPresenter characterPresenter=new CharacterPresenterImpl(characterContainer,projectName);
        NewCharacterActivity.setPresenter(characterPresenter);
        context.startActivity(intent);
    }

    /**
     * Moves to {@link NewSpeechActivity}
     * @param context
     */
    @Override
    public void goToNewSpeechActivity(Context context){
        Intent intent=new Intent(context, NewSpeechActivity.class);
        NewSpeechActivity.setPresenter(this);
        context.startActivity(intent);
    }





    @Override
    public void orderSpeech() {

    }
}
