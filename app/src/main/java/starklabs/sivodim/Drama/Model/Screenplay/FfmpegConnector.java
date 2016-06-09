package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;

import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.FFmpegExecuteResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;

/**
 * Created by io on 28/05/2016.
 */
public abstract class FfmpegConnector {
    private FFmpeg ffmpeg;
    private Context context;
    private Object lock=new Object();

    public FfmpegConnector(Context context){
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

    public void exec() throws FFmpegCommandAlreadyRunningException {
        String cmd=getCommand();
        ffmpeg.execute(cmd.split(" "), new FFmpegExecuteResponseHandler() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onProgress(String message) {

            }

            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {

            }
        });
        while (!ffmpeg.isFFmpegCommandRunning()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println("NOT WOOOOOOOOOOOOORK");
        }
        while (ffmpeg.isFFmpegCommandRunning()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println("WOOOOOOOOOOOOORK");
        }
    }

    public abstract String getCommand();
}
