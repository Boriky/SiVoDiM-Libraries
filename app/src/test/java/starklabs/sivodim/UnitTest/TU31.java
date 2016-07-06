package starklabs.sivodim.UnitTest;
import org.junit.Test;
import org.mockito.Mockito;
import java.io.File;
import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Utilities.Background;

import static org.junit.Assert.assertEquals;
/**
 * Created by Enrico on 23/06/16.
 */

//Test TU31 that test if you can set a backgroung to a chapter and if the size and the format are correct
public class TU31 {
    @Test
    public void testSetBackground() {
        String path = "C:Desktop/test/prova.png";
        File img=new File(path);
        System.out.println("img = " + img.exists());
        Background background = new Background(img.getAbsolutePath());

        Chapter chapter = Mockito.mock(Chapter.class);
        chapter.setBackground(background);

        assertEquals(path,background.getPath());

        //Extension check
        String estensione = "";
        int i = background.getPath().lastIndexOf('.');
        if (i >= 0) {
            estensione = background.getPath().substring(i + 1);
        }


        String png = "png";
        boolean formato_giusto = true;
        if (!estensione.equals(png))
            formato_giusto = false;
        assertEquals(true, formato_giusto);

        //Size check
        long size = 0;
        File imgFile=new File(background.getPath());
        if(imgFile.exists()){
            size=imgFile.length();
        }

        System.out.println("size: "+size);

        boolean size_giusta = false;
        if (size <= 10485760 && size>0) {
            size_giusta = true;
        }
        assertEquals(true, size_giusta);

    }
}
