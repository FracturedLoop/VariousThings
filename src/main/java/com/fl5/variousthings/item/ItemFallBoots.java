package com.fl5.variousthings.item;

import com.fl5.variousthings.tabs.VaiousThingsTabs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFallBoots extends ItemArmor{

 public ItemFallBoots(ArmorMaterial material, int renderIndex, int armorType) {
	    super(material, renderIndex, armorType);
	    setCreativeTab(VaiousThingsTabs.tabVariousThings);
	   
	}
	
 public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
	 player.fallDistance = 0.0F;
 }
}
