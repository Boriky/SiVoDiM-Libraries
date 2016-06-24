package starklabs.sivodim.Drama.Presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

import java.util.Iterator;
import java.util.Vector;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.ChapterImpl;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Screenplay.Screenplay;
import starklabs.sivodim.Drama.Model.Screenplay.ScreenplayImpl;
import starklabs.sivodim.Drama.Model.Utilities.Background;
import starklabs.sivodim.Drama.Model.Utilities.Soundtrack;
import starklabs.sivodim.Drama.View.EditChapterActivity;
import starklabs.sivodim.Drama.View.HomeInterface;
import starklabs.sivodim.Drama.View.ListChapterActivity;
import starklabs.sivodim.Drama.View.ListChapterInterface;
import starklabs.sivodim.Drama.View.ListCharacterActivity;
import starklabs.sivodim.Drama.View.ListSpeechesActivity;
import starklabs.sivodim.Drama.View.NewChapterActivity;
import starklabs.sivodim.Drama.View.NewChapterInterface;
import starklabs.sivodim.Drama.View.NewCharacterActivity;
import starklabs.sivodim.Drama.View.NewScreenplayInterface;
import starklabs.sivodim.R;

import static starklabs.sivodim.Drama.Model.Screenplay.ScreenplayImpl.saveScreenplay;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public class ScreenplayPresenterImpl implements ScreenplayPresenter {
    private NewScreenplayInterface newScreenplayInterface;
    private NewChapterInterface newChapterInterface;
    private Screenplay screenplay;
    // to share and export algorithms
    private ListChapterInterface listChapterInterface;
    // to keep track of the last screenplay when on home (after back operation)
    //HomeInterface homeInterface;
    private StringArrayAdapter titlesAdapter;
    private int selected=-1;
    private String selectedName=null;


    // ----------------------------- CONSTRUCTORS -------------------------------------------

    public ScreenplayPresenterImpl(Screenplay screenplay){
        this.screenplay=screenplay;
    }

    public ScreenplayPresenterImpl(NewScreenplayInterface newScreenplayInterface){
        this.newScreenplayInterface=newScreenplayInterface;
    }

    public ScreenplayPresenterImpl(NewChapterInterface newChapterInterface){
        this.newChapterInterface=newChapterInterface;
    }

    public ScreenplayPresenterImpl(ListChapterInterface listChapterActivity){
        this.listChapterInterface=listChapterActivity;
    }


    // ----------------------------- ACTIVITY ----------------------------------------------

    @Override
    public void setActivity(ListChapterInterface listChapterInterface){
        this.listChapterInterface=listChapterInterface;
    }

    @Override
    public void setActivity(NewChapterInterface newChapterInterface){
        this.newChapterInterface=newChapterInterface;
    }


    // ----------------------------- GETTER ----------------------------------------------

    @Override
    public Screenplay getScreenplay() { return this.screenplay; }

    @Override
    public int getChapterSelected() {
        return selected;
    }


    @Override
    public String getScreenplayTitle(){
        return screenplay.getTitle();
    }

    @Override
    public StringArrayAdapter getTitlesAdapter(Context context,String screenplay){
        titlesAdapter=new StringArrayAdapter(context,R.layout.screenplay_item);
        //new ArrayAdapter<String>(context, R.layout.screenplay_item,
        Vector<String> titles=loadChapterTitles(screenplay,context);
        for(int i=0;i<titles.size();i++){
            titlesAdapter.add(titles.get(i));
        }
        titlesAdapter.setStringSelected(getChapterSelected());
        return titlesAdapter;
    }

    @Override
    public String getChapterSelectedName(){
        return this.selectedName;
    }

    // ---------------------------- SETTER ------------------------------------

    @Override
    public void setChapterSelected(int index,String name) {
        this.selected=index;
        this.selectedName=name;
    }

    // ----------------------------- MOVE ----------------------------------------------

    @Override
    public void goToListSpeechesActivity(Context context,String selected){
        Intent intent=new Intent(context,ListSpeechesActivity.class);
        ChapterPresenter chapterPresenter=
                new ChapterPresenterImpl(screenplay.getChapter(selected),
                        screenplay.getCharacters(),
                        screenplay.getTitle());
        ListSpeechesActivity.setPresenter(chapterPresenter);
        context.startActivity(intent);
    }

    @Override
    public void goToListCharactersActivity(Context context){
        Intent listCharacterIntent=new Intent(context,ListCharacterActivity.class);
        CharacterPresenter characterPresenter=
                new CharacterPresenterImpl(screenplay.getCharacters(),getScreenplayTitle());
        ListCharacterActivity.setPresenter(characterPresenter);
        context.startActivity(listCharacterIntent);
    }

    @Override
    public void goToEditChapterActivity(Context context,String selected){
        Intent editChapterIntent=new Intent(context,EditChapterActivity.class);
        ChapterPresenter chapterPresenter=
                new ChapterPresenterImpl(screenplay.getChapter(selected),
                        screenplay.getCharacters(),
                        screenplay.getTitle());
        EditChapterActivity.setPresenter(chapterPresenter);
        context.startActivity(editChapterIntent);
    }

    @Override
    public void goToNewChapterActivity(Context context){
        Intent newChapterIntent=new Intent(context,NewChapterActivity.class);
        NewChapterActivity.setPresenter(this);
        context.startActivity(newChapterIntent);
    }

    @Override
    public void goToNewCharacterActivity(Context context){
        Intent intent=new Intent(context, NewCharacterActivity.class);
        CharacterPresenter characterPresenter=
                new CharacterPresenterImpl(screenplay.getCharacters(),getScreenplayTitle());
        NewCharacterActivity.setPresenter(characterPresenter);
        context.startActivity(intent);
    }


    // ----------------------------- UTILITIES ----------------------------------------------

    @Override
    public void export() {

    }

    @Override
    public void share() {

    }

    @Override
    public void newScreenplay(String title,Context context) {
        this.screenplay=new ScreenplayImpl(title);
        save(screenplay,context);
    }

    @Override
    public void moveUpChapter(int index) {
        this.screenplay.moveUpChapter(index);
    }

    @Override
    public void moveDownChapter(int index) {
        this.screenplay.moveDownChapter(index);
    }

    @Override
    public void removeChapter(int index) {
        screenplay.removeChapter(index);
    }

    @Override
    public void addCharacter(Character character){
        screenplay.addCharacter(character);
    }

    @Override
    public void importCharacter(String screenplay,Context context){
        this.screenplay.importCharacters(ScreenplayImpl.loadScreenplay(screenplay,context));
    }

    @Override
    public void newChapter(String title, Soundtrack soundtrack, Background background) {
        Chapter chapter=new ChapterImpl.ChapterBuilder()
                .setTitle(title).build();
        if(soundtrack!=null)chapter.setSoundtrack(soundtrack);
        if(background!=null)chapter.setBackground(background);
        screenplay.addChapter(chapter);
    }


    private Vector<String> loadChapterTitles(String screenplayTitle, Context context){
        if(this.screenplay==null){
            this.screenplay=ScreenplayImpl.loadScreenplay(screenplayTitle, context);
        }
        Iterator<Chapter> chapterIterator=this.screenplay.getChapterIterator();
        Vector<String> result=new Vector<>();
        while (chapterIterator.hasNext()){
            Chapter chapter=chapterIterator.next();
            result.add(chapter.getTitle());
        }
        return result;
    }

    @Override
    public boolean save(Screenplay screenplay, Context context){
        saveScreenplay(screenplay, context);
        return false;
    }

}
