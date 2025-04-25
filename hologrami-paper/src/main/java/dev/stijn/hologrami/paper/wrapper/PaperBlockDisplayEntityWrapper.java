package dev.stijn.hologrami.paper.wrapper;

import dev.stijn.hologrami.api.BlockDataWrapper;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;

public class PaperBlockDisplayEntityWrapper extends AbstractPaperEntityWrapper<BlockDataWrapper, BlockDisplay> {
    public PaperBlockDisplayEntityWrapper(final BlockDisplay entity) {
        super(entity);
    }

    @Override
    public void update(final BlockDataWrapper content) {
        this.entity.setBlock((BlockData) content);
    }
}
