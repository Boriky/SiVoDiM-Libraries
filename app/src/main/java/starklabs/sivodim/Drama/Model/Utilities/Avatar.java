package starklabs.sivodim.Drama.Model.Utilities;

import java.io.File;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public class Avatar extends Image {
    /**
     * the maximum dimension of the picture in bytes
     */
    private static final long maxSize=524288;

    /**
     * the File which links the picture
     */
    private File file;

    /**
     * Constructor that sets the File whith the path to the picture
     * @param path the path to the picture
     */
    public Avatar(String path){
        super(path);
    }

    /**
     * Return the maximum dimension in bytes of the picture
     * @return
     */
    @Override
    protected long maxSize() {
        return maxSize;
    }
}
