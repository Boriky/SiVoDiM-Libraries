package starklabs.libraries;

/**
 * Created by GINO on 27/06/2016.
 */

import org.junit.Test;

import starklabs.libraries.Model.Mivoq.AbstractFactory;
import starklabs.libraries.Model.Mivoq.MivoqConnection;
import starklabs.libraries.Model.Mivoq.MivoqConnectionFactory;
import static org.junit.Assert.assertEquals;
/**
 * Test TU49 that tests a correct connection creation
 */
public class TU49 {
    @Test
    public void testConnectionCreation(){
        AbstractFactory mivoqConnectionFactory=new MivoqConnectionFactory();
        MivoqConnection mivoqConnection=mivoqConnectionFactory.createConnection();


        assertEquals(true,!mivoqConnection.equals(null));
    }
}
