package com.chebab.spaceworlds;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.plugin.java.JavaPlugin;

public class SpaceWorlds extends JavaPlugin
{

    public void onEnable() {
        System.out.println( "[Space Worlds] enabled." );
    }

    public void onDisable() {
        System.out.println( "[Space Worlds] disabled." );
    }

    
    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        /* Define our new generator... */
        return new SpaceGen();
    }
}
