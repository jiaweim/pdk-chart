package pdk.chart.data.general;

import java.util.EventListener;

/**
 * The interface that must be supported by classes that wish to receive
 * notification of changes to a dataset.
 */
public interface DatasetChangeListener extends EventListener {

    /**
     * Receives notification of an dataset change event.
     *
     * @param event information about the event.
     */
    void datasetChanged(DatasetChangeEvent event);

}
