import javax.swing.JLabel;
import java.util.ArrayList;

public class Tower extends Piece
{
  public int n;

  public Tower(Side colorSide, int number, GameManager givenGM)
  {
    super(colorSide, givenGM);
    n = number;
    image = new JLabel("Tower");
      
    int x;
    if(number == 0) x = 0;
    else            x = 7;
    
    if(colorSide == Side.WHITE)
      currentPos = new Position(x,0);
    else if(colorSide == Side.BLACK)
      currentPos = new Position(x,7);
      
    updateCanGoTo();    
  }//constructor

  public void updateCanGoTo()
  {
    resetCanGoTo();
 
    for(int i = 0; i <= 1; i++)
      for(int j = -1; j <= 1; j++)
        for(int a = 1; a < 8; a++)
        {
          Position nextPos = new Position(currentPos.x + a * j * i, currentPos.y + a * j * not(i));

          if(gameManager.thereIsPieceAt(nextPos))
          {
            if(gameManager.thereIsEnemyPieceAt(nextPos,this))
              canGoTo.add(nextPos);  
            
            break;
          }//if

          canGoTo.add(nextPos);
        }//for   
  }//updateCanGoTo

  private int not(int a)
  {
    if(a == 1)
      return 0;
    else if(a == 0)
      return 1;
    else
      return -1;      
  }//not

  public String toString()
  {
    return super.toString() + " > Tower " + n + " (" + side + ")";
  }//toString

}//class
