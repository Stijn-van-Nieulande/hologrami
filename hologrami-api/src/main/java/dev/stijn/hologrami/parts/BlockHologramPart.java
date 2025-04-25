package dev.stijn.hologrami.parts;

import dev.stijn.hologrami.api.BlockDataWrapper;
import dev.stijn.hologrami.api.DisplayFactory;
import dev.stijn.hologrami.api.EntityWrapper;
import dev.stijn.hologrami.core.DisplayContext;
import org.jetbrains.annotations.NotNull;

/**
 * Block part of a hologram.
 */
public class BlockHologramPart extends AbstractHologramPart<BlockDataWrapper> {
    public BlockHologramPart(final BlockDataWrapper block) {
        this.content = block;
    }

    @Override
    protected EntityWrapper<BlockDataWrapper> spawnEntity(
        final DisplayContext ctx, @NotNull final DisplayFactory factory, final BlockDataWrapper block
    ) {
        return factory.spawnBlock(ctx, block);
    }
}
