package ca.etsmtl.log430.common;

import java.util.Date;



/**
 * This class defines the Resource object for the system. Besides the basic
 * attributes, there are two lists maintained. alreadyAssignedProjectList is a
 * ProjectList object that maintains a list of projects that the resource was
 * already assigned to prior to this execution of the system.
 * projectsAssignedList is also a ProjectList object that maintains a list of
 * projects assigned to the resource during the current execution or session.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.6, 2013-Oct-06
 */

/* Modification Log
 ****************************************************************************
 * v1.6, R. Champagne, 2013-Oct-06 - Various refactorings for new lab.
 * 
 * v1.5, R. Champagne, 2012-Jun-19 - Various refactorings for new lab.
 * 
 * v1.4, R. Champagne, 2012-May-31 - Various refactorings for new lab.
 * 
 * v1.3, R. Champagne, 2012-Feb-02 - Various refactorings for new lab.
 * 
 * v1.2, 2011-Feb-02, R. Champagne - Various refactorings, javadoc comments.
 *  
 * v1.1, 2002-May-21, R. Champagne - Adapted for use at ETS. 
 * 
 * v1.0, 12/29/99, A.J. Lattanze - Original version.

 ****************************************************************************/

public class Resource implements Identiable{

	public static final int MAX_WORK_CHARGE = 100;
	
	/**
	 * Resource's last name
	 */
	private String lastName;
	
	/**
	 * Resource's first name
	 */
	private String firstName;
	
	/**
	 * Resource's identification number
	 */
	private String id;
	
	/**
	 * Resource role 
	 */
	private Role role;
	//private String role;

	/**
	 *  List of projects the resource is already allocated to
	 */
	private ProjectList alreadyAssignedProjectList;

	/**
	 *  List of projects assigned to the resource in this session
	 */
	private ProjectList projectsAssignedList;

	private boolean overallocated;
	
	public Resource() {
		overallocated = false;
		alreadyAssignedProjectList = new ProjectList();
		projectsAssignedList = new ProjectList();
	}
	
	/**
	 * Assigns a project to a resource.
	 * 
	 * @param project
	 */
	public boolean assignProject(Project project) {
		
		if(!this.getAllProjectAssigned().contains(project)){
			if(!this.isAvailableForProject(project)){
				this.overallocated = true;
			}
			return this.projectsAssignedList.addProject(project);
		}
		
		return false;

	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getID() {
		return id;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	/*
	public void setRole(String role) {
		this.role = role;
	}*/

	public Role getRole() {
		return role;
	}
	/*public String getRole() {
		return role;
	}*/

	public void setPreviouslyAssignedProjectList(ProjectList projectList) {
		this.alreadyAssignedProjectList = projectList;
	}

	public ProjectList getPreviouslyAssignedProjectList() {
		return alreadyAssignedProjectList;
	}

	public void setProjectsAssigned(ProjectList projectList) {
		this.projectsAssignedList = projectList;
	}

	public ProjectList getProjectsAssigned() {
		return projectsAssignedList;
	}
	
	private ProjectList getAllProjectAssigned() {
		ProjectList projectAssigned = new ProjectList();
		
		projectAssigned.addAll(this.getPreviouslyAssignedProjectList());
		projectAssigned.addAll(this.getProjectsAssigned());
		
		return projectAssigned;
	}

	/**
	 * 
	 * @return the work charge previously assign for this resource (in percentage)
	 */
	public int getWorkChargePreviouslyAssigned() {
		int chargeWorkPreviouslyAssigned = 0;
		ProjectList projectList = this.getPreviouslyAssignedProjectList();
		
		if( projectList != null){
			for (Project project : projectList) {
				chargeWorkPreviouslyAssigned += project.getPriority().getRessourceCharge();
			}
		}
		
		return chargeWorkPreviouslyAssigned;
	}

	/**
	 * 
	 * @return the work charge currently assign for this resource (in percentage)
	 */
	public int getWorkChargeCurrentlyAssigned() {
		
		int chargeWorkCurrentlyAssigned = 0;
		ProjectList projectList = this.getProjectsAssigned();
		
		if( projectList != null){
			for (Project project : projectList) {
				chargeWorkCurrentlyAssigned += project.getPriority().getRessourceCharge();
			}
		}
		
		return chargeWorkCurrentlyAssigned;
	}
	
	/**
	 * 
	 * @return total work charge for this resource (in percentage)
	 */
	public int getWorkCharge() {
		return getWorkChargePreviouslyAssigned() + getWorkChargeCurrentlyAssigned();
	}
	
	/**
	 * 
	 * @return total work charge for a date for this resource (in percentage)
	 */
	public int getWorkCharge(Date d) {
		return getWorkChargePreviouslyAssigned() + getWorkChargeCurrentlyAssigned();
	}

	
	public boolean isAvailableForProject(Project project) {
		
		if(project != null) {
			for (Project p : this.getProjectsAssigned()) {
				if(p.getPeriode().contains(project.getStartDate())){
					if(p.getPriority().getRessourceCharge() + project.getPriority().getRessourceCharge() > Resource.MAX_WORK_CHARGE){
						return false;
					}
				}
				
				if(p.getPeriode().contains(project.getEndDate())){
					if(p.getPriority().getRessourceCharge() + project.getPriority().getRessourceCharge() > Resource.MAX_WORK_CHARGE){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean isOverallocated(){
		return overallocated;
	}
	
	
} // Resource class