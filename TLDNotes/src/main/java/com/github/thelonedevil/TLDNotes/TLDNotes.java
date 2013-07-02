package com.github.thelonedevil.TLDNotes;

import org.spout.api.command.annotated.AnnotatedCommandExecutorFactory;
import org.spout.api.plugin.Plugin;
import org.spout.api.plugin.PluginLogger;

import com.github.thelonedevil.TLDCommonlib.Lib;
import com.github.thelonedevil.TLDNotes.commands.PlayerCommands;

/**
 * If you have found this useful, please let me know.
 * 
 * @author Craig <tenowg at thedemgel.com>
 */
public class TLDNotes extends Plugin {
	private static TLDNotes instance;
	private Lib lib;

	@Override
	public void onLoad() {
		setInstance(this);
		((PluginLogger) getLogger()).setTag("[TLDNotes]");
		getLogger().info(lib.logged + "loaded.");
	}

	@Override
	public void onEnable() {
		AnnotatedCommandExecutorFactory.create(new PlayerCommands(this));
		getLogger().info(lib.logged + "enabled.");
	}

	@Override
	public void onDisable() {
		getLogger().info(lib.logged + "disabled.");
	}

	private static void setInstance(TLDNotes plugin) {
		instance = plugin;
	}

	public static TLDNotes getInstance() {
		return instance;
	}
}
