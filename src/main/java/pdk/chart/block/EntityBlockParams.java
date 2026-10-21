package pdk.chart.block;

/**
 * An interface that is used by the draw() method of some {@link Block}
 * implementations to determine whether to generate entities for the
 * items within the block.
 */
public interface EntityBlockParams {

    /**
     * Returns a flag that controls whether the block should return
     * entities for the items it draws.
     *
     * @return A boolean.
     */
    boolean getGenerateEntities();

}
