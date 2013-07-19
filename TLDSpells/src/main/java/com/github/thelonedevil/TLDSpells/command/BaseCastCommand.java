package com.github.thelonedevil.TLDSpells.command;

import org.spout.api.command.CommandArguments;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.CommandDescription;
import org.spout.api.command.annotated.Permissible;
import org.spout.api.exception.CommandException;

import com.github.thelonedevil.TLDSpells.TLDSpellsPlugin;

public class BaseCastCommand {
	
	private final TLDSpellsPlugin plugin;

	public BaseCastCommand(TLDSpellsPlugin instance) {
		this.plugin = instance;
	}

	@CommandDescription(aliases = {"Cast", "spell"}, desc = "This is an example of what a command might look like. Try it out with /cmd !")
	@Permissible("TLDSpells.some.permission")
	public void Cast(CommandSource source, CommandArguments args) throws CommandException {
		
	}
}
