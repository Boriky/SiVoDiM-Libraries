package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;

import java.io.File;

import starklabs.sivodim.Drama.Model.Utilities.Avatar;

/**
 * Created by io on 02/07/2016.
 */
public class VideoOverlayer extends FfmpegConnector {
    /**
     * File that represents the original video file
     */
    private File video;
    /**
     * File that represents the image file
     */
    private File image;
    /**
     * int that represents the begin of the video
     */
    private int begin;
    /**
     * int that represents the end of the video
     */
    private int end;
    /**
     * File that represents the completed video file
     */
    private File destination;

    /* ----- CONSTRUCTOR ----- */

    /**
     * Create VideoOverlayer object
     * @param context
     * @param video
     * @param image
     * @param begin
     * @param end
     * @param destination
     */
    public VideoOverlayer(Context context, File video, Avatar image,int begin,int end,File destination){
        super(context);
        this.video=video;
        this.image=new File(image.getPath());
        this.begin=begin;
        this.end=end;
        this.destination=destination;
    }

    /* ----- SETTER METHODS ----- */

    /**
     * Edit existing file destination or set new one
     * @param destination
     */
    public void setDestination(File destination){
        this.destination=destination;
    }

    /**
     * Edit existing image file or set new one
     * @param image
     */
    public void setImage(File image){
        this.image=image;
    }

    /**
     * Edit existing int begin attribute or set new one
     * @param begin
     */
    public void setBegin(int begin){
        this.begin=begin;
    }

    /**
     * Edit existing int end attribute or set new one
     * @param end
     */
    public void setEnd(int end){
        this.end=end;
    }

    /* ----- UTILITIES METHODS ----- */

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
