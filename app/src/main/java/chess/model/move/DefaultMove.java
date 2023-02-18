package chess.model.move;

import chess.model.board.Square;

public class DefaultMove implements Move {

    private Square source;
    private Square destination;

    public DefaultMove(Square source, Square destination){
        this.source = source;
        this.destination = destination;
    }

    @Override
    public void execute() {
        destination.setPiece(source.getPiece());
        source.removePiece();
    }

    @Override
    public void undo() {
        source.setPiece(destination.getPiece());
        destination.removePiece();
    }

    public Square getSource() {
        return source;
    }

    public Square getDestination() {
        return destination;
    }

}
