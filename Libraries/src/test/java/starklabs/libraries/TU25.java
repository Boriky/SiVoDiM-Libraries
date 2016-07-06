package starklabs.libraries;

import android.content.Context;
import android.widget.ArrayAdapter;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Iterator;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.EngineManager.EngineImpl;
import starklabs.libraries.Presenter.VoiceListPresenter;
import starklabs.libraries.Presenter.VoiceListPresenterImpl;
import starklabs.libraries.Presenter.VoicePresenter;
import starklabs.libraries.Presenter.VoicePresenterImpl;
import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;


/**
 * Created by Enrico on 27/06/16.
 */
public class TU25 {
    @Test

    public void testLoadVoiceNames(){
        Context context = Mockito.mock(Context.class);
        context.getApplicationContext();

        Engine engine = new EngineImpl(context.getApplicationContext());

        VoiceListPresenter voiceListPresenter = new VoiceListPresenterImpl(engine);

        voiceListPresenter.loadVoiceNames(context);

        //ArrayAdapter<String> arrayAdapter = Mockito.mock(ArrayAdapter.class);
        //when(arrayAdapter.add("enri")).thenReturn();


        ArrayAdapter<String> arrayAdapter = voiceListPresenter.getVoicesAdapter(context.getApplicationContext());
        arrayAdapter.add("Enri");

        System.out.println("arrayAdapter = " + arrayAdapter.getItem(0));


        arrayAdapter.isEmpty();

        boolean check = arrayAdapter.isEmpty();

        assertEquals(false, check);


    }
}
