package com.github.thelonedevil.TLDQuotes;

import org.spout.api.command.annotated.AnnotatedCommandExecutorFactory;
import org.spout.api.plugin.Plugin;
import org.spout.api.plugin.PluginLogger;

import com.github.thelonedevil.TLDCommonlib.Lib;
import com.github.thelonedevil.TLDQuotes.commands.PlayerCommands;

public class Quoter extends Plugin {

	private static Quoter instance;
	private Lib lib;

	@Override
	public void onLoad() {
		((PluginLogger) getLogger()).setTag("[TLDQuotes]");
		getLogger().info(lib.logged + "Loaded correctly");
		if (lib.testQuote != null) {
			getLogger().info("Quotes loaded correctly");
		} else
			getLogger().info("Quotes loaded incorrectly");

	}

	@Override
	public void onEnable() {
		// Commands
		AnnotatedCommandExecutorFactory.create(new PlayerCommands(this));

		getEngine().getEventManager().registerEvents(new EListener(this), this);
		getLogger().info(lib.logged + "enabled.");
	}

	@Override
	public void onDisable() {
		getLogger().info(lib.logged + "disabled.");
	}

	public static Quoter getInstance() {
		return instance;
	}
}
