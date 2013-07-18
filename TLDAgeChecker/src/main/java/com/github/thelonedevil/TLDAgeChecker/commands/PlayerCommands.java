package com.github.thelonedevil.TLDAgeChecker.commands;

import java.util.Calendar;
import java.util.Date;

import org.spout.api.command.CommandArguments;
import org.spout.api.command.CommandSource;
import org.spout.api.command.annotated.CommandDescription;
import org.spout.api.command.annotated.Filter;
import org.spout.api.command.annotated.Permissible;
import org.spout.api.command.filter.PlayerFilter;
import org.spout.api.entity.Player;
import org.spout.api.exception.CommandException;

import com.github.thelonedevil.TLDAgeChecker.TLDAgeChecker;
import com.github.thelonedevil.TLDCommonlib.Lib;

public class PlayerCommands {
	private final TLDAgeChecker plugin;

	public PlayerCommands(TLDAgeChecker instance) {
		this.plugin = instance;
	}

	private Lib lib = new Lib();

	@CommandDescription(aliases = { "command", "cmd" }, usage = "No Usage, replace this command", desc = "This is just an Example. Replace it.")
	@Permissible("TLDAgeChecker.some.permission")
	@Filter(PlayerFilter.class)
	public void aCommand(CommandSource source, CommandArguments args) throws CommandException {
		Player player = (Player) source;
		String name = player.getName();
		Date now = new Date();
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(now);
		cal1.add(Calendar.YEAR, -13);
		String dob1 = args.toString();
		String[] dob2 = dob1.split("/");
		int yyyy;
		int mm;
		int dd;
		if (lib.dateformat.equalsIgnoreCase("MM/DD/YYYY")) {
			yyyy = 2;
			mm = 0;
			dd = 1;
		} else if (lib.dateformat.equalsIgnoreCase("YYYY/MM/DD")) {
			yyyy = 0;
			mm = 1;
			dd = 2;
		} else {
			yyyy = 2;
			mm = 1;
			dd = 0;
		}
		String year2 = dob2[yyyy];
		String month2 = dob2[mm];
		String date2 = dob2[dd];
		int year3 = Integer.parseInt(year2);
		int month3 = Integer.parseInt(month2) - 1;
		int date3 = Integer.parseInt(date2);
		cal2.clear();
		cal2.set(year3, month3, date3);
		TLDAgeChecker.DOB.put(name, cal2);
		int year = cal1.get(Calendar.YEAR);
		int month = cal1.get(Calendar.MONTH);
		int day = cal1.get(Calendar.DATE);
		int year1 = cal2.get(Calendar.YEAR);
		int month1 = cal2.get(Calendar.MONTH);
		int day1 = cal2.get(Calendar.DATE);
		if (TLDAgeChecker.allowed.get(name) == null) {
			if (year > year1) {
				TLDAgeChecker.allowed.put(name, true);
			} else {
				if (year < year1) {
					TLDAgeChecker.allowed.put(name, false);
				}
				if (year == year1) {
					if (month > month1) {
						TLDAgeChecker.allowed.put(name, true);
					} else {
						if (month < month1) {
							TLDAgeChecker.allowed.put(name, false);
						}
						if (month == month1) {
							if (day >= day1) {
								TLDAgeChecker.allowed.put(name, true);
							} else if (day < day1) {
								TLDAgeChecker.allowed.put(name, false);
							}
						}
					}

				} else if (TLDAgeChecker.allowed.get(name) == false) {
					player.kick("You are not old enough to play on this server");
				} else if (TLDAgeChecker.allowed.get(name) == true) {
					player.sendMessage("You are old enough to play on this server");
				}

			}

		}
		args.assertCompletelyParsed();
	}
}
