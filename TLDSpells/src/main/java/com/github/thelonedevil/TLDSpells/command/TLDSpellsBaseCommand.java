
/*
 * This file is part of the Spout plugin TLDSpells. It also has a hard 
 * dependency on the Vanilla project.
 */
package com.github.thelonedevil.TLDSpells.command;

import org.spout.api.command.CommandArguments;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.CommandDescription;
import org.spout.api.command.annotated.Permissible;
import org.spout.api.exception.CommandException;

import com.github.thelonedevil.TLDSpells.TLDSpellsPlugin;

/**
 * Provides an example of a base command.
 */
public class TLDSpellsBaseCommand {
	private final TLDSpellsPlugin plugin;

	public TLDSpellsBaseCommand(TLDSpellsPlugin instance) {
		this.plugin = instance;
	}

	/**
	 * Provides an example command that can be issued to the Spout server.
	 */
	@CommandDescription(aliases = {"TLDSpells", "TLDS"}, desc = "")
	@Permissible("TLDSpells.some.permission")
	public void TLDSpells(CommandSource source, CommandArguments args) throws CommandException {
		
		// Calling this command will send whoever issued it the message below.
		source.sendMessage("The TLDSpells plugin base command has been successfully issued. (Type a sub-command)");
	}
	
}