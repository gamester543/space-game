package com.mday.client.ui.render.debug;

import com.mday.client.game.Units;
import com.mday.client.ui.Surface;
import com.mday.client.ui.SurfaceConsumer;
import com.mday.common.model.Ship;
import com.mday.common.model.UnitType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

/**
 * Responsible for drawing information about the currently selected ships.
 */
public class SelectedShipRenderer implements SurfaceConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SelectedShipRenderer.class);

    @Nonnull
    private final Units units;

    /**
     * Create an instance of this renderer.
     *
     * @param units manages the units available
     */
    public SelectedShipRenderer(@Nonnull final Units units) {
        this.units = units;
    }

    @Override
    public void accept(@Nonnull final Surface surface) {
        final Graphics2D graphics = surface.getDrawGraphics();
        graphics.setColor(new Color(200, 200, 200));

        final Set<String> selectedClasses = units.getSelected().stream()
                .filter(unit -> unit.getUnitType() == UnitType.SHIP)
                .map(unit -> (Ship) unit)
                .map(ship -> ship.getShipClass().name())
                .sorted()
                .collect(Collectors.toSet());

        final FontMetrics fontMetrics = graphics.getFontMetrics();
        //final int textHeight = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
        final int textWidth = selectedClasses.stream().mapToInt(fontMetrics::stringWidth).max().orElse(0);

        int displayed = 0;
        for (final String shipClass : selectedClasses) {
            final int x = surface.getWidth() - textWidth - 15;
            final int y = 15 + (displayed * (fontMetrics.getHeight() + 5));
            graphics.drawString(shipClass, x, y);
            displayed++;
        }
    }
}