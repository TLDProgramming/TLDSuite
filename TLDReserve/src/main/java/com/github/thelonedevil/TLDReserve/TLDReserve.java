package com.github.thelonedevil.TLDReserve;

import org.spout.api.command.annotated.AnnotatedCommandExecutorFactory;
import org.spout.api.plugin.Plugin;

import com.github.thelonedevil.TLDReserve.commands.PlayerCommands;
import com.github.thelonedevil.TLDReserve.commands.TLDReserveBaseCommand;

/**
 * If you have found this useful, please let me know.
 * 
 * @author Craig <tenowg at thedemgel.com>
 */
public class TLDReserve extends Plugin {
	private static TLDReserve instance;

	@Override
	public void onLoad() {
		setInstance(this);
	}

	@Override
	public void onEnable() {
		// Commands
		AnnotatedCommandExecutorFactory.create(new TLDReserveBaseCommand(this));
		AnnotatedCommandExecutorFactory.create(new PlayerCommands(this), getEngine().getCommandManager().getCommand("TLDReserve"));

		getEngine().getEventManager().registerEvents(new EListener(this), this);

	}

	@Override
	public void onDisable() {
	}

	private static void setInstance(TLDReserve plugin) {
		instance = plugin;
	}

	public static TLDReserve getInstance() {
		return instance;
	}

}
