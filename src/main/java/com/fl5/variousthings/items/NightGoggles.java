package com.fl5.variousthings.items;

import com.fl5.variousthings.tabs.VaiousThingsTabs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class NightGoggles extends ItemArmor{

 public NightGoggles(ArmorMaterial material, int renderIndex, int armorType) {
	    super(material, renderIndex, armorType);
	    setCreativeTab(VaiousThingsTabs.tabVariousThings);
	   
	}
	
 public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
	 
 }
}
