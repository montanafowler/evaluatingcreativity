package creative.framework.novelty;

import creative.framework.data.Instance;
import creative.framework.model.Apparel;
import creative.framework.parser.Parser;
import java.util.List;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

/**
 *
* @author creapar team
 */
public class BayesianSurprise implements Novelty<Apparel> {

    List<Mean> means;
    List<Variance> variances;
    Double lambda;
    Integer inputAttributes;
    Parser parser;

    public BayesianSurprise(
            List<Mean> means,
            List<Variance> variances,
            Double lambda,
            Integer inputAttributes,
            Parser parser) {
        this.means = means;
        this.variances = variances;
        this.lambda = lambda;
        this.inputAttributes = inputAttributes;
        this.parser = parser;
    }

    @Override
    public Double getNovelty(Apparel artifact) {
        Instance instance = parser.getInstance(artifact);
        double surprise = 0.0;
        Double varianceResult;
        Variance variance;
        Mean mean;
        Double meanResult;
        Double d;
        List<Double> data = instance.getAttributes();
        for (int i = 0; i < inputAttributes; i++) {
            d = data.get(i);
            mean = means.get(i);
            meanResult = mean.getResult();
            mean.increment(d);

            variance = variances.get(i);
            varianceResult = variance.getResult();
            variance.increment(d);

            surprise += surprise(d, varianceResult, meanResult);

        }
        return 1.0 - Math.exp(-lambda * surprise);
    }

    /**
     * Calculate the surprise amount of a new given data which factor n is equal
     * to 1.
     *
     * @param data
     * @param variance
     * @param average
     * @return
     */
    public double surprise(double data, double variance, double average) {
        if (variance == 0) {
            return 1.0;
        }
        double a = 1.0 / (2 * variance);
        double b = Math.pow(data - average, 2);
        return a * (variance + b);
    }

    /**
     * Calculate the surprise amount of a new given data
     *
     * @param data
     * @param n
     * @param variance
     * @param average
     * @return
     */
    public double surprise(double data, double n, double variance, double average) {
        if (variance == 0) {
            return n;
        }
        double a = n / (2 * variance);
        double b = Math.pow(data - average, 2);
        return a * (variance + b);
    }

    @Override
    public void updateNovelty(Apparel artifact) {
        Instance instance = parser.getInstance(artifact);
        Double d;
        List<Double> data = instance.getAttributes();
        for (int i = 0; i < inputAttributes; i++) {
            d = data.get(i);
            means.get(i).increment(d);
            variances.get(i).increment(d);
        }
    }
    
}