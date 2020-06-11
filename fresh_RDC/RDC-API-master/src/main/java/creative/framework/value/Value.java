package creative.framework.value;

/**
 *
 * The definition of how an artifact is creative occurs in function of how much
 * it is new (Novelty Interface) and how it is valued (Value Interface) in the
 * application domain context.
 *
 * This interface is responsible for calculating the Value metric. Thus, the
 * classes that implement it will be called in the creative process, at the
 * appropriate time.
 *
 * @author creapar team
 * @param <T>
 */
public interface Value<T> {

    /**
     * Gets the efficiency/performance/attractiveness value of an artifact
     *
     * @param artifact for which you want to calculate Value
     * @return efficiency/performance value
     */
    public Double getValue(T artifact);

}
