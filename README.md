# Android Platformer

A 2D platformer for Android built on top of a **custom, dependency-free game engine** written from scratch in Java. No LibGDX, no Unity — just `SurfaceView`, `Canvas`, and a hand-rolled multi-threaded game loop.

![Gameplay screenshot](https://user-images.githubusercontent.com/15932746/192160287-ab3412a0-09f1-414e-a216-62807b0e408c.png)

![Gameplay screenshot](https://user-images.githubusercontent.com/15932746/192160483-df2679f8-c32f-40a4-9734-0b05f3434e46.png)

## Features

- **Custom game engine** — no third-party game libraries; only the Android SDK
- **Fixed-timestep game loop** (~50 tick/s) with dedicated worker threads
- **Tile-based level loader** with a compact text-grid file format (`res/raw/level*.xml`)
- **Run-length-packed colliders** — horizontal and vertical block runs are merged into single colliders at load time to shrink the collision set
- **Rect-based collision detection** with area-sorted resolution and per-side response (top / bottom / left / right)
- **Player mechanics**: run, jump, **wall-jump**, gravity, friction, air control
- **Sprite animation** driven from sprite sheets (`SpriteSheet`, `NormalSprite`, `BigSprite`, `ParticleSprite`)
- **Object-pooled particle system** for jumps, wall-jumps, explosions, enemy deaths
- **Enemy pooling** — `Vacuum` enemies pre-allocated once per level
- **Scrolling camera** that follows the player and clamps to map bounds
- **Multiple activities**: start menu → level select → gameplay → level cleared / game over

## Architecture

The engine runs on **three threads** with the UI thread coordinating input and drawing:

```
   UI thread                Game thread                Touch thread
   ─────────                ───────────                ────────────
   onTouchEvent  ─►  LinkedBlockingQueue  ─►  TouchEventHandler
                                                       │
                                                       ▼
                          GameMonitor.putTouchEvent (synchronized)
                                     │
                                     ▼
                          GameLoop → GameMonitor.nextGameCycle
                                     │  (fixed ~20ms tick)
                                     │  update world → draw to off-screen Bitmap
                                     ▼
                         Handler.post(bitmap) ──► UI thread
                                                       │
                                                       ▼
                                            SurfaceHolder.lockCanvas
                                            blit bitmap → unlockCanvasAndPost
```

- **`GameLoop`** (`framework/threads/GameLoop.java`) — simple `while (!interrupted)` driving the monitor.
- **`GameMonitor`** (`framework/GameMonitor.java`) — synchroniser owning tick timing, touch dispatch, world update, and rendering hand-off.
- **`TouchEventHandler`** — decouples `MotionEvent` reception from the game tick via a `LinkedBlockingQueue`.
- **`World`** (`framework/game/World.java`) — owns `Player`, block/hazard/interactive/enemy `Container`s.
- **`CollisionHandler`** — for each collider, gathers overlapping objects sorted by overlap area (largest first), then resolves per side.
- **`GameDraw`** — draws to an off-screen `Bitmap`, then blits a viewport-sized crop centred on the player.

### Entity hierarchy

```
GameObject (abstract)
├── inanimates (Block, Fire, Goal)
└── Mover
    └── Collider
        ├── Player  (wall-jump, particles, animation states)
        └── Vacuum  (patrolling enemy)
```

### Level format

Levels are plain-text grids in `res/raw/`. Each line is prefixed with a marker character; individual characters map to tiles (blocks, hazards, spawn point, goal, enemies). Consecutive same-type tiles are packed into single wide/tall colliders at load time by `LevelCreator`.

## Tech stack

- **Language:** Java
- **Platform:** Android (min SDK 21 / Android 5.0)
- **Rendering:** `SurfaceView` + `Canvas` with an off-screen `Bitmap` back-buffer (`Bitmap.Config.RGB_565`)
- **Build:** Gradle
- **Dependencies:** `com.android.support:appcompat-v7` — that's it

> **Note:** the project currently targets an older toolchain (AGP 3.0.1, compileSdk 24). Modernising the build is on the roadmap.

## Build & run

```sh
git clone https://github.com/EmilAhlberg/androidplatformer.git
cd androidplatformer
./gradlew assembleDebug
```

Install the resulting APK from `build/outputs/apk/debug/` on an Android device or emulator running Android 5.0+.

## Controls

- **Tap left / right side of the screen** — move
- **Tap upper area** — jump
- **Wall-jump** — jump while sliding against a wall

Reach the goal (green tile) to clear the level. Touching fire, enemies, or falling off the map ends the run.

## Roadmap

- [ ] Modernise Gradle / AGP / SDK versions
- [ ] Delta-time-based physics (currently frame-locked)
- [ ] Move rendering fully onto the game thread (proper `SurfaceView` usage)
- [ ] Add `onPause` / `onResume` lifecycle handling
- [ ] Spatial partitioning for collisions (grid / quadtree)
- [ ] Unit tests for collision handler and level loader
- [ ] More levels, more enemy types
- [ ] Sound effects and music

## License

MIT — see [LICENSE](LICENSE).
