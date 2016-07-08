package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.github.hiteshsondhi88.libffmpeg.FFmpegExecuteResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.Model.Utilities.Background;
import starklabs.sivodim.Drama.View.ListChapterActivity;
import starklabs.sivodim.R;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public class VideoExport extends ExportAlgorithm {
    /**
     * View that gives the user feedback on the status of the export process
     */
    private TextView feedback;
    /**
     * Export audio for video(low dependency between classes)
     */
    private AudioForVideoExport audioExport;
    /**
     * Attribute that stores the time that has passed since the beginning of the export operation
     */
    private long timePassed;

    /**
     * Internal class for exporting audio
     */
    private class AudioForVideoExport extends AudioExport{

        /* ----- CONSTRUCTOR ----- */

        /**
         * Create an AudioForVideoExport object
         * @param feedback
         */
        public AudioForVideoExport(TextView feedback){
            super(feedback);
        }

        /**
         * Generate video file using FFmpeg library
         * FFmpeg is synchronized
         * @param context
         */
        @Override
        protected void finalizeExport(final Context context){
            String name=screenplay.getTitle().replace(" ","_");
            final File file=new File(context.getFilesDir(),"concatenation"+name+".wav");
            File destination=new File(context.getFilesDir(),name+".mp3");
            Mp3Converter mp3Converter=new Mp3Converter(context,file,destination);
            try {
                mp3Converter.exec(new FFmpegExecuteResponseHandler() {
                    @Override
                    public void onSuccess(String message) {
                        //call methods for video export..
                        Iterator<Chapter>iterator=screenplay.getChapterIterator();
                        makeChapterVideo(context,0,iterator,new ArrayList<File>());
                    }

                    @Override
                    public void onProgress(String message) {
                        System.out.println(message);
                    }

                    @Override
                    public void onFailure(String message) {
                        Intent intent=new Intent(context,ListChapterActivity.class);
                        context.startActivity(intent);
                        Toast.makeText(context,"Errore nella creazione dell'audio",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onStart() {
                        if(feedback!=null)feedback.setText("Converto in mp3..");
                    }

                    @Override
                    public void onFinish() {

                    }
                });
            } catch (FFmpegCommandAlreadyRunningException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Create a VideoExport object
     * @param feedback
     */
    public VideoExport(TextView feedback){
        this.feedback=feedback;
        audioExport=new AudioForVideoExport(feedback);
    }

    /**
     * Call the export(Context context) method to export an audio file
     * @param context
     */
    private void exportAudio(Context context){
        audioExport.export(context);
    }

    /**
     * Generate a video for a chapter
     * @param context
     * @param i
     * @param chapterIterator
     * @param chapterExportes
     */
    private void makeChapterVideo(final Context context, int i, final Iterator<Chapter>chapterIterator, final ArrayList<File> chapterExportes){
        if(chapterIterator.hasNext()){
            final Chapter chapter=chapterIterator.next();
            String name=screenplay.getTitle().replace(" ","_");
            final File destination=new File(context.getFilesDir(),"ch_bk_"+i+name+".mp4");
            chapterExportes.add(destination);
            i++;
            final int finalI=i;
            int duration= (int) Math.round(((double)chapter.getDuration())/1000D);
            Background background=chapter.getBackground();
            if(background==null || background.getPath()==null || background.getPath().equals("")){
                File backg=new File(context.getFilesDir(),"blank_background.png");
                if(backg.exists()){
                    background=new Background(backg.getAbsolutePath());
                }
                else {
                    Bitmap backgroundBitmap=
                            BitmapFactory.decodeResource(context.getResources(), R.drawable.blank_background);


                    FileOutputStream out = null;
                    try {
                        out = new FileOutputStream(backg);
                        backgroundBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (out != null) {
                                out.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    background=new Background(backg.getAbsolutePath());
                }
            }
            ImageVideoConverter imageVideoConverter=new ImageVideoConverter(
                    context,
                    background,
                    duration,
                    destination);
            //remember to check if background exists.. otherwise set a default background...

            try {
                imageVideoConverter.exec(new FFmpegExecuteResponseHandler() {
                    @Override
                    public void onSuccess(String message) {
                        timePassed=0;
                        createOverlay(context,finalI,chapterIterator,
                                chapterExportes,chapter.getSpeechIterator(),0);
                    }

                    @Override
                    public void onProgress(String message) {
                        System.out.println(message);
                    }

                    @Override
                    public void onFailure(String message) {
                        Intent intent=new Intent(context,ListChapterActivity.class);
                        context.startActivity(intent);
                        Toast.makeText(context,"Errore nella creazione dello sfondo"+finalI,
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onStart() {
                        feedback.setText("Creo lo sfondo del capitolo"+finalI+"..");
                    }

                    @Override
                    public void onFinish() {

                    }
                });
            } catch (FFmpegCommandAlreadyRunningException e) {
                e.printStackTrace();
            }
        }
        else{
            concatenateChapters(context,chapterExportes);
        }
    }


    private void combineImages(Bitmap bottomImage,Bitmap topImage,File destination){
// Start with the first in the constructor..
        Canvas comboImage = new Canvas(bottomImage);
// Then draw the second on top of that
        comboImage.drawBitmap(topImage, 0f, 0f, null);

// bottomImage is now a composite of the two.

// To write the file out to the SDCard:
        OutputStream os = null;
        try {
            os = new FileOutputStream(destination);
            bottomImage.compress(Bitmap.CompressFormat.PNG, 50, os);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void createOverlay(final Context context,final int i, final Iterator<Chapter>chapterIterator,
                               final ArrayList<File> chapterExportes,
                               final Iterator<Speech>speechIterator, int j){
        if(speechIterator.hasNext()){
            Speech speech=speechIterator.next();
            String name=screenplay.getTitle().replace(" ","_");
            final int begin=(int) Math.round(((double)timePassed)/1000D);
            timePassed+=speech.getDuration();
            final int end=(int) Math.round(((double)timePassed)/1000D);
            if(speech.getCharacter().getAvatar()==null ||
                    speech.getCharacter().getAvatar().getPath()==null ||
                    speech.getCharacter().getAvatar().getPath().equals("")){
                createOverlay(context,i,chapterIterator,chapterExportes,
                        speechIterator,j);
            }
            else {
                final File destination=new File(context.getFilesDir(),"ch_"+i+"tmp_"+j+name+".mp4");
                final File video=chapterExportes.remove(chapterExportes.size()-1);
                chapterExportes.add(destination);
                final int finalJ=(j+1)%2;
                VideoOverlayer videoOverlayer=new VideoOverlayer(context,video,
                    speech.getCharacter().getAvatar(),
                    begin,end,destination);
                try {
                    videoOverlayer.exec(new FFmpegExecuteResponseHandler() {
                        @Override
                        public void onSuccess(String message) {
                            createOverlay(context,i,chapterIterator,chapterExportes,
                                    speechIterator,finalJ);
                        }

                        @Override
                        public void onProgress(String message) {
                        System.out.println(message);
                    }

                        @Override
                        public void onFailure(String message) {
                            Intent intent=new Intent(context,ListChapterActivity.class);
                            context.startActivity(intent);
                            Toast.makeText(context,"Errore nell'inserimento di avatar",
                                    Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onStart() {
                        feedback.setText("Inserisco gli avatar..");
                    }

                        @Override
                        public void onFinish() {

                        }
                    });
                } catch (FFmpegCommandAlreadyRunningException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            //timePassed+=200;
            makeChapterVideo(context,i,chapterIterator,chapterExportes);
        }

    }

    /**
     * Concatenate the video generated for every chapter
     * @param context
     * @param chapterExportes
     */
    private void concatenateChapters(final Context context, ArrayList<File>chapterExportes){
        String name=screenplay.getTitle().replace(" ","_");
        File destination=new File(context.getFilesDir(),"concat_mp4_"+name+".mp4");
        VideoConcatenator videoConcatenator=new VideoConcatenator(context,destination);
        for(File file: chapterExportes){
            videoConcatenator.add(file);
        }
        try {
            videoConcatenator.exec(new FFmpegExecuteResponseHandler() {
                @Override
                public void onSuccess(String message) {
                    finalizeExport(context);
                }

                @Override
                public void onProgress(String message) {
                    System.out.println(message);
                }

                @Override
                public void onFailure(String message) {
                    Intent intent=new Intent(context,ListChapterActivity.class);
                    context.startActivity(intent);
                    Toast.makeText(context,"Errore nell'unione dei capitoli",
                            Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onStart() {
                    feedback.setText("Unisco i capitoli..");
                }

                @Override
                public void onFinish() {

                }
            });
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
        }
    }

    /**
     * Finalize the video export process
     * @param context
     */
    private void finalizeExport(final Context context){
        String name=screenplay.getTitle().replace(" ","_");
        File video=new File(context.getFilesDir(),"concat_mp4_"+name+".mp4");
        final File destination=new File(context.getFilesDir(),name+".mov");
        File audio=new File(context.getFilesDir(),name+".mp3");
        AudioVideoMixer audioVideoMixer=new AudioVideoMixer(context,video,audio,destination);
        try {
            audioVideoMixer.exec(new FFmpegExecuteResponseHandler() {
                @Override
                public void onSuccess(String message) {
                    mp4Conversion(context);
                }

                @Override
                public void onProgress(String message) {
                    System.out.println(message);
                }

                @Override
                public void onFailure(String message) {
                    Intent intent=new Intent(context,ListChapterActivity.class);
                    context.startActivity(intent);
                    Toast.makeText(context,"Errore nell'unione audio-video",
                            Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onStart() {
                    feedback.setText("Esporto il video..");
                }

                @Override
                public void onFinish() {

                }
            });
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
        }
    }

    private void mp4Conversion(final Context context){
        String name=screenplay.getTitle().replace(" ","_");
        File video=new File(context.getFilesDir(),name+".mov");
        final File destination=new File(context.getFilesDir(),name+".mp4");
        Mp4Converter mp4Converter=new Mp4Converter(context,video,destination);
        try {
            mp4Converter.exec(new FFmpegExecuteResponseHandler() {
                @Override
                public void onSuccess(String message) {
                    try {
                        File destCopy=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
                        copyFile(destination,new File(destCopy,destination.getName()));
                        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                                Uri.fromFile(destCopy)));
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromFile(destination));
                        intent.setDataAndType(Uri.fromFile(destination), "video/mp4");
                        context.startActivity(intent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent intent=new Intent(context,ListChapterActivity.class);
                    context.startActivity(intent);
                    Toast.makeText(context,"Il video è stato creato e inserito nella cartella Movies",
                            Toast.LENGTH_LONG).show();
                }

                @Override
                public void onProgress(String message) {

                }

                @Override
                public void onFailure(String message) {
                    Intent intent=new Intent(context,ListChapterActivity.class);
                    context.startActivity(intent);
                    Toast.makeText(context,"Errore nell'esportazione mp4",
                            Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onStart() {
                    feedback.setText("Converto in mp4..");
                }

                @Override
                public void onFinish() {

                }
            });
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param context
     */
    @Override
    public void export(Context context) {
        audioExport.setScreenplay(screenplay);
        exportAudio(context);
    }


    //https://developer.android.com/guide/topics/media/camera.html

    /**
     *
     * @param source
     * @param dest
     * @throws IOException
     */
    private static void copyFile(File source, File dest)
            throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            if(inputChannel!=null)inputChannel.close();
            if(outputChannel!=null)outputChannel.close();
        }
    }
}
