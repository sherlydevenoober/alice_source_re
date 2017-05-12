package edu.cmu.cs.stage3.alice.core.property;

import edu.cmu.cs.stage3.alice.core.Camera;

public class CameraProperty extends ModelProperty
{
  public CameraProperty(edu.cmu.cs.stage3.alice.core.Element owner, String name, Camera defaultValue) {
    super(owner, name, defaultValue, Camera.class);
  }
  
  public Camera getCameraValue() { return (Camera)getModelValue(); }
}
