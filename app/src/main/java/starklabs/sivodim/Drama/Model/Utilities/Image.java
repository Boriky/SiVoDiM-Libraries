package starklabs.sivodim.Drama.Model.Utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public abstract class Image {
    /**
     * the path to the picture
     */
    private String path;
    /**
     * a bitmap created from the image file
     */
    private Bitmap image;

    /**
     * Constructor that initialize the bitmap after checking the dimension of the file
     * @param path the path to the image
     */
    public Image(String path){
        this.path=path;
        File imgFile=new File(path);
        //check if the file exists
        if(imgFile.exists()){
            //check if the dimension of the image is acceptable
            long fileLength=imgFile.length();
            //use abstract method maxSize() to obtain the maximum dimension of the image
            if (fileLength<=maxSize()){
                image=BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            }
        }
    }

    /**
     * Return a Bitmap created from the file passed in the constructor
     * @return
     */
    public Bitmap getImage(){
        return image;
    }

    /**
     * Return the path passed to the constructor
     * @return
     */
    public String getPath(){
        return path;
    }

    /**
     * Abstract method to be defined in subclasses for stating the maximum acceptable dimension
     * @return
     */
    protected abstract long maxSize();

    /**
     * Return true only if the image is well defined: the path exists and the related image file
     * dimension is acceptable
     * @return
     */
    public boolean isDefined(){
        return image!=null;
    }

}
