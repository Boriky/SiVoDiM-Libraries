package starklabs.sivodim.Drama.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.Vector;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public interface HomePresenter {

    // ------------------------ GETTER ------------------------------------------

    /**
     * To obtain an ArrayAdapter of String with the name of the screenplays find in the memory.
     * This method does not load any screenplay yet.
     * @param context
     * @return
     */
    ArrayAdapter<String> getTitlesAdapter(Context context);

    /**
     * To obtain a Vector of String with the name of the screenplays find in the memory.
     * This method does not load any screenplay yet.
     * @param context
     * @return
     */
    Vector<String> getScreenplayTitles(Context context);

    // ------------------------ MOVE ----------------------------------------------------

    /**
     * Move to a ListChapterActivity after loading from memory a selected screenplay,
     * identified by name, and creating a {@link ChapterPresenter}
     * @param context
     * @param selected
     */
    void goToListChapter(Context context,String selected);
}
