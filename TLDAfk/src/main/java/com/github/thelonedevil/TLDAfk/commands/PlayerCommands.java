package com.github.thelonedevil.TLDAfk.commands;

import org.spout.api.Server;
import org.spout.api.command.CommandArguments;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.Command;
import org.spout.api.command.annotated.Permissible;
import org.spout.api.exception.CommandException;

import com.github.thelonedevil.TLDAfk.TLDAfk;
import com.github.thelonedevil.TLDCommonlib.Lib;

public class PlayerCommands {

	private TLDAfk plugin;
	
	public PlayerCommands(TLDAfk instance) {
		this.plugin = instance;
	}

	@Command(aliases = { "command", "cmd" }, usage = "No Usage, replace this command", desc = "This is just an Example. Replace it.", min = 0, max = 0)
	@Permissible("TLDAfk.toggle")
	public void afk(CommandSource source, CommandArguments args) throws CommandException {
		String name = source.getName();
		if (Lib.afk.get(name) == false || Lib.afk.get(name) == null) {
			Lib.afk.put(name, true);
			source.sendMessage("You are now AFK");
			String message = name + " Is now AFK";
			((Server) plugin.getEngine()).broadcastMessage(message);
		} else if (Lib.afk.get(name) == true) {
			Lib.afk.put(name, false);
			source.sendMessage("You are no longer AFK");
			String message = name + " Is no longer AFK";
			((Server) plugin.getEngine()).broadcastMessage(message);
		}
	}
}
