package com.chebab.spaceworlds;

import java.util.Random;
import java.lang.Math;
import org.bukkit.Material;
import org.bukkit.DyeColor;
import org.bukkit.World;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

public class PlatformPopulator extends BlockPopulator {
    private long seed;

    PlatformPopulator(long seed) {
        this.seed = seed;
    }

    public void populate( World world, Random random, Chunk chunk ) {
        // Do platforms
        if( chunk.getZ() % 2 == 0 ) {
            // Type A
            basicPlatform( world, random, chunk );

        }
        else {
            // Type B
            basicPlatform( world, random, chunk );
        }
    }

    private void basicPlatform( World world, Random random, Chunk chunk ) {
        byte colour = DyeColor.RED.getData(); // Make this random (based on seed)
        Block block = chunk.getBlock( 0, 64, 0 );
        int x, z;

        for( x = 1; x < 10; x++ ) {
            for( z = 7; z < 16; z++ ) {
                block = chunk.getBlock( x, 64, z );

                if( ! block.isEmpty() ) {
                    // Already handled (ie: right border)
                    continue;
                }

                // Default
                block.setType(Material.WOOL);
                block.setData( DyeColor.BLACK.getData() );

                // side borders
                if( (x == 1 || x == 9) && x == 1 ) {
                    block.setData( colour );
                    // right border
                    block = chunk.getBlock( 9, 64, z );
                    block.setType(Material.WOOL);
                    block.setData( colour );
                }

            }

            // top/bottom Borders
            if( z == 8 || z == 15 ) {
                block.setData( colour );
            }
        }
    }
}