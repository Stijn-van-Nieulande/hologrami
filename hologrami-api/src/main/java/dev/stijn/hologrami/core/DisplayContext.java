package dev.stijn.hologrami.core;

import dev.stijn.hologrami.api.LocationWrapper;
import dev.stijn.hologrami.core.enums.BillboardOrientation;
import dev.stijn.hologrami.core.enums.VisibilityMode;

/**
 * Context for spawning: location, world, persistence, orientation, visibility mode, etc.
 */
public class DisplayContext {
    public final LocationWrapper location;
    public final boolean persistent;
    public final BillboardOrientation billboard;
    public final VisibilityMode visibility;

    public DisplayContext(
        final LocationWrapper loc, final boolean persistent, final BillboardOrientation billboard, final VisibilityMode visibility
    ) {
        this.location = loc;
        this.persistent = persistent;
        this.billboard = billboard;
        this.visibility = visibility;
    }
}
