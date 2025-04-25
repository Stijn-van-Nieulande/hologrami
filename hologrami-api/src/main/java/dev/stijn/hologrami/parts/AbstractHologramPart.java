package dev.stijn.hologrami.parts;

import dev.stijn.hologrami.api.DisplayFactory;
import dev.stijn.hologrami.api.EntityWrapper;
import dev.stijn.hologrami.api.HologramPart;
import dev.stijn.hologrami.core.DisplayContext;
import dev.stijn.hologrami.core.Vector;
import dev.stijn.hologrami.core.enums.BillboardOrientation;
import dev.stijn.hologrami.core.enums.VisibilityMode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Abstract base for display-based hologram parts.
 */
public abstract class AbstractHologramPart<C> implements HologramPart<C> {
    protected C content;
    protected EntityWrapper<C> entity;
    protected Vector scale = new Vector(1, 1, 1);
    protected Vector rotation = new Vector(0, 0, 0);
    protected double margin = 0;
    protected BillboardOrientation billboard = BillboardOrientation.VERTICAL;
    protected VisibilityMode visibility = VisibilityMode.GLOBAL;
    private double height;
    private boolean customHeight = false;

    protected abstract EntityWrapper<C> spawnEntity(
        DisplayContext ctx, DisplayFactory factory, C content
    );

    @Override
    public void spawn(@NotNull final DisplayContext ctx, final DisplayFactory factory) {
        final DisplayContext partCtx = new DisplayContext(
            ctx.location.add(0, this.margin, 0), ctx.persistent, this.billboard, this.visibility
        );
        this.entity = this.spawnEntity(partCtx, factory, this.content);
        this.entity.setScale(this.scale);
        this.entity.setRotation(this.rotation);
        // determine bounding-box height if not custom
        if (!this.customHeight) {
            final double boundingBoxHeight = this.entity.getBoundingBoxHeight();
            if (boundingBoxHeight > 0) this.height = boundingBoxHeight;
        }
    }

    @Override
    public void remove() {
        if (this.entity != null) this.entity.remove();
    }

    @Override
    public void setScale(final Vector s) {
        this.scale = s;
        if (this.entity != null) this.entity.setScale(s);
    }

    @Override
    public void setRotation(final Vector r) {
        this.rotation = r;
        if (this.entity != null) this.entity.setRotation(r);
    }

    @Override
    public void setBillboard(final BillboardOrientation b) {
        this.billboard = b;
    }

    @Override
    public void setVisibility(final VisibilityMode v) {
        this.visibility = v;
    }

    @Override
    public double getMargin() {
        return this.margin;
    }

    @Override
    public void setMargin(final double m) {
        this.margin = m;
    }

    @Override
    public double getHeight() {
        if (!this.customHeight && this.entity != null) {
            final double boundingBoxHeight = this.entity.getBoundingBoxHeight();
            if (boundingBoxHeight > 0) this.height = boundingBoxHeight;
        }
        return this.height;
    }

    @Override
    public void setHeight(final double height) {
        this.height = height;
        this.customHeight = true;
    }

    @Override
    public void replaceWith(final HologramPart<C> other) {
        other.setScale(this.scale);
        other.setRotation(this.rotation);
        other.setMargin(this.margin);
        other.setBillboard(this.billboard);
        other.setVisibility(this.visibility);
        if (this.customHeight) other.setHeight(this.height);
        if (this.entity != null) this.entity.remove();
    }

    @Override
    public void update(final C newContent) {
        this.content = newContent;
        if (this.entity != null) {
            this.entity.update(newContent);
        }
    }

    @Nullable
    @Override
    public EntityWrapper<C> getEntity() {
        return this.entity;
    }
}
