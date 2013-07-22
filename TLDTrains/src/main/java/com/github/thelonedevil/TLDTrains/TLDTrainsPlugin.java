/*
 * This file is part of the Spout plugin TLDTrains. It also has a hard 
 * dependency on the Vanilla project.
 */
package com.github.thelonedevil.TLDTrains;

import org.spout.api.plugin.Plugin;
import org.spout.api.command.annotated.AnnotatedCommandExecutorFactory;

import com.github.thelonedevil.TLDTrains.command.TLDTrainsBaseCommand;
import com.github.thelonedevil.TLDTrains.command.TLDTrainsCommands;

/**
 * Defines the main class of the plugin.
 */
public class TLDTrainsPlugin extends Plugin {
	private static TLDTrainsPlugin instance;


	@Override
	public void onLoad() {
		setInstance(this);
	}

	@Override
	public void onEnable() {
		// Register Base Command (/command)
		AnnotatedCommandExecutorFactory.create(new TLDTrainsBaseCommand(this));
		// Register Commands under Base Command (/command command)
		AnnotatedCommandExecutorFactory.create(new TLDTrainsCommands(this), getEngine().getCommandManager().getCommand("command"));

		// Register Events
		getEngine().getEventManager().registerEvents(new TLDTrainsListener(this), this);

		getLogger().info("enabled.");
	}

	@Override
	public void onDisable() {
		getLogger().info("disabled.");
	}

	private static void setInstance(TLDTrainsPlugin plugin) {
		instance = plugin;
	}

	public static TLDTrainsPlugin getInstance() {
		return instance;
	}

}