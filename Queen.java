import javax.swing.JLabel;
import java.util.ArrayList;

public class Queen extends Piece
{
  public Queen(Side colorSide, GameManager givenGM)
  {
    super(colorSide, givenGM);
    image = new JLabel("Queen");
  
    if(colorSide == Side.WHITE)
      currentPos = new Position(3,0);
    else if(colorSide == Side.BLACK)
      currentPos = new Position(3,7); 
      
    updateCanGoTo();     
  }//constructor

  public void updateCanGoTo()
  {
    resetCanGoTo();
  
    for(int i = -1; i <= 1; i++)
      for(int j = -1; j <= 1; j++)
      {
        if(i == 0 && j == 0)
          continue;
        
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
    return super.toString() + " > Queen (" + side + ")";
  }//toString

}//class
