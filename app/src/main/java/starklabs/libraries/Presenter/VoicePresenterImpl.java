package starklabs.libraries.Presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

import starklabs.libraries.Model.EngineManager.Engine;
import starklabs.libraries.Model.Voice.Voice;
import starklabs.libraries.R;
import starklabs.libraries.View.EditVoiceActivity;
import starklabs.libraries.View.EditVoiceActivityInterface;
import starklabs.libraries.View.NewVoiceActivity;
import starklabs.libraries.View.NewVoiceActivityInterface;

/**
 * Created by AlbertoAndriolo on 26/05/2016.
 */
public class
VoicePresenterImpl implements VoicePresenter{

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
    public void setActivity(NewVoiceActivity newVoiceActivity) {
        this.newVoiceActivityInterface=newVoiceActivityInterface;
    }

    @Override
    public void setActivity(EditVoiceActivity editVoiceActivity) {
        this.editVoiceActivityInterface=editVoiceActivityInterface;
    }

    //--------------------GO TO----------------------------
    @Override
    public void goToNewVoiceActivity(Context context) {
        Intent newVoiceIntent = new Intent(context, NewVoiceActivity.class);
        NewVoiceActivity.setPresenter(this);
        context.startActivity(newVoiceIntent);
    }

    @Override
    public void goToEditVoiceActivity(Context context) {
        Intent editVoiceIntent = new Intent(context, EditVoiceActivity.class);
        EditVoiceActivity.setPresenter(this);
        context.startActivity(editVoiceIntent);
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
