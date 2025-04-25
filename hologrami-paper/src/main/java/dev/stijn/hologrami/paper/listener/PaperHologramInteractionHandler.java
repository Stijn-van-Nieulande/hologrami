package dev.stijn.hologrami.paper.listener;

import dev.stijn.hologrami.api.EntityWrapper;
import dev.stijn.hologrami.core.Hologram;
import dev.stijn.hologrami.core.HologramInteractionListener;
import dev.stijn.hologrami.core.HologramManager;
import dev.stijn.hologrami.paper.wrapper.PaperInteractionEntityWrapper;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.BoundingBox;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.WeakHashMap;

public class PaperHologramInteractionHandler implements Listener {
    private final HologramManager manager;
    private final Map<Player, Boolean> state = new WeakHashMap<>();

    public PaperHologramInteractionHandler(final HologramManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onPlayerMove(@NotNull final PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        for (
            final Hologram holo : this.manager.getHolograms()
        ) {
            final HologramInteractionListener listener = holo.getInteractionListener();
            final EntityWrapper<Void> hit = holo.getInteractionEntity();
            if (listener == null || hit == null) continue;

            final BoundingBox box = ((PaperInteractionEntityWrapper) hit).getBoundingBox();
            final Location loc = player.getLocation();
            final boolean inside = box.contains(loc.getX(), loc.getY(), loc.getZ());
            final boolean wasInside = this.state.getOrDefault(player, false);

            if (inside && !wasInside) {
                listener.onPlayerEnter(holo, player);
                this.state.put(player, true);
            } else if (!inside && wasInside) {
                listener.onPlayerLeave(holo, player);
                this.state.put(player, false);
            }
        }
    }
}
