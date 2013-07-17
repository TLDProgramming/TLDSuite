package com.github.thelonedevil.TLDRules;

import org.spout.api.command.annotated.AnnotatedCommandExecutorFactory;
import org.spout.api.plugin.Plugin;
import com.github.thelonedevil.TLDRules.commands.PlayerCommands;

/**
 * If you have found this useful, please let me know.
 * 
 * @author Craig <tenowg at thedemgel.com>
 */
public class CleanRules extends Plugin {
	private static CleanRules instance;
	@Override
	public void onLoad() {
		setInstance(this);
	}

	@Override
	public void onEnable() {
		// Commands
		getEngine().getEventManager().registerEvents(new PlayerJoin(this), this);
		AnnotatedCommandExecutorFactory.create(new PlayerCommands(this));
	}

	@Override
	public void onDisable() {
	}

	private static void setInstance(CleanRules plugin) {
		instance = plugin;
	}

	public static CleanRules getInstance() {
		return instance;
	}
}
