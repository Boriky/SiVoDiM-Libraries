package starklabs.libraries.Model.Mivoq;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */
public class MivoqTTSSingleton {
    private static MivoqTTSSingleton ourInstance = new MivoqTTSSingleton();

    public static MivoqTTSSingleton getInstance() {
        return ourInstance;
    }

    private MivoqTTSSingleton() {
    }
}
