package edu.cmu.cs.stage3.alice.authoringtool.util;

import edu.cmu.cs.stage3.alice.authoringtool.AuthoringTool;
import edu.cmu.cs.stage3.alice.authoringtool.MainUndoRedoStack;
import edu.cmu.cs.stage3.alice.core.Scheduler;
import edu.cmu.cs.stage3.alice.core.property.Matrix44Property;
import edu.cmu.cs.stage3.alice.scenegraph.Camera;
import edu.cmu.cs.stage3.alice.scenegraph.Scene;
import edu.cmu.cs.stage3.alice.scenegraph.renderer.OnscreenRenderTarget;
import edu.cmu.cs.stage3.math.MathUtilities;
import edu.cmu.cs.stage3.math.Matrix44;
import edu.cmu.cs.stage3.math.Sphere;
import edu.cmu.cs.stage3.math.Vector3;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Iterator;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector4d;






public class RenderTargetOrbitManipulator
  extends RenderTargetPickManipulator
{
  protected edu.cmu.cs.stage3.alice.scenegraph.Transformable helper = new edu.cmu.cs.stage3.alice.scenegraph.Transformable();
  protected Camera sgCamera = null;
  protected edu.cmu.cs.stage3.alice.core.Transformable eCameraTransformable = null;
  protected edu.cmu.cs.stage3.alice.scenegraph.Transformable sgCameraTransformable = null;
  protected Scene sgScene = null;
  protected edu.cmu.cs.stage3.alice.scenegraph.Transformable sgIdentity = new edu.cmu.cs.stage3.alice.scenegraph.Transformable();
  protected Vector3d tempVec = new Vector3d();
  protected Vector4d tempVec4 = new Vector4d();
  protected Matrix44 oldTransformation;
  protected double orbitRotationFactor;
  protected double orbitZoomFactor;
  protected double sizeFactor;
  protected boolean clippingPlaneAdjustmentEnabled = false;
  
  protected UndoRedoStack undoRedoStack;
  
  protected Scheduler scheduler;
  private Configuration orbitConfig = Configuration.getLocalConfiguration(RenderTargetOrbitManipulator.class.getPackage());
  
  public RenderTargetOrbitManipulator(OnscreenRenderTarget renderTarget, UndoRedoStack undoRedoStack, Scheduler scheduler) {
    super(renderTarget);
    this.undoRedoStack = undoRedoStack;
    this.scheduler = scheduler;
    helper.setName("helper");
    configInit();
  }
  
  public void setClippingPlaneAdjustmentEnabled(boolean enabled) {
    clippingPlaneAdjustmentEnabled = enabled;
  }
  
  private void configInit() {
    if (orbitConfig.getValue("renderTargetOrbitManipulator.orbitRotationFactor") == null) {
      orbitConfig.setValue("renderTargetOrbitManipulator.orbitRotationFactor", Double.toString(0.02D));
    }
    if (orbitConfig.getValue("renderTargetOrbitManipulator.orbitZoomFactor") == null) {
      orbitConfig.setValue("renderTargetOrbitManipulator.orbitZoomFactor", Double.toString(0.05D));
    }
  }
  
  public void mousePressed(MouseEvent ev)
  {
    if (enabled) {
      super.mousePressed(ev);
      
      orbitRotationFactor = Double.parseDouble(orbitConfig.getValue("renderTargetOrbitManipulator.orbitRotationFactor"));
      orbitZoomFactor = Double.parseDouble(orbitConfig.getValue("renderTargetOrbitManipulator.orbitZoomFactor"));
      
      if ((sgPickedTransformable == null) && (objectsOfInterest.size() == 1)) {
        ePickedTransformable = ((edu.cmu.cs.stage3.alice.core.Transformable)objectsOfInterest.iterator().next());
        sgPickedTransformable = ePickedTransformable.getSceneGraphTransformable();
        AuthoringTool.getHack().getUndoRedoStack().setIsListening(false);
        mouseIsDown = true;
      }
      
      if (sgPickedTransformable != null) {
        sizeFactor = Math.max(0.1D, ePickedTransformable.getBoundingSphere().getRadius());
        sgCamera = renderTarget.getCameras()[0];
        sgCameraTransformable = ((edu.cmu.cs.stage3.alice.scenegraph.Transformable)sgCamera.getParent());
        eCameraTransformable = ((edu.cmu.cs.stage3.alice.core.Transformable)sgCameraTransformable.getBonus());
        sgScene = ((Scene)sgCamera.getRoot());
        
        oldTransformation = new Matrix44(sgCameraTransformable.getLocalTransformation());
        
        helper.setParent(sgScene);
        sgIdentity.setParent(sgScene);
      }
    }
  }
  
  public void mouseReleased(MouseEvent ev)
  {
    if ((mouseIsDown) && 
      (eCameraTransformable != null)) {
      undoRedoStack.push(new PointOfViewUndoableRedoable(eCameraTransformable, oldTransformation, new Matrix44(sgCameraTransformable.getLocalTransformation()), scheduler));
    }
    

    super.mouseReleased(ev);
  }
  
  public void mouseDragged(MouseEvent ev)
  {
    if (enabled) {
      super.mouseDragged(ev);
      
      if (mouseIsDown)
      {
        if (sgPickedTransformable != null)
        {

          if (clippingPlaneAdjustmentEnabled) {
            double objectRadius = ePickedTransformable.getBoundingSphere().getRadius();
            double objectDist = sgPickedTransformable.getPosition(sgCameraTransformable).getLength();
            double farDist = Math.max(objectDist * 3.0D, objectDist + objectRadius);
            double nearDist = Math.max((objectDist - objectRadius) * 0.01D, 1.0E-4D);
            
            sgCamera.setFarClippingPlaneDistance(farDist);
          }
          
          boolean controlDown = ev.isControlDown();
          boolean shiftDown = ev.isShiftDown();
          
          tempVec.x = 0.0D;
          tempVec.y = 0.0D;
          tempVec.z = 0.0D;
          helper.setPosition(tempVec, sgPickedTransformable);
          
          helper.pointAt(sgCameraTransformable, null, MathUtilities.getYAxis(), null);
          helper.standUp(sgScene);
          

          sgCameraTransformable.pointAt(helper, null, MathUtilities.getYAxis(), null);
          
          if ((!controlDown) || 
          






            (shiftDown)) {
            sgCameraTransformable.rotate(MathUtilities.getYAxis(), dx * orbitRotationFactor, helper);
            sgCameraTransformable.rotate(MathUtilities.getXAxis(), -dy * orbitRotationFactor, helper);
          } else {
            sgCameraTransformable.translate(MathUtilities.multiply(MathUtilities.getZAxis(), dy * orbitZoomFactor * sizeFactor), sgCameraTransformable);
            sgCameraTransformable.rotate(MathUtilities.getYAxis(), dx * orbitRotationFactor, helper);
          }
          
          if (eCameraTransformable != null) {
            eCameraTransformable.localTransformation.set(sgCameraTransformable.getLocalTransformation());
          }
        }
      }
    }
  }
}
