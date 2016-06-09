package starklabs.libraries.Model.Voice;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */
public class EffectImpl implements Effect{

    private String effectName;
    private String value;

    /**Constructor of the EffectImpl class
     *
     * @param name of the Effect
     */
    public EffectImpl(String name)
    {
        effectName=name;
    }

    @Override
    public String getValue()
    {
        return value;
    }

    @Override
    public void setValue(String f)
    {
        value=f;
    }

    @Override
    public String getName()
    {
        return effectName;
    }

    @Override
    public String toString()
    {
        String result= "{"+effectName+":"+value+"}";
        return result;
    }
}
