package starklabs.libraries.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import starklabs.libraries.Presenter.VoicePresenter;
import starklabs.libraries.R;

public class NewVoiceActivity extends AppCompatActivity implements NewVoiceActivityInterface{

    private VoicePresenter voicePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_voice);
    }
}
