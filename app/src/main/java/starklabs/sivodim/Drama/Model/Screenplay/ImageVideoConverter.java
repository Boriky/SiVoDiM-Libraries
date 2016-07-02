package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;

import java.io.File;

import starklabs.sivodim.Drama.Model.Utilities.Background;

/**
 * Created by io on 02/07/2016.
 */
public class ImageVideoConverter extends FfmpegConnector {

    private File image;
    private File destination;
    private int duration;

    public ImageVideoConverter(Context context, Background background, int duration, File destination){
        super(context);
        this.image=new File(background.getPath());
        this.destination=destination;
        this.duration=duration;
    }

    @Override
    public String getCommand() {
        /** create a loop video from an image **/
        String cmd="-y -loop 1 -i "+image.getAbsolutePath()
                +" -c:v libx264 -t "+duration
                +" -pix_fmt yuv420p -vf scale=720x406,setdar=16:9 "+
                destination.getAbsolutePath();
        return cmd;
    }
}
