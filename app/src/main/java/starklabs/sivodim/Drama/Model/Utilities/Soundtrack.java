package starklabs.sivodim.Drama.Model.Utilities;

import java.io.File;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public class Soundtrack extends Sound {
    private static final long maxSize=15728640L;

    public Soundtrack(String path){
        super(path);
    }

    @Override
    protected long maxSize() {
        return maxSize;
    }
}
