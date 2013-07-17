package com.github.thelonedevil.TLDNotes;

import org.spout.api.command.annotated.AnnotatedCommandExecutorFactory;
import org.spout.api.plugin.Plugin;
import com.github.thelonedevil.TLDNotes.commands.BaseCommand;
import com.github.thelonedevil.TLDNotes.commands.PlayerCommands;

/**
 * If you have found this useful, please let me know.
 * 
 * @author Craig <tenowg at thedemgel.com>
 */
public class TLDNotes extends Plugin {
	private static TLDNotes instance;

	@Override
	public void onLoad() {
		setInstance(this);
	}

	@Override
	public void onEnable() {
		AnnotatedCommandExecutorFactory.create(new BaseCommand(this));
		AnnotatedCommandExecutorFactory.create(new PlayerCommands(this), getEngine().getCommandManager().getCommand("notes"));
	}

	@Override
	public void onDisable() {
	}

	private static void setInstance(TLDNotes plugin) {
		instance = plugin;
	}

	public static TLDNotes getInstance() {
		return instance;
	}
}
