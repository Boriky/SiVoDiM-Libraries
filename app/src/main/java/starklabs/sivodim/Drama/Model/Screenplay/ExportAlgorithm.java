package starklabs.sivodim.Drama.Model.Screenplay;

import android.content.Context;

/**
 * Created by Francesco Bizzaro on 25/05/2016.
 */
public abstract class ExportAlgorithm {
    /**
     * Screenplay attribute that represents the screenplay to export
     */
    protected Screenplay screenplay;

    /**
     * Create ExportAlgorithm object
     * @param screenplay
     */
    public void setScreenplay(Screenplay screenplay){
        this.screenplay=screenplay;
    }

    /**
     * Export the screenplay (abstract)
     * Concrete implementation in AudioExport and VideoExport
     * @param context
     */
    public abstract void export(Context context);
}
