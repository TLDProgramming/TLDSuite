/*
 * This file is part of the Spout plugin TLDFactions. It also has a hard 
 * dependency on the Vanilla project.
 */
package com.github.thelonedevil.TLDFactions.command;

import java.util.ArrayList;
import java.util.List;

import org.spout.api.command.CommandArguments;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.CommandDescription;
import org.spout.api.exception.CommandException;

import com.github.thelonedevil.TLDFactions.TLDFactionsPlugin;

/**
 * Provides an example of a base command.
 */
public class TLDFactionsBaseCommand {
	private TLDFactionsPlugin plugin;

	public TLDFactionsBaseCommand(TLDFactionsPlugin instance) {
		this.plugin = instance;
	}

	/**
	 * Provides an example command that can be issued to the Spout server.
	 */
	@CommandDescription(aliases = { "tldf", "TLDFactions" }, desc = "This is an example of what a command might look like. Try it out with /cmd !")
	public void TLDFactions(CommandSource source, CommandArguments args) throws CommandException {
		int amount = plugin.getEngine().getCommandManager().getCommand("TLDFactions").getChildren().size();
		org.spout.api.command.Command[] cmds = new org.spout.api.command.Command[amount];
		plugin.getEngine().getCommandManager().getCommand("tldf").getChildren().toArray(cmds);
		int i = 0;
		List<String> list = new ArrayList<String>();
		source.sendMessage("The TLDFactions plugin base command has been successfully issued. (Type a sub-command)");
		source.sendMessage("The sub commands are:");
		while (i < amount && cmds[i] != null){
			list.add(cmds[i].getName());
			source.sendMessage(cmds[i].getName());
			i++;
		}

		
	}
}