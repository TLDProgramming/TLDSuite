package com.github.thelonedevil.TLDNotes.commands;

import org.spout.api.command.CommandArguments;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.CommandDescription;
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
	@CommandDescription(aliases = { "TLDNotes", "Notes" }, desc = "")
	public void TLDNotes(CommandSource source, CommandArguments args) throws CommandException {	
	}
}
