
import java.beans.*;

class EditedAdaptor implements PropertyChangeListener {

    EditedAdaptor(PanelHojaProp t) {
	sink = t;
    }	

    public void propertyChange(PropertyChangeEvent evt) {
	sink.wasModified(evt);
    }

    PanelHojaProp sink;
}
