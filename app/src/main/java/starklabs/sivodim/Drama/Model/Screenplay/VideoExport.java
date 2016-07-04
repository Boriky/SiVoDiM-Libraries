package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.TextView;
import android.widget.Toast;

import com.github.hiteshsondhi88.libffmpeg.FFmpegExecuteResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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


    private TextView feedback;
    private AudioForVideoExport audioExport;
    private long timePassed;

    private class AudioForVideoExport extends AudioExport{

        public AudioForVideoExport(TextView feedback){
            super(feedback);
        }

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

                    }

                    @Override
                    public void onProgress(String message) {
                        System.out.println(message);
                    }

                    @Override
                    public void onFailure(String message) {

                    }

                    @Override
                    public void onStart() {
                        if(feedback!=null)feedback.setText("Converto in mp3..");
                    }

                    @Override
                    public void onFinish() {
                        //call methods for video export..
                        timePassed=0;
                        Iterator<Chapter>iterator=screenplay.getChapterIterator();
                        makeChapterVideo(context,0,iterator,new ArrayList<File>());
                    }
                });
            } catch (FFmpegCommandAlreadyRunningException e) {
                e.printStackTrace();
            }
        }

    }

    public VideoExport(TextView feedback){
        this.feedback=feedback;
        audioExport=new AudioForVideoExport(feedback);
    }

    private void exportAudio(Context context){
        audioExport.export(context);
    }

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

                    }

                    @Override
                    public void onProgress(String message) {
                        System.out.println(message);
                    }

                    @Override
                    public void onFailure(String message) {

                    }

                    @Override
                    public void onStart() {
                        feedback.setText("Creo lo sfondo del capitolo"+finalI+"..");
                    }

                    @Override
                    public void onFinish() {
                        createOverlay(context,finalI,chapterIterator,
                                chapterExportes,chapter.getSpeechIterator(),0);
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


    private void createOverlay(final Context context,final int i, final Iterator<Chapter>chapterIterator,
                               final ArrayList<File> chapterExportes,
                               final Iterator<Speech>speechIterator, int j){
        if(speechIterator.hasNext()){
            Speech speech=speechIterator.next();
            String name=screenplay.getTitle().replace(" ","_");
            final File destination=new File(context.getFilesDir(),"ch_"+i+"tmp_"+j+name+".mp4");
            final File video=chapterExportes.remove(chapterExportes.size()-1);
            chapterExportes.add(destination);
            final int finalJ=(j+1)%2;
            final int begin=(int) Math.round(((double)timePassed)/1000D);
            timePassed+=speech.getDuration();
            final int end=(int) Math.round(((double)timePassed)/1000D);
            if(speech.getCharacter().getAvatar()==null ||
                    speech.getCharacter().getAvatar().getPath()==null ||
                    speech.getCharacter().getAvatar().getPath().equals("")){
                createOverlay(context,i,chapterIterator,chapterExportes,
                        speechIterator,finalJ);
            }
            else {
                VideoOverlayer videoOverlayer=new VideoOverlayer(context,video,
                    speech.getCharacter().getAvatar(),
                    begin,end,destination);
                try {
                    videoOverlayer.exec(new FFmpegExecuteResponseHandler() {
                        @Override
                        public void onSuccess(String message) {

                        }

                        @Override
                        public void onProgress(String message) {
                        System.out.println(message);
                    }

                        @Override
                        public void onFailure(String message) {

                        }

                        @Override
                        public void onStart() {
                        feedback.setText("Inserisco gli avatar..");
                    }

                        @Override
                        public void onFinish() {
                            createOverlay(context,i,chapterIterator,chapterExportes,
                                speechIterator,finalJ);
                        }
                    });
                } catch (FFmpegCommandAlreadyRunningException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            timePassed+=200;
            makeChapterVideo(context,i,chapterIterator,chapterExportes);
        }

    }

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

                }

                @Override
                public void onProgress(String message) {
                    System.out.println(message);
                }

                @Override
                public void onFailure(String message) {

                }

                @Override
                public void onStart() {
                    feedback.setText("Unisco i capitoli..");
                }

                @Override
                public void onFinish() {
                    finalizeExport(context);
                }
            });
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
        }
    }

    private void finalizeExport(final Context context){
        String name=screenplay.getTitle().replace(" ","_");
        File video=new File(context.getFilesDir(),"concat_mp4_"+name+".mp4");
        File destination=new File(context.getFilesDir(),name+".mp4");
        File audio=new File(context.getFilesDir(),name+".mp3");
        AudioVideoMixer audioVideoMixer=new AudioVideoMixer(context,video,audio,destination);
        try {
            audioVideoMixer.exec(new FFmpegExecuteResponseHandler() {
                @Override
                public void onSuccess(String message) {

                }

                @Override
                public void onProgress(String message) {
                    System.out.println(message);
                }

                @Override
                public void onFailure(String message) {

                }

                @Override
                public void onStart() {
                    feedback.setText("Esporto in mp4..");
                }

                @Override
                public void onFinish() {
                    System.out.println("FINITO FFMPEG!!!!!!");
                    Intent intent=new Intent(context,ListChapterActivity.class);
                    context.startActivity(intent);
                    Toast.makeText(context,"Esportazione conclusa",Toast.LENGTH_SHORT).show();
                }
            });
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void export(Context context) {
        audioExport.setScreenplay(screenplay);
        exportAudio(context);
    }
}
