package starklabs.sivodim.Drama.Presenter;

import android.content.Context;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.View.EditChapterInterface;
import starklabs.sivodim.Drama.View.ListSpeechesInterface;

/**
 * Created by io on 25/05/2016.
 */
public interface ChapterPresenter {
    void orderSpeech();
    SpeechArrayAdapter getSpeeches(Context context);
    String getChapterTitle();
    void setChapterTitle(String title);
    void setActivity(ListSpeechesInterface listSpeechesInterface);
    void setActivity(EditChapterInterface editChapterInterface);
    void goToEditSpeechActivity(Context context,Speech selected);
    void goToListCharactersActivity(Context context);
    void goToEditChapterActivity(Context context);
}
