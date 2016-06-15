package starklabs.sivodim.Drama.Model.Screenplay;

import java.io.File;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public abstract class ShareAlgorithm {
    protected File fileToShare;

    public void setFile(File file){
        fileToShare=file;
    }

    public abstract void share();
}
