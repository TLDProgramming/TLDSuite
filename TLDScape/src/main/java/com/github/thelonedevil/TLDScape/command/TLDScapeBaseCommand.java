
/*
 * This file is part of the Spout plugin TLDScape. It also has a hard 
 * dependency on the Vanilla project.
 */
package com.github.thelonedevil.TLDScape.command;

import org.spout.api.command.annotated.CommandDescription;
import org.spout.api.command.annotated.Permissible;
import org.spout.api.command.CommandArguments;
import org.spout.api.command.CommandSource;
import org.spout.api.exception.CommandException;

import com.github.thelonedevil.TLDScape.TLDScapePlugin;

/**
 * Provides an example of a base command.
 */
public class TLDScapeBaseCommand {
	private final TLDScapePlugin plugin;

	public TLDScapeBaseCommand(TLDScapePlugin instance) {
		this.plugin = instance;
	}

	/**
	 * Provides an example command that can be issued to the Spout server.
	 */
	@CommandDescription(aliases = {"command", "cmd"}, desc = "This is an example of what a command might look like. Try it out with /cmd !")
	@Permissible("TLDScape.some.permission")
	public void exampleBaseCommand(CommandSource source, CommandArguments args) throws CommandException {
		
		// Calling this command will send whoever issued it the message below.
		source.sendMessage("The TLDScape plugin base command has been successfully issued. (Type a sub-command)");
	}
}