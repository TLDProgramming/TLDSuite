/*
 * This file is part of the Spout plugin TLDRules.
 */
package com.github.thelonedevil.TLDRules.commands;

import org.spout.api.command.CommandArguments;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.CommandDescription;
import org.spout.api.exception.CommandException;

import com.github.thelonedevil.TLDRules.TLDRules;

/**
 * Provides an example of a base command.
 */
public class TLDRulesBaseCommand {
	private TLDRules plugin;

	public TLDRulesBaseCommand(TLDRules instance) {
		this.plugin = instance;
	}

	/**
	 * Provides an example command that can be issued to the Spout server.
	 */
	@CommandDescription(aliases = { "TLDRules", "reserve" }, desc = "")
	public void TLDRules(CommandSource source, CommandArguments args) throws CommandException {
	}

}