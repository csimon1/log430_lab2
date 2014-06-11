/**
 * 
 */
package ca.etsmtl.log430.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Charly
 * 
 */
public class Periode {

	private Date startDate;
	private Date endDate;

	public Periode(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Periode(Periode periode) {
		this(periode.getStartDate(), periode.getEndDate());
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public boolean startBefore(Date d) {

		return this.getStartDate().before(d);
	}

	public boolean startAfter(Date d) {

		return this.getStartDate().after(d);
	}

	public boolean endBefore(Date d) {

		return this.getEndDate().before(d);
	}
	
	public boolean endAfter(Date d) {

		return this.getEndDate().after(d);
	}
	
	public boolean startAfter(Periode p) {

		return this.getStartDate().after(p.getEndDate());
	}
	
	public boolean endBefore(Periode p) {

		return this.getEndDate().before(p.getStartDate());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!super.equals(obj)){
			return false;
		}
		
		if(!(obj instanceof Periode)){
			return false;
		}
		
		Periode p = (Periode) obj;
		
		if(this.startDate.equals(p.startDate) && this.endDate.equals(p.endDate) ){
			return true;
		}
		
		return false;
	}

	public boolean contains(Date d) {
		
		return this.startBefore(d) && this.endAfter(d);
		
	}
	
	@Override
	public String toString() {
		
		DateFormat dForm = new SimpleDateFormat("yyyy-MM-dd");
		
		return dForm.format(this.startDate) + " -> " + dForm.format(this.endDate);
	}
	
	

}
