package starklabs.libraries.Model.Voice;

import java.util.List;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */
public abstract class Voice {

    private String gender;

    private Language language;

    private List<Effect> effects;

    public Voice(String gender, Language language) {
        this.gender = gender;
        this.language = language;
    }

    public void addEffect(Effect effect){
        //check the order of insertion effects??
        effects.add(effect);
    }

    public abstract String getEffects();
}
