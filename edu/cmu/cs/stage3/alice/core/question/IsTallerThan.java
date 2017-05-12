package edu.cmu.cs.stage3.alice.core.question;

import edu.cmu.cs.stage3.alice.core.Transformable;




















public class IsTallerThan
  extends SubjectObjectQuestion
{
  public IsTallerThan() {}
  
  private static Class[] s_supportedCoercionClasses = { IsShorterThan.class };
  
  public Class[] getSupportedCoercionClasses() {
    return s_supportedCoercionClasses;
  }
  
  public Class getValueClass() {
    return Boolean.class;
  }
  
  protected Object getValue(Transformable subjectValue, Transformable objectValue) {
    if (subjectValue.getHeight() > objectValue.getHeight()) {
      return Boolean.TRUE;
    }
    return Boolean.FALSE;
  }
}
