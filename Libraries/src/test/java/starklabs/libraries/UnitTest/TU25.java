package starklabs.libraries.UnitTest;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
public class TU25 extends InstrumentationTestCase {
    @Test

    public void testLoadVoiceNames(){
        Context context = Mockito.mock(Context.class);
        context.getApplicationContext();

        Engine engine = new EngineImpl(context.getApplicationContext());

        VoiceListPresenter voiceListPresenter = new VoiceListPresenterImpl(engine);


        ArrayAdapter<String> arrayAdapter = voiceListPresenter.getVoicesAdapter(context.getApplicationContext());
        voiceListPresenter.loadVoiceNames(context);

        arrayAdapter.isEmpty();

        boolean check = arrayAdapter.isEmpty();

        assertEquals(false, check);


    }
}
