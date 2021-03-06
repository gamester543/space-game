package com.mday.client.ui.render.debug;

import com.mday.client.event.Event;
import com.mday.client.event.EventConsumer;
import com.mday.client.event.type.input.MouseEvent;
import com.mday.client.ui.Surface;
import com.mday.client.ui.SurfaceConsumer;
import com.mday.common.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Responsible for drawing the current mouse coordinate location.
 */
public class MousePositionRenderer implements SurfaceConsumer, EventConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MousePositionRenderer.class);

    private int x = 0;
    private int y = 0;

    @Override
    public void accept(@Nonnull final Surface surface) {
        final Graphics2D graphics = surface.getDrawGraphics();
        graphics.setColor(new Color(200, 200, 200));

        final Point2D.Double point = new Point2D.Double(x, y);
        final Location location = surface.getCoordinateSystem().toLocation(point);

        graphics.drawString(String.format("PX: %.2f", point.getX()), 10, 20);
        graphics.drawString(String.format("PY: %.2f", point.getY()), 10, 40);
        graphics.drawString(String.format("LX: %.2f", location.getX()), 10, 60);
        graphics.drawString(String.format("LY: %.2f", location.getY()), 10, 80);
    }

    @Override
    public void accept(@Nonnull final Event event) {
        if (event instanceof MouseEvent) {
            final MouseEvent mouseEvent = (MouseEvent) event;
            x = mouseEvent.getMouseEvent().getX();
            y = mouseEvent.getMouseEvent().getY();
        }
    }
}
