package io.github.restioson.koth.game;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;
import xyz.nucleoid.plasmid.game.config.PlayerConfig;

public class KothConfig {
    public static final Codec<KothConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            PlayerConfig.CODEC.fieldOf("players").forGetter(config -> config.playerConfig),
            MapConfig.CODEC.fieldOf("map").forGetter(config -> config.map),
            Codec.INT.optionalFieldOf("time_limit_secs", 0).forGetter(config -> config.timeLimitSecs),
            Codec.INT.optionalFieldOf("first_to", 1).forGetter(config -> config.firstTo),
            Codec.BOOL.optionalFieldOf("winner_takes_all", false).forGetter(config -> config.winnerTakesAll),
            Codec.BOOL.optionalFieldOf("has_stick", false).forGetter(config -> config.hasStick),
            Codec.BOOL.optionalFieldOf("has_bow", false).forGetter(config -> config.hasStick),
            Codec.BOOL.optionalFieldOf("has_feather", false).forGetter(config -> config.hasFeather),
            Codec.BOOL.optionalFieldOf("deathmatch", false).forGetter(config -> config.deathmatch),
            Codec.BOOL.optionalFieldOf("spawn_invulnerability", true).forGetter(config -> config.spawnInvuln),
            Codec.BOOL.optionalFieldOf("knockoff", false).forGetter(config -> config.knockoff)

            ).apply(instance, KothConfig::new));

    public final PlayerConfig playerConfig;
    public final MapConfig map;
    public final int timeLimitSecs;
    public final int firstTo;
    public final boolean winnerTakesAll;
    public final boolean hasStick;
    public final boolean hasBow;
    public final boolean hasFeather;
    public final boolean deathmatch;
    public final boolean spawnInvuln;
    public final boolean knockoff;

    public KothConfig(
            PlayerConfig players,
            MapConfig map,
            int timeLimitSecs,
            int firstTo,
            boolean winnerTakesAll,
            boolean hasStick,
            boolean hasBow,
            boolean hasFeather,
            boolean deathmatch,
            boolean spawnInvuln,
            boolean knockoff
    ) {
        this.playerConfig = players;
        this.map = map;
        this.timeLimitSecs = timeLimitSecs;
        this.firstTo = firstTo;
        this.winnerTakesAll = winnerTakesAll;
        this.hasStick = hasStick;
        this.hasBow = hasBow;
        this.hasFeather = hasFeather;
        this.deathmatch = deathmatch;
        this.spawnInvuln = spawnInvuln;
        this.knockoff = knockoff;

    }

    public static class MapConfig {
        public static final Codec<MapConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Identifier.CODEC.fieldOf("id").forGetter(config -> config.id),
                Codec.INT.fieldOf("spawn_angle").forGetter(config -> config.spawnAngle),
                Codec.LONG.optionalFieldOf("time", 6000L).forGetter(config -> config.time)
        ).apply(instance, MapConfig::new));

        public final Identifier id;
        public final int spawnAngle;
        public final long time;

        public MapConfig(Identifier id, int spawnAngle, long time) {
            this.id = id;
            this.spawnAngle = spawnAngle;
            this.time = time;
        }
    }
}
