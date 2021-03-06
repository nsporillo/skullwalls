package net.porillo.skullwalls.commands;

import net.porillo.skullwalls.SkullWalls;
import net.porillo.skullwalls.walls.Wall;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.Set;

import static org.bukkit.ChatColor.*;

public class ListCommand extends BaseCommand {

    public ListCommand(SkullWalls plugin) {
        super(plugin);
        super.setName("list");
        super.addUsage(null, null, "Lists all SkullWalls");
        super.setPermission("skullwalls.list");
        super.setConsoleOnly(false);
    }

    public void runCommand(CommandSender sender, List<String> args) {
        if (args.size() == 0) {
            Set<Wall> wallsCopy = SkullWalls.getWallHandler().getReadOnlyWalls();
            Wall[] walls = wallsCopy.toArray(new Wall[wallsCopy.size()]);
            sender.sendMessage(BLUE + "Listing the info for " + wallsCopy.size() + " SkullWalls");
            for (int i = 0; i < walls.length; i++) {
                Wall w = walls[i];
                sender.sendMessage(BLUE + "[" + GOLD + (i + 1) + BLUE + "] Name: " + WHITE + w.getName() + GOLD
                        + " Type: " + WHITE + w.getType() + GOLD + " Bounds: " + WHITE + w.getStringBounds());
            }
        }
    }
}
