package starklabs.sivodim.Drama.Presenter;

import android.content.Context;
import android.graphics.drawable.shapes.Shape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import starklabs.sivodim.Drama.Model.Chapter.SpeechImpl;
import starklabs.sivodim.R;

/**
 * Created by pc_ps on 23/06/2016.
 */
public class EmotionArrayAdapter extends BaseAdapter {
    private ArrayList<String> emotionList = new ArrayList<String>();
    private Context context;

    // gets the context so it can be used later
    public EmotionArrayAdapter(Context context) {
        this.context = context;
        emotionList = SpeechImpl.getEmotions();
    }

    // total number of objects contained within the adapter
    public int getCount() {
        return emotionList.size();
    }

    public String getItem(int index) {
        return this.emotionList.get(index);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String emotion = getItem(position);
        System.out.println(emotion);
        ImageView emotionIcon;
        TextView emotionName;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.emotion_single_grid, parent, false);
        }
            emotionIcon = (ImageView) convertView.findViewById(R.id.emotionIconImageView);
            emotionName = (TextView) convertView.findViewById(R.id.emotionNameTextView);
            switch (emotion) {
                case "NONE":
                    emotionIcon.setImageResource(R.mipmap.emotionless_icon);
                    emotionName.setText("Neutro");
                    break;
                case "FEAR":
                    emotionIcon.setImageResource(R.mipmap.fear_icon);
                    emotionName.setText("Paura");
                    break;
                case "HAPPINESS":
                    emotionIcon.setImageResource(R.mipmap.happy_icon);
                    emotionName.setText("Gioia");
                    break;
                case "ANGER":
                    emotionIcon.setImageResource(R.mipmap.angry_icon);
                    emotionName.setText("Rabbia");
                    break;
                case "SADNESS":
                    emotionIcon.setImageResource(R.mipmap.sad_icon);
                    emotionName.setText("Tristezza");
                    break;
                case "SURPRISE":
                    emotionIcon.setImageResource(R.mipmap.surprise_icon);
                    emotionName.setText("Sorpresa");
                    break;
                case "DISGUST":
                    emotionIcon.setImageResource(R.mipmap.disgust_icon);
                    emotionName.setText("Disgusto");
                    break;
                default:
                    emotionIcon.setImageResource(R.mipmap.emotionless_icon);
                    emotionName.setText("Default");
                    break;
            }
        return convertView;
    }
}
