package dev.stijn.hologrami.paper.wrapper;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.TextDisplay;

public class PaperTextDisplayEntityWrapper extends AbstractPaperEntityWrapper<Component, TextDisplay> {

    public PaperTextDisplayEntityWrapper(final TextDisplay entity) {
        super(entity);
    }

    @Override
    public void update(final Component content) {
        this.entity.text(content);
    }
}
