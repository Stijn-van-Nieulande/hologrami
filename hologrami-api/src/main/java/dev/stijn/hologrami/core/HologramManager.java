package dev.stijn.hologrami.core;

import dev.stijn.hologrami.api.DisplayFactory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages creation and lifecycle of holograms.
 */
public class HologramManager {
    private final DisplayFactory factory;
    private final List<Hologram> holograms = new ArrayList<>();

    public HologramManager(final DisplayFactory factory) {
        this.factory = factory;
    }

    /**
     * Creates and registers a new hologram.
     */
    public Hologram createHologram() {
        final Hologram holo = new Hologram(this.factory);
        this.holograms.add(holo);
        return holo;
    }

    /**
     * Removes and unregisters a hologram.
     */
    public void destroyHologram(@NotNull final Hologram holo) {
        holo.remove();
        this.holograms.remove(holo);
    }

    /**
     * For adapter listeners to access all managed holograms.
     */
    public List<Hologram> getHolograms() {
        return Collections.unmodifiableList(this.holograms);
    }
}
