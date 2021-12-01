package net.alfiesmith.aoc15.day.seven;

import java.util.HashMap;
import java.util.Map;
import net.alfiesmith.aoc15.day.Day;

public final class DaySeven extends Day {

  public DaySeven() {
    super(7);
  }

  @Override
  public Object partOne(String input) {
    Map<String, Gate> gates = new HashMap<>();

    for (String line : input.split("\n")) {
      String[] splitLine = line.split(" -> ");
      line = splitLine[0];
      Gate gate = new Gate(splitLine[1]);

      if (line.contains("AND")) {
        String[] split = line.split(" AND ");
        gate.a = split[0];
        gate.b = split[1];
        gate.type = Type.AND;
      } else if (line.contains("OR")) {
        String[] split = line.split(" OR ");
        gate.a = split[0];
        gate.b = split[1];
        gate.type = Type.OR;
      } else if (line.contains("NOT")) {
        line = line.substring(4);
        gate.a = line;
        gate.type = Type.NOT;
      } else if (line.contains("LSHIFT")) {
        String[] split = line.split(" LSHIFT ");
        gate.a = split[0];
        gate.value = (char) Integer.parseInt(split[1]);
        gate.type = Type.LSHIFT;
      } else if (line.contains("RSHIFT")) {
        String[] split = line.split(" RSHIFT ");
        gate.a = split[0];
        gate.value = (char) Integer.parseInt(split[1]);
        gate.type = Type.RSHIFT;
      } else {
        try {
          gate.value = (char) Integer.parseInt(line);
          gate.type = Type.ASSIGN;
        } catch (NumberFormatException err) {
          gate.a = line;
          gate.type = Type.ASSIGN;
        }

      }


      gates.put(gate.id, gate);
    }

    return (int) gates.get("a").getValue(gates);
  }

  @Override
  public Object partTwo(String input) {
    Map<String, Gate> gates = new HashMap<>();

    for (String line : input.split("\n")) {
      String[] splitLine = line.split(" -> ");
      line = splitLine[0];
      Gate gate = new Gate(splitLine[1]);

      if (line.contains("AND")) {
        String[] split = line.split(" AND ");
        gate.a = split[0];
        gate.b = split[1];
        gate.type = Type.AND;
      } else if (line.contains("OR")) {
        String[] split = line.split(" OR ");
        gate.a = split[0];
        gate.b = split[1];
        gate.type = Type.OR;
      } else if (line.contains("NOT")) {
        line = line.substring(4);
        gate.a = line;
        gate.type = Type.NOT;
      } else if (line.contains("LSHIFT")) {
        String[] split = line.split(" LSHIFT ");
        gate.a = split[0];
        gate.value = (char) Integer.parseInt(split[1]);
        gate.type = Type.LSHIFT;
      } else if (line.contains("RSHIFT")) {
        String[] split = line.split(" RSHIFT ");
        gate.a = split[0];
        gate.value = (char) Integer.parseInt(split[1]);
        gate.type = Type.RSHIFT;
      } else {
        try {
          gate.value = (char) Integer.parseInt(line);
          gate.type = Type.ASSIGN;
        } catch (NumberFormatException err) {
          gate.a = line;
          gate.type = Type.ASSIGN;
        }

      }


      gates.put(gate.id, gate);
    }

    char value = gates.get("a").getValue(gates);
    for (Gate gate : gates.values()) {
      gate.knownSignal = Integer.MIN_VALUE;
    }
    gates.get("b").knownSignal = value;

    return (int) gates.get("a").getValue(gates);
  }

  enum Type {
    AND,
    OR,
    NOT,
    LSHIFT,
    RSHIFT,
    ASSIGN;
  }

  static class Gate {

    public String id;

    public Gate(String id) {
      this.id = id;
    }

    public String a;
    public String b;
    public char value;
    public Type type;
    int knownSignal = Integer.MIN_VALUE;

    public char getValue(Map<String, Gate> gates) {
      if (this.knownSignal != Integer.MIN_VALUE) {
        return (char) this.knownSignal;
      }

      switch (type) {
        case ASSIGN -> {
          if (a == null) {
            this.knownSignal = value;
          } else {
            this.knownSignal = getGateValue(a, gates);
          }
          return (char) knownSignal;
        }
        case AND -> {
          this.knownSignal = (getGateValue(a, gates) & getGateValue(b, gates));
          return (char) knownSignal;
        }
        case OR -> {
          this.knownSignal = (getGateValue(a, gates) | getGateValue(b, gates));
          return (char) knownSignal;
        }
        case NOT -> {
          this.knownSignal = ~getGateValue(a, gates);
          return (char) this.knownSignal;
        }
        case LSHIFT -> {
          this.knownSignal = (getGateValue(a, gates) << this.value);
          return (char) this.knownSignal;
        }
        case RSHIFT -> {
          this.knownSignal = (char) (getGateValue(a, gates) >> this.value);
          return (char) this.knownSignal;
        }
      }


      return 0;
    }

    private char getGateValue(String gateCode, Map<String, Gate> gates) {
      Gate gate = gates.get(gateCode);
      if (gate == null) {
        return (char) Integer.parseInt(gateCode);
      } else {
        return gate.getValue(gates);
      }
    }
  }

}
