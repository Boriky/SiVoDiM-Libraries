package starklabs.sivodim.Drama.Model.Utilities;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public class SoundFxContainer {
    Vector<SoundFx> soundFxes;

    public Iterator<SoundFx> getIterator(){
        return soundFxes.iterator();
    }

    public void loadSounds(){

    }

}
