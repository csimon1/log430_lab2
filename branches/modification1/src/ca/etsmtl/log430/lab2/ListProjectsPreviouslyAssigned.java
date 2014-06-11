package ca.etsmtl.log430.lab2;

import java.util.Observable;

import ca.etsmtl.log430.common.Displays;
import ca.etsmtl.log430.common.Menus;
import ca.etsmtl.log430.common.Resource;



public class ListProjectsPreviouslyAssigned extends Communication
{
	public ListProjectsPreviouslyAssigned(Integer registrationNumber,String componentName)
	{
		super(registrationNumber, componentName);
	}
	
	public void update(Observable thing, Object notificationNumber)
	{
		Menus menu = new Menus();
		Displays display = new Displays();
		Resource myResource = new Resource();

		if (registrationNumber.compareTo((Integer) notificationNumber) == 0)
		{
			addToReceiverList("ListResourcesComponent");
			signalReceivers("ListResourcesComponent");
			
			myResource = menu.pickResource(CommonData.theListOfResources
					.getListOfResources());
			
			if (myResource != null) {
				display.displayProjectsPreviouslyAssignedToResource(myResource);;
			} else {
				System.out.println("\n\n *** Resource not found ***");
			}
		}
		
		removeFromReceiverList("ListResourcesComponent");
	}
}
