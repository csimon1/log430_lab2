package ca.etsmtl.log430.lab2;

import java.util.Observable;

import ca.etsmtl.log430.common.Displays;
import ca.etsmtl.log430.common.Menus;
import ca.etsmtl.log430.common.Project;

/**
 * Upon notification, first list the projects and prompt the user to pick one
 * by specifying it's ID. If the project's ID is valid, then the roles assigned
 * to that project is listed.
 *   
 * @author Samuel
 * @version 1.0, 2014-06-12  
 */

/*
 * Modification Log **********************************************************
 * v1.0, A. Samuel , 12/06/2014 - Original version.
 * ***************************************************************************
 */
public class ListRolesAssignedToProject extends Communication
{

	public ListRolesAssignedToProject(Integer registrationNumber,String componentName) 
	{
		super(registrationNumber, componentName);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * The update() method is an abstract method that is called whenever the
	 * notifyObservers() method is called by the Observable class. First we
	 * check to see if the NotificationNumber is equal to this thread's
	 * RegistrationNumber. If it is, then we execute.
	 * 
	 * @see ca.etsmtl.log430.lab2.Communication#update(java.util.Observable,
	 *      java.lang.Object)
	 */
	public void update(Observable thing, Object notificationNumber) 
	{
		Menus menu = new Menus();
		Displays display = new Displays();
		Project myProject = new Project();
		
		if(registrationNumber.compareTo((Integer)notificationNumber) == 0)
		{
			addToReceiverList("ListProjectsComponent");
			signalReceivers("ListProjectsComponent");

			// Next we ask them to pick a project
			myProject = menu.pickProject(CommonData.theListOfProjects.getListOfProjects());

			if (myProject != null) 
			{
				/*
				 * If the project is valid (exists in the list), then we display
				 * the roles that are assigned to it.
				 */
				display.displayRoles(CommonData.theListOfResources.getListOfResources(),myProject);
			} 
			else 
			{
				System.out.println("\n\n *** Project not found ***");
			}
		}
		
		removeFromReceiverList("ListProjectsComponent");
	}
}
