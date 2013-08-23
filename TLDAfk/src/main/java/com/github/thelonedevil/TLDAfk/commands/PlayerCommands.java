package com.github.thelonedevil.TLDAfk.commands;

import org.spout.api.Server;
import org.spout.api.command.CommandArguments;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.CommandDescription;
import org.spout.api.command.annotated.Filter;
import org.spout.api.command.annotated.Permissible;
import org.spout.api.command.filter.PlayerFilter;
import org.spout.api.exception.CommandException;

import com.github.thelonedevil.TLDAfk.TLDAfk;
import com.github.thelonedevil.TLDCommonlib.Lib;

public class PlayerCommands {

	private TLDAfk plugin;
	
	public PlayerCommands(TLDAfk instance) {
		this.plugin = instance;
	}
	private Lib lib = new Lib();

	@CommandDescription(aliases = { "TLDAfk", "Afk" }, usage = "", desc = "")
	@Permissible("TLDAfk.toggle")
	@Filter(PlayerFilter.class)
	public void TLDAfk(CommandSource source, CommandArguments args) throws CommandException {
		String name = source.getName();
		if (lib.afk.get(name) == false || lib.afk.get(name) == null) {
			lib.afk.put(name, true);
			source.sendMessage("You are now AFK");
			String message = name + " Is now AFK";
			((Server) lib.getEngine()).broadcastMessage(message);
		} else if (lib.afk.get(name) == true) {
			lib.afk.put(name, false);
			source.sendMessage("You are no longer AFK");
			String message = name + " Is no longer AFK";
			((Server) lib.getEngine()).broadcastMessage(message);
		}
	}
}
