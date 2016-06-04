package starklabs.libraries.Model.Voice;

import java.util.ArrayList;

/**
 * Created by AlbertoAndriolo on 02/06/2016.
 */
public class MivoqVoice extends android.speech.tts.Voice {
    private String Name;
    private String Gender;
    private ArrayList<Effect> Effects;
    private Emotion myEmotion;

    private Language Lang;

    public MivoqVoice(String name, String voiceName, Language locale)
    {
        //Need to double check the 5's for latency and quality
        super(voiceName,null,5,5,true,null);
        Effects= new ArrayList<Effect>();
        Name=name;
        Lang=locale;
    }

    public String getLanguage()
    {
        return Lang.toString();
    }

    public String getVoiceName()
    {
        return super.getName();
    }

    public String getName()
    {
        return Name;
    }

    //return the String with effects to apply to the voice for the request to Mivoq server
    public String getStringEffects()
    {
        String result="[";

        if(myEmotion!=null)
            result+=myEmotion.toString();

        for (int i=0; i<Effects.size(); i++)
            result+=Effects.get(i).toString();

        result+="]";
        return result;
    }

    //return the list of the effects saved
    public ArrayList<Effect> getEffects()
    {
        return Effects;
    }

    public String getGender()
    {
        return Gender;
    }

    public void setGender(String G) { Gender=G; }

    //set Emotion of the Speech
    public void setEmotion(Emotion E)
    {
        myEmotion=E;
    }

    //set Effect of the Voice
    public void setEffect(Effect E) {  Effects.add(E);  }

    public void removeEffect(int index)
    {
        Effects.remove(index);
    }
}
