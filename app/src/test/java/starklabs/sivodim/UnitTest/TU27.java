package starklabs.sivodim.UnitTest;

/**
 * Created by GINO on 23/06/2016.
 */

import org.junit.Test;

import java.util.Iterator;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.ChapterImpl;
import starklabs.sivodim.Drama.Model.Screenplay.Screenplay;
import starklabs.sivodim.Drama.Model.Screenplay.ScreenplayImpl;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenter;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenterImpl;

import static org.junit.Assert.assertEquals;

/**
 * Test TU27 that tests if it is possible to modify the order of a chapter list in a screenplay
 */
public class TU27 {
    @Test
    public void testMoveChapter(){
        Screenplay screenplay=new ScreenplayImpl("screenplay",0);
        Screenplay screenplay1=new ScreenplayImpl("screenplay1",0);
        Chapter chapter1=new ChapterImpl.ChapterBuilder()
                .setTitle("chapter1").build();
        Chapter chapter2=new ChapterImpl.ChapterBuilder()
                .setTitle("chapter2").build();
        Chapter chapter3=new ChapterImpl.ChapterBuilder()
                .setTitle("chapter3").build();
        screenplay.addChapter(chapter1);
        screenplay1.addChapter(chapter1);
        screenplay.addChapter(chapter2);
        screenplay1.addChapter(chapter3);
        screenplay.addChapter(chapter3);
        screenplay1.addChapter(chapter3);

        ScreenplayPresenter screenplayPresenter=new ScreenplayPresenterImpl(screenplay);
        ScreenplayPresenter screenplayPresenter1=new ScreenplayPresenterImpl(screenplay1);
        screenplayPresenter.moveDownChapter(0);

        Iterator<Chapter> it=screenplayPresenter.getScreenplay().getChapterIterator();
        Iterator<Chapter> it1=screenplayPresenter1.getScreenplay().getChapterIterator();
        int c=0;
        boolean down=true;
        while(it.hasNext()){
            if(it.next().getTitle().equals(it1.next().getTitle())){
                c++;
            }
        }
        if(c==3){down=false;}
        screenplayPresenter.moveUpChapter(1);
        Iterator<Chapter> it2=screenplayPresenter.getScreenplay().getChapterIterator();
        Iterator<Chapter> it3=screenplayPresenter1.getScreenplay().getChapterIterator();
        boolean up=true;
        int d=0;
        while(it2.hasNext() && up){
            if(it2.next().getTitle().equals(it3.next().getTitle())){
                c++;
            }
        }
        if(c<3){down=false;}
        assertEquals(true,down);
        assertEquals(true,up);
    }

}
