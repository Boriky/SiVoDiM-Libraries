package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;

import java.io.File;

import starklabs.sivodim.Drama.Model.Utilities.Avatar;

/**
 * Created by io on 02/07/2016.
 */
public class VideoOverlayer extends FfmpegConnector {

    private File video;
    private File image;
    private int begin;
    private int end;
    private File destination;

    public VideoOverlayer(Context context, File video, Avatar image,int begin,int end,File destination){
        super(context);
        this.video=video;
        this.image=new File(image.getPath());
        this.begin=begin;
        this.end=end;
        this.destination=destination;
    }

    public void setDestination(File destination){
        this.destination=destination;
    }

    public void setImage(File image){
        this.image=image;
    }

    public void setBegin(int begin){
        this.begin=begin;
    }

    public void setEnd(int end){
        this.end=end;
    }

    @Override
    public String getCommand() {
        /** overlay an image to a video and set it visible in the time indicated **/
        String cmd="-y -i "+video.getAbsolutePath()
                +" -i "+image.getAbsolutePath()
                +" -filter_complex overlay=(main_w-overlay_w)/2:(main_h-overlay_h)/2:enable='between(t,"+
                begin+","+end+")' -codec:a copy "+destination.getAbsolutePath();
        return cmd;
    }
}
