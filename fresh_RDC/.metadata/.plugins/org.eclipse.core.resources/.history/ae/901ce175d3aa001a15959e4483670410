package creative.framework.value;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import creative.framework.model.Scene;
import creative.framework.model.SceneItem;
import creative.framework.model.SceneValue;
import edu.uci.ics.jung.algorithms.shortestpath.DijkstraShortestPath;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;

public class LabValue implements Value<Scene> {

    Map<SceneValue, List<SceneValue>> colorSynergy;
    Graph<SceneItem, Integer> graph;

    public LabValue(Map<SceneValue, List<SceneValue>> colorSynergy) {
        this.colorSynergy = colorSynergy;
    }
    
    

    @Override
    public Double getValue(Scene artifact) {
        // create new graph
        graph = new SparseMultigraph<>();
        // add vertex
        addVertex(artifact);
        // add edges
        addEdges(artifact);

        return 0.5*(kc()+p());
    }

    /**
     *
     * @param graph
     * @param artifact
     */
    private void addVertex(Scene artifact) {
        for (SceneItem item : artifact.getSceneItems()) {
            graph.addVertex(item);
        }
    }

    private void addEdges(Scene artifact) {
        Integer edges = 0;
        SceneValue uColor, vColor;

        for (SceneItem u : artifact.getSceneItems()) {
            uColor = u.getValue();
            for (SceneItem v : artifact.getSceneItems()) {
                vColor = v.getValue();
                if (isSynergic(uColor, vColor)) {
                    graph.addEdge(edges++, u, v);
                }

            }

        }
    }

    private Double kc() {
        DijkstraShortestPath<SceneItem, Integer> djk = new DijkstraShortestPath(graph);
        Collection<SceneItem> vertices = graph.getVertices();
        int vertexCount = graph.getVertexCount();
        int paths = 0;
        for (SceneItem v1 : vertices) {
            for (SceneItem v2 : vertices) {
                if (!v1.equals(v2)) {
                    if (djk.getDistance(v1, v2) != null) {
                        paths++;
                    }
                }
            }
        }
        return (paths) / (1.0 * vertexCount * (vertexCount - 1));
    }

    private Double p() {
        int e = graph.getEdgeCount();
        int n = graph.getVertexCount();
        return 1.0 * e / (n * (n - 1));
    }

    private boolean isSynergic(SceneValue uColor, SceneValue vColor) {
        return colorSynergy.get(vColor).contains(uColor);
    }

}