package starklabs.libraries.Model.Integration;

import android.app.ListActivity;
import android.os.Bundle;
import android.preference.Preference;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

import starklabs.libraries.Model.Mivoq.MivoqTTSSingleton;

/**
 * Created by AlbertoAndriolo on 20/06/2016.
 */
public class DownloadVoiceData extends ListActivity implements TextToSpeech.OnInitListener{

    private Preference mEngineStatus;
    private Locale mCurrentDefaultLocale;

    //private MivoqTTSSingleton mTts;
    private static final String TAG = "TextToSpeechSettings";
    private static final boolean DBG = false;
    private TextToSpeech mTts = new TextToSpeech(this, this, "starklabs.libraries");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MivoqTTSSingleton engine= MivoqTTSSingleton.getInstance();

        if(!engine.hasContext())
            engine.setContext(this);

        finish();
    }


    /**
     * Called when the TTS engine is initialized.

    public void onInitEngine(int status) {
        if (status == TextToSpeech.SUCCESS) {
            if (DBG) Log.d(TAG, "TTS engine for settings screen initialized.");
            checkDefaultLocale();
        } else {
            if (DBG) Log.d(TAG, "TTS engine for settings screen failed to initialize successfully.");
            updateWidgetState(false);
        }
    }

    private void checkDefaultLocale() {
        Locale defaultLocale = mTts.getDefaultLanguage();
        if (defaultLocale == null) {
            Log.e(TAG, "Failed to get default language from engine ");
            updateWidgetState(false);
            updateEngineStatus(R.string.tts_status_not_supported);
            return;
        }

        mCurrentDefaultLocale = defaultLocale;

        int defaultAvailable = mTts.setLanguage(defaultLocale);
        if (defaultAvailable == TextToSpeech.LANG_NOT_SUPPORTED) {
            Log.d(TAG, "Default locale for this TTS engine is not supported.");
            updateWidgetState(false);
            updateEngineStatus(R.string.tts_status_not_supported);
        } else {
            if (isNetworkRequiredForSynthesis()) {
                updateEngineStatus(R.string.tts_status_requires_network);
            } else {
                updateEngineStatus(R.string.tts_status_ok);
            }
            //getSampleText();
        }
    }

    private void updateEngineStatus(int resourceId) {
        Locale locale = mCurrentDefaultLocale;
        if (locale == null) {
            locale = Locale.getDefault();
        }
        mEngineStatus.setSummary(getString(resourceId, locale.getDisplayName()));
    }

    private boolean isNetworkRequiredForSynthesis() {
        //Set<String> features = mTts.getFeatures(mTts.getLanguage());
        //Set<String> features = mTts.getFeatures(mCurrentDefaultLocale);
        //return features.contains(TextToSpeech.Engine.KEY_FEATURE_NETWORK_SYNTHESIS) &&
        //        !features.contains(TextToSpeech.Engine.KEY_FEATURE_EMBEDDED_SYNTHESIS);
        return true;
    }

    private void updateWidgetState(boolean enable) {
        //mPlayExample.setEnabled(enable);
        //mDefaultRatePref.setEnabled(enable);
        mEngineStatus.setEnabled(enable);
    }

*/
    @Override
    public void onInit(int status) {

    }
}