package creative.framework.model;

import java.util.List;
import java.util.ArrayList;

public class Scene extends Artifact {
	List<SceneItem> sceneItems;

    public Scene() {
    	//scene = new ArrayList<SceneItem>();
    }

    public Scene(List<SceneItem> sceneItems) {
        this.sceneItems = sceneItems;
    }

    public List<SceneItem> getSceneItems() {
        return sceneItems;
    }

    public void setSceneItems(List<SceneItem> sceneItems) {
        this.sceneItems = sceneItems;
    }

    @Override
    public String toString() {
        StringBuilder apparel = new StringBuilder();
        apparel.append("\nApparel Items:\n");
       // System.out.println("SCENE " + sceneItems);
        for (SceneItem item : sceneItems) {
            apparel.append(item.getType()).append(":").append(item.getValue()).append("\n");
        }
        return apparel.toString();
    }
}