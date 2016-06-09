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
 * Created by io on 02/06/2016.
 */
public class CharacterArrayAdapter extends ArrayAdapter {
    private List<Character> characterList = new ArrayList<Character>();
    private Context context;
    private TextView characterName;
    private ImageView characterAvatar;

    public CharacterArrayAdapter(Context context, int resource) {
        super(context, resource);
        this.context=context;
    }

    public void add(Character object) {
        characterList.add(object);
        super.add(object);
    }

    public int getCount() {
        return this.characterList.size();
    }

    public Character getItem(int index) {
        return this.characterList.get(index);
    }

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
            characterAvatar.setImageResource(R.mipmap.undefined_user);
        return row;
    }
}
