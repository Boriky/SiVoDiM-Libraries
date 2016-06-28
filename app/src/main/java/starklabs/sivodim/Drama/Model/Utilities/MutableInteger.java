package starklabs.sivodim.Drama.Model.Utilities;

/**
 * Created by io on 27/06/2016.
 */
public class MutableInteger {
    private int integer;
    public MutableInteger(int value){
        integer=value;
    }

    public void setInteger(int i){
        integer=i;
    }

    public int getInteger(){
        return integer;
    }
}
