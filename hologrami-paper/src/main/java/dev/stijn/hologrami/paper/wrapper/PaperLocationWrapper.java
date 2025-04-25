package dev.stijn.hologrami.paper.wrapper;

import dev.stijn.hologrami.api.LocationWrapper;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

/**
 * LocationWrapper backed by Bukkit Location.
 */
public class PaperLocationWrapper implements LocationWrapper {
    private final Location loc;

    public PaperLocationWrapper(final Location loc) {
        this.loc = loc;
    }

    @NotNull public static Location unwrap(@NotNull final LocationWrapper w) {
        return ((PaperLocationWrapper) w).loc.clone();
    }

    @Override
    public LocationWrapper add(final double x, final double y, final double z) {
        return new PaperLocationWrapper(
            this.loc.clone()
                .add(x, y, z)
        );
    }

    @Override
    public double getX() {
        return this.loc.getX();
    }

    @Override
    public double getY() {
        return this.loc.getY();
    }

    @Override
    public double getZ() {
        return this.loc.getZ();
    }

    public Location getHandle() {
        return this.loc;
    }
}
