/*
 * This file is part of the Spout plugin TLDSpells. It also has a hard 
 * dependency on the Vanilla project.
 */
package com.github.thelonedevil.TLDSpells;

import org.spout.api.plugin.Plugin;
import org.spout.api.command.annotated.AnnotatedCommandExecutorFactory;

import com.github.thelonedevil.TLDSpells.Spells.FireBolt;
import com.github.thelonedevil.TLDSpells.Spells.HealingBolt;
import com.github.thelonedevil.TLDSpells.Spells.IceBolt;
import com.github.thelonedevil.TLDSpells.command.TLDSpellsBaseCommand;
import com.github.thelonedevil.TLDSpells.command.TLDSpellsCommands;

/**
 * Defines the main class of the plugin.
 */
public class TLDSpellsPlugin extends Plugin {
	private static TLDSpellsPlugin instance;

	@Override
	public void onLoad() {
		setInstance(this);
		getLogger().info("loaded.");
	}

	@Override
	public void onEnable() {
		// Register Base Command (/command)
		AnnotatedCommandExecutorFactory.create(new TLDSpellsBaseCommand(this));
		// Register Commands under Base Command (/command command)
		AnnotatedCommandExecutorFactory.create(new TLDSpellsCommands(this), getEngine().getCommandManager().getCommand("TLDSpells"));

		// Register Events
		getEngine().getEventManager().registerEvents(new TLDSpellsListener(this), this);
		getEngine().getEventManager().registerEvents(new IceBolt(this), this);
		getEngine().getEventManager().registerEvents(new FireBolt(this), this);
		getEngine().getEventManager().registerEvents(new HealingBolt(this), this);

		getLogger().info("enabled.");
	}

	@Override
	public void onDisable() {
		getLogger().info("disabled.");
	}

	private static void setInstance(TLDSpellsPlugin plugin) {
		instance = plugin;
	}

	public static TLDSpellsPlugin getInstance() {
		return instance;
	}

}