package com.github.thelonedevil.TLDRules.commands;

import org.spout.api.command.CommandArguments;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.Command;
import org.spout.api.exception.CommandException;

import com.github.thelonedevil.TLDCommonlib.Lib;
import com.github.thelonedevil.TLDRules.CleanRules;

public class PlayerCommands {
	private CleanRules plugin;

	public PlayerCommands(CleanRules instance) {
		this.plugin = instance;
	}

	private Lib lib;

	@Command(aliases = { "ruleset", "rules" }, desc = "Displays the rules")
	public void rules(CommandSource source, CommandArguments args) throws CommandException {
		int page = 0;
		if (args.length() > 0) {
			if (args.isInteger(0)) {
				page = args.getInteger(0);
			} else
				throw new CommandException(args.getString(0) + " is not a valid integer!");
		}
		try {
			for (String rule : lib.rules.get(page)) {
				source.sendMessage(rule);
			}
			source.sendMessage("To see the next page, do /rules " + (page + 1));
		} catch (NullPointerException e) {
			source.sendMessage("There are no rules on that page :O");
		}
	}
}
