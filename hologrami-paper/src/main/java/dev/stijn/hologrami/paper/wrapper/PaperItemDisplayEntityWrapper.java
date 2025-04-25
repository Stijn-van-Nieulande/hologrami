package dev.stijn.hologrami.paper.wrapper;

import dev.stijn.hologrami.api.ItemStackWrapper;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;

public class PaperItemDisplayEntityWrapper extends AbstractPaperEntityWrapper<ItemStackWrapper, ItemDisplay> {

    public PaperItemDisplayEntityWrapper(final ItemDisplay entity) {
        super(entity);
    }

    @Override
    public void update(final ItemStackWrapper content) {
        this.entity.setItemStack((ItemStack) content);
    }
}
