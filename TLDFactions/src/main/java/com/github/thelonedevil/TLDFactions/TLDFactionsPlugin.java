/*
 * This file is part of the Spout plugin TLDFactions. It also has a hard 
 * dependency on the Vanilla project.
 */
package com.github.thelonedevil.TLDFactions;

import org.spout.api.command.annotated.AnnotatedCommandExecutorFactory;
import org.spout.api.plugin.Plugin;

import com.github.thelonedevil.TLDFactions.command.TLDFactionsBaseCommand;
import com.github.thelonedevil.TLDFactions.command.TLDFactionsCommands;

/**
 * Defines the main class of the plugin.
 */
public class TLDFactionsPlugin extends Plugin {
	private static TLDFactionsPlugin instance;
	@Override
	public void onLoad() {
		setInstance(this);
	}

	@Override
	public void onEnable() {
		// Register Base Command (/command)
		AnnotatedCommandExecutorFactory.create(new TLDFactionsBaseCommand(this));
		// Register Commands under Base Command (/command command)
		AnnotatedCommandExecutorFactory.create(new TLDFactionsCommands(this), getEngine().getCommandManager().getCommand("TLDFactions"));

		// Register Events
		getEngine().getEventManager().registerEvents(new TLDFactionsListener(this), this);
	}

	@Override
	public void onDisable() {
	}

	private static void setInstance(TLDFactionsPlugin plugin) {
		instance = plugin;
	}

	public static TLDFactionsPlugin getInstance() {
		return instance;
	}
}