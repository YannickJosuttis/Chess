package chess.model.move;

import chess.model.board.Square;

public class DefaultMove extends Move {

    public DefaultMove(Square source, Square destination) {
        super(source, destination);
    }

    @Override
    public void execute() {
        if (source.isEmpty()) {
            throw new IllegalStateException("Source must not be empty!");
        }
        if (destination.isOccupied()) {
            throw new IllegalStateException("Destination must not be occupied!");
        }
        destination.setPiece(source.getPiece());
        source.removePiece();
    }

	@Override
	public void undo() {
		source.setPiece(destination.getPiece());
        destination.removePiece();
	}

}
