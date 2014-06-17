/**
 * 
 */
package ca.etsmtl.log430.common;



/**
 * @author Charly
 *
 */
public class Role implements Identiable {

	private String id;
	private String name;

	@Override
	public String getID() {
		return id;
	}

	
	public Role(String ID) {
		this.id = ID;
		this.name = this.id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(super.equals(obj)){
			return true;
		}
		
		if(! (obj instanceof Role)){
			return false;
		}
		
		Role r = (Role) obj;
		
		return this.getID().equalsIgnoreCase(r.getID());
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();		
	}	
	
}
