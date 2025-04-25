package dev.stijn.hologrami.api;

public interface LocationWrapper {
    LocationWrapper add(double x, double y, double z);

    double getX();

    double getY();

    double getZ();
}
