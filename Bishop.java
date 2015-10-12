import javax.swing.JLabel;
import java.util.ArrayList;

public class Bishop extends Piece
{
  public int n;

  public Bishop(Side colorSide, int number, GameManager givenGM)
  {
    super(colorSide, givenGM);
    n = number;
    image = new JLabel("Bishop");
  
    int x;
    if(number == 0) x = 2;
    else            x = 5;
    
    if(colorSide == Side.WHITE)
      currentPos = new Position(x,0);
    else if(colorSide == Side.BLACK)
      currentPos = new Position(x,7);
      
    updateCanGoTo();
  }//constructor

  public void updateCanGoTo()
  {
    resetCanGoTo();

    for(int i : new int[]{-1,1})
      for(int j : new int[]{-1,1})
      {
        for(int a = 1; a < 8; a++)
        {
          Position nextPos = new Position(currentPos.x + a * i, currentPos.y + a * j);
          
          if(gameManager.thereIsPieceAt(nextPos))
          {
            if(gameManager.thereIsEnemyPieceAt(nextPos,this))
              canGoTo.add(nextPos);  
            
            break;
          }//if
          
          canGoTo.add(nextPos);
        }//for
      }//for

  }//updateCanGoTo
  
  public String toString()
  {
    return super.toString() + " > Bishop " + n + " (" + side + ")";
  }//toString
  
}//class
