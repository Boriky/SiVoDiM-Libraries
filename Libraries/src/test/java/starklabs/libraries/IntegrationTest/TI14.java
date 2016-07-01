package starklabs.libraries.IntegrationTest;

import android.content.Context;

import org.junit.Test;
import org.mockito.Mockito;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.EngineManager.EngineImpl;

/**
 * Created by Enrico on 01/07/16.
 */
public class TI14 {
    @Test
    public void testMivoqEngine(){
        Context context = Mockito.mock(Context.class);
        context.getApplicationContext();
        Engine engine = new EngineImpl(context);

    }
}
