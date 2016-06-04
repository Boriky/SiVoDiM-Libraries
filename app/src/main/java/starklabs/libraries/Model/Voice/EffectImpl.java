package starklabs.libraries.Model.Voice;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */
public class EffectImpl implements Effect{

    private String effect_name;
    private String value;

    public EffectImpl(String name)
    {
        effect_name=name;
    }

    @Override
    public String effectDetails() {
        return null;
    }

    @Override
    public String getValue()
    {
        return value;
    }

    @Override
    public void setValue(String f)
    {
        value="f";
    }

    @Override
    public String getName()
    {
        return effect_name;
    }

    @Override
    public String toString()
    {
        String result= "{"+effect_name+":"+value+"}";
        return result;
    }
}
