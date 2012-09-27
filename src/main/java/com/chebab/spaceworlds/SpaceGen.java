package com.chebab.spaceworlds;

import java.util.Random;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;

public class SpaceGen extends ChunkGenerator {

    /**
     * A quite simple constructor don't you say?
     */
    SpaceGen() {
    }

    /**
     * Generator :D
     */
    public byte[] generate( World world, Random rand, int chunkx, int chunkz ) {
        byte[] blocks = new byte[65536];
        int x, z;

        // Since air is 0 it's allready filled with air,
        if( chunkx == 0 && chunkz == 0 ) {
            // Special since we need to take care of our spawn platform.
            System.out.println("[SpaceGen] start chunk");
            blocks[coordsToInt(1, 63, 1)] = (byte)Material.GLOWSTONE.getId();
            for( x = 0; x < 3; x++ ) {
                for( z = 0; z < 3; z++) {
                    if( x == 1 && z == 1 )
                        blocks[coordsToInt(x, 62, z)] = (byte)Material.BEDROCK.getId();
                    else
                        blocks[coordsToInt(x, 62, z)] = (byte)Material.GLOWSTONE.getId();
                }
            }
            blocks[coordsToInt(1, 61, 1)] = (byte)Material.GLOWSTONE.getId();
        }

        return blocks;
    }

    private int coordsToInt( int x, int y, int z ) {
        return ( x * 16 + z ) * 256 + y;
    }

    
    @Override
    public org.bukkit.Location getFixedSpawnLocation( World world, Random random ) {
        if ( !world.isChunkLoaded(0, 0) )
        {
            world.loadChunk(0, 0);
        }
        
        return new org.bukkit.Location( world, 1, 64, 1 );
    }
}