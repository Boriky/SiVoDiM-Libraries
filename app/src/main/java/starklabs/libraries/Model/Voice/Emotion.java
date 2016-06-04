package starklabs.libraries.Model.Voice;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */
public class Emotion {

    private String Effects;

    public Emotion (String s){Effects=s;}

    public static final Emotion Happiness= new Emotion("{F0Scale:1.5,F0Add:25.0,Rate:0.75}");
    public static final Emotion Disgust= new Emotion("{F0Add:40,Rate:0.75,Volume:1.1}");
    public static final Emotion Fear= new Emotion("{F0Add:60.0,F0Flut:1.5}");
    public static final Emotion Sadness= new Emotion("{F0Add:-50.0,Rate:1.25}");
    public static final Emotion Surprise= new Emotion("F0Scale:1.25,F0Add:50.0,F0Flut:1.25,Rate:0.85");
    public static final Emotion Anger= new Emotion("");



    public String toString()
    {
        return Effects;
    }

/*
    private Vector<Effect> effects;

    public String getEmotionDetails(){
        return null;
    }

    protected void addEffect(Effect effect){
        effects.add(effect);
    }


*/

}
