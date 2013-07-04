package com.github.thelonedevil.TLDQuotes;

import org.spout.api.command.annotated.AnnotatedCommandExecutorFactory;
import org.spout.api.plugin.Plugin;

import com.github.thelonedevil.TLDCommonlib.Lib;
import com.github.thelonedevil.TLDQuotes.commands.PlayerCommands;

public class Quoter extends Plugin {

	private static Quoter instance;

	@Override
	public void onLoad() {
		setInstance(this);
	}

	@Override
	public void onEnable() {
		// Commands
		AnnotatedCommandExecutorFactory.create(new PlayerCommands(this));

		getEngine().getEventManager().registerEvents(new EListener(this), this);
		
	}

	@Override
	public void onDisable() {
	}
	
	private static void setInstance(Quoter plugin) {
		instance = plugin;
	}

	public static Quoter getInstance() {
		return instance;
	}
}
