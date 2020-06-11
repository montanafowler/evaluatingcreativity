package creative.framework.main;

import creative.framework.model.Scene;
import creative.framework.novelty.Novelty;
import creative.framework.value.Value;

public class SceneJudge implements ArtifactJudge<Scene> {

    public SceneJudge() {
    }

    /**
     * Evaluates an artifact
     *
     * @param context
     * @param artifact
     * @return
     */
    @Override
    public String evaluateArtifact(ArtifactContext<Scene> context, Scene artifact) {
       
        StringBuilder result = new StringBuilder();
        Novelty<Scene> noveltyModel = context.getNoveltyModel();
        Value<Scene> valueModel = context.getValueModel();
        Double n_a = noveltyModel.getNovelty(artifact);
        Double v_a = valueModel.getValue(artifact);

//        result.append("\nnovelty: ").append(n_a);
//        result.append("\nvalue: ").append(v_a);
//        result.append("\nrdc: ").append(v_a + n_a - p(v_a, n_a));
        result.append(",").append(n_a);
        result.append(",").append(v_a);
        result.append(",").append(v_a + n_a - p(v_a, n_a));

        return result.toString();
    }

    /**
     * Penalty function
     *
     * @param v_a
     * @param n_a
     * @return
     */
    private double p(Double v_a, Double n_a) {
        Double s = v_a + n_a;
        Double d = Math.abs(v_a - n_a);
        return s * (1 - Math.exp(-1.0 * d));
    }

}