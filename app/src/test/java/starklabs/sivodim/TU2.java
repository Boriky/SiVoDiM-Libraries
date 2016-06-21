package starklabs.sivodim;

/**
 * Created by GINO on 21/06/2016.
 */

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.File;
import static org.junit.Assert.*;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.ChapterImpl;
import starklabs.sivodim.Drama.Model.Screenplay.Screenplay;
import starklabs.sivodim.Drama.Model.Screenplay.ScreenplayImpl;
import starklabs.sivodim.Drama.Presenter.HomePresenter;
import starklabs.sivodim.Drama.Presenter.HomePresenterImpl;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenter;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenterImpl;
import starklabs.sivodim.Drama.View.HomeInterface;
import starklabs.sivodim.Drama.View.ListChapterActivity;
import starklabs.sivodim.Drama.View.ListChapterInterface;

import static org.mockito.Mockito.when;

/**
 * test TU2 that tests the correct display of the names of the chapters in a screenplay
 */
public class TU2 {
    @Test
    public void testGotoListChapter(){
        Screenplay screenplay= Mockito.mock(ScreenplayImpl.class);
        when(screenplay.getTitle()).thenReturn("titolo");
        Context context= Mockito.mock(Context.class);
        File dir=Mockito.mock(File.class);
        when(context.getFilesDir()).thenReturn(dir);
        File f=Mockito.mock(File.class);
        when(f.getName()).thenReturn("titolo.scrpl");

        Chapter chapter1= Mockito.mock(ChapterImpl.class);
        Chapter chapter2= Mockito.mock(ChapterImpl.class);
        Chapter chapter3= Mockito.mock(ChapterImpl.class);

        when(chapter1.getTitle()).thenReturn("chapter1");
        when(chapter2.getTitle()).thenReturn("chapter2");
        when(chapter3.getTitle()).thenReturn("chapter3");

        screenplay.addChapter(chapter1);
        screenplay.addChapter(chapter2);
        screenplay.addChapter(chapter3);


        HomeInterface homeInterface=Mockito.mock(HomeInterface.class);
        HomePresenter homePresenter=new HomePresenterImpl(homeInterface);
        homePresenter.goToListChapter(context,screenplay.getTitle());
        //ListChapterInterface listChapterInterface=Mockito.mock(ListChapterInterface.class);
        //ScreenplayPresenter screenplayPresenter=new ScreenplayPresenterImpl(listChapterInterface);


    }
}