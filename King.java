import javax.swing.JLabel;
import java.util.ArrayList;

public class King extends Piece
{
  public King(Side colorSide, GameManager givenGM)
  {
    super(colorSide, givenGM);
    image = new JLabel("King");
  
    if(colorSide == Side.WHITE)
      currentPos = new Position(4,0);
    else if(colorSide == Side.BLACK)
      currentPos = new Position(4,7);
      
    updateCanGoTo();
  }//constructor

  public void updateCanGoTo()
  {
    resetCanGoTo();
  
    for(int a = -1; a <= 1; a++)
      for(int b = -1; b <= 1; b++)
      {
        if(a == 0 && b == 0)
          continue;
        
        Position nextPos = new Position(currentPos.x + a, currentPos.y + b);
        
        if(gameManager.thereIsAllyPieceAt(nextPos,this))
          continue;

        canGoTo.add(nextPos);
      }//for
      
      Position towerPos = new Position(currentPos.x + 3, currentPos.y);
      Position swapTowerPos = new Position(currentPos.x + 2, currentPos.y);
      
      if(!hasMovedAlready && gameManager.getPieceAt(towerPos) instanceof Tower
          && !(gameManager.getPieceAt(towerPos).hasMovedAlready)
            && !(gameManager.thereIsPieceAt(new Position(currentPos.x + 1, currentPos.y)))
              && !(gameManager.thereIsPieceAt(swapTowerPos)))
        canGoTo.add(swapTowerPos);
              
  }//updateCanGoTo

  public String toString()
  {
    return super.toString() + " > King (" + side + ")";
  }//toString

}//class
