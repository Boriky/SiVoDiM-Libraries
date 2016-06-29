
        package starklabs.sivodim.Drama.Model.Screenplay;

        import android.content.Context;
        import android.widget.Toast;

        import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;

        import java.io.File;
        import java.util.Iterator;
        import java.util.ListIterator;

        import starklabs.sivodim.Drama.Model.Chapter.Chapter;
        import starklabs.sivodim.Drama.Model.Chapter.Speech;
        import starklabs.sivodim.Drama.Model.Utilities.Soundtrack;

        /**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public class AudioExport extends ExportAlgorithm {

    private void concatenateSpeeches(Context context){
        File dir=new File(screenplay.getPath(context));
        if(!dir.exists()){
            dir.mkdir();
        }

        String name=screenplay.getTitle().replace(" ","_");
        File destination=new File(context.getFilesDir(),"concatenation"+name+".wav");
        AudioConcatenator audioConcatenator=new AudioConcatenator(context,destination);
        audioConcatenator.setDestination(destination);
        Iterator<Chapter>chapterIterator= screenplay.getChapterIterator();
        while (chapterIterator.hasNext()){
            Chapter chapter=chapterIterator.next();
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
        }
        try {
            audioConcatenator.exec();
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
        }
    }

    private File addSoundtrack(Context context,File chapterExportes, Soundtrack soundtrack){
        String name=chapterExportes.getName();
        File destination=new File(context.getFilesDir(),"merged_"+name);
        AudioMixer audioMixer=new AudioMixer(context,chapterExportes,soundtrack.getAudio(),destination);
        try {
            audioMixer.exec();
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
        }
        return destination;
    }

    private void finalizeExport(Context context){
        String name=screenplay.getTitle().replace(" ","_");
        File file=new File(context.getFilesDir(),"concatenation"+name+".wav");
        File destination=new File(context.getFilesDir(),name+".mp3");
        Mp3Converter mp3Converter=new Mp3Converter(context,file,destination);
        try {
            mp3Converter.exec();
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void export(Context context) {
        concatenateSpeeches(context);
        finalizeExport(context);
        Toast.makeText(context,"Esportazione conclusa",Toast.LENGTH_SHORT).show();
    }
}






/*package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;
import android.widget.Toast;

import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.Speech;
import starklabs.sivodim.Drama.Model.Utilities.Soundtrack;*/

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
/*public class AudioExport extends ExportAlgorithm {

    private void concatenateSpeeches(Context context){
        File dir=new File(screenplay.getPath(context));
        if(!dir.exists()){
            dir.mkdir();
        }
        File dirPatials=new File(dir,"Partials");
        if(!dirPatials.exists()){
            dirPatials.mkdir();
        }
        String name=screenplay.getTitle().replace(" ","_");
        File destination=new File(context.getFilesDir(),"concatenation"+name+".wav");
        Vector<File>chapterAudios=new Vector<>();
        Iterator<Chapter>chapterIterator= screenplay.getChapterIterator();
        int i=0;
        while (chapterIterator.hasNext()){
            Chapter chapter=chapterIterator.next();
            System.out.println(chapter.getTitle().toUpperCase());
            File chapterConcat=new File(context.getFilesDir(),"concat"+i+name+".wav");
            if(!chapterConcat.exists()){
                try {
                    chapterConcat.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            chapterConcat=speechChapter(context,chapter,chapterConcat);
            /*AudioConcatenator chapterConcatenator=new AudioConcatenator(context,chapterConcat);
            chapterConcatenator.setDestination(chapterConcat);
            ListIterator<Speech>speechListIterator=chapter.getSpeechIterator();
            while (speechListIterator.hasNext()){
                Speech speech=speechListIterator.next();
                while (speech.getAudioStatus()==false){
                    System.out.println("Battuta"+i+"si sta scaricando");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                File speechFile=new File(speech.getAudioPath());
                chapterConcatenator.addFile(speechFile);
            }
            try {
                chapterConcatenator.exec();
            } catch (FFmpegCommandAlreadyRunningException e) {
                e.printStackTrace();
            }
            Toast.makeText(context,"Esportato "+chapter.getTitle(),Toast.LENGTH_SHORT).show();
            if(chapter.getSoundtrack()!=null &&
                    chapter.getSoundtrack().getAudio()!=null &&
                    chapter.getSoundtrack().getAudio().exists())
                chapterAudios.add(addSoundtrack(context,chapterConcat,chapter.getSoundtrack()));
            else
                chapterAudios.add(chapterConcat);
            i++;
        }

        AudioConcatenator audioConcatenator=new AudioConcatenator(context,destination);
        audioConcatenator.setDestination(destination);
        Iterator<File>fileIterator=chapterAudios.iterator();
        while (fileIterator.hasNext()){
            File fileExp=fileIterator.next();
            audioConcatenator.addFile(fileExp);
        }
        try {
            audioConcatenator.exec();
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
        }
        // delete garbage from physical memory
        /*Iterator<File>fileIterator2=chapterAudios.iterator();
        while (fileIterator2.hasNext()){
            File fileExp=fileIterator2.next();
            if(fileExp.exists())
                fileExp.delete();
        }*/
    /*}

    private File speechChapter(Context context,Chapter chapter,File destination){
        AudioConcatenator chapterConcatenator=new AudioConcatenator(context,destination);
        chapterConcatenator.setDestination(destination);
        ListIterator<Speech>speechListIterator=chapter.getSpeechIterator();
        int i=0;
        while (speechListIterator.hasNext()){
            Speech speech=speechListIterator.next();
            // check if audio is still downloading
            while (speech.getAudioStatus()==false){
                System.out.println("Battuta"+i+"si sta scaricando");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            File speechFile=new File(speech.getAudioPath());
            chapterConcatenator.addFile(speechFile);
            i++;
        }
        try {
            chapterConcatenator.exec();
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
        }
        return destination;
    }

    private File addSoundtrack(Context context,File chapterExportes, Soundtrack soundtrack){
        String name=chapterExportes.getName();
        File destination=new File(context.getFilesDir(),"merged_"+name);
        AudioMixer audioMixer=new AudioMixer(context,chapterExportes,soundtrack.getAudio(),destination);
        try {
            audioMixer.exec();
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
        }
        return destination;
    }

    private void finalizeExport(Context context){
        String name=screenplay.getTitle().replace(" ","_");
        File file=new File(context.getFilesDir(),"concatenation"+name+".wav");
        File destination=new File(context.getFilesDir(),name+".mp3");
        Mp3Converter mp3Converter=new Mp3Converter(context,file,destination);
        try {
            mp3Converter.exec();
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void export(Context context) {
        concatenateSpeeches(context);
        finalizeExport(context);
    }
}
*/