package ca.etsmtl.log430.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This class displays various types of information on projects and resources
 * (individually and as lists) to the screen.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.6, 2013-Sep-13
 */

/*
 * Modification Log
 * ************************************************************************
 * v1.6, R. Champagne, 2013-Sep-13 - Various refactorings for new lab.
 * 
 * v1.5, R. Champagne, 2012-Jun-19 - Various refactorings for new lab.
 * 
 * v1.3, R. Champagne, 2012-Feb-02 - Various refactorings for new lab.
 * 
 * v1.2, 2011-Feb-02, R. Champagne - Various refactorings, javadoc comments.
 * 
 * v1.1, 2002-May-21, R. Champagne - Adapted for use at ETS.
 * 
 * v1.0, 12/29/99, A.J. Lattanze - Original version.
 * ************************************************************************
 */

public class Displays {

	private int lineCount = 0;
	private int maxLinesDisplayed = 18;

	/**
	 * Counts the number of lines that has been printed. Once a set number of
	 * lines has been printed, the user is asked to press the enter key to
	 * continue. This prevents lines of text from scrolling off of the page.
	 * 
	 * @param linesToAdd
	 */
	private void lineCheck(int linesToAdd) {

		Termio terminal = new Termio();

		if (lineCount >= maxLinesDisplayed) {

			lineCount = 0;
			System.out.print("\n*** Press Enter To Continue ***");
			terminal.keyboardReadChar();

		} else {

			lineCount += linesToAdd;

		} // if

	} // LineCheck

	/**
	 * Displays a resource object's elements as follows: Resource's first name,
	 * last name, ID number, role.
	 * 
	 * Note that the projects previously assigned to the resource and the projects
	 * assigned to the resource in this execution of the system are not displayed.
	 * 
	 * @param resource
	 */
	public void displayResource(Resource resource) {

		System.out.println(resource.getID() + " "
				+ resource.getFirstName() + " "
				+ resource.getLastName() + " "
				+ resource.getRole().getName());
	}

	/**
	 * Displays a project object's elements as follows: ID, name, start date,
	 * end date, and priority. Note that the resources assigned to the project
	 * are not listed by this method.
	 * 
	 * @param project
	 */
	public void displayProject(Project project) {
		System.out.println(project.getID() + " "
				+ project.getProjectName() + " "
				+ project.getStartDate() + " "
				+ project.getEndDate() + " "
				+ project.getPriority());
	}

	/**
	 * Lists the resources that have been assigned to the project.
	 * 
	 * @param project
	 */
	public void displayResourcesAssignedToProject(Project project) {

		boolean done;
		Resource resource;

		System.out.println("\nResources assigned to: " + " "
				+ project.getID() + " " + project.getProjectName() + " :");
		lineCheck(1);

		System.out
				.println("===========================================================");
		lineCheck(1);

		project.getResourcesAssigned().goToFrontOfList();
		done = false;

		while (!done) {

			resource = project.getResourcesAssigned().getNextResource();

			if (resource == null) {

				done = true;

			} else {

				displayResource(resource);

			} // if

		} // while

	}

	/**
	 * Lists the projects currently assigned to a resource during this session.
	 * 
	 * @param resource
	 */
	public void displayProjectsAssignedToResource(Resource resource) {

		boolean done;
		Project project;

		System.out.println("\nProjects assigned (in this session) to : "
				+ resource.getFirstName() + " " + resource.getLastName() + " "
				+ resource.getID());
		lineCheck(2);
		System.out
				.println("========================================================= ");
		lineCheck(1);

		resource.getProjectsAssigned().goToFrontOfList();
		done = false;

		while (!done) {

			project = resource.getProjectsAssigned().getNextProject();

			if (project == null) {

				done = true;

			} else {

				displayProject(project);
				lineCheck(2);

			} // if

		} // while

	}

	/**
	 * Displays the resources in a resource list. Displays the same information
	 * that is listed in the displayResource() method listed above.
	 * 
	 * @param list
	 */
	public void displayResourceList(ResourceList list) {

		boolean done;
		Resource resource;

		System.out.print("\n");
		lineCheck(1);

		list.goToFrontOfList();

		done = false;

		while (!done) {

			resource = list.getNextResource();

			if (resource == null) {

				done = true;

			} else {

				displayResource(resource);
				lineCheck(1);

			} // if

		} // while

	}

	/**
	 * Displays the projects in a project list. Displays the same
	 * information that is listed in the displayProject() method listed above.
	 * 
	 * @param list
	 */
	public void displayProjectList(ProjectList list) {

		boolean done;
		Project project;

		System.out.print("\n");
		lineCheck(1);

		list.goToFrontOfList();
		done = false;

		while (!done) {

			project = list.getNextProject();

			if (project == null) {

				done = true;

			} else {

				displayProject(project);
				lineCheck(1);

			} // if

		} // while

	}
	
	public void displayRoles(ResourceList resources, Project pickedProject) 
	{
		// Added The list of resources in parameter, because every time
		// we create a project object, the list of resources assigned to this project are empty.
		// there is no way to load the resource files and then add roles assigned to this project . 
		Map<Role, Integer> previousRoles = new HashMap<>();
		Map<Role, Integer> currentRoles = new HashMap<>();
		
		for(Resource r : resources)
		{
			Role role = r.getRole();
			
			// check if the current project id exist in the previously project list 
			if(!r.getPreviouslyAssignedProjectList().isEmpty())
			{
				ProjectList previousProjects = r.getPreviouslyAssignedProjectList();
				
				// Iterates throughout each previous project to check if the current project exist.
				for(Project previousP : previousProjects)
				{
					if(previousP.getID().equalsIgnoreCase(pickedProject.getID()))
					{
						Integer roleCount = previousRoles.get(role);
						
						if(roleCount != null)
						{
							previousRoles.put(role, roleCount++);
						}
						else
						{
							previousRoles.put(role, 1);
						}
					}
				}
			}
			if(!r.getProjectsAssigned().isEmpty())
			{
				ProjectList currentProjects = r.getProjectsAssigned();
				
				// Iterates throughout each project in the current session to check if the picked project exist in the list.
				for(Project currentP : currentProjects)
				{
					if(currentP.getID().equalsIgnoreCase(pickedProject.getID()))
					{
						Integer roleCount = currentRoles.get(role);
						
						if(roleCount != null)
						{
							currentRoles.put(role, roleCount++);
						}
						else
						{
							currentRoles.put(role, 1);
						}
					}
				}
			}
		}// end for
		
		// Now Displaying resources.
		
		displaySeparator();

		System.out.println("Roles assigned to Project : " + pickedProject.getID()
				+ " " + pickedProject.getProjectName());

		lineCheck(1);

		displaySubSeparator();

		if (previousRoles.isEmpty()) 
		{
			System.out
					.println("The project does not have ressources already assigned!");
			lineCheck(1);
		}
		else 
		{
			System.out.println("Roles already assigned :");
			
			lineCheck(1);

			for (Entry<Role, Integer> role : previousRoles.entrySet()) 
			{
				System.out.println(role.getKey().getName() + " -> "	+ role.getValue());
				lineCheck(1);
			}

			lineCheck(1);
		}

		displaySubSeparator();

		if (currentRoles.isEmpty()) 
		{
			System.out.println("No resources have been assigned for this current session!");
			lineCheck(1);
		}
		else 
		{
			System.out.println("\nRoles currently assigned :");

			lineCheck(1);

			for (Entry<Role, Integer> role : currentRoles.entrySet()) 
			{
				System.out.println(role.getKey().getName() + " -> " + role.getValue());
				lineCheck(1);
			}

			System.out.println("\n");
			lineCheck(1);
		}

		displaySubSeparator();

		System.out.println("Total roles assigned : "	+ (previousRoles.size() + currentRoles.size()));

		lineCheck(3);

		displaySeparator();
	}

	private void displaySeparator() 
	{
		lineCheck(1);
		System.out.println("\n===========================================================\n");
		lineCheck(1);
	}

	private void displaySubSeparator() 
	{
		System.out.println("\t---------------------------------------------------");
		lineCheck(1);
	}
} // Display