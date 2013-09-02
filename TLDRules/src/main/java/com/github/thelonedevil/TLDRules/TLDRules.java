package com.github.thelonedevil.TLDRules;

import org.spout.api.command.annotated.AnnotatedCommandExecutorFactory;
import org.spout.api.plugin.Plugin;

import com.github.thelonedevil.TLDRules.commands.PlayerCommands;
import com.github.thelonedevil.TLDRules.commands.TLDRulesBaseCommand;

/**
 * If you have found this useful, please let me know.
 * 
 * @author Craig <tenowg at thedemgel.com>
 */
public class TLDRules extends Plugin {
	private static TLDRules instance;

	@Override
	public void onLoad() {
		setInstance(this);
	}

	@Override
	public void onEnable() {
		// Commands
		getEngine().getEventManager().registerEvents(new PlayerJoin(this), this);
		AnnotatedCommandExecutorFactory.create(new TLDRulesBaseCommand(this));
		AnnotatedCommandExecutorFactory.create(new PlayerCommands(this), getEngine().getCommandManager().getCommand("TLDRules"));
	}

	@Override
	public void onDisable() {
	}

	private static void setInstance(TLDRules plugin) {
		instance = plugin;
	}

	public static TLDRules getInstance() {
		return instance;
	}
}
