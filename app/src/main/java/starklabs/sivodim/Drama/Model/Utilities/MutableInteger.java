package starklabs.sivodim.Drama.Model.Utilities;

/**
 * Created by Francesco Bizzaro on 27/06/2016.
 * An utility class that represents an integer which changes during time
 */
public class MutableInteger {
    /**
     * The state of the class
     */
    private int integer;

    /**
     * Constructor that sets the initial value
     * @param value the initial value
     */
    public MutableInteger(int value){
        integer=value;
    }

    /**
     * A setter method for setting the int value
     * @param i the value to set
     */
    public void setInteger(int i){
        integer=i;
    }

    /**
     * Return the current value
     * @return
     */
    public int getInteger(){
        return integer;
    }
}
