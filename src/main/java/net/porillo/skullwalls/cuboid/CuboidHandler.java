package net.porillo.skullwalls.cuboid;

import net.porillo.skullwalls.walls.Wall;
import net.porillo.skullwalls.walls.WallType;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CuboidHandler {

    private Map<String, Session> sessions = new HashMap<>();

    public void add(Player player, Block b) {
        if (this.sessions.containsKey(player.getName())) {
            Session s = this.sessions.get(player.getName());
            if (s.one == null) {
                s.setOne(b);
            } else {
                s.setTwo(b);
            }
        } else {
            Session ses = new Session();
            ses.setOne(b);
            this.sessions.put(player.getName(), ses);
        }
    }

    public void clear(Player player) {
        this.sessions.remove(player.getName());
    }

    public Wall createWall(Player player, WallType type, String name) {
        Wall wall = null;
        if (this.sessions.containsKey(player.getName())) {
            Session s = this.sessions.get(player.getName());
            if (s.isComplete()) {
                int[] b = {s.one.getX(), s.one.getY(), s.one.getZ(), s.two.getX(), s.two.getY(), s.two.getZ()};
                wall = new Wall(type, name, s.one.getWorld(), b);
            }

            clear(player);
        }
        return wall;
    }

    public int getStep(Player p) {
        if (this.sessions.containsKey(p.getName())) {
            Session s = this.sessions.get(p.getName());
            if (s.one == null && s.two == null)
                return 0;
            if (s.one != null && s.two == null)
                return 1;
            if (s.one != null)
                return 2;
        }
        return -1;
    }
}