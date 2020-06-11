package creative.framework.parser;

import creative.framework.data.Instance;

/**
 * 
 * The Parser interface is responsible for creating an instance 
 * of the data in an appropriate format for use during the stages 
 * of the creative process . This interface must be implemented in 
 * accordance with the application context needs.
 * 
 * @author creapar team
 * @param <T>
 */
public interface Parser<T>{


	/**
	 * Get instance data in the desired format
	 * @param artifact to be converted into the desired format
	 * @return
	 */
    public Instance getInstance(T artifact);
    
    public Integer attributeCount();

}