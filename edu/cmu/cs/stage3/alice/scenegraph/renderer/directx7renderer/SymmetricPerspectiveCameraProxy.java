package edu.cmu.cs.stage3.alice.scenegraph.renderer.directx7renderer;

import edu.cmu.cs.stage3.alice.scenegraph.renderer.nativerenderer.BackgroundProxy;
import edu.cmu.cs.stage3.alice.scenegraph.renderer.nativerenderer.SceneProxy;
import javax.vecmath.Matrix4d;

class SymmetricPerspectiveCameraProxy
  extends edu.cmu.cs.stage3.alice.scenegraph.renderer.nativerenderer.SymmetricPerspectiveCameraProxy
{
  SymmetricPerspectiveCameraProxy() {}
  
  protected native void createNativeInstance();
  
  protected native void releaseNativeInstance();
  
  protected native void onAbsoluteTransformationChange(Matrix4d paramMatrix4d);
  
  protected native void addToScene(SceneProxy paramSceneProxy);
  
  protected native void removeFromScene(SceneProxy paramSceneProxy);
  
  protected native void onNearClippingPlaneDistanceChange(double paramDouble);
  
  protected native void onFarClippingPlaneDistanceChange(double paramDouble);
  
  protected native void onBackgroundChange(BackgroundProxy paramBackgroundProxy);
  
  protected native void onVerticalViewingAngleChange(double paramDouble);
  
  protected native void onHorizontalViewingAngleChange(double paramDouble);
}
