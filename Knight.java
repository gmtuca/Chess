import javax.swing.JLabel;
import java.util.ArrayList;

public class Knight extends Piece
{
  public int n;

  public Knight(Side colorSide, int number, GameManager givenGM)
  {
    super(colorSide, givenGM);
    image = new JLabel("Knight");
    
    int x;
    if(number == 0) x = 1;
    else            x = 6;
    
    if(colorSide == Side.WHITE)
      currentPos = new Position(x,0);
    else if(colorSide == Side.BLACK)
      currentPos = new Position(x,7);
      
    updateCanGoTo();
  }//constructor
  
  public void updateCanGoTo()
  {
    resetCanGoTo();
  
    for(int x = -2; x <= 2; x++)
      for(int y = -2; y <= 2; y++)
      {
        if(x == y || x == -y || x == 0 || y == 0)
          continue;
          
        Position nextPos = new Position(currentPos.x + x, currentPos.y + y);
        
        if(gameManager.thereIsAllyPieceAt(nextPos,this))
          continue;
        
        canGoTo.add(nextPos);
      }//for
  }//updateCanGoTo

  public String toString()
  {
    return super.toString() + " > Knight " + n + " (" + side + ")";
  }//toString

}//class
