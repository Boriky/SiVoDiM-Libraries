package starklabs.libraries.Model.Voice;

/**
 * Created by Alberto Andriolo on 25/05/2016.
 */
public class Emotion {

    private String Effetts;

    public static final Emotion Happiness= new Emotion("{F0Scale:1.5,F0Add:40.0,Rate:0.75}");
    public static final Emotion Disgust= new Emotion("{F0Scale:2,Rate:1.15}");
    public static final Emotion Fear= new Emotion("{F0Add:60.0,F0Flut:0.5,Rate:0.8}");
    public static final Emotion Sadness= new Emotion("{F0Add:-50.0,Rate:1.25}");
    public static final Emotion Surprise= new Emotion("{F0Scale:1.5,F0Add:50.0,F0Flut:0.05,Rate:0.95}");
    public static final Emotion Anger= new Emotion("{F0Scale:2,F0Add:-10,Rate:0.75,Robot:10}");

    public Emotion (String s){Effetts=s;}

    public String toString()
    {
        return Effetts;
    }
}
