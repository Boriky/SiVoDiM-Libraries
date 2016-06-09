package starklabs.sivodim.Drama.Presenter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.Model.Character.Character;
import starklabs.sivodim.Drama.Model.Utilities.Avatar;
import starklabs.sivodim.R;

/**
 * Created by io on 31/05/2016.
 */
public class SpeechArrayAdapter extends ArrayAdapter {
    private List<Speech> speechList = new ArrayList<Speech>();
    private Context context;
    private TextView speechText;
    private ImageView speechAvatar;
    private TextView characterName;

    public SpeechArrayAdapter(Context context, int resource) {
        super(context, resource);
        this.context=context;
    }

    public void add(Speech object) {
        speechList.add(object);
        super.add(object);
    }

    public int getCount() {
        return this.speechList.size();
    }

    public Speech getItem(int index) {
        return this.speechList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Speech speechObj = getItem(position);
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.speech_layout, parent, false);
        speechText = (TextView) row.findViewById(R.id.speechText);
        speechAvatar=(ImageView)row.findViewById(R.id.speechAvatar);
        characterName=(TextView)row.findViewById(R.id.characterName);
        Drawable drawable;
        int color=Color.BLACK;
        switch (speechObj.getEmotion()){
            case "FEAR":
                drawable=speechText.getResources().getDrawable(R.drawable.fear);
                color=Color.WHITE;
                break;
            case "HAPPINESS":
                drawable=speechText.getResources().getDrawable(R.drawable.happiness);
                break;
            case "ANGER":
                drawable=speechText.getResources().getDrawable(R.drawable.anger);
                break;
            case "SADNESS":
                drawable=speechText.getResources().getDrawable(R.drawable.sadness);
                color=Color.WHITE;
                break;
            case "SURPRISE":
                drawable=speechText.getResources().getDrawable(R.drawable.surprise);
                break;
            case "DISGUST":
                drawable=speechText.getResources().getDrawable(R.drawable.disgust);
                break;
            default:
                drawable=speechText.getResources().getDrawable(R.drawable.sadness);
                color=Color.WHITE;
                break;
        }
        //speechText.setBackground(drawable);
        speechText.setTextColor(color);
        speechText.setBackgroundDrawable(drawable);
        speechText.setText(speechObj.getText());
        Character character=speechObj.getCharacter();
        if(character!=null){
            characterName.setText(character.getName());
            Avatar avatar=character.getAvatar();
            if(avatar!=null && avatar.getImage()!=null)
                speechAvatar.setImageBitmap(speechObj.getCharacter().getAvatar().getImage());
            else
                speechAvatar.setImageResource(R.mipmap.undefined_user);
        }
        return row;
    }
}
