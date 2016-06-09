package starklabs.sivodim.Drama.Model.Utilities;

import java.io.File;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public class Avatar extends Image {
    private static final long maxSize=524288;

    private File file;

    public Avatar(String path){
        super(path);
    }

    @Override
    protected long maxSize() {
        return maxSize;
    }
}
