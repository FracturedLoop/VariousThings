package com.fl5.variousthings.events;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovementInput;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

import com.fl5.variousthings.init.VariousThingsBlocks;
import com.fl5.variousthings.init.VariousThingsItems;

public class VariousThingsEventHandler {
	
	public static boolean hasLevitated;
	public static boolean hoverBootsOn = true;
	public boolean hasJumped = false;
	public boolean hasSnuck = false;
	private boolean wasJumping = false;
	private boolean tempJump;
	
	//this is for the jump booster block
	@SubscribeEvent
    public void bounce(LivingJumpEvent event) {
    	if (!(event.entity instanceof EntityPlayer)) {
    		return;
    	}
    	BlockPos underPlayer = new BlockPos((int) Math.floor(event.entity.posX), (int) Math.floor(event.entity.posY) - 1, (int) Math.floor(event.entity.posZ));
    	BlockPos twoUnderPlayer = new BlockPos((int) Math.floor(event.entity.posX), (int) Math.floor(event.entity.posY) - 2, (int) Math.floor(event.entity.posZ));
    	BlockPos threeUnderPlayer = new BlockPos((int) Math.floor(event.entity.posX), (int) Math.floor(event.entity.posY) - 3, (int) Math.floor(event.entity.posZ));
    	BlockPos currentSearch;
    	
    	int blocksUnder = 1;
    	
    	float jumpMultiplier = 1;
    	if (event.entity.worldObj.getBlockState(underPlayer).getBlock() == VariousThingsBlocks.jump_booster) {
    		currentSearch = underPlayer;
    		blocksUnder = 1;
    		
    	}

    	else if (event.entity.worldObj.getBlockState(twoUnderPlayer).getBlock() == VariousThingsBlocks.jump_booster) {
    		currentSearch = twoUnderPlayer;
    		blocksUnder = 2;

    	
    	}
    	else if (((EntityPlayer) event.entity).getCurrentArmor(0) != null && ((EntityPlayer) event.entity).getCurrentArmor(0).getItem() == VariousThingsItems.hover_boots && hoverBootsOn == true) {
    	 			
    	 		currentSearch = threeUnderPlayer;
        		blocksUnder = 3;
        		
    	 }
    	
    	
    	else {
    		return;
    	}
    	
    	while(event.entity.worldObj.getBlockState(currentSearch).getBlock() == VariousThingsBlocks.jump_booster) {
			jumpMultiplier += 0.5;
			blocksUnder += 1;
			currentSearch = new BlockPos((int) Math.floor(event.entity.posX), (int) Math.floor(event.entity.posY) - blocksUnder, (int) Math.floor(event.entity.posZ));
    	}
    	event.entity.motionY *= jumpMultiplier;

    }
	
	//This is for the levitator block
	@SubscribeEvent
	public void levitate(PlayerTickEvent event) {
		
		
		
		BlockPos underPlayer = new BlockPos((int) Math.floor(event.player.posX), (int) Math.floor(event.player.posY) - 1, (int) Math.floor(event.player.posZ));
		BlockPos twoUnderPlayer = new BlockPos((int) Math.floor(event.player.posX), (int) Math.floor(event.player.posY) - 2, (int) Math.floor(event.player.posZ));
		BlockPos threeUnderPlayer = new BlockPos((int) Math.floor(event.player.posX), (int) Math.floor(event.player.posY) - 3, (int) Math.floor(event.player.posZ));
		BlockPos fourUnderPlayer = new BlockPos((int) Math.floor(event.player.posX), (int) Math.floor(event.player.posY) - 4, (int) Math.floor(event.player.posZ));
		EntityPlayer player = event.player;
		BlockPos levitator = null;
		
		if ((event.player.worldObj.getBlockState(twoUnderPlayer).getBlock() == VariousThingsBlocks.levitator || event.player.worldObj.getBlockState(underPlayer).getBlock() == VariousThingsBlocks.levitator || event.player.worldObj.getBlockState(threeUnderPlayer).getBlock() == VariousThingsBlocks.levitator || event.player.worldObj.getBlockState(fourUnderPlayer).getBlock() == VariousThingsBlocks.levitator)) {
			
			if (event.player.worldObj.getBlockState(underPlayer).getBlock() == VariousThingsBlocks.levitator) {
				levitator = underPlayer;
			}
			else if (event.player.worldObj.getBlockState(twoUnderPlayer).getBlock() == VariousThingsBlocks.levitator){
				levitator = twoUnderPlayer;
			}
			else if (event.player.worldObj.getBlockState(threeUnderPlayer).getBlock() == VariousThingsBlocks.levitator){
				levitator = threeUnderPlayer;
			}
			else {
				levitator = fourUnderPlayer;
			}
			
			if (player.isSneaking()) {
				player.motionY = -0.2;				
			}
			else if (player.posY - 2 - levitator.getY() >= 2.8 && player.posY - 2 - levitator.getY() <= 3.0) {
				player.motionY = 0;
			}
			else if (player.posY - 2 - levitator.getY() < 2.8) {
				player.motionY = 0.2;
			}
			hasLevitated = true;
			return;
		}
	}
	//this is for the levitator block too
	@SubscribeEvent
	public void negateFallDamage(LivingFallEvent event) {
		if (hasLevitated == true) {
			event.setCanceled(true);
			hasLevitated = false;
		}
		return;
	}
	
	
	//this is for the hoverboots
	@SubscribeEvent
	public void hover(PlayerTickEvent event) {
		BlockPos underPlayer = new BlockPos((int) Math.floor(event.player.posX), (int) event.player.posY - 1, (int) Math.floor(event.player.posZ));
	 	BlockPos twoUnderPlayer = new BlockPos((int) Math.floor(event.player.posX), (int) event.player.posY - 2, (int) Math.floor(event.player.posZ));
	 	EntityPlayer player = event.player; 
	 	if (player.getCurrentArmor(0) != null) {
	 		if (player.getCurrentArmor(0).getItem() == VariousThingsItems.hover_boots) {
	 				if (hoverBootsOn == true) {
	 					
	 					MovementInput input = Minecraft.getMinecraft().thePlayer.movementInput;
	 					
	 					player.capabilities.setFlySpeed(0.1F);
	 					player.capabilities.setPlayerWalkSpeed(0.15F);
	 					
	 					tempJump = input.jump;
	 					if (input.sneak) {
	 						player.motionY = -0.2;
	 					}
	 					
	 					else if (input.jump && !wasJumping && !hasJumped) {
	 						player.jump();
	 						hasJumped = true;
	 					}
	 					
	 					//float up and decrease speed to a graceful stop
	 					else if ((player.worldObj.getBlockState(underPlayer).getBlock().isFullBlock())) {
	 						player.motionY = (1 - (player.posY - Math.floor(player.posY))) / 5;
	 						if (player.motionY < 0.03) player.motionY = 0.03;
	 						hasJumped = false;
	 					} 
	 					
	 					//stop once desired height has been reached
	 					else if ((player.posY - underPlayer.getY() >= 1.0 && player.posY - underPlayer.getY() <= 1.1) && player.worldObj.getBlockState(twoUnderPlayer).getBlock() != Blocks.air && player.motionY <= 0.1) {
	 						player.motionY = 0;
	 						hasJumped = false;
	 					} 
			
	 				}
	 				
	 				else {
	 					player.capabilities.setFlySpeed(0.05F);
	 					player.capabilities.setPlayerWalkSpeed(0.125F);
	 				}
	 		}
	 		
	 		else {
	 			player.capabilities.setFlySpeed(0.05F);
					player.capabilities.setPlayerWalkSpeed(0.1F);
	 		}
	 	} 
	 	
	 	wasJumping = tempJump;
	 		
	} 
	
	 
	
	
	
}
