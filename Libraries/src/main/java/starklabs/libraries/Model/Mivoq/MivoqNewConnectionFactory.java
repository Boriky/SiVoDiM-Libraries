package starklabs.libraries.Model.Mivoq;

/**
 * Created by AlbertoAndriolo on 20/06/2016.
 */

public class MivoqNewConnectionFactory extends AbstractFactory {

    public MivoqConnection createConnection() {
        return new MivoqNewConnectionImpl();
    }

}

