package starklabs.libraries.Presenter;

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
 * Created by AlbertoAndriolo on 27/06/2016.
 */
public class StringArrayAdapter extends ArrayAdapter {

    /**
     * The list of Strings to show
     */
    private List<String> textList = new ArrayList<String>();

    /**
     * The TextView that will show the text
     */
    private TextView textView;

    private int stringSelected=-1;

    /**
     * Main constructor that initialize the list and the layout
     * @param context
     * @param resource The id of layout resource utilized
     */
    public StringArrayAdapter(Context context, int resource) {
        super(context, resource);
    }


    /**
     * To add a new Speech in the screen
     * @param object
     */
    public void add(String object) {
        textList.add(object);
//        super.add(object);
    }

    /**
     * To get the current number of Strings in the list
     * @return
     */
    public int getCount() {
        return this.textList.size();
    }

    /**
     * To obtain a reference to the String in a specified position
     * @param index The position inside the list
     * @return
     */
    public String getItem(int index) {
        return this.textList.get(index);
    }

    public void setStringSelected(int position){
        stringSelected=position;
    }

    /**
     * Create the view filling informations in the related fields. This method is automatically called
     * during the graphics construction
     * @param position
     * @param convertView
     * @param parent
     * @return
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String txtObj = getItem(position);
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        int layout;

        if(position==0)
            layout=R.layout.voice_item_default;
        else if(position==stringSelected)
            layout=R.layout.voice_item_selected;
        else
            layout= R.layout.voice;

        row = inflater.inflate(layout, parent, false);
        textView = (TextView) row.findViewById(R.id.voice);
        if (textView!=null)
            textView.setText(txtObj);
        else{

            textView=(TextView) row.findViewById(R.id.voiceDefault);
            if(textView!=null)
                textView.setText(txtObj);
        }

        return row;
    }
}
