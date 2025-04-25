package dev.stijn.hologrami.paper;

import dev.stijn.hologrami.api.BlockDataWrapper;
import dev.stijn.hologrami.api.DisplayFactory;
import dev.stijn.hologrami.api.EntityWrapper;
import dev.stijn.hologrami.api.ItemStackWrapper;
import dev.stijn.hologrami.core.DisplayContext;
import dev.stijn.hologrami.core.Vector;
import dev.stijn.hologrami.core.enums.BillboardOrientation;
import dev.stijn.hologrami.paper.wrapper.*;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * PaperMC-backed wrappers for core interfaces.
 */
public class PaperDisplayFactory implements DisplayFactory {

    @Override
    public EntityWrapper<Component> spawnText(
        @NotNull final DisplayContext ctx, @Nullable final Component text
    ) {
        final Location loc = ((PaperLocationWrapper) ctx.location).getHandle();
        final TextDisplay display = loc.getWorld()
            .spawn(loc, TextDisplay.class, d -> {
                d.text(text);
                this.configure(d, ctx);
            });
        return new PaperTextDisplayEntityWrapper(display);
    }

    @Override
    public EntityWrapper<ItemStackWrapper> spawnItem(
        @NotNull final DisplayContext ctx, final ItemStackWrapper itemWrapper
    ) {
        final Location loc = ((PaperLocationWrapper) ctx.location).getHandle();
        final ItemStack item = ((PaperItemStackWrapper) itemWrapper).getHandle();
        final ItemDisplay display = loc.getWorld()
            .spawn(loc, ItemDisplay.class, d -> {
                d.setItemStack(item);
                this.configure(d, ctx);
            });

        display.setTransformation(
            PaperTransformationUtils.setTranslation(display.getTransformation(), new Vector(0, 0.5, 0))
        );
        return new PaperItemDisplayEntityWrapper(display);
    }

    @Override
    public EntityWrapper<BlockDataWrapper> spawnBlock(
        @NotNull final DisplayContext ctx, final BlockDataWrapper blockWrapper
    ) {
        final Location loc = ((PaperLocationWrapper) ctx.location).getHandle();
        final BlockData data = ((PaperBlockDataWrapper) blockWrapper).getHandle();
        final BlockDisplay display = loc.getWorld()
            .spawn(loc, BlockDisplay.class, d -> {
                d.setBlock(data);
                this.configure(d, ctx);
            });

        display.setTransformation(
            PaperTransformationUtils.setTranslation(display.getTransformation(), new Vector(-0.5, 0, -0.5))
        );
        return new PaperBlockDisplayEntityWrapper(display);
    }

    @Override
    public EntityWrapper<Void> spawnInteraction(
        @NotNull final DisplayContext ctx, final double radius
    ) {
        final Location loc = ((PaperLocationWrapper) ctx.location).getHandle();
        final Interaction interaction = loc.getWorld()
            .spawn(loc, Interaction.class, e -> {
                e.setInteractionWidth((float) radius);
                e.setInteractionHeight((float) radius);
                e.setPersistent(ctx.persistent);
            });
        return new PaperInteractionEntityWrapper(interaction);
    }

    private void configure(@NotNull final Display display, @NotNull final DisplayContext ctx) {
        display.setPersistent(ctx.persistent);
        display.setBillboard(
            ctx.billboard == BillboardOrientation.CENTER ? Display.Billboard.CENTER : Display.Billboard.VERTICAL
        );
    }
}
