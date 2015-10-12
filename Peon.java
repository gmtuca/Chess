import javax.swing.JLabel;
import java.util.ArrayList;

public class Peon extends Piece
{
  public int n;

  public Peon(Side colorSide, int number, GameManager givenGM)
  {
    super(colorSide, givenGM);
    n = number;
    image = new JLabel("Peon");
  
    if(colorSide == Side.WHITE)
      currentPos = new Position(number,1);
    else if(colorSide == Side.BLACK)
      currentPos = new Position(number,6);

    updateCanGoTo();      
  }//constructor

  public void updateCanGoTo()
  {
    resetCanGoTo();

    Position nextPos = new Position(currentPos.x, currentPos.y + (side == Side.WHITE ? 1 : -1 ));
    
    if(!gameManager.thereIsPieceAt(nextPos))
      canGoTo.add(nextPos);
    
    if(!hasMovedAlready)
    {
      Position nextnextPos = nextPos.plus(0,(side == Side.WHITE ? 1 : -1 ));
      
      if(!gameManager.thereIsPieceAt(nextPos))
        canGoTo.add(nextnextPos);
    }//if
    
    Position killPos1 = new Position(currentPos.x - 1, currentPos.y + (side == Side.WHITE ? 1 : -1 ));
    Position killPos2 = new Position(currentPos.x + 1, currentPos.y + (side == Side.WHITE ? 1 : -1 ));
    
    if(gameManager.thereIsEnemyPieceAt(killPos1,this))
      canGoTo.add(killPos1);
    if(gameManager.thereIsEnemyPieceAt(killPos2,this))
      canGoTo.add(killPos2);
    
  }//updateCanGoTo

  public String toString()
  {
    return super.toString() + " > Peon " + n + " (" + side + ")";
  }//toString

}//class
