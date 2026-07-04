package pdk.chart.data.gantt;

import pdk.chart.api.PublicCloneable;
import pdk.chart.data.time.SimpleTimePeriod;
import pdk.chart.data.time.TimePeriod;
import pdk.chart.util.Args;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * A simple representation of a task.  The task has a description and a
 * duration.  You can add sub-tasks to the task.
 */
public class Task implements Cloneable, PublicCloneable, Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = 1094303785346988894L;

    /**
     * The task description.
     */
    private String description;

    /**
     * The time period for the task (estimated or actual).
     */
    private TimePeriod duration;

    /**
     * The percent complete ({@code null} is permitted).
     */
    private Double percentComplete;

    /**
     * Storage for the sub-tasks (if any).
     */
    private List subtasks;

    /**
     * Creates a new task.
     *
     * @param description the task description ({@code null} not
     *                    permitted).
     * @param duration    the task duration ({@code null} permitted).
     */
    public Task(String description, TimePeriod duration) {
        Args.nullNotPermitted(description, "description");
        this.description = description;
        this.duration = duration;
        this.percentComplete = null;
        this.subtasks = new java.util.ArrayList();
    }

    /**
     * Creates a new task.
     *
     * @param description the task description ({@code null} not
     *                    permitted).
     * @param start       the start date ({@code null} not permitted).
     * @param end         the end date ({@code null} not permitted).
     */
    public Task(String description, Date start, Date end) {
        this(description, new SimpleTimePeriod(start, end));
    }

    /**
     * Returns the task description.
     *
     * @return The task description (never {@code null}).
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the task description.
     *
     * @param description the description ({@code null} not permitted).
     */
    public void setDescription(String description) {
        Args.nullNotPermitted(description, "description");
        this.description = description;
    }

    /**
     * Returns the duration (actual or estimated) of the task.
     *
     * @return The task duration (possibly {@code null}).
     */
    public TimePeriod getDuration() {
        return this.duration;
    }

    /**
     * Sets the task duration (actual or estimated).
     *
     * @param duration the duration ({@code null} permitted).
     */
    public void setDuration(TimePeriod duration) {
        this.duration = duration;
    }

    /**
     * Returns the percentage complete for this task.
     *
     * @return The percentage complete (possibly {@code null}).
     */
    public Double getPercentComplete() {
        return this.percentComplete;
    }

    /**
     * Sets the percentage complete for the task.
     *
     * @param percent the percentage ({@code null} permitted).
     */
    public void setPercentComplete(Double percent) {
        this.percentComplete = percent;
    }

    /**
     * Sets the percentage complete for the task.
     *
     * @param percent the percentage.
     */
    public void setPercentComplete(double percent) {
        setPercentComplete(Double.valueOf(percent));
    }

    /**
     * Adds a sub-task to the task.
     *
     * @param subtask the subtask ({@code null} not permitted).
     */
    public void addSubtask(Task subtask) {
        Args.nullNotPermitted(subtask, "subtask");
        this.subtasks.add(subtask);
    }

    /**
     * Removes a sub-task from the task.
     *
     * @param subtask the subtask.
     */
    public void removeSubtask(Task subtask) {
        this.subtasks.remove(subtask);
    }

    /**
     * Returns the sub-task count.
     *
     * @return The sub-task count.
     */
    public int getSubtaskCount() {
        return this.subtasks.size();
    }

    /**
     * Returns a sub-task.
     *
     * @param index the index.
     * @return The sub-task.
     */
    public Task getSubtask(int index) {
        return (Task) this.subtasks.get(index);
    }

    /**
     * Tests this object for equality with an arbitrary object.
     *
     * @param object the other object ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Task)) {
            return false;
        }
        Task that = (Task) object;
        if (!Objects.equals(this.description, that.description)) {
            return false;
        }
        if (!Objects.equals(this.duration, that.duration)) {
            return false;
        }
        if (!Objects.equals(this.percentComplete, that.percentComplete)) {
            return false;
        }
        if (!Objects.equals(this.subtasks, that.subtasks)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.description);
        hash = 71 * hash + Objects.hashCode(this.duration);
        hash = 71 * hash + Objects.hashCode(this.percentComplete);
        hash = 71 * hash + Objects.hashCode(this.subtasks);
        return hash;
    }

    /**
     * Returns a clone of the task.
     *
     * @return A clone.
     * @throws CloneNotSupportedException never thrown by this class, but
     *                                    subclasses may not support cloning.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Task clone = (Task) super.clone();
        return clone;
    }

}
