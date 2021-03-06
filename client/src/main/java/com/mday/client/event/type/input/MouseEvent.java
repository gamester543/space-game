package com.mday.client.event.type.input;

import static com.mday.client.event.EventType.MOUSE;

import com.mday.client.event.Event;

import javax.annotation.Nonnull;

/**
 * An event representing a mouse action.
 */
public class MouseEvent extends Event {
    @Nonnull
    private final java.awt.event.MouseEvent mouseEvent;

    /**
     * Create an instance of this event class.
     *
     * @param mouseEvent the mouse event that occurred
     */
    public MouseEvent(@Nonnull final java.awt.event.MouseEvent mouseEvent) {
        super(MOUSE);
        this.mouseEvent = mouseEvent;
    }

    /**
     * Retrieve the mouse event.
     *
     * @return the mouse event
     */
    @Nonnull
    public java.awt.event.MouseEvent getMouseEvent() {
        return mouseEvent;
    }
}
