package starklabs.sivodim;

import android.content.Context;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;

import starklabs.sivodim.Drama.Model.Screenplay.Screenplay;
import starklabs.sivodim.Drama.Presenter.ScreenplayPresenterImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
/**
 * Created by Enrico on 21/06/16.
 */
/**
 * test TU3 that test the creation of a ScreenPlay with the correct title
 */
public class TU3 {
    //changed name of test
    @Test
    public void testNewScreenplay(){
        Context context= Mockito.mock(Context.class);
        when(context.getFilesDir()).thenReturn(new File("/Users/Enrico/Desktop/test"));

        Screenplay s=null;
        ScreenplayPresenterImpl test = new ScreenplayPresenterImpl(s);
        String name="titolo";
        String error=",:%YIK/KL£&(L";
        test.newScreenplay(name,context.getApplicationContext());
        test.newScreenplay(error,context.getApplicationContext());

        File file=new File(context.getFilesDir(),name+".scrpl");
        File fileError=new File(context.getFilesDir(),error+".scrpl");


        assertEquals(true,file.exists());
        assertEquals(false,fileError.exists());
    }
}
