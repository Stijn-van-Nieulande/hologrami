package dev.stijn.hologrami.api;

import dev.stijn.hologrami.core.DisplayContext;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Factory for creating and managing display entities in a platform-agnostic way.
 */
public interface DisplayFactory {
    /**
     * Spawn a text display at given position with initial text.
     */
    EntityWrapper<Component> spawnText(@NotNull DisplayContext context, @Nullable Component text);

    /**
     * Spawn an item display at given position with given item stack.
     */
    EntityWrapper<ItemStackWrapper> spawnItem(
        @NotNull DisplayContext context, ItemStackWrapper item
    );

    /**
     * Spawn a block display at given position with given block data.
     */
    EntityWrapper<BlockDataWrapper> spawnBlock(
        @NotNull DisplayContext context, BlockDataWrapper block
    );

    /**
     * Spawn an invisible interaction entity to detect collisions/hits.
     */
    EntityWrapper<Void> spawnInteraction(@NotNull DisplayContext context, double radius);
}
