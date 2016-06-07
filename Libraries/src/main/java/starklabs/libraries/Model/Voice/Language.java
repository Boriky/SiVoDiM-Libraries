package starklabs.libraries.Model.Voice;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */

public class Language {

    private String lang;
    private String state;
    private String modifier;

    // lang_state.modifier

    public Language(String l)
    {
        lang=l;
    }

    public void setLanguage(String l)
    {
        lang=l;
    }

    public void setState(String s)
    {
        state=s;
    }

    public void setModifier(String m)
    {
        modifier=m;
    }

    public String toString()
    {
        String result=lang;
        if(state != null && state.equals(state))
        {
            result+="_"+state;
            if(modifier != null && modifier.equals(modifier))
                result+="."+modifier;
        }
        return result;
    }
}
