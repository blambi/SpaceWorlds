package com.chebab.spaceworlds;

import java.util.Random;
import java.lang.Math;
import org.bukkit.Material;
import org.bukkit.DyeColor;
import org.bukkit.World;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

public class WalkwaysPopulator extends BlockPopulator {
    private long seed;

    WalkwaysPopulator(long seed) {
        this.seed = seed;
    }

    public void populate( World world, Random random, Chunk chunk ) {
        // Do only the horizontal one on even ones
        if( chunk.getZ() % 2 == 0 ) {
            walkwayHorizontal( world, random, chunk );
            walkwayVertical( world, random, chunk );
        }
        else {
            walkwayVertical( world, random, chunk );
        }
    }

    private void walkwayHorizontal( World world, Random random, Chunk chunk ) {
        byte colour = DyeColor.GRAY.getData();
        Block block = chunk.getBlock( 0, 64, 0 );
        int x, z;

        // Long pice first
        for( x = 0; x < 16; x++ ) {
            for( z = 2; z < 5; z++ ) {
                block = chunk.getBlock( x, 64, z );

                if( ! block.isEmpty() ) {
                    // Already handled (ie: right border)
                    continue;
                }

                // Default
                block.setType(Material.WOOL);

                if( z == 3 || x == 5 || x == 13 )
                    block.setData( DyeColor.BLACK.getData() );
                else
                    block.setData( colour );
                    
            }

            // Lamps
            if((x == 1 || x == 9) && (z == 2 || z == 4)) {
                block.setType( Material.REDSTONE_TORCH_ON );
                block = chunk.getBlock( x, 65, z );
                block.setType( Material.REDSTONE_LAMP_ON );
            }
        }

        // Connectors
        for( x = 4 ; x < 7; x++ ) {
            if( x != 5 )
                colour = DyeColor.GRAY.getData();
            else
                colour = DyeColor.BLACK.getData();
            
            block = chunk.getBlock( x, 64, 0 );
            block.setType( Material.WOOL );
            block.setData( colour );

            block = chunk.getBlock( x, 64, 1 );
            block.setType( Material.WOOL );
            block.setData( colour );

            block = chunk.getBlock( x, 64, 5 );
            block.setType( Material.WOOL );
            block.setData( colour );

            block = chunk.getBlock( x, 64, 6 );
            block.setType( Material.WOOL );
            block.setData( colour );
        }
    }

    private void walkwayVertical( World world, Random random, Chunk chunk ) {
    }
}