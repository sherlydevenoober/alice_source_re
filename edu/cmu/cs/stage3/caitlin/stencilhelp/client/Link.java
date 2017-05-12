package edu.cmu.cs.stage3.caitlin.stencilhelp.client;

import edu.cmu.cs.stage3.lang.Messages;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D.Double;
import java.util.Vector;

public class Link implements StencilObject, MouseEventListener
{
  protected StencilManager stencilManager = null;
  protected ObjectPositionManager posManager = null;
  
  protected boolean next = false;
  protected String message = null;
  protected RoundRectangle2D.Double underLink;
  protected RoundRectangle2D.Double bgLink;
  protected Shape messageShape = null;
  private Font font = new Font("Arial", 1, 16);
  protected boolean isModified = true;
  
  protected Vector shapes = null;
  
  public Link(StencilManager stencilManager, ObjectPositionManager posManager, boolean next) {
    this.stencilManager = stencilManager;
    this.posManager = posManager;
    this.next = next;
    
    shapes = new Vector();
    
    if (next)
      message = Messages.getString("Load_Next_Chapter"); else {
      message = Messages.getString("Reload_Last_Chapter");
    }
    createShapes();
  }
  
  protected void createShapes() {
    TextLayout wordLayout = new TextLayout(message, font, new java.awt.font.FontRenderContext(null, false, false));
    AffineTransform textAt = new AffineTransform();
    int xStart = 10;
    int yStart = 10;
    int width = (int)wordLayout.getBounds().getWidth();
    if (next) {
      xStart = (int)posManager.getScreenWidth() - width - 50;
      yStart = (int)posManager.getScreenHeight() - 80;
    }
    textAt.translate(xStart + 5, yStart + 18);
    
    messageShape = wordLayout.getOutline(textAt);
    underLink = new RoundRectangle2D.Double(xStart + 2, yStart - 2, width + 10, 25.0D, 10.0D, 10.0D);
    bgLink = new RoundRectangle2D.Double(xStart, yStart, width + 10, 25.0D, 10.0D, 10.0D);
    
    ScreenShape shape = new ScreenShape(new Color(255, 200, 240, 100), underLink, true, 1);
    shapes.addElement(shape);
    shape = new ScreenShape(new Color(255, 180, 210, 150), bgLink, true, 2);
    shapes.addElement(shape);
    shape = new ScreenShape(new Color(0, 0, 180), messageShape, true, 0);
    shapes.addElement(shape);
  }
  
  public Vector getShapes() { return shapes; }
  
  public Rectangle getRectangle()
  {
    return bgLink.getBounds();
  }
  
  public Rectangle getPreviousRectangle() { return bgLink.getBounds(); }
  
  public boolean isModified() {
    if (isModified) {
      isModified = false;
      return true;
    }
    return false;
  }
  
  public boolean intersectsRectangle(Rectangle rect) { return bgLink.getBounds().intersects(rect); }
  

  public void addStencilObjectPositionListener(StencilObjectPositionListener posListener) {}
  
  public void removeStencilObjectPositionListener(StencilObjectPositionListener posListener) {}
  
  public String getComponentID()
  {
    return null;
  }
  
  public boolean contains(Point point)
  {
    return bgLink.contains(point.getX(), point.getY());
  }
  
  public boolean mousePressed(MouseEvent e) {
    return false;
  }
  
  public boolean mouseReleased(MouseEvent e) { return false; }
  
  public boolean mouseClicked(MouseEvent e) {
    if (next)
      stencilManager.showNextStack(); else
      stencilManager.showPreviousStack();
    return false;
  }
  
  public boolean mouseEntered(MouseEvent e) { return false; }
  
  public boolean mouseExited(MouseEvent e) {
    return false;
  }
  
  public boolean mouseMoved(MouseEvent e)
  {
    return false;
  }
  
  public boolean mouseDragged(MouseEvent e) { return false; }
}
