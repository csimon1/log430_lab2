/**
 * 
 */
package ca.etsmtl.log430.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author aj98150
 *
 */
public class Priority {
	

	public static final Priority HIGH = new Priority("H", "Haute", 100);
	public static final Priority MEDIUM = new Priority("M", "Medium", 50 );
	public static final Priority LOW = new Priority("L", "Low", 25);
	
	private static Map<String, Priority> allPriorities;
	
	static{
		allPriorities = new HashMap<String, Priority>();
		
		allPriorities.put(HIGH.id,HIGH);
		allPriorities.put(MEDIUM.id,MEDIUM);
		allPriorities.put(LOW.id,LOW);
	}
	
	public static Priority getPriority(String id){	
		return allPriorities.get(id);
	}
	
	
	private String id;
	private String desc;
	private int ressource_occupation;// pourcentage
	
	private Priority(String id, String desc, int ressource_occupation) {
		this.id = id;
		this.desc = desc;
		this.ressource_occupation = ressource_occupation;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @return the ressource_occupation
	 */
	public int getRessourceCharge() {
		return ressource_occupation;
	}
	
	@Override
	public String toString() {
		return this.desc;
	}
	
	
	
}
