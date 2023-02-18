package chess.model.move;

public class Castle extends CompositeMove{

    public Castle(Move kingMove, Move rookMove){
        super(kingMove, rookMove);
    }
    
}
