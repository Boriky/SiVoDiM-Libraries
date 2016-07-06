package starklabs.libraries.Model.Voice;

import java.util.ArrayList;

/**
 * Created by AlbertoAndriolo on 02/06/2016.
 */
    public class MivoqVoice
    {
        private String name;
        private String voiceName;
        private String gender;

        private ArrayList<Effect> effects;
        private boolean femaleDeAddingEffects;
        private Emotion myEmotion;
        private Language lang;

        /**
         *
         * @param name of the voice
         * @param myVoiceName name of the voice on the Mivoq Server
         * @param locale language between it - en - fr - de
         */
        public MivoqVoice(String name, String myVoiceName, Language locale)
        {
            voiceName=myVoiceName;
            this.name=name;
            lang=locale;
            effects= new ArrayList<Effect>();
            femaleDeAddingEffects=false;
        }

        /**
         *
         * @param gen Gender of the Voice
         * @param myLang Language of the Voice
         */
        public void setGenderLanguage(String gen, String myLang)
        {
            voiceName= getEncodedName(gen,myLang);

            femaleDeAddingEffects=false;

            if(gen.equals("female") && lang.equals("de"))
                femaleDeAddingEffects=true;

            lang= new Language(myLang);
            gender = gen;
        }

        /**
         *
         * @param b b==true only if it is the German female
         */
        public void setFemaleDe(boolean b)
        {
            femaleDeAddingEffects=b;
        }

        /**
         *
         * @param gen set Gender of the Voice
         * @param myLang set Language of the Voice
         * @return
         */
        public String getEncodedName(String gen, String myLang)
        {
            String myVoiceName=null;

            switch (myLang)
            {
                case "it":
                    if(gen.equals("female")) myVoiceName="istc-lucia-hsmm";
                    else myVoiceName="istc-speaker_internazionale-hsmm";

                    break;
                case "fr":
                    if(gen.equals("female")) myVoiceName="enst-camilla-hsmm";
                    else myVoiceName="upmc-pierre-hsmm";
                    break;
                case "de":
                    myVoiceName="dfki-stefan-hsmm";
                    break;
                case "en":
                case "en_US":
                    if(gen.equals("female")) myVoiceName="cmu-slt-hsmm";
                    else myVoiceName="istc-piero-hsmm";
                    break;
            }
            return myVoiceName;
        }

        /**
         *
         * @param myLang
         * @return a String used to synthesize an example for a given language
         */
        public static String getSampleText (String myLang)
        {
            switch(myLang)
            {
                case "it":
                    return "Benvenuto nel mondo della sintesi vocale!";
                case "fr":
                    return "Bienvenue dans le monde de la synthèse de la parole!";
                case "de":
                    return "Willkommen in der Welt der Sprachsynthese!";
                case "en":
                case "en_US":
                    return "Welcome to the world of speech synthesis!";
            }
            return "Welcome!";
        }

        public void setName(String n)
        {
            name=n;
        }

        public void setGender(String g)
        {
            gender=g;
        }

        public String getVoiceName()
        {
            return voiceName;
        }

        public String getName()
        {
            return name;
        }

        /**
         *
         * @return the String with Effect to be applied to the Voice
         */
        public String getStringEffects()
        {
            String result="[";

            if(myEmotion!=null)
                result+=myEmotion.toString()+",";

            for (int i=0; i<effects.size(); i++)
            {
                result+=effects.get(i).toString();
                if( i != effects.size()-1 )
                    result+= "," ;
            }
            if(femaleDeAddingEffects)
                result+=",{HMMTractScaler:1.3,F0Add:120.0}";

            result+="]";
            return result;
        }

        public String getGender()
        {
            return gender;
        }

        public String getLanguage()
        {
            return lang.toString();
        }

        public void setEmotion(Emotion E)
        {
            myEmotion=E;
        }

        public void setEffect(Effect E)
        {
            effects.add(E);
        }

        public ArrayList<Effect> getEffects()
        {
            return effects;
        }

        public void removeEffect(int index)
        {
            effects.remove(index);
        }

    }
