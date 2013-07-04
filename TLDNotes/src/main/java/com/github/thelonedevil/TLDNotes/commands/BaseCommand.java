package com.github.thelonedevil.TLDNotes.commands;

import org.spout.api.command.CommandArguments;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.Command;
import org.spout.api.exception.CommandException;

import com.github.thelonedevil.TLDNotes.TLDNotes;

public class BaseCommand {
	private TLDNotes plugin;
	
	public BaseCommand(TLDNotes instance) {
		this.plugin = instance;
	}

	/**
	 * Provides an example command that can be issued to the Spout server.
	 */
	@Command(aliases = { "Notes" }, desc = "This is an example of what a command might look like. Try it out with /cmd !")
	public void notes(CommandSource source, CommandArguments args) throws CommandException {	
	}
}
