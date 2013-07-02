package com.github.thelonedevil.TLDNotes.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.spout.api.command.CommandArguments;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.Command;
import org.spout.api.command.annotated.Permissible;
import org.spout.api.entity.Player;
import org.spout.api.exception.CommandException;

import com.github.thelonedevil.TLDCommonlib.Lib;
import com.github.thelonedevil.TLDNotes.TLDNotes;

public class PlayerCommands {
	private final TLDNotes plugin;

	public PlayerCommands(TLDNotes instance) {
		this.plugin = instance;
	}
	private Lib lib;

	@Command(aliases = { "notes add", "notes new" }, usage = "<Note to add>", desc = "Adds notes to your notes file")
	@Permissible("TLDNotes.add")
	public void add(CommandSource source, CommandArguments args) throws CommandException {
		if (source instanceof Player ) {
			Player player = (Player) source;
			String name = player.getName();
			String path = "plugins/TLDcommonLib/notes/" + name + ".txt";
			File file = new File(path);
			BufferedReader br = null;
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException e1) {
				plugin.getLogger().warning(lib.error);
				e1.printStackTrace();
			}
			try {
				br = new BufferedReader(new FileReader(file));
				List<String> h = new ArrayList<String>();
				while (br.readLine() != null){
					h.add(br.readLine());
				}
				if (h.size() < lib.amount){
					lib.Namount.put(name, false);
				}else if (h.size() >= lib.amount){
					lib.Namount.put(name, true);
				}
				
			} catch (FileNotFoundException e1) {
				plugin.getLogger().warning(lib.error);
				e1.printStackTrace();
			} catch (IOException e) {
				plugin.getLogger().warning(lib.error);
				e.printStackTrace();
			}
			final String note = getFinalArg(args, 0);
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(path, true));
				if (note.length() <= lib.Length){
					if(!lib.Namount.get(name)){
						out.write("\n" + note);
					} else if (lib.Namount.get(name)){
						player.sendMessage("Too many notes");
					}
				} else if(note.length() > lib.Length){
					player.sendMessage("Note is too long");
				}

				out.close();
			} catch (FileNotFoundException e) {
				plugin.getLogger().warning(lib.error);
				e.printStackTrace();
			} catch (IOException e) {
				plugin.getLogger().warning(lib.error);
				e.printStackTrace();
			}

		}

	}

	@Command(aliases = { "notes read", }, desc = "Reads back your notes, line by line", min = 0, max = 0)
	@Permissible("TLDNotes.read")
	public void read(CommandSource source, CommandArguments args) throws CommandException {
		if (source instanceof Player) {
			Player player = (Player) source;
			String name = player.getName();
			String path = "plugins/TLDcommonLib/notes/" + name + ".txt";
			File file = new File(path);
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e1) {
				plugin.getLogger().warning(lib.error);
				e1.printStackTrace();
			}
			String line;
			try {
				while ((line = br.readLine()) != null) {
					player.sendMessage(line);
				}
			} catch (IOException e ) {
				plugin.getLogger().warning(lib.error);
				e.printStackTrace();
			} catch (NullPointerException e1){
				source.sendMessage("You have no notes to read, try adding some with \"/Notes add <note>\"");
			}
			try {
				br.close();
			} catch (IOException e) {
				plugin.getLogger().warning(lib.error);
				e.printStackTrace();
			}
		}
	}

	public static String getFinalArg(final CommandArguments args, final int start) throws CommandException {
		final StringBuilder bldr = new StringBuilder();
		for (int i = start; i < args.length(); i++) {
			if (i != start) {
				bldr.append(" ");
			}
			bldr.append(args.getString(i));
		}
		return bldr.toString();
	}
}
