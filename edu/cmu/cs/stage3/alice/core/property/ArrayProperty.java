package edu.cmu.cs.stage3.alice.core.property;

import edu.cmu.cs.stage3.alice.core.Array;

public class ArrayProperty extends CollectionProperty
{
  public ArrayProperty(edu.cmu.cs.stage3.alice.core.Element owner, String name, Array defaultValue) {
    super(owner, name, defaultValue, Array.class);
  }
  
  public Array getArrayValue() { return (Array)getCollectionValue(); }
}
