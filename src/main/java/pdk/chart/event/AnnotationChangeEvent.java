package pdk.chart.event;

import pdk.chart.annotations.Annotation;
import pdk.chart.util.Args;

/**
 * An event that can be forwarded to any {@link AnnotationChangeListener} to
 * signal a change to an {@link Annotation}.
 */
public class AnnotationChangeEvent extends ChartChangeEvent {

    /**
     * The annotation that generated the event.
     */
    private final Annotation annotation;

    /**
     * Creates a new {@code AnnotationChangeEvent} instance.
     *
     * @param source     the event source.
     * @param annotation the annotation that triggered the event
     *                   ({@code null} not permitted).
     */
    public AnnotationChangeEvent(Object source, Annotation annotation) {
        super(source);
        Args.nullNotPermitted(annotation, "annotation");
        this.annotation = annotation;
    }

    /**
     * Returns the annotation that triggered the event.
     *
     * @return The annotation that triggered the event (never {@code null}).
     */
    public Annotation getAnnotation() {
        return this.annotation;
    }

}
