package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Clorus extends Creature {

    private int r;
    private int g;
    private int b;

    public Clorus(double e) {
        super("clorus"); // creature's name
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    public Clorus() {
        this(1);
    }

    private void energyLimit(double energy) {
        if (energy < 0) {
            this.energy = 0;
        }
    }

    public Color color() {
        g = 0;
        r = 34;
        b = 231;
        return color(r, g, b);
    }

    public void attack(Creature c) {
        energy = energy + c.energy();
    }

    public void move() {
        energy = energy - 0.03;
        // energyLimit(energy);
    }

    public void stay() {
        energy = energy - 0.01;
        // energyLimit(energy);
    }

    public Clorus replicate() {
        double tempEnergy = energy;
        energy = energy * 0.5;
        return new Clorus(tempEnergy * 0.5);
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();

        for (Map.Entry<Direction, Occupant> temp : neighbors.entrySet()) {
            Direction key = temp.getKey();
            Occupant value = temp.getValue();
            if (value.name().equals("empty")) {
                emptyNeighbors.add(key);
            }
            if (value.name().equals("plip")) {
                plipNeighbors.add(key);
            }
        }
        // Rule 1: If there are no empty squares, the Clorus will STAY
        // (even if there are Plips nearby they could attack since plip
        // squares do not count as empty squares).
        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        } else if (plipNeighbors.size() > 0) {
            // Rule 2: Otherwise, if any Plips are seen, the Clorus will ATTACK
            // one of them randomly.
            return new Action(Action.ActionType.ATTACK, randomEntry(plipNeighbors));
        } else if (energy >= 1) {
            // Rule 3: Otherwise, if the Clorus has energy greater than or equal to one,
            // it will REPLICATE to a random empty square.
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        } else {
            // Rule 4: Otherwise, the Clorus will MOVE to a random empty square.
        return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
        }
    }
}
