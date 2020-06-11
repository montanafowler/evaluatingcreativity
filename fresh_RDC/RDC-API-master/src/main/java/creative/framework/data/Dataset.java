package creative.framework.data;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.Variance;

/**
 *
 * @author creapar team
 *
 */
public class Dataset {

    private List<Instance> instances;
    private final Integer inputAttributes;

    public Dataset(Integer inputAttributes) {
        instances = new ArrayList<>();
        this.inputAttributes=inputAttributes;
    }
    
    /**
     * Constructor 
     * @param instances 
     */
    public Dataset(ArrayList<Instance> instances) {
        this.instances = instances;
        this.inputAttributes = instances.get(0).getNumberOfAtributtes();

    }
    
    /**
     * Adds instance to Dataset
     * @param instance 
     */
    public void addInstance(Instance instance){
        instances.add(instance);
    }

    /**
     * Returns current instances
     * @return
     */
    public List<Instance> getInstances() {
        return instances;
    }

    /**
     *
     * @param instances
     */
    public void setInstances(ArrayList<Instance> instances) {
        this.instances = instances;
    }

    /**
     * Return a list of means for each attribute
     *
     * @return
     */
    public List<Mean> getMeans() {
        List<Mean> means = new ArrayList<>();

        for (int i = 0; i < inputAttributes; i++) {
            means.add(new Mean());
        }

        List<Double> instanceData;
        for (Instance instance : instances) {
            instanceData = instance.getData();
            for (int i = 0; i < inputAttributes; i++) {
                means.get(i).increment(instanceData.get(i));
            }
        }

        return means;
    }
    
    

    /**
     * Return a list of variance for each attribute
     *
     * @return
     */
    public List<Variance> getVariances() {
        ArrayList<Variance> variances = new ArrayList<>();
        List<Double> instanceData;

        for (int i = 0; i < inputAttributes; i++) {
            variances.add(new Variance());
        }

        for (Instance instance : instances) {
            instanceData = instance.getData();
            for (int i = 0; i < inputAttributes; i++) {
                variances.get(i).increment(instanceData.get(i));
            }
        }

        return variances;
    }
    
    @Override
    public String toString(){
        StringBuilder dataset = new StringBuilder();
        dataset.append("Dataset:");
        for (Instance instance : instances) {
            dataset.append("\n").append(instance.getAttributes());
        }
         dataset.append("\n");
        return dataset.toString();
    }
}
