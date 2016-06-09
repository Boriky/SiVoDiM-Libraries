package starklabs.sivodim.Drama.Model.Chapter;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import starklabs.sivodim.Drama.Model.Utilities.Background;
import starklabs.sivodim.Drama.Model.Utilities.Soundtrack;

/**
 * Created by Riccardo Rizzo on 25/05/2016.
 */
public interface Chapter {
    // test
    ListIterator<Speech> getSpeechIterator();
    void addSpeech(Speech speech);//push_back
    void deleteSpeech(ListIterator<Speech> iterator);
    void moveSpeech(ListIterator<Speech> iterator);
    void setTitle(String title);
    void setBackground(Background background);
    void setSoundtrack(Soundtrack soundtrack);
    void deleteBackground();
    void deleteSoundtrack();
    String getTitle();
    int getSpeechId(Speech speech);
    Speech getSpeechById(int id);
    void moveSpeech();
}
