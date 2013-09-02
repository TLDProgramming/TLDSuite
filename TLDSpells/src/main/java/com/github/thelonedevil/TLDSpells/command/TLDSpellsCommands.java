/*
 * This file is part of the Spout plugin TLDSpells. It also has a hard
 * dependency on the Vanilla project.
 */
package com.github.thelonedevil.TLDSpells.command;

import org.spout.api.Server;
import org.spout.api.command.CommandArguments;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.CommandDescription;
import org.spout.api.command.annotated.Filter;
import org.spout.api.command.annotated.Permissible;
import org.spout.api.command.filter.PlayerFilter;
import org.spout.api.entity.Player;
import org.spout.api.exception.CommandException;
import org.spout.api.inventory.ItemStack;
import org.spout.api.inventory.Slot;
import org.spout.vanilla.component.entity.inventory.PlayerInventory;
import org.spout.vanilla.material.VanillaMaterials;
import org.spout.vanilla.util.PlayerUtil;

import com.github.thelonedevil.TLDSpells.TLDSpellsPlugin;
import com.github.thelonedevil.TLDSpells.Spells.IceBolt;
import com.github.thelonedevil.TLDSpells.staffs.StaffMaterials;

/**
 * Provides an example of a class to hold commands.
 */
public class TLDSpellsCommands {
	private final TLDSpellsPlugin plugin;

	public TLDSpellsCommands(TLDSpellsPlugin instance) {
		this.plugin = instance;
	}

	/**
	 * Provides an example command that can be issued to the Spout server.
	 */
	@CommandDescription(aliases = { "command", "cmd" }, desc = "This is an example of what a command might look like. Try it out with /cmd !")
	@Permissible("TLDSpells.some.permission")
	@Filter(PlayerFilter.class)
	public void exampleCommand(CommandSource source, CommandArguments args) throws CommandException {
		IceBolt icebolt = new IceBolt(plugin);
		icebolt.cast((Player) source);
		ItemStack stick = new ItemStack(VanillaMaterials.STICK, 1);
		String disname = stick.getMaterial().getDisplayName();
		source.sendMessage(disname);
		// Calling this command will send whoever issued it the message below.
		source.sendMessage("The TLDSpells plugin command has been successfully issued.");
	}

	@CommandDescription(aliases = { "bind", "bind" }, desc = "")
	@Permissible("TLDSpells.bind")
	@Filter(PlayerFilter.class)
	public void bind(CommandSource source, CommandArguments args) throws CommandException {
		String name = source.getName();
		String spell = args.popRemainingStrings("Spell");
		Player p = ((Server) plugin.getEngine()).getPlayer(name, true);
		Slot slot = PlayerUtil.getHeldSlot(p);
		if (!PlayerUtil.isCostSuppressed(p) && slot != null && slot.get() != null && VanillaMaterials.STICK.equals(slot.get().getMaterial())) {
			slot.addAmount(-1);
			if (spell.equalsIgnoreCase("IceBolt")) {
				ItemStack newitemstack = new ItemStack(StaffMaterials.ICEBOLTSTAFF, 1);
				p.get(PlayerInventory.class).add(newitemstack);
				String item = newitemstack.getMaterial().getName();
				source.sendMessage("You have bound the spell " + spell + " to the item " + item);
			} else if (spell.equalsIgnoreCase("FireBolt")) {
				ItemStack newitemstack = new ItemStack(StaffMaterials.FIREBOLTSTAFF, 1);
				p.get(PlayerInventory.class).add(newitemstack);
				String item = newitemstack.getMaterial().getName();
				source.sendMessage("You have bound the spell " + spell + " to the item " + item);
			} else if (spell.equalsIgnoreCase("Healing")) {
				ItemStack newitemstack = new ItemStack(StaffMaterials.HEALINGSTAFF, 1);
				p.get(PlayerInventory.class).add(newitemstack);
				String item = newitemstack.getMaterial().getName();
				source.sendMessage("You have bound the spell " + spell + " to the item " + item);
			}
		}
	}
}
