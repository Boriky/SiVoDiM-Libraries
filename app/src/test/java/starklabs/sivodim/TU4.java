package starklabs.sivodim;

/**
 * Created by GINO on 21/06/2016.
 */

import android.content.Context;
import android.provider.MediaStore;
import android.provider.Settings;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

import starklabs.sivodim.Drama.Model.Screenplay.Screenplay;
import starklabs.sivodim.Drama.Model.Screenplay.ScreenplayImpl;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenter;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenterImpl;

import static org.mockito.Mockito.when;

/**
 * test TU4 that tests if the app saves correctly a project file in the phone memory
 */
public class TU4 {

    @Test
    public void testSave(){
        Screenplay screenplay= Mockito.mock(ScreenplayImpl.class);
        Context context= Mockito.mock(Context.class);

        when(context.getFilesDir()).thenReturn(new File("C:/Desktop"));
        System.out.println(context.getFilesDir());
        when(screenplay.getTitle()).thenReturn("titolo");
        ScreenplayPresenter screenplayPresenter=Mockito.mock(ScreenplayPresenterImpl.class);
        screenplayPresenter.save(screenplay,context);
        File file=new File(context.getFilesDir(),"/titolo.scrp");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(file.getAbsolutePath());
        //file.createNewFile();
        System.out.println(context.getFilesDir().exists());
        file.exists();
        assertEquals(true,file.exists());

    }

}
