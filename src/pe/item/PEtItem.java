package pe.item;

import java.util.List;

import pe.ITickListener;
import pe.StackUtils;
import pe.WatcherManager;

import com.google.common.base.Splitter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;

public class PEtItem extends ItemArmor {

	public PEtItem(int id, EnumArmorMaterial armorMaterial, int renderIndex) {
		super(id, armorMaterial, renderIndex, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		NBTTagCompound compound = StackUtils.getNBT(par1ItemStack);
		if (!compound.hasKey("pe_watch")) {
			compound.setString("pe_watch", "test");
		}
		if (compound.hasKey("pe_watch")) {
			Iterable<String> iterable = Splitter.on(';').split(
					compound.getString("pe_watch"));
			for (String watch : iterable) {
				par3List.add(watch);
			}
		}
	}

	
}
