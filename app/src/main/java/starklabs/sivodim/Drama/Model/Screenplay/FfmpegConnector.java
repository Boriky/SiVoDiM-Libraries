package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;

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

    /**
     * Constructor that load {@link FFmpeg} binaries.
     * @param context
     */
    public FfmpegConnector(Context context){
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

    /**
     * Uses getCommand() abstract method to obtain the {@link FFmpeg} command (with all parameters)
     * and executes that.
     * @throws FFmpegCommandAlreadyRunningException
     */
    public void exec() throws FFmpegCommandAlreadyRunningException {
        String cmd=getCommand();
        ffmpeg.execute(cmd.split(" "), new FFmpegExecuteResponseHandler() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onProgress(String message) {
                System.out.println(message);
            }

            @Override
            public void onFailure(String message) {
                System.out.println(message);
            }

            @Override
            public void onStart() {
                System.out.println("START OPERATION");
            }

            @Override
            public void onFinish() {
                System.out.println("END OPERATION");
            }
        });
        while (!ffmpeg.isFFmpegCommandRunning()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (ffmpeg.isFFmpegCommandRunning()){
            try {
                Thread.sleep(10);
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
