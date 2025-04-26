# Hologrami

A simple modular and extensible library for creating holograms using Minecraft display entities.
Supports text, item, and block parts with scaling, rotation, pivoting, and basic interaction handling.

> [!WARNING]
> This library is currently still under development. The API is **not stable yet** and is subject to **breaking changes**. Please use it with caution in production environments and expect future updates to require changes to your code.

## 📦 Modules

- **`hologrami-api`**: Contains the platform-independent API and logic.
- **`hologrami-paper`**: PaperMC implementation adapter.

## 🛠 Installation

### Maven

```xml

<repository>
    <id>hologrami</id>
    <url>https://maven.pkg.github.com/Stijn-van-Nieulande/hologrami</url>
</repository>
```

```xml
<!-- Core API -->
<dependency>
  <groupId>dev.stijn.hologrami</groupId>
  <artifactId>hologrami-core</artifactId>
  <version>0.0.0+11</version>
</dependency>

<!-- Paper adapter -->
<dependency>
  <groupId>dev.stijn.hologrami</groupId>
  <artifactId>hologrami-paper</artifactId>
  <version>0.0.0+11</version>
</dependency>
```

### Gradle

```groovy
repositories {
    maven {
        name = "hologrami"
        url = "https://maven.pkg.github.com/Stijn-van-Nieulande/hologrami"
    }
}
```

```groovy
// Core API
implementation "dev.stijn.hologrami:hologrami-core:0.0.0+11"

// Paper adapter
implementation "dev.stijn.hologrami:hologrami-paper:0.0.0+11"
```

## 🚀 Quick Start (Paper Example)

```java
final HologramManager hologramManager = new HologramManager(new PaperDisplayFactory());
final Hologram hologram = hologramManager.createHologram();

Bukkit.getPluginManager().registerEvents(new PaperHologramInteractionHandler(hologramManager), plugin);

hologram.addPart(textPart(Component.text("Hello world!")));
hologram.addPart(itemPart(new ItemStack(Material.DIAMOND_SWORD)));
hologram.addPart(blockPart(Bukkit.createBlockData(Material.GOLD_BLOCK)));

hologram.setPivot(HologramPivot.BOTTOM_CENTER);
hologram.setInteraction(1.5, new HologramInteractionListener() {
    @Override public void onPlayerEnter(Hologram hologram, Object player) {
        player.sendMessage("You entered a hologram bounding box!");
    }
    @Override public void onPlayerLeave(Hologram hologram, Object player) {
        player.sendMessage("You left the hologram bounding box!");
    }
});

hologram.spawn(new PaperLocationWrapper(new Location(Bukkit.getWorld("world"), 0, 65, 0)), false);
```
