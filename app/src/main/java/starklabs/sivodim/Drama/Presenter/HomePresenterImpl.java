package starklabs.sivodim.Drama.Presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.File;
import java.util.Vector;

import starklabs.sivodim.Drama.Model.Screenplay.ScreenplayImpl;
import starklabs.sivodim.Drama.View.HomeInterface;
import starklabs.sivodim.Drama.View.ListChapterActivity;
import starklabs.sivodim.R;


/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public class HomePresenterImpl implements HomePresenter {
    /**
     * {@link HomeInterface} that uses this presenter
     */
    HomeInterface homeInterface;
    // content of the view
    StringArrayAdapter titlesAdapter;

    //For selection of screenplay in ListSpeechesActivity
    int screenplaySelected=-1;
    String screenplayTitleSelected=null;

    // ------------------------ CONSTRUCTORS ------------------------------------

    public HomePresenterImpl(HomeInterface homeInterface){
        this.homeInterface=homeInterface;
    }


    // ------------------------ GETTER ------------------------------------------

    @Override
    public Vector<String> getScreenplayTitles(Context context){
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
    public int getScreenplaySelected() {
        return this.screenplaySelected;
    }

    @Override
    public StringArrayAdapter getTitlesAdapter(Context context){
        titlesAdapter=new StringArrayAdapter(context,R.layout.screenplay_item);
        File dir = context.getFilesDir();
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (int i=0; i < directoryListing.length; ++i) {
                String name=directoryListing[i].getName();
                String extension=name.substring(name.lastIndexOf(".")+1);
                if(extension.equals("scrpl")){
                    titlesAdapter.add(name.substring(0,name.lastIndexOf(".")));
                }
            }
        } else {
            // Handle the case where dir is not really a directory.
            // Checking dir.isDirectory() above would not be sufficient
            // to avoid race conditions with another process that deletes
            // directories.
        }
                //new ArrayAdapter<String>(context, R.layout.screenplay_item,getScreenplayTitles(context));
        titlesAdapter.setStringSelected(screenplaySelected);
        return titlesAdapter;
    }

    // ----------------------- SETTER ------------------------------------------------

    @Override
    public void setScreenplaySelected(int index,String title) {
        this.screenplaySelected=index;
        this.screenplayTitleSelected=title;
    }

    // ------------------------ MOVE ----------------------------------------------------

    @Override
    public void goToListChapter(Context context,String selected){
        Intent intent=new Intent(context,ListChapterActivity.class);
        ScreenplayPresenter screenplayPresenter=new ScreenplayPresenterImpl(
                ScreenplayImpl.loadScreenplay(selected,context));
        ListChapterActivity.setPresenter(screenplayPresenter);
        context.startActivity(intent);
    }

    public static boolean removeDirectory(File directory) {

        // System.out.println("removeDirectory " + directory);

        if (directory == null)
            return false;
        if (!directory.exists())
            return true;
        if (!directory.isDirectory())
            return false;

        String[] list = directory.list();

        // Some JVMs return null for File.list() when the
        // directory is empty.
        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                File entry = new File(directory, list[i]);

                //        System.out.println("\tremoving entry " + entry);

                if (entry.isDirectory())
                {
                    if (!removeDirectory(entry))
                        return false;
                }
                else
                {
                    if (!entry.delete())
                        return false;
                }
            }
        }

        return directory.delete();
    }



    @Override
    public void deleteScreenplaySelected(Context context) {
        File projectFile=new File(context.getFilesDir(),screenplayTitleSelected+".scrpl");
        File exportedFile=new File(context.getFilesDir(),screenplayTitleSelected.replace(" ","_")+".mp3");
        File projectDir=new File(context.getFilesDir(),screenplayTitleSelected.replace(" ","_"));
        if(projectFile.exists())
            projectFile.delete();
        if(exportedFile.exists())
            exportedFile.delete();
        if(projectDir.exists())
            removeDirectory(projectDir);
    }

}
