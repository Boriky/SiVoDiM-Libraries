package starklabs.sivodim.Drama.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.Vector;

/**
 * Created by io on 25/05/2016.
 */
public interface HomePresenter {
    Vector<String> getScreenplayNames();
    void createScreenplayList();
    ArrayAdapter<String> getTitlesAdapter(Context context);
    Vector<String> loadScreenplayTitles(Context context);
    void goToListChapter(Context context,String selected);
}
