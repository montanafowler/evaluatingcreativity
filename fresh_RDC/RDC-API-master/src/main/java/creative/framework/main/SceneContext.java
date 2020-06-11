package creative.framework.main;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import creative.framework.data.Dataset;
import creative.framework.model.Scene;
import creative.framework.model.SceneValue;
import creative.framework.novelty.SceneBayesianSurprise;
import creative.framework.novelty.Novelty;
import creative.framework.parser.Lab7Parser;
import creative.framework.parser.Lab8Parser;
import creative.framework.parser.Parser;
import creative.framework.util.Utils;
import creative.framework.value.LabValue;
import creative.framework.value.Value;

public class SceneContext implements ArtifactContext<Scene> {

    Dataset dataset;
    Parser parser;
    Double lambda;
    Map<SceneValue, List<SceneValue>> colorSynergy;
    Utils utils;

    public SceneContext(String datasetFileDescription, String synergyFileDescription, boolean lab7) {
        this.utils = new Utils();
        if(lab7)
        	this.parser = new Lab7Parser();
        else
        	this.parser = new Lab8Parser();
        this.dataset = getApparelDataset(datasetFileDescription);
        this.colorSynergy = getApparelSynergy(synergyFileDescription);
        this.lambda = 0.1;

    }

    @Override
    public Novelty<Scene> getNoveltyModel() {
        return new SceneBayesianSurprise(
                dataset.getMeans(),
                dataset.getVariances(),
                lambda,
                parser.attributeCount(),
                parser);
    }

    @Override
    public Value<Scene> getValueModel() {
        return new LabValue(colorSynergy);
    }

    private Map<SceneValue, List<SceneValue>> getApparelSynergy(String synergyFileDescription) {
        Gson gson = new Gson();

        Type synergyType = new TypeToken< HashMap<SceneValue, List<SceneValue>>>() {
        }.getType();
        return gson.fromJson(utils.getReader(synergyFileDescription), synergyType);

    }

    private Dataset getApparelDataset(String datasetFileDescription) {

        Type artifactType = new TypeToken<List<Scene>>() {
        }.getType();

        Gson gson = new Gson();
        
        List<Scene> existingArtifacts = gson.fromJson(utils.getReader(datasetFileDescription), artifactType);

        Integer attributeCount = parser.attributeCount();

        Dataset dataset = new Dataset(attributeCount);
        int i = 0;
        for (Scene apparel : existingArtifacts) {
        	i++;
            dataset.addInstance(parser.getInstance(apparel));
        }
        return dataset;
    }

    @Override
    public String toString() {
        StringBuilder context = new StringBuilder();
        context.append(dataset.toString());
        context.append("\nAverages:\n").append(meansToString(dataset.getMeans()));
        context.append("\nVariances:\n").append(variancesToString(dataset.getVariances()));
        return context.toString();
    }

    private StringBuilder meansToString(List<Mean> means) {
        StringBuilder m = new StringBuilder();
        NumberFormat formatter = new DecimalFormat("#0.00");

        for (Mean mean : means) {
            m.append(formatter.format(mean.getResult())).append(" ");
        }
        return m;
    }

    private StringBuilder variancesToString(List<Variance> variances) {
        StringBuilder v = new StringBuilder();
        NumberFormat formatter = new DecimalFormat("#0.00");
        for (Variance variance : variances) {
            v.append(formatter.format(variance.getResult())).append(" ");
        }
        return v;
    }

}
