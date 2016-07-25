package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import starklabs.sivodim.Drama.Model.Utilities.Avatar;
import starklabs.sivodim.Drama.Model.Utilities.Background;
import starklabs.sivodim.R;

/**
 * Created by Francesco Bizzaro on 25/07/2016.
 */
public class ImageCombiner {

    private Background background;
    private Avatar avatar;
    private File destination;
    private Context context;

    public ImageCombiner(Context context,Background background,Avatar avatar,File destination){
        this.background=background;
        this.avatar=avatar;
        this.destination=destination;
        this.context=context;
    }

    public void setBackground(Background background){
        this.background=background;
    }

    public void setAvatar(Avatar avatar){
        this.avatar=avatar;
    }


    public void setDestination(File destination){
        this.destination=destination;
    }

    public void combine(){
        // Get images from their files
        Bitmap bottomImage = null;
        if(background!=null)
            bottomImage=BitmapFactory.decodeFile(background.getPath());
        if(bottomImage==null)
            bottomImage=BitmapFactory.decodeResource(context.getResources(), R.drawable.blank_background);
        Bitmap topImage = null;
        if(avatar!=null)
            topImage=avatar.getImage();
        if(bottomImage!=null)
            bottomImage=Bitmap.createScaledBitmap(bottomImage,720,406,false);
        if(topImage!=null)
            topImage=Bitmap.createScaledBitmap(topImage,120,120,false);

// use the canvas to combine them.
// Start with the first in the constructor..
        Bitmap mutableBottomImage = bottomImage.copy(Bitmap.Config.ARGB_8888, true);
        Canvas comboImage = new Canvas(mutableBottomImage);
// Then draw the second on top of that
        if(topImage!=null)
            comboImage.drawBitmap(topImage, 300f, 143f, null);

// bottomImage is now a composite of the two.

// To write the file out to the SDCard:
        OutputStream os = null;
        try {
            if (!destination.exists())
                destination.createNewFile();
            os = new FileOutputStream(destination);
            mutableBottomImage.compress(Bitmap.CompressFormat.PNG, 50, os);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
