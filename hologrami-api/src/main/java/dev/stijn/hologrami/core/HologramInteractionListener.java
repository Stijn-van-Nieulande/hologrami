package dev.stijn.hologrami.core;

/**
 * Listener for hologram interaction events.
 */
public interface HologramInteractionListener {
    void onPlayerEnter(Hologram hologram, Object player);

    void onPlayerLeave(Hologram hologram, Object player);
}
