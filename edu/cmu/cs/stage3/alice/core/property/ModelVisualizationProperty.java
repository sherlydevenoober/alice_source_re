package edu.cmu.cs.stage3.alice.core.property;

import edu.cmu.cs.stage3.alice.core.visualization.ModelVisualization;

public class ModelVisualizationProperty extends VisualizationProperty
{
  public ModelVisualizationProperty(edu.cmu.cs.stage3.alice.core.Element owner, String name, ModelVisualization defaultValue) {
    super(owner, name, defaultValue, ModelVisualization.class);
  }
  
  public ModelVisualization getModelVisualizationValue() { return (ModelVisualization)getVisualizationValue(); }
}
