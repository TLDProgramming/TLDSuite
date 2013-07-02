package com.github.thelonedevil.TLDReserve.commands;

import org.spout.api.command.CommandArguments;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.Command;
import org.spout.api.command.annotated.Permissible;
import org.spout.api.exception.CommandException;

import com.github.thelonedevil.TLDCommonlib.Lib;
import com.github.thelonedevil.TLDReserve.TLDReserve;

public class PlayerCommands {
	private final TLDReserve plugin;

	public PlayerCommands(TLDReserve instance) {
		this.plugin = instance;
	}
	private Lib lib;

	@Command(aliases = { "admin", }, usage = "No Usage, replace this command", desc = "This is just an Example. Replace it.", min = 1, max = 2)
	@Permissible("TLDReserve.some.permission")
	public void admin(CommandSource source, CommandArguments args) throws CommandException {
		String target = args.getString(0);
		if (args.length() == 1) {
			if (!lib.admins.contains(target)) {
				lib.admins.add(target);
				source.sendMessage(target + " has had a place on the server reserved for them");
			} else {
				source.sendMessage(target + " already has a place reserved for him");
			}
		} else if (args.length() == 2) {
			if (args.getString(1).equalsIgnoreCase("remove")) {
				if (lib.admins.contains(target)) {
					lib.admins.remove(target);
					source.sendMessage(target + " no longer has a place on the server reserved for them");
				} else {
					source.sendMessage(target + " did not have a place reserved for them");
				}
			}
		}

	}
}
