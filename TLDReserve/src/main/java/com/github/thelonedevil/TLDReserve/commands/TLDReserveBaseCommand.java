/*
 * This file is part of the Spout plugin TLDReserve. 
 */
package com.github.thelonedevil.TLDReserve.commands;

import org.spout.api.command.CommandArguments;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.CommandDescription;
import org.spout.api.exception.CommandException;

import com.github.thelonedevil.TLDReserve.TLDReserve;

/**
 * Provides an example of a base command.
 */
public class TLDReserveBaseCommand {
	private TLDReserve plugin;

	public TLDReserveBaseCommand(TLDReserve instance) {
		this.plugin = instance;
	}

	/**
	 * Provides an example command that can be issued to the Spout server.
	 */
	@CommandDescription(aliases = { "TLDReserve", "reserve" }, desc = "")
	public void TLDReserve(CommandSource source, CommandArguments args) throws CommandException {
	}

}