package dev.stijn.hologrami.paper.wrapper;

import dev.stijn.hologrami.api.BlockDataWrapper;
import org.bukkit.block.data.BlockData;

/**
 * BlockDataWrapper backed by Bukkit BlockData.
 */
public class PaperBlockDataWrapper implements BlockDataWrapper {
    private final BlockData data;

    public PaperBlockDataWrapper(final BlockData data) {
        this.data = data;
    }

    public BlockData getHandle() {
        return this.data;
    }
}
