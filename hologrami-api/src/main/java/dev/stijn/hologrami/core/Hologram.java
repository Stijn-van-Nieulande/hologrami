package dev.stijn.hologrami.core;

import dev.stijn.hologrami.api.DisplayFactory;
import dev.stijn.hologrami.api.EntityWrapper;
import dev.stijn.hologrami.api.HologramPart;
import dev.stijn.hologrami.api.LocationWrapper;
import dev.stijn.hologrami.core.enums.BillboardOrientation;
import dev.stijn.hologrami.core.enums.HologramPivot;
import dev.stijn.hologrami.core.enums.VisibilityMode;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Composite container to manage multiple hologram parts as a single entity.
 */
public class Hologram {
    private final DisplayFactory factory;
    private final List<HologramPart<?>> parts = new ArrayList<>();
    private DisplayContext baseCtx;
    private HologramPivot pivot = HologramPivot.BOTTOM_CENTER;
    private EntityWrapper<Void> interactionEntity = null;
    private HologramInteractionListener interactionListener = null;

    public Hologram(final DisplayFactory factory) {
        this.factory = factory;
    }

    public <C> Hologram addPart(final HologramPart<C> part) {
        this.parts.add(part);
        return this;
    }

    public List<HologramPart<?>> getParts() {
        return Collections.unmodifiableList(this.parts);
    }

    public void spawn(final LocationWrapper location, final boolean persistent) {
        // First, spawn all parts at the base location (position will be corrected later)
        final DisplayContext baseCtx = new DisplayContext(
            location, persistent, BillboardOrientation.VERTICAL, VisibilityMode.GLOBAL
        );
        for (
            final HologramPart<?> part : this.parts
        ) {
            part.spawn(baseCtx, this.factory);
        }
        // Now measure heights (using bounding box or custom)
        final double totalHeight = this.parts.stream()
            .mapToDouble(p -> p.getHeight() + p.getMargin())
            .sum();
        final double offsetY = switch (this.pivot) {
            case CENTER -> totalHeight / 2;
            case BOTTOM_CENTER -> totalHeight;
            default -> 0;
        };
        // Place parts top-down, repositioning wrappers
        LocationWrapper cursor = location.add(0, offsetY, 0);
        for (
            final HologramPart<?> part : this.parts
        ) {
            cursor = cursor.add(0, -(part.getHeight() + part.getMargin()), 0);
            if (part.getEntity() != null) {
                part.getEntity()
                    .setPosition(cursor);
            }
        }
        // Interaction entity spawn
        if (this.interactionListener != null) {
            if (this.interactionEntity != null) this.interactionEntity.remove();
            this.interactionEntity = this.factory.spawnInteraction(baseCtx, 1.0);
            this.interactionEntity.setPosition(location);
        }
    }

    public void remove() {
        this.parts.forEach(HologramPart::remove);
        if (this.interactionEntity != null) this.interactionEntity.remove();
    }

    public Hologram moveTo(final LocationWrapper newLoc) {
        this.remove();
        this.spawn(newLoc, this.baseCtx.persistent);
        return this;
    }

    public Hologram setPivot(final HologramPivot pivot) {
        this.pivot = pivot;
        return this;
    }

    /**
     * Adds an interaction hitbox; platform adapters must trigger listener when collision between a player and this
     * entity occurs.
     */
    public Hologram setInteraction(final double radius, final HologramInteractionListener listener) {
        this.interactionListener = listener;
        // interactionEntity will be spawned on next spawn()
        return this;
    }

    @Nullable
    public HologramInteractionListener getInteractionListener() {
        return this.interactionListener;
    }

    @Nullable
    public EntityWrapper<Void> getInteractionEntity() {
        return this.interactionEntity;
    }
}
