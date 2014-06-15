package ca.etsmtl.log430.common;

import java.util.Calendar;
import java.util.Date;



/** This class defines the Project object for the system.
* 
* @author A.J. Lattanze, CMU
* @version 1.7, 2013-Oct-06
*/

/*
* Modification Log **********************************************************
* v1.7, R. Champagne, 2013-Oct-06 - Various refactorings for new lab.
*
* v1.6, R. Champagne, 2012-Jun-19 - Various refactorings for new lab.
* 
* v1.5, R. Champagne, 2012-May-31 - Various refactorings for new lab.
* 
* v1.4, R. Champagne, 2012-Feb-02 - Various refactorings for new lab.
* 
* v1.3, R. Champagne, 2011-Feb-02 - Various refactorings, conversion of
* comments to javadoc format.
* 
* v1.2, R. Champagne, 2002-May-21 - Adapted for use at ETS.
* 
* v1.1, G.A.Lewis, 01/25/2001 - Bug in second constructor. Removed null
* assignment to deliveryID after being assigned a value.
* 
* v1.0, A.J. Lattanze, 12/29/99 - Original version.
* ***************************************************************************
*/

public class Project {

	/**
	 * Project ID
	 */
	private String id;

	/**
	 * Project name.
	 */
	private String name;

	/**
	 * project period
	 */
	private Periode projectPeriode;

	/**
	 * Project priority
	 */
	private Priority priority;

	/**
	 * List of resources assigned to the project
	 */
	private ResourceList resourcesAssigned = new ResourceList();

	public Project() {
		this(null);
	}

	public Project(String id) {
		
		resourcesAssigned = new ResourceList();

		this.setID(id);
		
		Date today = Calendar.getInstance().getTime();
		
		this.projectPeriode = new Periode(today,today);

	}

	/**
	 * Assign a resource to a project.
	 * 
	 * @param resource
	 */
	public boolean assignResource(Resource resource) {
		if(!this.resourcesAssigned.contains(resource)){
			resource.assignProject(this);
			return resourcesAssigned.addResource(resource);
		}
		
		return false;
	}

	public void setID(String projectID) {
		this.id = projectID;
	}

	public String getID() {
		return id;
	}

	public void setProjectName(String time) {
		this.name = time;
	}

	public String getProjectName() {
		return name;
	}

	public void setStartDate(Date startDate) {
		this.projectPeriode = new Periode(startDate, this.projectPeriode.getEndDate());
	}

	public Date getStartDate() {
		return this.projectPeriode.getStartDate();
	}

	public void setEndDate(Date endDate) {
		this.projectPeriode = new Periode(this.projectPeriode.getStartDate(),endDate);
	}

	public Date getEndDate() {
		return this.projectPeriode.getEndDate();
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public void setResourcesAssigned(ResourceList resourcesAssigned) {
		this.resourcesAssigned = resourcesAssigned;
	}

	public ResourceList getResourcesAssigned() {
		return resourcesAssigned;
	}
	
	public Periode getPeriode() {
		return new Periode(this.projectPeriode);
	}

} // Project class