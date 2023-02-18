package chess.model.move;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import chess.model.board.Square;

public class CompositeMove implements Move {
    
    private List<Move> moves;
    private List<Move> movesReversed;

    public CompositeMove(Move... moves){
        this.moves = Arrays.asList(moves);
        this.movesReversed = this.moves;
        Collections.reverse(movesReversed);
    }

    @Override
    public void execute() {
        moves.forEach(move -> move.execute());
    }

    @Override
    public void undo() {
        movesReversed.forEach(move -> move.undo());
    }

    @Override
    public Square getSource() {
        return moves.get(0).getSource();
    }

    @Override
    public Square getDestination() {
        return moves.get(0).getDestination();
    } 
}
