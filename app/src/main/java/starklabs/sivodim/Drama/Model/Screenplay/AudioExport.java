package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;
        import android.content.Intent;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.github.hiteshsondhi88.libffmpeg.FFmpegExecuteResponseHandler;
        import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;

        import java.io.File;
        import java.util.ArrayList;
        import java.util.Iterator;
        import java.util.List;
        import java.util.ListIterator;

        import starklabs.sivodim.Drama.Model.Chapter.Chapter;
        import starklabs.sivodim.Drama.Model.Chapter.Speech;
        import starklabs.sivodim.Drama.Model.Utilities.Soundtrack;
        import starklabs.sivodim.Drama.View.ListChapterActivity;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public class AudioExport extends ExportAlgorithm {

     protected TextView feedback;

    /* ----- CONSTRUCTOR ----- */

    /**
     * Create an AudioExport object: initialize the feedback view
     * @param feedback
     */
     public AudioExport(TextView feedback){
         this.feedback=feedback;
     }

    /* ----- UTILITIES METHODS ----- */

    /**
     * Method that:
     * 1) concatenates all the audio files associated to the Speech objects
     * 2) concatenates all the audio files that has been created for every Chapter object
     * The method uses FFmpeg library
     * FFmpeg library calls are synchronized
     * @param context
     * @param i
     * @param chapterExportes
     * @param chapterIterator
     */
     private void concatenateSpeeches(final Context context, int i, final List<File> chapterExportes, final Iterator<Chapter> chapterIterator){
         if(chapterIterator.hasNext()){// join the speeches of the chapter
             String name=screenplay.getTitle().replace(" ","_");
             final File destination=new File(context.getFilesDir(),"ch"+i+name+".wav");
             chapterExportes.add(destination);
             i++;
             final int finalI=i;
             AudioConcatenator audioConcatenator=new AudioConcatenator(context,destination);
             audioConcatenator.setDestination(destination);
             final Chapter chapter=chapterIterator.next();
             ListIterator<Speech>speechListIterator=chapter.getSpeechIterator();
             boolean empty=true;
             while (speechListIterator.hasNext()){
                 Speech speech=speechListIterator.next();
                 int limit=300;
                 while (speech.getAudioStatus()==false){
                     try {
                         limit--;
                         Thread.sleep(10);
                         if (limit<0){
                             Intent intent=new Intent(context,ListChapterActivity.class);
                             context.startActivity(intent);
                             Toast.makeText(context,"Errore nello scaricamento delle battute",Toast.LENGTH_SHORT).show();
                             return;
                         }
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
                 File speechFile=new File(speech.getAudioPath());
                 audioConcatenator.addFile(speechFile);
                 empty=false;
             }
             if(empty){
                 concatenateSpeeches(context,finalI,chapterExportes,chapterIterator);
             }
             else{
             try {
                 audioConcatenator.exec(new FFmpegExecuteResponseHandler() {
                     @Override
                     public void onSuccess(String message) {

                     }

                     @Override
                     public void onProgress(String message) {
                        System.out.println(message);
                     }

                     @Override
                     public void onFailure(String message) {
                         System.out.println("FAIL CONCAT"+message);
                     }

                     @Override
                     public void onStart() {
                         if(feedback!=null)feedback.setText("Esporto il capitolo "+finalI+"..");
                     }

                     @Override
                     public void onFinish() {
                        if(chapter.getSoundtrack()!=null && chapter.getSoundtrack().getAudio()!=null
                                && chapter.getSoundtrack().getAudio().exists()) {
                            System.out.println("ENTRO IN ADD SOUNDTRACK");
                            addSoundtrack(context, finalI, chapterExportes, chapterIterator,
                                    chapter, destination.getName());
                        }
                        else{
                            System.out.println("NON C'E' SOUNDTRACK");
                            concatenateSpeeches(context,finalI,chapterExportes,chapterIterator);
                            }
                     }
                 });
             } catch (FFmpegCommandAlreadyRunningException e) {
                 e.printStackTrace();
             }
         }

         }else{// join all chapters
             File dir=new File(screenplay.getPath(context));
             if(!dir.exists()){
                 dir.mkdir();
             }
             String name=screenplay.getTitle().replace(" ","_");
             File destination=new File(context.getFilesDir(),"concatenation"+name+".wav");
             AudioConcatenator audioConcatenator=new AudioConcatenator(context,destination);
             audioConcatenator.setDestination(destination);
             boolean empty=true;
             for (File file: chapterExportes){
                 if(file.exists()){
                     audioConcatenator.addFile(file);
                     empty=false;
                 }
             }
             if(empty){
                 Intent intent=new Intent(context,ListChapterActivity.class);
                 context.startActivity(intent);
                 Toast.makeText(context,"Lo sceneggiato non ha battute",Toast.LENGTH_SHORT).show();
                 return;
             }
             try {
                 audioConcatenator.exec(new FFmpegExecuteResponseHandler() {
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
                         if(feedback!=null)feedback.setText("Unisco i capitoli..");
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
     }

    /**
     * Method that adds a soundtrack to the export process
     * The method uses FFmpeg library
     * FFmpeg library calls are synchronized
     * @param context
     * @param i
     * @param chapterExportes
     * @param chapterIterator
     * @param chapter
     * @param dest
     * @return
     */
    private File addSoundtrack(final Context context,final int i, final List<File> chapterExportes,
                               final Iterator<Chapter> chapterIterator, Chapter chapter,String dest){
        File destination=new File(context.getFilesDir(),"merged_"+dest);
        File primary=chapterExportes.remove(chapterExportes.size()-1);
        AudioMixer audioMixer=new AudioMixer(context,
                primary,
                chapter.getSoundtrack().getAudio(),
                destination);
        chapterExportes.add(destination);
        try {
            audioMixer.exec(new FFmpegExecuteResponseHandler() {
                @Override
                public void onSuccess(String message) {

                }

                @Override
                public void onProgress(String message) {
                    System.out.println(message);
                }

                @Override
                public void onFailure(String message) {
                    System.out.println("FAIL SOUNDTRACK "+message);
                }

                @Override
                public void onStart() {
                    if(feedback!=null)feedback.setText("Aggiungo il sottofondo al capitolo "+i+"..");
                }

                @Override
                public void onFinish() {
                    concatenateSpeeches(context,i,chapterExportes,chapterIterator);
                }
            });
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
        }
        return destination;
    }

    /**
     * Conversion to MP3 and save of complete file in device memory
     * The method uses FFmpeg library
     * FFmpeg library calls are synchronized
     * @param context
     */
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

    /**
     * Public method that is called to start the FFmpeg conversion and export process (conversion + save on SD)
     * @param context
     */
    @Override
    public void export(Context context) {
        Iterator<Chapter>chapterIterator= screenplay.getChapterIterator();
        concatenateSpeeches(context,0,new ArrayList<File>(),chapterIterator);
    }
}
