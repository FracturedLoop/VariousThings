package com.fl5.variousthings.gui;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.fl5.variousthings.gui.inventory.ContainerItemDisplay;
import com.fl5.variousthings.tileentity.TileEntityItemDisplay;
	
	@SideOnly(Side.CLIENT)
	public class GuiItemDisplay extends GuiContainer {
		
		// This is the resource location for the background image for the GUI
		private static final ResourceLocation texture = new ResourceLocation("variousthings", "textures/gui/item_display_bg.png");
		private TileEntityItemDisplay TileEntityItemDisplay;

		public GuiItemDisplay(InventoryPlayer invPlayer, TileEntityItemDisplay tile) {
			super(new ContainerItemDisplay(invPlayer, tile));
			TileEntityItemDisplay = tile;
			// Set the width and height of the gui.  Should match the size of the texture!
			xSize = 176;
			ySize = 133;
		}

		// draw the background for the GUI - rendered first
		@Override
		protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {
			// Bind the image texture of our custom container
			Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
			// Draw the image
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		}

		// draw the foreground for the GUI - rendered after the slots, but before the dragged items and tooltips
		// renders relative to the top left corner of the background
		@Override
		protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {			
			String name = this.TileEntityItemDisplay.getDisplayName().getUnformattedText();
	        this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
	        

		}
}
