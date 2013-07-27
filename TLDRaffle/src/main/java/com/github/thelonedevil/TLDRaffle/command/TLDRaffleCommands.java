/*
 * This file is part of the Spout plugin TLDRaffle. It also has a hard 
 * dependency on the Vanilla project.
 */
package com.github.thelonedevil.TLDRaffle.command;

import org.spout.api.command.CommandArguments;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.CommandDescription;
import org.spout.api.command.annotated.Permissible;
import org.spout.api.exception.CommandException;

import com.github.thelonedevil.TLDRaffle.TLDRafflePlugin;

/**
 * Provides an example of a class to hold commands.
 */
public class TLDRaffleCommands {
	private final TLDRafflePlugin plugin;

	public TLDRaffleCommands(TLDRafflePlugin instance) {
		this.plugin = instance;
	}

	/**
	 * Provides an example command that can be issued to the Spout server.
	 */
	@CommandDescription(aliases = {"command", "cmd"}, desc = "This is an example of what a command might look like. Try it out with /cmd !")
	@Permissible("TLDRaffle.some.permission")
	public void exampleCommand(CommandSource source, CommandArguments args) throws CommandException {
		
		// Calling this command will send whoever issued it the message below.
		source.sendMessage("The TLDRaffle plugin command has been successfully issued.");
	}
}
