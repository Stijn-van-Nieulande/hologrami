package dev.stijn.hologrami.paper.wrapper;

import dev.stijn.hologrami.api.EntityWrapper;
import dev.stijn.hologrami.api.LocationWrapper;
import dev.stijn.hologrami.core.Vector;
import dev.stijn.hologrami.paper.PaperTransformationUtils;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Display;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TextDisplay;
import org.jetbrains.annotations.NotNull;

/**
 * Wraps a Bukkit Entity and exposes transform/remove.
 */
abstract class AbstractPaperEntityWrapper<C, E extends Entity> implements EntityWrapper<C> {
    protected final E entity;

    public AbstractPaperEntityWrapper(final E entity) {
        this.entity = entity;
    }

    @Override
    public void setScale(final Vector scale) {
        if (this.entity instanceof final Display display) {
            display.setTransformation(PaperTransformationUtils.setScale(display.getTransformation(), scale));
        }
    }

    @Override
    public void setRotation(@NotNull final Vector euler) {
        this.entity.setRotation((float) euler.y(), (float) euler.x());
    }

    @Override
    public void remove() {
        this.entity.remove();
    }

    @Override
    public double getBoundingBoxHeight() {
        if (this.entity instanceof final Display display) {
            final float scale = display.getTransformation()
                .getScale().y;

            if (display instanceof final TextDisplay textDisplay) {
                final String plainText = PlainTextComponentSerializer.plainText()
                    .serialize(textDisplay.text());
                final String[] lines = plainText.split("\n");
                final int lineCount = lines.length;
                return lineCount * 0.25f * scale;
            }
            return scale;
        }
        return this.entity.getBoundingBox()
            .getHeight();
    }

    @Override
    public LocationWrapper getLocation() {
        return new PaperLocationWrapper(this.entity.getLocation());
    }

    @Override
    public void setPosition(final LocationWrapper loc) {
        this.entity.teleport(PaperLocationWrapper.unwrap(loc));
    }
}
