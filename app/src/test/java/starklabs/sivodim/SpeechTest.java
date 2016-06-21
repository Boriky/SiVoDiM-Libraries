package starklabs.sivodim;

        import org.junit.Test;
        import org.mockito.Mockito;

        import java.io.File;
        import java.util.Iterator;

        import starklabs.sivodim.Drama.Model.Utilities.Avatar;

        import static org.mockito.Mockito.*;
        import static org.junit.Assert.*;

public class SpeechTest{

    @Test
    public void test1()  {
        //  create mock
        File test = Mockito.mock(File.class);
        // define return value for method getUniqueId()
        when(test.getAbsolutePath()).thenReturn("/path/file");

        // use mock in test....
        Avatar avatar=new Avatar(test.getAbsolutePath());
        assertEquals(avatar.getPath(), "/path/file");
    }
}