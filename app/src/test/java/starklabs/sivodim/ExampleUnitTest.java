package starklabs.sivodim;

import org.junit.Test;

import starklabs.sivodim.Drama.Model.Utilities.Soundtrack;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Soundtrack soundtrack=new Soundtrack("khbbkh,");
        assertEquals("khbbkh", soundtrack.getAudio().getAbsolutePath());
    }
}