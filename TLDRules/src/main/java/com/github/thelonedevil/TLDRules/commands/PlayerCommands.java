package com.github.thelonedevil.TLDRules.commands;

import org.spout.api.command.CommandArguments;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.CommandDescription;
import org.spout.api.exception.CommandException;

import com.github.thelonedevil.TLDCommonlib.Lib;
import com.github.thelonedevil.TLDRules.TLDRules;

public class PlayerCommands {

	private TLDRules plugin;

	public PlayerCommands(TLDRules instance) {
		this.plugin = instance;
	}

	private Lib lib = new Lib();

	@CommandDescription(aliases = { "ruleset", "rules" }, desc = "Displays the rules")
	public void rules(CommandSource source, CommandArguments args) throws CommandException {
		int page = 0;
		if (args.length() > 0) {
			page = args.popInteger("page");
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
