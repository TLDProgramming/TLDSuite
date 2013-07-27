/*
 * This file is part of the Spout plugin TLDRaffle. It also has a hard
 * dependency on the Vanilla project.
 */
package com.github.thelonedevil.TLDRaffle;

import java.util.Random;

import org.spout.api.Server;
import org.spout.api.material.Material;
import org.spout.api.plugin.Plugin;
import org.spout.api.command.annotated.AnnotatedCommandExecutorFactory;
import org.spout.api.entity.Player;
import org.spout.api.inventory.ItemStack;
import org.spout.vanilla.component.entity.inventory.PlayerInventory;

import com.github.thelonedevil.TLDCommonlib.Lib;
import com.github.thelonedevil.TLDRaffle.command.TLDRaffleBaseCommand;
import com.github.thelonedevil.TLDRaffle.command.TLDRaffleCommands;

/**
 * Defines the main class of the plugin.
 */
public class TLDRafflePlugin extends Plugin {
	private static TLDRafflePlugin instance;

	@Override
	public void onLoad() {
		setInstance(this);

		getLogger().info("loaded.");
	}

	@Override
	public void onEnable() {
		// Register Base Command (/command)
		AnnotatedCommandExecutorFactory.create(new TLDRaffleBaseCommand(this));
		// Register Commands under Base Command (/command command)
		AnnotatedCommandExecutorFactory.create(new TLDRaffleCommands(this), getEngine().getCommandManager().getCommand("command"));

		// Register Events
		getEngine().getEventManager().registerEvents(new TLDRaffleListener(this), this);

		getLogger().info("enabled.");
	}

	@Override
	public void onDisable() {
		getLogger().info("disabled.");
	}

	private static void setInstance(TLDRafflePlugin plugin) {
		instance = plugin;
	}

	public static TLDRafflePlugin getInstance() {
		return instance;
	}

	private Lib lib = new Lib();

	public void raffle() {
		long last = 0;
		long now;
		long dif = 0;
		String[] ids = lib.raffleprize.split(":"); 
		short id = Short.parseShort(ids[0]);
		short data= Short.parseShort(ids[1]);
		Material material = Material.get(id, data);
		ItemStack prize = new ItemStack(material, 1);
		do {
			now = System.currentTimeMillis();
			dif = now - last;
			if (dif >= lib.interval) {
				Player[] players = ((Server) getEngine()).getOnlinePlayers();
				int random = new Random().nextInt(players.length);
				Player p = players[random];
				p.get(PlayerInventory.class).add(prize);
				last = now;
				dif = 0;
			}
		} while (dif < lib.interval);
	}
}