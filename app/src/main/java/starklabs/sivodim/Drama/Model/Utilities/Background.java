package starklabs.sivodim.Drama.Model.Utilities;

import java.io.File;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public class Background extends Image {
    /**
     * The maximum dimension for a background
     */
    private static final long maxSize=1048576;

    /**
     * A File that links the picture for the background
     */
    private File file;

    /**
     * Constructor that sets up the file with the path to the picture
     * @param path
     */
    public Background(String path){
        super(path);
    }

    /**
     * Return the maximum dimension in bytes for a Background
     * @return
     */
    @Override
    protected long maxSize() {
        return maxSize;
    }
}
