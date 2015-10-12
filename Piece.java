import javax.swing.JLabel;
import java.util.ArrayList;

public class Piece
{
  public Piece(Side colorSide, GameManager GM)
  {
    gameManager = GM;
    side = colorSide;
  }//constructor

  public boolean dead = false;
  public boolean hasMovedAlready = false;
  public JLabel image = new JLabel("Piece");
  public enum Side { WHITE, BLACK };
  public Position currentPos = null;
  public ArrayList<Position> canGoTo = new ArrayList<Position>();
  public Side side;
  
  public GameManager gameManager;
  
  public void updateCanGoTo(){};
  
  public void moveTo(Position posToMove)
  {
    if(gameManager.thereIsEnemyPieceAt(posToMove,this))
      gameManager.getPieceAt(posToMove).die();
    
    currentPos = posToMove;
    resetCanGoTo();
    updateCanGoTo();
    hasMovedAlready = true;
  }//moveTo

  public void die()
  {
    dead = true;
    currentPos = new Position(-1,-1);

    if(this instanceof King)
      gameManager.checkMate();
  }//die
  
  public void resetCanGoTo()
  {
    canGoTo = new ArrayList<Position>();
  }//resetCanGoTo

  public String toString()
  {
    return "Piece";
  }//toString
  
}//class
