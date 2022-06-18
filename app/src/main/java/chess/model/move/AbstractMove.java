package chess.model.move;

import chess.model.Square;

/**
 * AbstractMove
 */
public abstract class AbstractMove {

    protected Square source;
    protected Square destination;

    public AbstractMove(Square source, Square destination) {
        this.source = source;
        this.destination = destination;
    }

}