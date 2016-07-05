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
        String newLang;
        switch(lang)
        {
            case "ita":
            case "it": newLang="ita";
                break;
            case "deu":
            case "de": newLang="deu";
                break;
            case "fra":
            case "fr": newLang="fra";
                break;
            case "eng":
            case "en": newLang="eng";
                break;
            default: newLang="alg";
        }

        String result=newLang;
        /*if(state != null && state.equals(state))
        {
            result+="-"+state;
            if(modifier != null && modifier.equals(modifier))
                result+="-"+modifier;
        }*/
        return result;
    }
}
