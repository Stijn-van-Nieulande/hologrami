package dev.stijn.hologrami.paper.wrapper;

import org.bukkit.entity.Interaction;
import org.bukkit.util.BoundingBox;

public class PaperInteractionEntityWrapper extends AbstractPaperEntityWrapper<Void, Interaction> {
    public PaperInteractionEntityWrapper(final Interaction entity) {
        super(entity);
    }

    @Override
    public void update(final Void content) {
        /* no-op */
    }

    public BoundingBox getBoundingBox() {
        return this.entity.getBoundingBox();
    }
}
