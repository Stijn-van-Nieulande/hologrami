package dev.stijn.hologrami.parts;

import dev.stijn.hologrami.api.DisplayFactory;
import dev.stijn.hologrami.api.EntityWrapper;
import dev.stijn.hologrami.api.ItemStackWrapper;
import dev.stijn.hologrami.core.DisplayContext;

/**
 * Item part of a hologram.
 */
public class ItemHologramPart extends AbstractHologramPart<ItemStackWrapper> {
    public ItemHologramPart(final ItemStackWrapper item) {
        this.content = item;
    }

    @Override
    protected EntityWrapper<ItemStackWrapper> spawnEntity(
        final DisplayContext ctx, final DisplayFactory factory, final ItemStackWrapper item
    ) {
        return factory.spawnItem(ctx, item);
    }
}
