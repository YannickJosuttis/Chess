package chess.model.piece;

import java.util.List;

import chess.model.Position;
import chess.model.move.Move;
import chess.model.piece.properties.Color;
import chess.model.piece.properties.Type;

public interface IPiece {

    public Color getColor();

    public Type getType();

    public List<Move> getPseudoLegalMoves(Position position);

}
