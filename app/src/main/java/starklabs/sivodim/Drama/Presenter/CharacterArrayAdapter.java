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
 * Created by Francesco Bizzaro on 02/06/2016.
 */
public class CharacterArrayAdapter extends ArrayAdapter {
    /**
     * The list of characters to show
     */
    private List<Character> characterList = new ArrayList<Character>();

    /**
     *The TextView that will show the character's name
     */
    private TextView characterName;

    /**
     * The ImageView that will show the character's avatar
     */
    private ImageView characterAvatar;

    /**
     * Main constructor that initialize the list and the layout
     * @param context
     * @param resource The id of layout resource utilized
     */
    public CharacterArrayAdapter(Context context, int resource) {
        super(context, resource);
    }

    /**
     * To add a character in the list
     * @param object
     */
    public void add(Character object) {
        characterList.add(object);
        super.add(object);
    }

    /**
     * To get the nimber of characters in the list
     * @return
     */
    public int getCount() {
        return this.characterList.size();
    }

    /**
     * To get the character in a specific position
     * @param index
     * @return
     */
    public Character getItem(int index) {
        return this.characterList.get(index);
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
        Character characterObj = getItem(position);
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.show_character_layout, parent, false);
        characterName = (TextView) row.findViewById(R.id.characterName);
        characterAvatar=(ImageView)row.findViewById(R.id.characterAvatar);
        characterName.setText(characterObj.getName());
        Avatar avatar=characterObj.getAvatar();
        if(avatar!=null && avatar.getImage()!=null)
            characterAvatar.setImageBitmap(avatar.getImage());
        else
            characterAvatar.setImageResource(R.drawable.noavatar);
        return row;
    }
}
