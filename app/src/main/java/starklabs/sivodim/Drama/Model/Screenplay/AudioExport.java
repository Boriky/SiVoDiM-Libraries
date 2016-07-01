
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

     private TextView feedback;

     public AudioExport(TextView feedback){
         this.feedback=feedback;
     }

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
             while (speechListIterator.hasNext()){
                 Speech speech=speechListIterator.next();
                 while (speech.getAudioStatus()==false){
                     System.out.println("Battuta si sta scaricando");
                     try {
                         Thread.sleep(10);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
                 File speechFile=new File(speech.getAudioPath());
                 audioConcatenator.addFile(speechFile);
             }
             try {
                 audioConcatenator.exec(new FFmpegExecuteResponseHandler() {
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
                         if(feedback!=null)feedback.setText("Esporto il capitolo "+finalI+"..");
                     }

                     @Override
                     public void onFinish() {
                        if(chapter.getSoundtrack()!=null && chapter.getSoundtrack().getAudio()!=null
                                && chapter.getSoundtrack().getAudio().exists())
                            addSoundtrack(context,finalI,chapterExportes,chapterIterator,chapter,destination.getName());
                         else
                            concatenateSpeeches(context,finalI,chapterExportes,chapterIterator);
                     }
                 });
             } catch (FFmpegCommandAlreadyRunningException e) {
                 e.printStackTrace();
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
             for (File file: chapterExportes){
                 if(file.exists())
                     audioConcatenator.addFile(file);
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

    private void finalizeExport(final Context context){
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

    @Override
    public void export(Context context) {
        Iterator<Chapter>chapterIterator= screenplay.getChapterIterator();
        concatenateSpeeches(context,0,new ArrayList<File>(),chapterIterator);
    }
}
