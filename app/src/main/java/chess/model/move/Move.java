package chess.model.move;

import chess.model.board.Square;

public abstract class Move {

    protected Square source, destination;

    public Move(Square source, Square destination) {
        this.source = source;
        this.destination = destination;
    }

    public abstract void execute();
    
    public abstract void undo();

}

