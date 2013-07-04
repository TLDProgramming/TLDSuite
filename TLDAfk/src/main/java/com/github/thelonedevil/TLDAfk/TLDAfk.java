package com.github.thelonedevil.TLDAfk;

import org.spout.api.command.annotated.AnnotatedCommandExecutorFactory;
import org.spout.api.plugin.Plugin;

import com.github.thelonedevil.TLDAfk.commands.PlayerCommands;

/**
 * If you have found this useful, please let me know.
 * 
 * @author Craig <tenowg at thedemgel.com>
 */
public class TLDAfk extends Plugin {
	private static TLDAfk instance;

	@Override
	public void onLoad() {
		setInstance(this);
	}

	@Override
	public void onEnable() {
		AnnotatedCommandExecutorFactory.create(new PlayerCommands(this));
		getEngine().getEventManager().registerEvents(new EListener(this), this);
	}

	@Override
	public void onDisable() {
	}

	private static void setInstance(TLDAfk plugin) {
		instance = plugin;
	}

	public static TLDAfk getInstance() {
		return instance;
	}
}
