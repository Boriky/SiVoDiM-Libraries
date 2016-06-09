package starklabs.sivodim.Drama.Presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

import java.io.File;
import java.util.Vector;

import starklabs.sivodim.Drama.Model.Screenplay.ScreenplayImpl;
import starklabs.sivodim.Drama.View.HomeInterface;
import starklabs.sivodim.Drama.View.ListChapterActivity;
import starklabs.sivodim.R;


/**
 * Created by io on 25/05/2016.
 */
public class HomePresenterImpl implements HomePresenter {
    HomeInterface homeInterface;
    ScreenplayPresenter screenplayPresenter;
    // content of the view
    ArrayAdapter<String> titlesAdapter;

    public HomePresenterImpl(HomeInterface homeInterface){
        this.homeInterface=homeInterface;
    }

    @Override
    public Vector<String> loadScreenplayTitles(Context context){
        File dir = context.getFilesDir();
        File[] directoryListing = dir.listFiles();
        Vector<String> screenplayTitles = new Vector<String>();
        if (directoryListing != null) {
            for (int i=0; i < directoryListing.length; ++i) {
                String name=directoryListing[i].getName();
                String extension=name.substring(name.lastIndexOf(".")+1);
                if(extension.equals("scrpl"))
                screenplayTitles.add(name.substring(0,name.lastIndexOf(".")));
            }
        } else {
            // Handle the case where dir is not really a directory.
            // Checking dir.isDirectory() above would not be sufficient
            // to avoid race conditions with another process that deletes
            // directories.
        }
        return screenplayTitles;
    }

    @Override
    public void goToListChapter(Context context,String selected){
        Intent intent=new Intent(context,ListChapterActivity.class);
        ScreenplayPresenter screenplayPresenter=new ScreenplayPresenterImpl(
                ScreenplayImpl.loadScreenplay(selected,context));
        ListChapterActivity.setPresenter(screenplayPresenter);
        context.startActivity(intent);
    }

    @Override
    public ArrayAdapter<String> getTitlesAdapter(Context context){
        //if(titlesAdapter==null){
            titlesAdapter=new ArrayAdapter<String>(context, R.layout.screenplay_item,loadScreenplayTitles(context));
        //}
        return titlesAdapter;
    }

    @Override
    public Vector<String> getScreenplayNames() {
        return null;
    }

    @Override
    public void createScreenplayList() {

    }
}
