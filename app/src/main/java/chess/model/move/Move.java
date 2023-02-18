package chess.model.move;

import chess.model.board.Square;

public interface Move {

    public void execute();

    public void undo();

    public Square getSource();

    public Square getDestination();
    
}
