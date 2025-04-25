package dev.stijn.hologrami.api;

import dev.stijn.hologrami.core.DisplayContext;
import dev.stijn.hologrami.core.Vector;
import dev.stijn.hologrami.core.enums.BillboardOrientation;
import dev.stijn.hologrami.core.enums.VisibilityMode;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a single part of a composite hologram.
 */
public interface HologramPart<C> {
    /** Spawns the underlying display entity(s). */
    void spawn(DisplayContext context, DisplayFactory factory);

    /** Removes the display entity(s). */
    void remove();

    /**
     * Scale this part.
     *
     * @param scale uniform scale vector
     */
    void setScale(Vector scale);

    /**
     * Rotate this part.
     *
     * @param rotation Euler angles (pitch, yaw, roll)
     */
    void setRotation(Vector rotation);

    double getMargin();

    /**
     * Adds margin offset along vertical axis.
     *
     * @param margin extra height offset
     */
    void setMargin(double margin);

    /**
     * Sets billboard orientation.
     */
    void setBillboard(BillboardOrientation orientation);

    /**
     * Sets visibility mode global or per-player.
     */
    void setVisibility(VisibilityMode mode);

    /**
     * Height of this part (world units). If not overridden by setHeight, uses entity's bounding box height.
     */
    double getHeight();

    /**
     * Override the automatically determined height (world units).
     */
    void setHeight(double height);

    /**
     * Updates content for this part (text, item, or block).
     */
    void update(C content);

    /**
     * Replace this part with another part, preserving transform.
     */
    void replaceWith(HologramPart<C> other);

    @Nullable EntityWrapper<C> getEntity();
}
