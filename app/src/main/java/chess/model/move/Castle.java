package chess.model.move;

import chess.model.Square;

public class Castle extends Move {

    private final Square rookSource;
    private final Square rookDestination;

    public Castle(Square source, Square destination, Square rookSource, Square rookDestination) {
        super(source, destination);
        this.rookSource = rookSource;
        this.rookDestination = rookDestination;
    }

    public Square getRookSource() {
        return rookSource;
    }

    public Square getRookDestination() {
        return rookDestination;
    }

}
