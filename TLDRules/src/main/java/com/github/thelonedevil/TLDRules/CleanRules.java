package com.github.thelonedevil.TLDRules;

import org.spout.api.command.annotated.AnnotatedCommandExecutorFactory;
import org.spout.api.plugin.Plugin;
import org.spout.api.plugin.PluginLogger;

import com.github.thelonedevil.TLDCommonlib.Lib;
import com.github.thelonedevil.TLDRules.commands.PlayerCommands;

/**
 * If you have found this useful, please let me know.
 * 
 * @author Craig <tenowg at thedemgel.com>
 */
public class CleanRules extends Plugin {
	private static CleanRules instance;
	private Lib lib;

	@Override
	public void onLoad() {
		setInstance(this);
		((PluginLogger) getLogger()).setTag("[TLDRules] ");
		getLogger().info(lib.logged + "Loaded.");
	}

	@Override
	public void onEnable() {
		// Commands
		getEngine().getEventManager().registerEvents(new PlayerJoin(this), this);
		getLogger().info(lib.logged + "enabled.");
		AnnotatedCommandExecutorFactory.create(new PlayerCommands(this));
	}

	@Override
	public void onDisable() {
		getLogger().info(lib.logged + "disabled.");
	}

	private static void setInstance(CleanRules plugin) {
		instance = plugin;
	}

	public static CleanRules getInstance() {
		return instance;
	}
}
