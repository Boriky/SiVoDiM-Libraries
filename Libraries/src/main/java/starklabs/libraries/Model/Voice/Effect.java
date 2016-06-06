package starklabs.libraries.Model.Voice;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */
public interface Effect {

    String effectDetails();

    String getName();

    String getValue();
    void setValue(String f);

    String toString();
}
