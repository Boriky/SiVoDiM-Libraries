package starklabs.libraries.Presenter;

import android.content.Context;
import android.widget.ArrayAdapter;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.Voice.Voice;
import starklabs.libraries.R;
import starklabs.libraries.View.EditVoiceActivityInterface;
import starklabs.libraries.View.NewVoiceActivityInterface;

/**
 * Created by AlbertoAndriolo on 26/05/2016.
 */
public class
VoicePresenterImpl implements VoicePresenter{
    /**Javadoc try*/

    private Voice voice;

    private Engine engine;

    private EditVoiceActivityInterface editVoiceActivityInterface;
    private VoicePresenter voicePresenter;
    ArrayAdapter<String> genderAdapter;
    ArrayAdapter<String> languageAdapter;

    private NewVoiceActivityInterface newVoiceActivityInterface;

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
    public void setActivity(NewVoiceActivityInterface newVoiceActivityInterface) {
        this.newVoiceActivityInterface=newVoiceActivityInterface;
    }

    @Override
    public void setActivity(EditVoiceActivityInterface editVoiceActivityInterface) {
        this.editVoiceActivityInterface=editVoiceActivityInterface;
    }

    @Override
    public Voice getVoice() {
        return null;
    }

    public VoicePresenterImpl(NewVoiceActivityInterface newVoiceActivityInterface) {
        this.newVoiceActivityInterface=newVoiceActivityInterface;
    }

    public VoicePresenterImpl(EditVoiceActivityInterface editVoiceActivityInterface){
        this.editVoiceActivityInterface=editVoiceActivityInterface;
    }
}
