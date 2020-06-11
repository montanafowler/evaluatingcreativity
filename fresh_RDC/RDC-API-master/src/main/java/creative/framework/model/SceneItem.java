package creative.framework.model;

public class SceneItem {
	Type type;
	SceneValue value;
	
	public SceneItem(Type type, SceneValue value) {
	    this.type = type;
	    this.value = value;
	}
	
	public Type getType() {
	    return type;
	}
	
	public void setType(Type type) {
	    this.type = type;
	}
	
	public SceneValue getValue() {
	    return this.value;
	}
	
	public void setValue(SceneValue value) {
	    this.value = value;
	}
}
