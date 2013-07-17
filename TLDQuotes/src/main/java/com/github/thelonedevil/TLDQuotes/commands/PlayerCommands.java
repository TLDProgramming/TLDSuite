package com.github.thelonedevil.TLDQuotes.commands;

import java.util.Random;

import org.spout.api.Server;
import org.spout.api.command.CommandArguments;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.CommandDescription;
import org.spout.api.command.annotated.Permissible;
import org.spout.api.entity.Player;
import org.spout.api.exception.CommandException;

import com.github.thelonedevil.TLDCommonlib.Lib;
import com.github.thelonedevil.TLDQuotes.Quoter;

public class PlayerCommands {

	private Quoter plugin;

	public PlayerCommands(Quoter instance) {
		this.plugin = instance;
	}
	private Lib lib = new Lib();
	
	final String[] quote1 = new String[1];
	String quote;
	String last;

	@CommandDescription(aliases = "Quote", usage = "/Quote", desc = "Displays a random quote from a list")
	@Permissible("Quoter.quote")
	public void Quotes(CommandSource source, CommandArguments args) throws CommandException {
		int random = new Random().nextInt(lib.Quotes.size());
		if (args.length() == 0) {
			quote = lib.Quotes.get(random);
			quote1[0] = quote;
			source.sendMessage(quote);
		} else if (args.length() == 1 && args.popString("shareing").equalsIgnoreCase("share")) {
			if (quote1[0] != null) {
				last = quote1[0];
				((Server) plugin.getEngine()).broadcastMessage(last);
				source.sendMessage(last);
			} else if (quote1[0] == null) {
				source.sendMessage("You do not have a quote to share :O");
			}

		} else if ((args.length() == 2 && args.popString("Shareing").equalsIgnoreCase("share")) && (args.get() != null)) {
			Player target = ((Server)plugin.getEngine()).getPlayer(args.popString("Targer player"), true);
			if (target == null) {
				source.sendMessage( "Player is not online!");
				return;
			} else if (target != null && quote1[0] != null) {
				last = quote1[0];
				target.sendMessage(last);
			} else if (quote1[0] == null) {
				source.sendMessage("You do not have a quote to share :O");
			}
		}
		args.assertCompletelyParsed();
	}
}
