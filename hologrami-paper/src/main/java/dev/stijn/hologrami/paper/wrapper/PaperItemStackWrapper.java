package dev.stijn.hologrami.paper.wrapper;

import dev.stijn.hologrami.api.ItemStackWrapper;
import org.bukkit.inventory.ItemStack;

/**
 * ItemStackWrapper backed by Bukkit ItemStack.
 */
public class PaperItemStackWrapper implements ItemStackWrapper {
    private final ItemStack item;

    public PaperItemStackWrapper(final ItemStack item) {
        this.item = item;
    }

    public ItemStack getHandle() {
        return this.item;
    }
}
