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
	private final TLDAfk plugin;

	public PlayerCommands(TLDAfk instance) {
		this.plugin = instance;
	}
	private Lib lib;
	@Command(aliases = { "command", "cmd" }, usage = "No Usage, replace this command", desc = "This is just an Example. Replace it.", min = 0, max = 0)
	@Permissible("TLDAfk.toggle")
	public void afk(CommandSource source, CommandArguments args) throws CommandException {
		String name = source.getName();
		if (lib.afk.get(name) == false || lib.afk.get(name) == null) {
			lib.afk.put(name, true);
			source.sendMessage("You are now AFK");
			String message = name + " Is now AFK";
			((Server) plugin.getInstance().getEngine()).broadcastMessage(message);
		} else if (lib.afk.get(name) == true) {
			lib.afk.put(name, false);
			source.sendMessage("You are no longer AFK");
			String message = name + " Is no longer AFK";
			((Server) plugin.getInstance().getEngine()).broadcastMessage(message);
		}
	}
}
