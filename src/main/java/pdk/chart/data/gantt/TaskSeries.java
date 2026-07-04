package pdk.chart.data.gantt;

import pdk.chart.data.general.Series;
import pdk.chart.data.general.SeriesChangeEvent;
import pdk.chart.util.Args;
import pdk.chart.util.CloneUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A series that contains zero, one or many {@link Task} objects.
 * <p>
 * This class is used as a building block for the {@link TaskSeriesCollection}
 * class that can be used to construct basic Gantt charts.
 *
 * @param <K> the key type.
 */
public class TaskSeries<K extends Comparable<K>> extends Series<K> {

    /**
     * Storage for the tasks in the series.
     */
    private List<Task> tasks;

    /**
     * Constructs a new series with the specified name.
     *
     * @param name the series name ({@code null} not permitted).
     */
    public TaskSeries(K name) {
        super(name);
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the series and sends a
     * {@link SeriesChangeEvent} to all registered
     * listeners.
     *
     * @param task the task ({@code null} not permitted).
     */
    public void add(Task task) {
        Args.nullNotPermitted(task, "task");
        this.tasks.add(task);
        fireSeriesChanged();
    }

    /**
     * Removes a task from the series and sends
     * a {@link SeriesChangeEvent}
     * to all registered listeners.
     *
     * @param task the task.
     */
    public void remove(Task task) {
        this.tasks.remove(task);
        fireSeriesChanged();
    }

    /**
     * Removes all tasks from the series and sends
     * a {@link SeriesChangeEvent}
     * to all registered listeners.
     */
    public void removeAll() {
        this.tasks.clear();
        fireSeriesChanged();
    }

    /**
     * Returns the number of items in the series.
     *
     * @return The item count.
     */
    @Override
    public int getItemCount() {
        return this.tasks.size();
    }

    /**
     * Returns a task from the series.
     *
     * @param index the task index (zero-based).
     * @return The task.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the task in the series that has the specified description.
     *
     * @param description the name ({@code null} not permitted).
     * @return The task (possibly {@code null}).
     */
    public Task get(String description) {
        Task result = null;
        for (Task t : this.tasks) {
            if (t.getDescription().equals(description)) {
                result = t;
                break;
            }
        }
        return result;
    }

    /**
     * Returns an unmodifialble list of the tasks in the series.
     *
     * @return The tasks.
     */
    public List<Task> getTasks() {
        return Collections.unmodifiableList(this.tasks);
    }

    /**
     * Tests this object for equality with an arbitrary object.
     *
     * @param obj the object to test against ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TaskSeries)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        TaskSeries that = (TaskSeries) obj;
        if (!this.tasks.equals(that.tasks)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.tasks);
        return hash;
    }

    /**
     * Returns an independent copy of this series.
     *
     * @return A clone of the series.
     * @throws CloneNotSupportedException if there is some problem cloning
     *                                    the dataset.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        TaskSeries clone = (TaskSeries) super.clone();
        clone.tasks = CloneUtils.cloneList(this.tasks);
        return clone;
    }

}
