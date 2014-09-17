package digitalseraphim.hc.worldgen.village;

import java.util.List;
import java.util.Random;

import digitalseraphim.hc.block.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

public class ComponentStable extends StructureVillagePieces.Village{

	private int averageGroundLevel = -1;

	public ComponentStable() {
	}

	public ComponentStable(
			StructureVillagePieces.Start par1ComponentVillageStartPiece,
			int par2, Random par3Random,
			StructureBoundingBox par4StructureBoundingBox, int par5) {
		super(par1ComponentVillageStartPiece, par2);
		this.coordBaseMode = par5;
		this.boundingBox = par4StructureBoundingBox;
	}

	@Override
	public void buildComponent(StructureComponent p_74861_1_, List p_74861_2_,
			Random p_74861_3_) {
		// TODO Auto-generated method stub
		super.buildComponent(p_74861_1_, p_74861_2_, p_74861_3_);
	}
	
	@SuppressWarnings("rawtypes")
	public static ComponentStable buildComponent(
			StructureVillagePieces.Start villagePiece, List pieces,
			Random random, int p1, int p2, int p3, int p4, int p5) {
		StructureBoundingBox structureboundingbox = StructureBoundingBox
				.getComponentToAddBoundingBox(p1, p2, p3, 0, 0, 0, 17, 6, 11,
						p4);
		return canVillageGoDeeper(structureboundingbox)
				&& StructureComponent.findIntersecting(pieces,
						structureboundingbox) == null ? new ComponentStable(
				villagePiece, p5, random, structureboundingbox, p4) : null;
	}

	byte[][][] stable = {
			{ { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0 },
					{ 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0 },
					{ 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0 },
					{ 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0 },
					{ 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0 },
					{ 0, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0 },
					{ 0, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0 },
					{ 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0 },
					{ 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0 } },
			{ { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 4, 4, 4, 4, 4, 4, 7, 7, 7, 7, 7, 7, 7, 7, 7, 0 },
					{ 0, 4, 6, 6, 6, 6, 4, 9, 9, 7, 9, 9, 7, 9, 9, 7, 0 },
					{ 0, 4, 6, 0, 0, 6, 4, 9, 9, 7, 9, 9, 7, 9, 9, 7, 0 },
					{ 0, 4,10, 0, 0,11, 4, 9, 9, 7, 9, 9, 7, 9, 9, 7, 0 },
					{ 0, 4, 7, 0, 0, 0, 4, 8, 8, 7, 8, 8, 7, 8, 8, 7, 0 },
					{ 0, 4,10, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 4, 6, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 4, 6, 6, 6, 6, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
			{ { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 4, 4,13,13, 4, 4, 0, 0, 7, 0, 0, 7, 0, 0, 7, 0 },
					{ 0, 4, 6, 0, 0, 6, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0,13, 0, 0, 0, 0,13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0,13, 0, 0, 0, 0,13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 4,12, 0, 0, 0, 4, 0, 0, 7, 0, 0, 7, 0, 0, 7, 0 },
					{ 0,13, 0, 0, 0, 0,14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0,13, 0, 0, 0, 0,14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 4, 6, 0, 0, 6, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 4, 4,13,13, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
			{ { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 4, 4, 4, 4, 4, 4, 0, 0, 7, 0, 0, 7, 0, 0, 7, 0 },
					{ 0, 4, 6, 0, 0, 6, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 4, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 4, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 4, 0, 0, 0, 0, 4, 0, 0, 7, 0, 0, 7, 0, 0, 7, 0 },
					{ 0, 4, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 4, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 4, 6, 0, 0, 6, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
			{ { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
					{ 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } } };

	/**
	 * second Part of Structure generating, this for example places Spiderwebs,
	 * Mob Spawners, it closes Mineshafts at the end, it adds Fences...
	 */
	public boolean addComponentParts(World world, Random random,
			StructureBoundingBox sbb) {
		if (this.averageGroundLevel < 0) {
			this.averageGroundLevel = this.getAverageGroundLevel(world, sbb);

			if (this.averageGroundLevel < 0) {
				return true;
			}

			this.boundingBox.offset(0, this.averageGroundLevel
					- this.boundingBox.maxY + 4, 0);
		}

		Block cobblestone = Blocks.cobblestone;
		Block stone = Blocks.stone;
		Block gravel = Blocks.gravel;
		Block planks = Blocks.planks;
		Block doorWood = Blocks.wooden_door;
		Block hay = Blocks.hay_block;
		Block fence = Blocks.fence;
		Block fenceGate = Blocks.fence_gate;
		Block stairsWoodOak = Blocks.oak_stairs;
		Block pressurePlatePlanks = Blocks.wooden_pressure_plate;
		Block thinGlass = Blocks.glass_pane;
		
		
		int k; //used for metadata values below
		for(int yy=0;yy<5;yy++){
			for(int xx=0;xx<17;xx++){
				for(int zz=0;zz<11;zz++){
					
					switch(stable[yy][10-zz][xx]){
					case 0:
						//air;
						break;
					case 1:
						//cobble;
						this.placeBlockAtCurrentPosition(world, cobblestone, 0, xx, yy, zz, sbb);
						break;
					case 2:
						//stone
						this.placeBlockAtCurrentPosition(world, stone, 0, xx, yy, zz, sbb);
						break;
					case 3:
						//gravel
						this.placeBlockAtCurrentPosition(world, gravel, 0, xx, yy, zz, sbb);
						break;
					case 4:
						//planks
						this.placeBlockAtCurrentPosition(world, planks, 0, xx, yy, zz, sbb);
						break;
					case 5:
						//door-bottom
						k = this.getMetadataWithOffset(doorWood, 0);
						this.placeDoorAtCurrentPosition(world, sbb, random, xx, yy, zz, k);
						break;
					case 6:
						//hay bale
						if(yy == 1 || this.getBlockAtCurrentPosition(world, xx, yy-1, zz, sbb) == hay){
							if(random.nextInt(3) > 0){
								this.placeBlockAtCurrentPosition(world, hay, random.nextInt(6), xx, yy, zz, sbb);
							}
						}
						break;
					case 7:
						//fence
						this.placeBlockAtCurrentPosition(world, fence, 0, xx, yy, zz, sbb);
						break;
					case 8:
						//gate
						k = this.getMetadataWithOffset(fenceGate, 0);
						this.placeBlockAtCurrentPosition(world, fenceGate, k, xx, yy, zz, sbb);
						break;
					case 9:
						//hay pile
						if(random.nextInt(4) > 0){
							this.placeBlockAtCurrentPosition(world, ModBlocks.hayPile, random.nextInt(4), xx, yy, zz, sbb);
						}
						break;
					case 10:
						//stairs
						k = this.getMetadataWithOffset(stairsWoodOak, zz > 5?3:2);
						this.placeBlockAtCurrentPosition(world, stairsWoodOak, k, xx, yy, zz, sbb);
						break;
					case 11:
						//chest
						break;
					case 12:
						//pressure plate
						this.placeBlockAtCurrentPosition(world, pressurePlatePlanks, 0, xx, yy, zz, sbb);
						break;
					case 13:
						//glass pane
						this.placeBlockAtCurrentPosition(world, thinGlass, 0, xx, yy, zz, sbb);
						break;
					case 14:
						//door top
						//ignore, this is handled under (5/door-bottom)
						break;
					}
				}
			}
		}
		return true;
	}
}
