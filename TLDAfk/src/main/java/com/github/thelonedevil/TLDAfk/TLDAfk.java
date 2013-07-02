package com.github.thelonedevil.TLDAfk;

import org.spout.api.command.annotated.AnnotatedCommandExecutorFactory;
import org.spout.api.plugin.Plugin;

import com.github.thelonedevil.TLDAfk.commands.PlayerCommands;
import com.github.thelonedevil.TLDCommonlib.Lib;

/**
 * If you have found this useful, please let me know.
 * 
 * @author Craig <tenowg at thedemgel.com>
 */
public class TLDAfk extends Plugin {
	private static TLDAfk instance;
	private Lib lib;

	@Override
	public void onLoad() {
		setInstance(this);
		getLogger().info(lib.logged + "loaded.");
	}

	@Override
	public void onEnable() {
		AnnotatedCommandExecutorFactory.create(new PlayerCommands(this));

		getEngine().getEventManager().registerEvents(new EListener(this), this);
		getLogger().info(lib.logged + "enabled.");
	}

	@Override
	public void onDisable() {
		getLogger().info(lib.logged + "disabled.");
	}

	private static void setInstance(TLDAfk plugin) {
		instance = plugin;
	}

	public TLDAfk getInstance() {
		return instance;
	}
}
