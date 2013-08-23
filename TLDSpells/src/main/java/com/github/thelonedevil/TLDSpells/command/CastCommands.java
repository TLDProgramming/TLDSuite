package com.github.thelonedevil.TLDSpells.command;

import org.spout.api.command.CommandArguments;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.CommandDescription;
import org.spout.api.command.annotated.Filter;
import org.spout.api.command.annotated.Permissible;
import org.spout.api.command.filter.PlayerFilter;
import org.spout.api.entity.Player;
import org.spout.api.exception.CommandException;

import com.github.thelonedevil.TLDSpells.TLDSpellsPlugin;
import com.github.thelonedevil.TLDSpells.Spells.FireBolt;
import com.github.thelonedevil.TLDSpells.Spells.HealingBolt;
import com.github.thelonedevil.TLDSpells.Spells.IceBolt;

public class CastCommands {
	private final TLDSpellsPlugin plugin;

	public CastCommands(TLDSpellsPlugin instance) {
		this.plugin = instance;
	}
	@CommandDescription(aliases = {"Icebolt"}, desc = "")
	@Permissible("TLDSpells.some.permission")
	@Filter(PlayerFilter.class)
	public void Icebolt(CommandSource source, CommandArguments args) throws CommandException {
		IceBolt icebolt = new IceBolt(plugin);
		icebolt.cast((Player)source);
	}
	@CommandDescription(aliases = {"Firebolt"}, desc = "")
	@Permissible("TLDSpells.some.permission")
	@Filter(PlayerFilter.class)
	public void Firebolt(CommandSource source, CommandArguments args) throws CommandException {
		FireBolt firebolt = new FireBolt(plugin);
		firebolt.cast((Player)source);
	}
	
	@CommandDescription(aliases = {"Healingbolt"}, desc = "")
	@Permissible("TLDSpells.some.permission")
	@Filter(PlayerFilter.class)
	public void Healingbolt(CommandSource source, CommandArguments args) throws CommandException {
		HealingBolt healingbolt = new HealingBolt(plugin);
		healingbolt.cast((Player)source);
	}
}
