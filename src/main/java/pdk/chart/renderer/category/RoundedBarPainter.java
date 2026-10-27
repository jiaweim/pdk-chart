package pdk.chart.renderer.category;

import pdk.chart.api.RectangleEdge;

import java.awt.*;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;
import java.util.Objects;

/**
 *
 *
 * @author Jiawei Mao
 * @version 1.0.0
 * @since 27 Oct 2026, 9:26 AM
 */
public class RoundedBarPainter extends StandardBarPainter {


    private final boolean fullyRounded;

    public RoundedBarPainter(boolean fullyRounded) {
        this.fullyRounded = fullyRounded;
    }

    @Override
    public void paintBar(Graphics2D g2, BarRenderer renderer, int row, int column, RectangularShape bar, RectangleEdge base) {
        super.paintBar(g2, renderer, row, column, toRoundedShape(bar), base);
    }

    @Override
    public void paintBarShadow(Graphics2D g2, BarRenderer renderer, int row, int column,
            RectangularShape bar, RectangleEdge base, boolean pegShadow) {
        super.paintBarShadow(g2, renderer, row, column, toRoundedShape(bar), base, pegShadow);
    }

    private RoundRectangle2D toRoundedShape(RectangularShape bar) {
        double shortSide = Math.min(bar.getWidth(), bar.getHeight());
        double arc = fullyRounded ? shortSide : Math.min(5.0, shortSide);
        return new RoundRectangle2D.Double(
                bar.getX(), bar.getY(), bar.getWidth(), bar.getHeight(),
                arc, arc
        );
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RoundedBarPainter that)) return false;
        if (!super.equals(o)) return false;
        return fullyRounded == that.fullyRounded;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fullyRounded);
    }
}
