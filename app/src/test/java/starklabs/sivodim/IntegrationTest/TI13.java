package starklabs.sivodim.IntegrationTest;

import android.content.Context;
import android.test.InstrumentationTestCase;

import org.junit.Test;

import java.io.File;
import java.util.Iterator;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.ChapterImpl;
import starklabs.sivodim.Drama.Model.Screenplay.Screenplay;
import starklabs.sivodim.Drama.Model.Screenplay.ScreenplayImpl;
import starklabs.sivodim.Drama.Model.Utilities.Background;
import starklabs.sivodim.Drama.Model.Utilities.Soundtrack;

/**
 * Created by AlbertoAndriolo on 06/07/2016.
 */
public class TI13 extends InstrumentationTestCase {
    @Test
    public void testChapter() {
        Context context=getInstrumentation().getContext();
        File backgroundFile=new File(context.getFilesDir(), "background.png");
        Background background=new Background(backgroundFile.getAbsolutePath());

        File soundtrackFile=new File(context.getFilesDir(), "soundtrack.mp3");
        Soundtrack soundtrack=new Soundtrack(soundtrackFile.getAbsolutePath());

        Chapter chapter=new ChapterImpl.ChapterBuilder()
                .setTitle("Chapter1")
                .setSoundtrack(soundtrack)
                .build();

        if(background.isDefined()) chapter.setBackground(background);

        Screenplay screenplay=new ScreenplayImpl("Screenplay",0);

        screenplay.addChapter(chapter);

        //check if the screenplay is not null
        Iterator<Chapter> chapterIterator=screenplay.getChapterIterator();
        boolean found=false;
        while(!found && chapterIterator.hasNext()){
            Chapter c=chapterIterator.next();
            if (c.getTitle().equals("Chapter1")){
                found=true;
                assertEquals(chapter,c);
            }
        }
        assertEquals(true, found);

        //check if there is the expected soundtrack
        assertEquals(soundtrack, chapter.getSoundtrack());

        //check if there is the expected background image
        assertEquals(background, background.getImage());
    }
}
