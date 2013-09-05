/*
 * This file is part of the Spout plugin TLDNPC. It also has a hard 
 * dependency on the Vanilla project.
 */
package com.github.thelonedevil.TLDNPC;

import org.spout.api.plugin.Plugin;
import org.spout.api.command.annotated.AnnotatedCommandExecutorFactory;

import com.github.thelonedevil.TLDNPC.command.TLDNPCBaseCommand;
import com.github.thelonedevil.TLDNPC.command.TLDNPCCommands;

/**
 * Defines the main class of the plugin.
 */
public class TLDNPCPlugin extends Plugin {
	private static TLDNPCPlugin instance;

	@Override
	public void onLoad() {
		setInstance(this);
		getLogger().info("loaded.");
	}

	@Override
	public void onEnable() {
		// Register Base Command (/command)
		AnnotatedCommandExecutorFactory.create(new TLDNPCBaseCommand(this));
		// Register Commands under Base Command (/command command)
		AnnotatedCommandExecutorFactory.create(new TLDNPCCommands(this), getEngine().getCommandManager().getCommand("command"));

		// Register Events
		getEngine().getEventManager().registerEvents(new TLDNPCListener(this), this);

		getLogger().info("enabled.");
	}

	@Override
	public void onDisable() {
		getLogger().info("disabled.");
	}

	private static void setInstance(TLDNPCPlugin plugin) {
		instance = plugin;
	}

	public static TLDNPCPlugin getInstance() {
		return instance;
	}

}