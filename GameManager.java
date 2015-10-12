import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GameManager
{
  ArrayList<Piece> piecesOnField;
  ChessGUI chessGUI;
  
  Piece.Side sideTurn = Piece.Side.WHITE; 

  public GameManager() throws Exception
  {
    initialize();
    
    for(int y = 0; y < 8; y++)
      for(int x = 0; x < 8; x++)
      {
        final int xx = x, yy = y;
        chessGUI.grid[y][x].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(MouseEvent evt)
                {
                  if(chessGUI.posIsIlluminated(new Position(xx,yy)))
                  {
                    Position posToMove = new Position(xx,yy);
       /*             
                    //Weird swap of King/Tower
                    if(clickedPiece instanceof King && King.currentPos.x == posToMove.x - 2)
                    {
                      Position posToMoveTower = new Position(posToMove.x - 1, posToMove.y);
                      Piece tower = getPieceAt(new Position(posToMove.x + 1, posToMove.y));
                      
                      chessGUI.setBlank(tower.currentPos);
                      clickedPiece.moveTo(posToMoveTower);
                      chessGUI.setPiece(tower,posToMoveTower);
                    }//if
         */           
                    chessGUI.setBlank(clickedPiece.currentPos);
                    clickedPiece.moveTo(posToMove);
                                        
                    for(Piece piece : piecesOnField)
                      piece.updateCanGoTo();

                    chessGUI.setPiece(clickedPiece,posToMove);
                    chessGUI.resetColors();
                      
                    changeTurn();
                  }//if              
                  else
                  {
                    Piece pieceClickedToMove = getPieceAt(new Position(xx,yy));
                    
                    if(pieceClickedToMove != null && sideTurn == pieceClickedToMove.side)
                      illuminateMovement(pieceClickedToMove);
                    else if(pieceClickedToMove == null)
                      illuminateMovement(null);
                  }//else
                }//mouseClicked
            });
      }//for
    }//constructor 

  public boolean thereIsEnemyKingAt(Position pos, Piece piece)
  {
    if(thereIsPieceAt(pos) && getPieceAt(pos) instanceof King && getPieceAt(pos).side == piece.side)
      return true;
    
    return false;
  }//thereIsKingAt

  public void checkMate()
  {
    System.out.println("CHECK MATE");
    System.exit(0);  
  }//start
  
  public void check()
  {
    System.out.println("CHECK");
  }//check

  public void initialize() throws Exception
  {
    chessGUI = new ChessGUI();
    chessGUI.setVisible(true);
    
    piecesOnField = new ArrayList<Piece>();
        
    for(Piece.Side side : Piece.Side.values())
    {
      add(new Queen(side,this));
      add(new King(side,this));
    
      for(int n = 0; n <= 1; n++)
      {
       add(new Tower (side,n,this));
       add(new Knight(side,n,this));
       add(new Bishop(side,n,this));
      }//for

      for(int n = 0; n < 8; n++)
        add(new Peon(side,n,this));
    }//for
    
    for(Piece piece : piecesOnField)
      piece.updateCanGoTo();
    
  }//initialize

  public void add(Piece piece)
  {
    chessGUI.setPiece(piece,piece.currentPos);
    
    piecesOnField.add(piece);
  }//add

  public Piece getPieceAt(Position pos)
  {
    for(Piece piece : piecesOnField)
      if(piece.currentPos.x == pos.x && piece.currentPos.y == pos.y)
        return piece;
        
    return null;
  }//getPieceAt

  public boolean thereIsPieceAt(Position pos)
  {
    if(getPieceAt(pos) == null)
      return false;
    
    return getPieceAt(pos) instanceof Piece;
  }//thereIsPieceAt
  
  public boolean thereIsEnemyPieceAt(Position pos, Piece myPiece)
  {
    if(!thereIsPieceAt(pos) || getPieceAt(pos) == null || myPiece == null)
      return false;
    
    return getPieceAt(pos).side != (myPiece.side);
  }//thereIsEnemyPieceAt
  
  public boolean thereIsAllyPieceAt(Position pos, Piece myPiece)
  {
    if(!thereIsPieceAt(pos))
      return false;
    
    return getPieceAt(pos).side == myPiece.side;
  }//thereIsEnemyPieceAt
  
  public Piece clickedPiece = null;
  
  public void illuminateMovement(Piece piece)
  {
    chessGUI.resetColors();
    clickedPiece = piece;
        
    if(piece == null)
      return;    
    
    chessGUI.showClicked(piece.currentPos);
    
    for(Position pos : piece.canGoTo)
    {
      if(thereIsEnemyPieceAt(pos,piece))
        chessGUI.showKillable(pos);
      else       
        chessGUI.showMovable(pos);
    }//for
  }//illuminateMovement
  
  public void changeTurn()
  {
    if(sideTurn == Piece.Side.WHITE)
      sideTurn = Piece.Side.BLACK;
    else
      sideTurn = Piece.Side.WHITE;
  }//changeTurn
    
}//class

