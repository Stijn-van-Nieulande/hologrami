package dev.stijn.hologrami.paper;

import dev.stijn.hologrami.api.BlockDataWrapper;
import dev.stijn.hologrami.api.HologramPart;
import dev.stijn.hologrami.api.ItemStackWrapper;
import dev.stijn.hologrami.paper.wrapper.PaperBlockDataWrapper;
import dev.stijn.hologrami.paper.wrapper.PaperItemStackWrapper;
import dev.stijn.hologrami.parts.BlockHologramPart;
import dev.stijn.hologrami.parts.ItemHologramPart;
import dev.stijn.hologrami.parts.TextHologramPart;
import net.kyori.adventure.text.Component;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class PaperHologramParts {
    private PaperHologramParts() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    @NotNull
    @Contract(
        "_ -> new"
    )
    public static HologramPart<Component> textPart(final Component text) {
        return new TextHologramPart(text);
    }

    @NotNull
    @Contract(
        "_ -> new"
    )
    public static HologramPart<ItemStackWrapper> itemPart(final ItemStack stack) {
        return new ItemHologramPart(new PaperItemStackWrapper(stack));
    }

    @NotNull
    @Contract(
        "_ -> new"
    )
    public static HologramPart<BlockDataWrapper> blockPart(final BlockData blockData) {
        return new BlockHologramPart(new PaperBlockDataWrapper(blockData));
    }
}
