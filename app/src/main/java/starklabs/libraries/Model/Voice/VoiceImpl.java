package starklabs.libraries.Model.Voice;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */
public class VoiceImpl extends Voice{
    public VoiceImpl(String gender, Language language) {
        super(gender, language);
    }

    @Override
    public String getEffects(){
        return null;
    }
}
