package edu.cmu.cs.stage3.alice.core.property;

import edu.cmu.cs.stage3.alice.core.Set;

public class SetProperty extends CollectionProperty
{
  public SetProperty(edu.cmu.cs.stage3.alice.core.Element owner, String name, Set defaultValue) {
    super(owner, name, defaultValue, Set.class);
  }
  
  public Set getSetValue() { return (Set)getCollectionValue(); }
}
