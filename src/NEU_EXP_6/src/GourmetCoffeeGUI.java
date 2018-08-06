import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.text.*;

/**
 * Gourmet Coffee System.
 *
 * @author iCarnegie
 * @version 1.1.0
 * @see Product
 * @see Coffee
 * @see CoffeeBrewer
 * @see Catalog
 * @see OrderItem
 * @see Order
 * @see Sales
 * @see CatalogLoader
 * @see FileCatalogLoader
 * @see DataFormatException
 * @see SalesFormatter
 * @see PlainTextSalesFormatter
 * @see HTMLSalesFormatter
 * @see XMLSalesFormatter
 * @see DataField
 */
public class GourmetCoffeeGUI extends JPanel {

	/* Standar error stream */
	static private PrintWriter  stdErr = new  PrintWriter(System.err, true);

	/* Window width in pixels */
	static private int WIDTH = 620;

	/* Window height in pixels */
	static private int HEIGHT = 530;

	/* Size of the catalog list cell in pixels */
	static private int CATALOG_CELL_SIZE = 50;

	/* Visible rows in catalog list */
	static private int CATALOG_LIST_ROWS = 14;

	/* Size of the order list cell in pixels */
	static private int ORDER_CELL_SIZE = 100;

	/* Visible rows in order list */
	static private int ORDER_LIST_ROWS = 6;

	/* Size quantity text field */
	static private int QUANTITY__TEXTFIELD_SIZE = 5;

	/* Size total text field */
	static private int TOTAL__TEXTFIELD_SIZE = 8;

	/* Rows in status text area rows */
	static private int STATUS_ROWS = 10;

	/* Rows in status text area cols */
	static private int STATUS_COLS = 40;

	private JList catalogList;
	private JList orderList;
	private JButton addModifyButton;
	private JButton removeButton;
	private JButton registerSaleButton;
	private JButton displaySalesButton;
	private JButton saveSalesButton;
	private JPanel productPanel;
	private JLabel quantityLabel;
	private JLabel totalLabel;
	private JTextField quantityTextField;
	private JTextField totalTextField;
	private JTextArea statusTextArea;
	private JRadioButton  plainRadioButton;
	private JRadioButton  HTMLRadioButton;
	private JRadioButton  XMLRadioButton;

	private JFileChooser  fileChooser;

	private Catalog  catalog;
	private Order  currentOrder;
	private Sales  sales;
	private SalesFormatter salesFormatter;
	private NumberFormat dollarFormatter;

	/**
	 * Loads a product catalog and starts the application.
	 *
	 * @param args  String arguments.  Not used.
	 * @throws IOException if there are errors in the loading the catalog.
	 */
	public static void  main(String[]  args) throws IOException {

		String filename = "";

		if (args.length != 1) {
			filename = "catalog.dat";
		} else {
			filename = args[0];
		}
		try {
			Catalog catalog =
				(new FileCatalogLoader()).loadCatalog(filename);

			JFrame frame = new JFrame("Gourmet Coffee");

			frame.setContentPane(new GourmetCoffeeGUI(catalog));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(WIDTH, HEIGHT);
			frame.setResizable(true);
			frame.setVisible(true);

		} catch (FileNotFoundException fnfe) {
			stdErr.println("The file does not exist");

			System.exit(1);

		} catch (DataFormatException dfe) {
			stdErr.println("The file contains malformed data: "
			               + dfe.getMessage());

			System.exit(1);
		}
	}

	/**
	 * Instantiates the components and arranges them in a window.
	 *
	 * @param initialCatalog a product catalog.
	 */
	public  GourmetCoffeeGUI(Catalog initialCatalog) {

		// create the components
		catalogList = new JList();
		orderList = new JList();
		catalogList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		catalogList.setVisibleRowCount(CATALOG_LIST_ROWS);
		catalogList.setFixedCellWidth(CATALOG_CELL_SIZE);
		orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		orderList.setVisibleRowCount(ORDER_LIST_ROWS);
		orderList.setFixedCellWidth(ORDER_CELL_SIZE);
		addModifyButton = new JButton("Add|Modify Order Item");
		removeButton = new JButton("Remove Order Item");
		registerSaleButton = new JButton("Register Sale of Current Order");
		displaySalesButton = new JButton("Display Sales");
		saveSalesButton = new JButton("Save Sales");
		quantityLabel = new JLabel("Quantity:");
		totalLabel = new JLabel("Total:");
		quantityTextField = new JTextField("", QUANTITY__TEXTFIELD_SIZE);
		totalTextField = new JTextField("$0.00", TOTAL__TEXTFIELD_SIZE);
		totalTextField.setEditable(false);
		statusTextArea = new JTextArea(STATUS_ROWS, STATUS_COLS);
		statusTextArea.setEditable(false);
		plainRadioButton = new JRadioButton("Plain", true);
		HTMLRadioButton = new JRadioButton("HTML");
		XMLRadioButton = new JRadioButton("XML");

		ButtonGroup group = new ButtonGroup();

		group.add(plainRadioButton);
		group.add(HTMLRadioButton);
		group.add(XMLRadioButton);

		// Product Information panel
		productPanel = new JPanel();
		productPanel.setBorder(
				BorderFactory.createTitledBorder("Product Information"));

		// Catalog panel
		JPanel catalogPanel = new JPanel();

		catalogPanel.setBorder(BorderFactory.createTitledBorder("Catalog"));
		catalogPanel.add (
			new JScrollPane(catalogList,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

		// "Add|Modify Product" panel
		JPanel centralPanel = new JPanel(new BorderLayout());
		JPanel addModifyPanel = new JPanel(new GridLayout(2, 1));
		JPanel quantityPanel = new JPanel();

		quantityPanel.add(quantityLabel);
		quantityPanel.add(quantityTextField);
		addModifyPanel.add(quantityPanel);
		addModifyPanel.add(addModifyButton);
		centralPanel.add(productPanel, BorderLayout.CENTER);
		centralPanel.add(addModifyPanel, BorderLayout.SOUTH);

		// Order panel
		JPanel orderPanel = new JPanel(new BorderLayout());

		orderPanel.setBorder(BorderFactory.createTitledBorder("Order"));

		JPanel totalPanel = new JPanel();

		totalPanel.add(totalLabel);
		totalPanel.add(totalTextField);

		JPanel buttonsPanel = new JPanel(new GridLayout(2, 1));

		buttonsPanel.add(removeButton);
		buttonsPanel.add(registerSaleButton);
		orderPanel.add(totalPanel, BorderLayout.NORTH);
		orderPanel.add(new JScrollPane(orderList,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		orderPanel.add(buttonsPanel, BorderLayout.SOUTH);

		// Status panel
		JPanel bottomPanel = new JPanel(new BorderLayout());

		bottomPanel.setBorder(BorderFactory.createTitledBorder("Status"));

		JPanel salesButtonsPanel = new JPanel(new GridLayout(1, 5));

		salesButtonsPanel.add(plainRadioButton);
		salesButtonsPanel.add(HTMLRadioButton);
		salesButtonsPanel.add(XMLRadioButton);
		salesButtonsPanel.add(displaySalesButton);
		salesButtonsPanel.add(saveSalesButton);
		bottomPanel.add (new JScrollPane(statusTextArea,
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
		bottomPanel.add(salesButtonsPanel, BorderLayout.SOUTH);

		// arrange panels in window
		setLayout(new BorderLayout());
		add(catalogPanel, BorderLayout.WEST);
		add(centralPanel, BorderLayout.CENTER);
		add(orderPanel, BorderLayout.EAST);
		add(bottomPanel, BorderLayout.SOUTH);

		// start listening for list and buttons events
		catalogList.addListSelectionListener(new DisplayProductListener());
		addModifyButton.addActionListener(new AddModifyListener());
		removeButton.addActionListener(new RemoveListener());
		registerSaleButton.addActionListener(new RegisterSaleListener());
		displaySalesButton.addActionListener(new DisplaySalesListener());
		saveSalesButton.addActionListener(new SaveSalesListener());
		plainRadioButton.addActionListener(new PlainListener());
		HTMLRadioButton.addActionListener(new HTMLListener());
		XMLRadioButton.addActionListener(new XMLListener());

		// populate the product catalog
		catalog = initialCatalog;
		catalogList.setListData(catalog.getCodes());

		currentOrder = new Order();
		sales = new Sales();
		salesFormatter = PlainTextSalesFormatter.getSingletonInstance();
		fileChooser = new JFileChooser();
		dollarFormatter = NumberFormat.getCurrencyInstance();
	}

	/*
	 * Obtains a {@link JPanel} object with the information of a product.
	 *
	 * @param dataFields  an {@link ArrayList} of {@link DataField}
	 *                    with the product information.
	 * @return  a {@link JPanel} with the product information.
	 */
	private JPanel getDataFieldsPanel(ArrayList<DataField> dataFields) {
		JPanel product_info=new JPanel(new BorderLayout());
		JPanel left = new JPanel(new GridLayout(9,1));
		JPanel right = new JPanel(new GridLayout(9,1));
		left.setPreferredSize(new Dimension(100,180));//h00）会超出Jpanel的空间，无法显示
		right.setPreferredSize(new Dimension(200,180));
		for (DataField data : dataFields) {
			//输出DataField内的键值对
			JLabel name = new JLabel(data.getName() + ":");
			JLabel value = new JLabel(data.getValue());
			name.setPreferredSize(new Dimension(80, 20));
			value.setPreferredSize(new Dimension(200, 20));

			//产生Panel内的表格边框
			value.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

			left.add(name);
			right.add(value);
		}
		//不能直接传入Jpanel，否则是流式布局，自上而下相互交错
		product_info.add("West",left);
		product_info.add("East",right);
		return product_info;
	}

	/**
	 * This inner class handles list-selection events.
	 */
	class DisplayProductListener implements ListSelectionListener {

		/**
		 * Displays the information of the selected product.
		 *
		 * @param event  the event object.
		 */
		public void valueChanged(ListSelectionEvent event) {

			if (! catalogList.getValueIsAdjusting()) {

				String code = (String) catalogList.getSelectedValue();
				Product product = catalog.getProduct(code);

				productPanel.removeAll();
				productPanel.setVisible(false);                   // Use this
				productPanel.add(                                 // to update
					getDataFieldsPanel(product.getDataFields())); // the panel
				productPanel.setVisible(true);                    // correctly

				statusTextArea.setText("Product " + code
				                       + " has been displayed");
			}
		}
	}

	/**
	 * This inner class processes <code>addModifyButton</code> events.
	 */
	class AddModifyListener implements ActionListener {

		/**
		 * Adds an order item to the current order.
		 *
		 * @param event  the event object.
		 */
		public void actionPerformed(ActionEvent event) {
			//获取选取的Catalog中的产品：catalogList.getSelectedValue()
			Product product = catalog.getProduct((String)catalogList.getSelectedValue());
			//直接点击添加或修改订单时
			if(product == null) {
				statusTextArea.setText("You did not select any products.");
				//将文本输出到框中
				return;
				//否则不能达到预期输出，经网上搜索后，需加上return，否则无法输出以上语句。
			}

			OrderItem orderItem;
			String quantity_text = quantityTextField.getText();
			int quantity;
			try {
				quantity = Integer.parseInt(quantity_text);
			} catch(NumberFormatException e) {
				statusTextArea.setText("The quantity should be integer.");
				return;
			}
			if(quantity <= 0) {
				statusTextArea.setText("The quantity should not less than 0");
				return;
			}
			if((orderItem = currentOrder.getItem(product)) == null) {
				orderItem = new OrderItem(product, quantity);
				currentOrder.addItem(orderItem);
			} else {
				orderItem.setQuantity(quantity);
			}
			//仿照源代码,使用setListData给添加OrderItem到Jlist
			orderList.setListData(currentOrder.getItems());
			totalTextField.setText(dollarFormatter.format(currentOrder.getTotalCost()));
			/* PLACE YOUR CODE HERE */

		}
	}

	/**
	 * This inner class processes <code>removeButton</code> events.
	 */
	class RemoveListener implements ActionListener {

		/**
		 * Removes an order item from the current order.
		 *
		 * @param event  the event object.
		 */
		public void actionPerformed(ActionEvent event) {
			if(currentOrder.getNumberOfItems() == 0) {
				statusTextArea.setText("The current order is empty.");
				return;
			}
			OrderItem orderItem = (OrderItem)orderList.getSelectedValue();
			if(orderItem == null) {
				statusTextArea.setText("The user did not select an item in the order.");
				return;
			}
			currentOrder.removeItem(orderItem);
			orderList.setListData(currentOrder.getItems());
			totalTextField.setText(dollarFormatter.format(currentOrder.getTotalCost()));

			/* PLACE YOUR CODE HERE */


		}
	}

	/**
	 * This inner class processes <code>registerSaleButton</code> button events.
	 */
	class RegisterSaleListener implements ActionListener {

		/**
		 * Registers the sale of the current order.
		 *
		 * @param event  the event object.
		 */
		public void actionPerformed(ActionEvent event) {

			if (currentOrder.getNumberOfItems() == 0) {
				statusTextArea.setText("The order is empty.");
			} else {
				sales.addOrder(currentOrder);
				currentOrder = new Order();
				orderList.setListData(currentOrder.getItems());
				totalTextField.setText(dollarFormatter.format(0));
				statusTextArea.setText("The sale has been registered.");
			}
		}
	}

	/**
	 * This inner class processes <code>displaySalesButton</code>events.
	 */
	class DisplaySalesListener implements ActionListener {

		/**
		 * Displays the sales information.
		 *
		 * @param event  the event object.
		 */
		public void actionPerformed(ActionEvent event) {

			if (sales.getNumberOfOrders() == 0) {
				statusTextArea.setText("No orders have been sold.");
			} else {
				statusTextArea.setText(salesFormatter.formatSales(sales));
			}
		}
	}

	/*
	 * This inner class processes <code>saveSalesButton</code>  events.
	 */
	class SaveSalesListener implements ActionListener {

		/**
		 * Saves the sales informations in a file.
		 *
		 * @param event  the event object.
		 */
		public void actionPerformed(ActionEvent event) {
			String out_string = salesFormatter.formatSales(sales);
			if(sales.getNumberOfOrders() == 0) {
				statusTextArea.setText("No orders.");
			}
			String extensions = "";
			//创建过滤器：
			// .isSelected()判断按钮被点击(即被选中)
			//FileNameExtensionFilter 首个参数description为过滤所需文件
			String extension=".txt";
			if(plainRadioButton.isSelected()) {
				extensions = ".txt";
			} else if(HTMLRadioButton.isSelected()) {
				extensions = ".html";
			} else if(XMLRadioButton.isSelected()) {
				extensions = ".xml";
			}
			FileNameExtensionFilter filter = new FileNameExtensionFilter(extension,extensions);
			fileChooser.addChoosableFileFilter(filter);//给文件选择器加入过滤条件
			fileChooser.setFileFilter(filter);//设为过滤器

			int result = fileChooser.showSaveDialog(fileChooser);
			//返回 APPROVE_OPTION 代表成功打开文件
			if(result == JFileChooser.APPROVE_OPTION) {
				File saveFile = fileChooser.getSelectedFile();
				if(saveFile == null) {
					statusTextArea.setText("The saveFile was null.");
					return;
				}
				try {
					if(!saveFile.exists()) {
						saveFile = new File(saveFile.getAbsolutePath() + extensions);//获取路径
						saveFile.createNewFile();
					}
					BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(saveFile));
					bufferedWriter.write(out_string);
					bufferedWriter.close();
				} catch (IOException e) {
					e.toString();
					statusTextArea.setText("The file can not be created or opened.");
				}
			} else {
				statusTextArea.setText("Failure：File can not be opened");
				return;
			}

			/* PLACE YOUR CODE HERE */

		}
	}

	/*
	 * This inner class processes <code>plainRadioButton</code> events.
	 */
	class PlainListener implements ActionListener {

		/**
		 * Sets the sales formatter to plain text.
		 *
		 * @param event  the event object.
		 */
		public void actionPerformed(ActionEvent event) {

			salesFormatter =
				PlainTextSalesFormatter.getSingletonInstance();
		}
	}

	/*
	 * This inner class processes <code>HTMLRadioButton</code> events.
	 */
	class HTMLListener implements ActionListener {

		/**
		 * Sets the sales formatter to HTML.
		 *
		 * @param event  the event object.
		 */
		public void actionPerformed(ActionEvent event) {

			salesFormatter = HTMLSalesFormatter.getSingletonInstance();
		}
	}

	/*
	 * This inner class processes <code>XMLRadioButton</code> events.
	 */
	class XMLListener implements ActionListener {

		/**
		 * Sets the sales formatter to XML.
		 *
		 * @param event  the event object.
		 */
		public void actionPerformed(ActionEvent event) {

			salesFormatter = XMLSalesFormatter.getSingletonInstance();
		}
	}
}