package starklabs.libraries.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.Voice.MivoqVoice;
import starklabs.libraries.R;
import starklabs.libraries.View.EditVoiceActivityInterface;
import starklabs.libraries.View.NewVoiceActivityInterface;

/**
 * Created by AlbertoAndriolo on 26/05/2016.
 */
public class VoicePresenterImpl implements VoicePresenter{

    private MivoqVoice mivoqVoice;
    private Engine engine;
    private String gender, language;
    ArrayAdapter<String> genderAdapter;
    ArrayAdapter<String> languageAdapter;
    private EditVoiceActivityInterface editVoiceActivityInterface;
    private NewVoiceActivityInterface newVoiceActivityInterface;
    private boolean defaultVoice;


    /** Constructor of the VoicePresenterImpl
     *
     * @param engine from the HomeActivity
     */
    public VoicePresenterImpl(Engine engine) {
        this.engine = engine;
        gender="male";
        language="it";
        mivoqVoice=engine.getVoiceByName("");

        if(mivoqVoice==null)
            mivoqVoice=engine.createVoice("","male","it");
        int i=mivoqVoice.getEffects().size();
        while (i>0){
            mivoqVoice.removeEffect(0);
            i--;
        }
        mivoqVoice.setGenderLanguage("male","it");
        defaultVoice=false;
    }

    /** Second constructor of the VoicePresenterImpl
     *
     * @param mivoqVoice from the VoiceListActivity
     */
    public VoicePresenterImpl(MivoqVoice mivoqVoice, Engine engine) {
        this.engine = engine;
        this.mivoqVoice=mivoqVoice;
        gender=mivoqVoice.getGender();
        language=mivoqVoice.getLanguage();
        defaultVoice=false;
    }

    @Override
    public void setActivity(NewVoiceActivityInterface newVoiceActivityInterface) {
        this.newVoiceActivityInterface=newVoiceActivityInterface;
    }

    @Override
    public void setActivity(EditVoiceActivityInterface editVoiceActivityInterface) {
        this.editVoiceActivityInterface=editVoiceActivityInterface;
    }

    @Override
    public void loadVoiceGender(Context context){
        //fill gender spinner
        genderAdapter=new ArrayAdapter<String>(context, R.layout.row);
        genderAdapter.add("Maschio");
        genderAdapter.add("Femmina");
        genderAdapter.add("Neutro");
        genderAdapter.add("Sconosciuto");
    }

    @Override
    public ArrayAdapter<String> getGenderAdapter(Context context){
        if(genderAdapter==null){
            loadVoiceGender(context);
        }
        return genderAdapter;
    }

    @Override
    public void loadVoiceLanguage(Context context){
        //fill language spinner
        languageAdapter=new ArrayAdapter<String>(context, R.layout.row);
        languageAdapter.add("Italiano");
        languageAdapter.add("Inglese");
        languageAdapter.add("Tedesco");
        languageAdapter.add("Francese");
    }

    @Override
    public ArrayAdapter<String> getLanguageAdapter(Context context){
        if(languageAdapter==null){
            loadVoiceLanguage(context);
        }
        return languageAdapter;
    }

    @Override
    public String getVoiceName() {
        return mivoqVoice.getName();
    }

    @Override
    public MivoqVoice getVoice() {
        return mivoqVoice;
    }

    @Override
    public Engine getEngine() {
        return engine;
    }

    @Override
    public void setGender(int pos) {
        switch (pos){
            case 0: gender="male"; break;
            case 1: gender="female"; break;
            case 2: gender="neutral"; break;
            case 3: gender="unknown"; break;
        }
        mivoqVoice.setGenderLanguage(gender,language);
    }

    @Override
    public void setLanguage(int pos) {
        switch (pos){
            case 0: language="it"; break;
            case 1: language="en"; break;
            case 2: language="de"; break;
            case 3: language="fr"; break;
        }
        mivoqVoice.setGenderLanguage(gender,language);
    }

    @Override
    public int getGenderPos(String genderTag){
        switch(genderTag)
        {
            case "male":
                return 0;
            case "female":
                return 1;
            case "neutral":
                return 2;
            case "unknown":
                return 3;
        }
        return 0;
    }

    @Override
    public int getLanguagePos(String langTag) {
        switch(langTag)
        {
            case "ita":
            case "it":
                return 0;
            case "eng":
            case "en":
                return 1;
            case "deu":
            case "de":
                return 2;
            case "fra":
            case "fr":
                return 3;
        }
        return 0;    }

    @Override
    public void setDefaultVoice(int pos) {
        engine.setDefaultVoice(pos);
    }

    @Override
    public void setDefaultVoice(boolean b) {
        defaultVoice=b;
    }

    @Override
    public boolean isDefaultVoice() {
        return defaultVoice;
    }

    @Override
    public void setVoiceName(String s) {
        MivoqVoice myVoice=engine.getVoiceByName(s);
        int i = 1;
        while (myVoice!=null){
            s=s.concat(Integer.toString(i));
            i++;
            myVoice=engine.getVoiceByName(s);
        }
        mivoqVoice.setName(s);
    }

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public String getGender() {
        return gender;
    }



}
