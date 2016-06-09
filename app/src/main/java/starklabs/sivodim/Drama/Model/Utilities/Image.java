package starklabs.sivodim.Drama.Model.Utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public abstract class Image {
    private String path;
    private Bitmap image;

    public Image(String path){
        this.path=path;
        File imgFile=new File(path);
        if(imgFile.exists()){
            long fileLength=imgFile.length();
            if (fileLength<=maxSize()){
                image=BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            }
        }
    }

    public Bitmap getImage(){
        return image;
    }

    public String getPath(){
        return path;
    }

    protected abstract long maxSize();

    public boolean isDefined(){
        return image!=null;
    }

}
