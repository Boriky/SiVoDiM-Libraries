package starklabs.sivodim;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;

import starklabs.sivodim.Drama.Model.Utilities.Soundtrack;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        File test = Mockito.mock(File.class);
        // define return value for method getUniqueId()
        when(test.getAbsolutePath()).thenReturn("/path/file");
        Soundtrack soundtrack=new Soundtrack(test.getAbsolutePath());
        assertEquals(test.getAbsolutePath(), soundtrack.getAudio().getPath());
    }
}