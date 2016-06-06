package starklabs.libraries.Model.Voice;

import java.util.ArrayList;

/**
 * Created by AlbertoAndriolo on 02/06/2016.
 */
    public class MivoqVoice
    {
        private String Name;
        private String VoiceName;
        private String Gender;

        private ArrayList<Effect> Effects;
        private boolean Female_de_Adding_Effects;
        private Emotion myEmotion;
        private Language Lang;

        public MivoqVoice(String name, String myVoiceName, Language locale)
        {
            VoiceName=myVoiceName;
            Name=name;
            Lang=locale;
            Effects= new ArrayList<Effect>();
            Female_de_Adding_Effects=false;
        }

        public void setGenderLanguage(String gen, String lang)
        {
            VoiceName= getEncodedName(gen,lang);

            Female_de_Adding_Effects=false;

            if(gen.equals("female") && lang.equals("de"))
                Female_de_Adding_Effects=true;

            Lang= new Language(lang);
            Gender=gen;
        }

        public void setFemaleDe(boolean b)
        {
            Female_de_Adding_Effects=b;
        }

        public String getEncodedName(String gen, String lang)
        {
            String VoiceName=null;
            //boolean effects=false;

            switch (lang)
            {
                case "it":
                    if(gen.equals("female")) VoiceName="istc-lucia-hsmm";
                    else VoiceName="istc-speaker_internazionale-hsmm";

                    break;
                case "fr":
                    if(gen.equals("female")) VoiceName="enst-camilla-hsmm";
                    else VoiceName="upmc-pierre-hsmm";
                    break;
                case "de":
                    //if(Gender=="female")
                    //effects=true;
                    VoiceName="dfki-stefan-hsmm";
                    break;
                case "en":
                case "en_US":
                    if(gen.equals("female")) VoiceName="cmu-slt-hsmm";
                    else VoiceName="istc-piero-hsmm";
                    break;
            }
            return VoiceName;
        }

        public void setName(String N)
        {
            Name=N;
        }

        public void setGender(String G)
        {
            Gender=G;
        }

        public String getVoiceName()
        {
            return VoiceName;
        }

        public String getName()
        {
            return Name;
        }

        public String getStringEffects()
        {
            String result="[";

            if(myEmotion!=null)
                result+=myEmotion.toString();

            for (int i=0; i<Effects.size(); i++)
            {
                result+=Effects.get(i).toString();
                if( i != Effects.size()-1 )
                    result+= "," ;
            }
            if(Female_de_Adding_Effects)
                result+=",{HMMTractScaler:1.3,F0Add:120.0}";

            result+="]";
            return result;
        }

        public String getGender()
        {
            return Gender;
        }

        public String getLanguage()
        {
            return Lang.toString();
        }

        public void setEmotion(Emotion E)
        {
            myEmotion=E;
        }

        public void setEffect(Effect E)
        {
            Effects.add(E);
        }

        public ArrayList<Effect> getEffects()
        {
            return Effects;
        }

        public void removeEffect(int index)
        {
            Effects.remove(index);
        }

    }
