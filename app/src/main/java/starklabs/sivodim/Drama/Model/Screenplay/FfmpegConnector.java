package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;
import android.widget.Toast;

import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.FFmpegExecuteResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;

/**
 * Created by Francesco Bizzaro on 28/05/2016.
 */


public abstract class FfmpegConnector {
    private FFmpeg ffmpeg;
    private boolean finish=false;
    private Context context;
    private boolean errors=false;
    private boolean print=false;

    /* ----- CONSTRUCTORS ----- */

    /**
     * Constructor that load {@link FFmpeg} binaries.
     * @param context
     */
    public FfmpegConnector(Context context){
        this(context,false);
    }

    public FfmpegConnector(Context context,boolean print){
        this.print=print;
        this.context=context;
        this.ffmpeg=FFmpeg.getInstance(context);
        try {
            ffmpeg.loadBinary(new LoadBinaryResponseHandler() {

                @Override
                public void onStart() {}

                @Override
                public void onFailure() {}

                @Override
                public void onSuccess() {}

                @Override
                public void onFinish() {}
            });
        } catch (FFmpegNotSupportedException e) {
            // Handle if FFmpeg is not supported by device
        }
    }

    /* ----- UTILITIES METHODS ----- */

    /**
     * Gives true if there was an error
     * @return
     */
    public boolean isErrors(){
        return errors;
    }

    /**
     * Uses getCommand() abstract method to obtain the {@link FFmpeg} command (with all parameters)
     * and executes that.
     * @throws FFmpegCommandAlreadyRunningException
     */
    public void exec(FFmpegExecuteResponseHandler ffmpegExReAn) throws FFmpegCommandAlreadyRunningException {
        String cmd=getCommand();
        ffmpeg.execute(cmd.split(" "),ffmpegExReAn );

        while (ffmpeg.isFFmpegCommandRunning()){
            try {
                Thread.sleep(10);

                System.out.println("Executing FFMPEG");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Abstract method that give the {@link String} command for {@link FFmpeg}
     * @return
     */
    public abstract String getCommand();
}
