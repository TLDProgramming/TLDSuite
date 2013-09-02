/*
 * This file is part of the Spout plugin TLDScape. It also has a hard
 * dependency on the Vanilla project.
 */
package com.github.thelonedevil.TLDScape;

import org.spout.api.plugin.Plugin;
import org.spout.api.command.annotated.AnnotatedCommandExecutorFactory;

import com.github.thelonedevil.TLDScape.command.TLDScapeBaseCommand;
import com.github.thelonedevil.TLDScape.command.TLDScapeCommands;
import com.github.thelonedevil.TLDScape.skills.AttackListener;
import com.github.thelonedevil.TLDScape.skills.HitPointsListener;
import com.github.thelonedevil.TLDScape.skills.MiningListener;
import com.github.thelonedevil.TLDScape.skills.WoodCuttingListener;

/**
 * Defines the main class of the plugin.
 */
public class TLDScapePlugin extends Plugin {
	private static TLDScapePlugin instance;

	@Override
	public void onLoad() {
		setInstance(this);
		getLogger().info("loaded.");
	}

	@Override
	public void onEnable() {
		// Register Base Command (/command)
		AnnotatedCommandExecutorFactory.create(new TLDScapeBaseCommand(this));
		// Register Commands under Base Command (/command command)
		AnnotatedCommandExecutorFactory.create(new TLDScapeCommands(this), getEngine().getCommandManager().getCommand("command"));

		// Register Events
		getEngine().getEventManager().registerEvents(new TLDScapeListener(this), this);
		getEngine().getEventManager().registerEvents(new HitPointsListener(this), this);
		getEngine().getEventManager().registerEvents(new WoodCuttingListener(this), this);
		getEngine().getEventManager().registerEvents(new MiningListener(this), this);
		getEngine().getEventManager().registerEvents(new AttackListener(this), this);

		getLogger().info("enabled.");
	}

	@Override
	public void onDisable() {
		getLogger().info("disabled.");
	}

	private static void setInstance(TLDScapePlugin plugin) {
		instance = plugin;
	}

	public static TLDScapePlugin getInstance() {
		return instance;
	}

}