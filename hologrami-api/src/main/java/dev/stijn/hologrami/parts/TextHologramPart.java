package dev.stijn.hologrami.parts;

import dev.stijn.hologrami.api.DisplayFactory;
import dev.stijn.hologrami.api.EntityWrapper;
import dev.stijn.hologrami.core.DisplayContext;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

/**
 * Text part of a hologram.
 */
public class TextHologramPart extends AbstractHologramPart<Component> {
    public TextHologramPart(final Component text) {
        this.content = text;
    }

    @Override
    protected EntityWrapper<Component> spawnEntity(
        final DisplayContext ctx, @NotNull final DisplayFactory factory, final Component text
    ) {
        return factory.spawnText(ctx, text);
    }

    @Override
    public void update(final Component newText) {
        super.update(newText);
    }
}
