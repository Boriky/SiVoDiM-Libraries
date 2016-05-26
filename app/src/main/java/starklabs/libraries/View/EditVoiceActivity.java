package starklabs.libraries.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import starklabs.libraries.Presenter.VoicePresenter;
import starklabs.libraries.R;

public class EditVoiceActivity extends AppCompatActivity implements EditVoiceActivityInterface{

    private VoicePresenter voicePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_voice);
    }
}
