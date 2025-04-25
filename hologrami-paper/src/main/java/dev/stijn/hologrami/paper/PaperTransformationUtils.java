package dev.stijn.hologrami.paper;

import dev.stijn.hologrami.core.Vector;
import org.bukkit.util.Transformation;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public class PaperTransformationUtils {
    private PaperTransformationUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    @NotNull
    @Contract(
        "_, _ -> new"
    )
    public static Transformation setTranslation(
        @NotNull final Transformation transformation, @NotNull final Vector translation
    ) {
        return new Transformation(
            new Vector3f((float) translation.x(), (float) translation.y(), (float) translation.z()), transformation
            .getLeftRotation(), transformation.getScale(), transformation.getRightRotation()
        );
    }

    @NotNull
    @Contract(
        "_, _ -> new"
    )
    public static Transformation setScale(@NotNull final Transformation transformation, @NotNull final Vector scale) {
        return new Transformation(
            transformation.getTranslation(), transformation.getLeftRotation(), new Vector3f(
            (float) scale.x(), (float) scale.y(), (float) scale.z()
        ), transformation.getRightRotation()
        );
    }
}
