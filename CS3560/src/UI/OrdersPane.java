package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Controllers.OrderController;
import Utility.ConnectionFactory;
import shipping.*;

import java.awt.*;
import java.util.*;

/**
 * @author Gabriel Fok
 */

@SuppressWarnings("serial")
public class OrdersPane extends JPanel
{
	private String id;
	private final String[] orderColNames = { "Date", "Order ID", "Name" };
	private String[][] orderCol;
	private JTable orderList;
	private JScrollPane orderPane;
	private static ArrayList<Order> orders;

	/**
	 * contructor for a display
	 * 
	 */

	public OrdersPane()
	{

		setLayout(new BorderLayout());

		// Temporary array for order list
		orderCol = new String[0][100];

		// Create JTable for order list
		orderList = new JTable(orderCol, orderColNames)
		{
			public boolean editCellAt(int row, int column, EventObject e)
			{
				return false;
			}
		};
		orderList.setRowHeight(45);
		// Add JTable to JScrollPane to make scrollable
		orderPane = new JScrollPane(orderList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		orderList.setFillsViewportHeight(true);
		orderPane.setAutoscrolls(true);

		// Add ScrollPane to Jpanel
		add(orderPane, BorderLayout.CENTER);
		orders = new ArrayList<Order>();
		updateTable();
	}

	// The method that return the selected order id
	public String getID(int row)
	{
		id = orderCol[row][1];
		return id;
	}

	// The method that return JTable
	public JTable getTable()
	{
		return orderList;
	}

	// Refreshes order list, updates this OrdersPane's display, and returns the
	// ArrayList of orders
	public static ArrayList<Order> getOrders()
	{
		ConnectionFactory.createConnection();
		orders.clear();
		ArrayList<HashMap<String, Object>> orders_sql = ConnectionFactory.createOrderConnection().getAllOrderList();
		if(orders_sql != null)
		{
			for (int entry = 0; entry < orders_sql.size(); entry++)
			{
				// Create each Order
				int order_id = (int) orders_sql.get(entry).get("orderId");
				orders.add(OrderController.getOrder(order_id));
				if(ConnectionFactory.createOrderLineItemConnection().getOrderLineItemListWithoutPackageBasedOnOrder(order_id).size() == 0)
				{
					// Fully packaged
					ConnectionFactory.createOrderConnection().updateOrderStatus(order_id, 2);
				} else if (ConnectionFactory.createOrderLineItemConnection().getOrderLineItemListWithoutPackageBasedOnOrder(order_id).size() == orders.get(entry).getOrderLineItemList().size())
				{
					// None packaged
					ConnectionFactory.createOrderConnection().updateOrderStatus(order_id, 0);
				} else
				{
					// Partially packaged
					ConnectionFactory.createOrderConnection().updateOrderStatus(order_id, 1);
				}
			}
			return orders;
		}
		System.out.println("No orders found.");
		return null;
	}
	public void updateTable()
	{
		getOrders();
		orderCol = new String[orders.size()][100];
		for (int entry = 0; entry < orders.size(); entry++)
		{
			orderCol[entry][0] = "" + orders.get(entry).getDate().toString();
			orderCol[entry][1] = "" + orders.get(entry).getOrderId();
			orderCol[entry][2] = "" + orders.get(entry).getCustomerInfo().getCustomerName()[0] + " "
			+ orders.get(entry).getCustomerInfo().getCustomerName()[1];
		}
		orderList.setModel(new DefaultTableModel(orderCol, orderColNames));
	}
	public static Order getOrder(int order_id)
	{
		for (int entry = 0; entry < orders.size(); entry++)
		{
			if (orders.get(entry).getOrderId() == order_id)
				return orders.get(entry);
		}
		return null;
	}
}