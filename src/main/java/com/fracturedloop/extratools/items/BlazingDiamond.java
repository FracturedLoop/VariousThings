package com.fracturedloop.extratools.items;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.fracturedloop.extratools.tabs.ModTabs;

public class BlazingDiamond extends ItemFlintAndSteel
{
	
	public BlazingDiamond(String name)
	{
		super();
		setCreativeTab(ModTabs.tabExtraTools);
		this.setMaxDamage(-1);
		
	}
	
	public void onUpdate(ItemStack itemstack, World par2World, Entity par3Entity, int par4, boolean par5) {
		if(itemstack.isItemEnchanted() == false) {
			itemstack.addEnchantment(Enchantment.fireAspect, 3);
		}
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack BlazingDiamond, EntityPlayer player, EntityLivingBase target)
	{
			 target.setFire(8);;
			 return true;
	}
	
	
}
