package starklabs.libraries.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import starklabs.libraries.R;

/**
 * Created by AlbertoAndriolo on 29/05/2016.
 */
public class VoiceAdapter extends ArrayAdapter<String>{

    private TextView voiceText;
    private List<String> voicesList = new ArrayList<String>();
    private Context context;

    @Override
    public  void add(String object){
        voicesList.add(object);
        super.add(object);
    }

    public VoiceAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    public int getCount() { return this.voicesList.size(); }

    public String getItem(int index) { return  this.voicesList.get(index); }

    public View getView(int position, View convertView, ViewGroup parent){
        String VoiceObj = getItem(position);
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row= inflater.inflate(R.layout.voice, parent, false);
        voiceText = (TextView) row.findViewById(R.id.voic);
        TextView labelText = (TextView) row.findViewById(R.id.voicelabel);
        labelText.setText("Voce " + (position+1));
        voiceText.setText(VoiceObj);
        return row;
    }
}
