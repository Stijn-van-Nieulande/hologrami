package dev.stijn.hologrami.api;

import dev.stijn.hologrami.core.Vector;
import org.jetbrains.annotations.Nullable;

/**
 * Wrapper around platform-specific entity, exposing transform and remove.
 */
public interface EntityWrapper<C> {
    void setScale(Vector scale);

    void setRotation(Vector euler);

    void update(C content);

    void remove();

    @Nullable LocationWrapper getLocation();

    void setPosition(LocationWrapper loc);

    double getBoundingBoxHeight();
}
