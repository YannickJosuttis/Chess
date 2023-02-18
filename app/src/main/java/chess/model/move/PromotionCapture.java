package chess.model.move;

public class PromotionCapture extends CompositeMove {
    
    public PromotionCapture(Promotion promotion, Capture capture) {
        super(capture, promotion); 
    }

}
