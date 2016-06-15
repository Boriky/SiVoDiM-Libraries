package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public abstract class ExportAlgorithm {
    protected Screenplay screenplay;

    public void setScreenplay(Screenplay screenplay){
        this.screenplay=screenplay;
    }

    public abstract void export(Context context);
}
