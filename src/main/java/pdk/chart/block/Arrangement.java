package pdk.chart.block;

import java.awt.*;

/**
 * An object that is responsible for arranging a collection of {@link Block}s
 * within a {@link BlockContainer}.
 */
public interface Arrangement {

    /**
     * Adds a block and a key which can be used to determine the position of
     * the block in the arrangement.  This method is called by the container
     * (you don't need to call this method directly) and gives the arrangement
     * an opportunity to record the details if they are required.
     *
     * @param block the block.
     * @param key   the key ({@code null} permitted).
     */
    void add(Block block, Object key);

    /**
     * Arranges the blocks within the specified container, subject to the given
     * constraint.
     *
     * @param container  the container ({@code null} not permitted).
     * @param g2         the graphics device.
     * @param constraint the constraint.
     * @return The container size after the arrangement.
     */
    Size2D arrange(BlockContainer container, Graphics2D g2, RectangleConstraint constraint);

    /**
     * Clears any cached layout information retained by the arrangement.
     */
    void clear();

}
