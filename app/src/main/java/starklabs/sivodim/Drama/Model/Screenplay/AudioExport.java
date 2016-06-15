package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;

import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;

import java.io.File;
import java.util.Iterator;
import java.util.ListIterator;

import starklabs.sivodim.Drama.Model.Chapter.Chapter;
import starklabs.sivodim.Drama.Model.Chapter.Speech;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public class AudioExport extends ExportAlgorithm {

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
        AudioConcatenator audioConcatenator=new AudioConcatenator(context,destination);
        audioConcatenator.setDestination(destination);
        Iterator<Chapter>chapterIterator= screenplay.getChapterIterator();
        while (chapterIterator.hasNext()){
            Chapter chapter=chapterIterator.next();
            ListIterator<Speech>speechListIterator=chapter.getSpeechIterator();
            while (speechListIterator.hasNext()){
                Speech speech=speechListIterator.next();
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

    private void addSoundtrack(){

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
