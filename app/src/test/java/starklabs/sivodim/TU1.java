package starklabs.sivodim;

import android.content.Context;
import android.widget.ArrayAdapter;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.StringReader;
import java.util.Vector;

import static org.junit.Assert.*;

import starklabs.sivodim.Drama.Presenter.HomePresenter;
import starklabs.sivodim.Drama.Presenter.HomePresenterImpl;
import starklabs.sivodim.Drama.Presenter.StringArrayAdapter;
import starklabs.sivodim.Drama.View.HomeInterface;
import static org.mockito.Mockito.when;

/**
 * Created by Enrico on 20/06/16.
 */

/**
 * test TU1 that tests the correct display of the screenplays saved in phone memory
 */

public class TU1{
    @Test
    public void testGetTitlesAdapter(){
        Context context= Mockito.mock(Context.class);
        File dir=Mockito.mock(File.class);
        when(context.getFilesDir()).thenReturn(dir);

        File[] list=new File[3];
        File f=Mockito.mock(File.class);
        File f2=Mockito.mock(File.class);
        File f3=Mockito.mock(File.class);
        list[0]=f;
        list[1]=f2;
        list[2]=f3;
        when(f.getName()).thenReturn("nome.scrpl");
        when(f2.getName()).thenReturn("Sceneggiato numero 2.mp3");
        when(f3.getName()).thenReturn("Questo è un titolo.scrpl");
        when(dir.listFiles()).thenReturn(list);

        HomeInterface homeInterface=Mockito.mock(HomeInterface.class);
        HomePresenter homePresenter=new HomePresenterImpl(homeInterface);
        StringArrayAdapter stringArrayAdapter=homePresenter.getTitlesAdapter(context);

        StringArrayAdapter expected = new StringArrayAdapter(context,R.layout.screenplay_item);
        expected.add("nome");
        expected.add("Questo è un titolo");
        //assert (expected.equals(stringArrayAdapter));
        assertAdapterEquals(expected,stringArrayAdapter);
    }

    private void assertAdapterEquals(StringArrayAdapter expected, StringArrayAdapter stringArrayAdapter) {
        assertEquals(expected.getCount(),stringArrayAdapter.getCount());
        for(int i=0;i<expected.getCount() && i<stringArrayAdapter.getCount();i++){
            assertEquals(expected.getItem(i),stringArrayAdapter.getItem(i));
        }
    }


}