/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creative.framework.main;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import creative.framework.data.Dataset;
import creative.framework.model.Apparel;
import creative.framework.model.Color;
import creative.framework.novelty.BayesianSurprise;
import creative.framework.novelty.Novelty;
import creative.framework.parser.ApparelParser;
import creative.framework.parser.Parser;
import creative.framework.util.Utils;
import creative.framework.value.SynergyValue;
import creative.framework.value.Value;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

/**
 *
 * @author creapar team
 *
 */
public class ApparelContext implements ArtifactContext<Apparel> {

    Dataset dataset;
    Parser parser;
    Double lambda;
    Map<Color, List<Color>> colorSynergy;
    Utils utils;

    public ApparelContext(String datasetFileDescription, String synergyFileDescription) {
        this.utils = new Utils();
        this.parser = new ApparelParser();
        this.dataset = getApparelDataset(datasetFileDescription);
        this.colorSynergy = getApparelSynergy(synergyFileDescription);
        this.lambda = 0.1;

    }

    @Override
    public Novelty<Apparel> getNoveltyModel() {
        return new BayesianSurprise(
                dataset.getMeans(),
                dataset.getVariances(),
                lambda,
                parser.attributeCount(),
                parser);
    }

    @Override
    public Value<Apparel> getValueModel() {
        return new SynergyValue(colorSynergy);
    }

    private Map<Color, List<Color>> getApparelSynergy(String synergyFileDescription) {
        Gson gson = new Gson();

        Type synergyType = new TypeToken< HashMap<Color, List<Color>>>() {
        }.getType();
        return gson.fromJson(utils.getReader(synergyFileDescription), synergyType);

    }

    private Dataset getApparelDataset(String datasetFileDescription) {

        Type artifactType = new TypeToken<List<Apparel>>() {
        }.getType();

        Gson gson = new Gson();

        List<Apparel> existingArtifacts = gson.fromJson(utils.getReader(datasetFileDescription), artifactType);

        Integer attributeCount = parser.attributeCount();

        Dataset dataset = new Dataset(attributeCount);

        for (Apparel apparel : existingArtifacts) {
        	System.out.println(apparel);
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
