package starklabs.libraries.Model.Mivoq;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */
public abstract class AbstractFactory {
    public abstract MivoqConnection createConnection();
    public abstract MivoqInfo 		createInfoConnection();

}
