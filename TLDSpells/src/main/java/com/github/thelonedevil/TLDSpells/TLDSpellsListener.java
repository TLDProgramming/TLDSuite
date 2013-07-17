/*
 * This file is part of the Spout plugin TLDSpells. It also has a hard 
 * dependency on the Vanilla project.
 */
package com.github.thelonedevil.TLDSpells;

import org.spout.api.entity.Player;
import org.spout.api.event.EventHandler;
import org.spout.api.event.player.PlayerChatEvent;
import org.spout.api.event.player.input.PlayerClickEvent;
import org.spout.api.event.Listener;
import org.spout.api.input.Mouse;
import org.spout.api.inventory.Slot;
import org.spout.vanilla.util.PlayerUtil;

import com.github.thelonedevil.TLDSpells.Spells.FireBolt;
import com.github.thelonedevil.TLDSpells.Spells.HealingBolt;
import com.github.thelonedevil.TLDSpells.Spells.IceBolt;
import com.github.thelonedevil.TLDSpells.event.PlayerCastSpellEvent;
import com.github.thelonedevil.TLDSpells.staffs.StaffMaterials;

/**
 * Provides an example of an event listener class.
 */
public class TLDSpellsListener implements Listener {
	private TLDSpellsPlugin plugin;

	public TLDSpellsListener(TLDSpellsPlugin instance) {
		this.plugin = instance;
	}

	@EventHandler
	public void onPlayerChat(PlayerChatEvent event) {
		// Do Something on PlayerChatEvent
	}

	@EventHandler
	public void onPlayerRightClick(PlayerClickEvent event) {
		int click = event.getButton();
		if (click == Mouse.BUTTON_RIGHT) {
			Player p = event.getPlayer();
			Slot slot = PlayerUtil.getHeldSlot(p);
			if (slot.get().getMaterial() == StaffMaterials.ICEBOLTSTAFF) {
				IceBolt icebolt = new IceBolt(plugin);
				icebolt.cast(p);
			} else if (slot.get().getMaterial() == StaffMaterials.FIREBOLTSTAFF) {
				FireBolt firebolt = new FireBolt(plugin);
				firebolt.cast(p);
			} else if (slot.get().getMaterial() == StaffMaterials.HEALINGSTAFF) {
				HealingBolt healingbolt = new HealingBolt(plugin);
				healingbolt.cast(p);
			}
		}
	}
}
