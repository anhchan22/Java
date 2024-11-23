public class Boat {
    private String name;
    private int size;
    private int hits;
    private String symbol;
    private boolean counted;
    public Boat(String name, int size, String symbol) {
        this.name = name;
        this.size = size;
        this.symbol = symbol;
        this.hits = 0;
    }
    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public String getSymbol() {
        return symbol;
    }

    public void hit() {
        hits++;
    }

    public boolean isSunk() {
        if(hits >= size) {
            return true;
        }
        return false;
    }

    public boolean isCounted() {
        return counted;
    }

    public void setCounted(boolean counted) {
        this.counted = counted;
    }
}

class PatrolBoat extends Boat {
    public PatrolBoat() { super("Patrol Boat", 2, "P"); }
}

class Destroyer extends Boat {
    public Destroyer() {
        super("Destroyer", 4, "D");
    }
}

class Submarine extends Boat {
    public Submarine() {
        super("Submarine", 3, "S");
    }
}

class Battleship extends Boat {
    public Battleship() {
        super("Battleship", 5, "B");
    }
}


