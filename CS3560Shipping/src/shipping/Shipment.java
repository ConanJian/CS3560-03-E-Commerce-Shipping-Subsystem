package shipping;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class Shipment
{
	private int shipmentID;
	private ArrayList<Package> packagelist = new ArrayList<Package>();
	private  Date dateShipped;

	public Shipment(int shipmentID, ArrayList<Package> packagelist, Date dateshipped) // create a new shipment?
	{
		this.shipmentID = shipmentID;
		this.packagelist = packagelist;
		this.dateShipped = dateshipped;
	}

	public void addPackage(Package box) // add a package to the shipment
	{
		packagelist.add(box);
	}

	public void removePackage(Package box) // remove a package from the shipment
	{
		packagelist.remove(box);
	}

	public int getShipmentID() // returns the ID of this shipment
	{
		return shipmentID;
	}

	public ArrayList<Package> getPackageList()// returns a list of packages that are part of this shipment
	{
		return packagelist;
	}

	public Date getDateShipped() // returns the date this shipment was shipped
	{
		return dateShipped;
	}
}
