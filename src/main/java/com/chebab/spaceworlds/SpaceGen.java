package com.chebab.spaceworlds;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.BlockPopulator;

public class SpaceGen extends ChunkGenerator {
    private List<BlockPopulator> populators;
    private long seed;
    /**
     * A quite simple constructor don't you say?
     */
    SpaceGen() {
    }

    /**
     * Fill it all with space! (except spawn..)
     */
    @Override
    public byte[][] generateBlockSections( World world, Random random, int chunkx, int chunkz, BiomeGrid biomes ) {
        if( this.seed == 0 ) {
            this.seed = world.getSeed();
        }

        int roof_height = world.getMaxHeight();
        int section_count = roof_height / 16;

        byte[][] blocks = new byte[section_count][];

        for( int section = 0; section < section_count; section++ ) {
            for( int x = 0; x < 16; x++ ) {
                for( int z = 0; z < 16; z++ ) {
                    biomes.setBiome( x, z, Biome.DESERT ); // We don't want rain sounds in this world..
                    if( chunkx == 0 && chunkz == 0 && x == 5 && z == 3 ) {
                        setBlock( blocks, x, 64, z, (byte)Material.BEDROCK.getId() );
                        // directions
                        setBlock( blocks, x-1, 64, z, (byte)Material.GLOWSTONE.getId() );
                        setBlock( blocks, x+1, 64, z, (byte)Material.GLOWSTONE.getId() );
                        setBlock( blocks, x, 64, z-1, (byte)Material.GLOWSTONE.getId() );
                        setBlock( blocks, x, 64, z+1, (byte)Material.GLOWSTONE.getId() );
                        setBlock( blocks, x, 63, z, (byte)Material.GLOWSTONE.getId() );
                    }
                }
            }
        }

        return blocks;
    }

    private void setBlock(byte[][] result, int x, int y, int z, byte blkid) {
        if (result[y >> 4] == null) {
            result[y >> 4] = new byte[4096];
        }
        result[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = blkid;
    }

    @Override
    public org.bukkit.Location getFixedSpawnLocation( World world, Random random ) {
        if ( !world.isChunkLoaded(0, 0) )
        {
            world.loadChunk(0, 0);
        }

        return new org.bukkit.Location( world, 5, 64, 3 );
    }
}
