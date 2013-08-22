package com.github.thelonedevil.TLDQuotes;

import org.spout.api.command.annotated.AnnotatedCommandExecutorFactory;
import org.spout.api.plugin.Plugin;

import com.github.thelonedevil.TLDQuotes.commands.PlayerCommands;

public class TLDQuotes extends Plugin {

	private static TLDQuotes instance;

	@Override
	public void onLoad() {
		setInstance(this);
	}

	@Override
	public void onEnable() {
		if (getEngine().getPluginManager().getPlugin("Vanilla") == null) {
			getLogger().info("Vanilla plugin not found, disabling.");
			getEngine().getPluginManager().disablePlugin(this);
		} else {
			// Commands
			AnnotatedCommandExecutorFactory.create(new PlayerCommands(this));

			// Listener
			getEngine().getEventManager().registerEvents(new EListener(this), this);

		}
	}

	@Override
	public void onDisable() {
	}

	private static void setInstance(TLDQuotes plugin) {
		instance = plugin;
	}

	public static TLDQuotes getInstance() {
		return instance;
	}
}
