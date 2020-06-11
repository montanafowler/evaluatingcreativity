package creative.framework.novelty;

/**
 * The definition of how an artifact is creative occurs in function
 * of how much it is new (Novelty Interface) and how it is valued (Value Interface) 
 * in the application domain context.
 * 
 * This interface is responsible for calculating the Novelty metric. Thus, the classes 
 * that implement it will be called in the creative process, at the appropriate time.
 * 
 * @author creapar team
 * @param <T>
 */
public interface Novelty <T>{
    
    /**
     * Returns the amount of novelty of a artifact
     * @param artifact for which you want to calculate Novelty
     * @return novelty value
     */
    public Double getNovelty (T artifact);   
    
    /**
     * Update the dataset used to calculate novelty
     * @param artifact 
     */
    public void updateNovelty(T artifact);
}
