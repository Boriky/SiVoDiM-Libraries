package starklabs.libraries.Model.Voice;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */

public class Language {

    private String Lang;
    private String State;
    private String Modifier;

    // lang_state.modifier

    public Language(String l)
    {
        Lang=l;
    }

    public void setLanguage(String l)
    {
        Lang=l;
    }

    public void setState(String s)
    {
        State=s;
    }

    public void setModifier(String m)
    {
        Modifier=m;
    }

    public String toString()
    {
        String result=Lang;
        if(State != null && State.equals(State))
        {
            result+="_"+State;
            if(Modifier != null && Modifier.equals(Modifier))
                result+="."+Modifier;
        }
        return result;
    }

    /*
    private String languageDetails;

    public String getLanguageDetails(){
        return languageDetails;
    }

    public Language(String languageDetails){
        this.languageDetails=languageDetails;
    }
    */

}
