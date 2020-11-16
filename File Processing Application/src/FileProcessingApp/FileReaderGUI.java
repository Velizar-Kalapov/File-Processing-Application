package FileProcessingApp;

import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FileReaderGUI extends JFrame implements ActionListener  {
	private JTextField fieldFirstLine;
	private JTextField fieldSecondLine;
	private JTextField fieldFirstWord;
	private JTextField fieldSecondWord;
	private JTextField selectedLine;
	private JTextField selectedWord;
	private JTable jt;
	private JButton openFileButton; 
	private File file;
	private FileReader flr;
	private String path;
	private JButton buttonSwapLines, buttonSwapWords, buttonSetFirst,buttonSetSecond ;
	private String[][] lineArr;
	private String[] columns;
	private JScrollPane pane;
	DefaultTableModel model = new DefaultTableModel();
	/**
	 * Create the panel.
	 */
	public FileReaderGUI() {
		getContentPane().setLayout(null);
		this.setBounds(100, 100, 500, 480);
		//this.setPreferredSize(new Dimension (100, 100));
		this.setResizable(false);
		
		openFileButton = new JButton("Open File");
		openFileButton.addActionListener(this);
			
		
		openFileButton.setBounds(43, 45, 100, 21);
		getContentPane().add(openFileButton);
		
		JLabel lblNewLabel = new JLabel("Line 1:");
		lblNewLabel.setBounds(43, 76, 45, 13);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Line 2:");
		lblNewLabel_1.setBounds(147, 76, 45, 13);
		getContentPane().add(lblNewLabel_1);
		
		fieldFirstLine = new JTextField();
		fieldFirstLine.setBounds(98, 73, 20, 19);
		getContentPane().add(fieldFirstLine);
		fieldFirstLine.setColumns(10);
		
		fieldSecondLine = new JTextField();
		fieldSecondLine.setBounds(207, 73, 20, 19);
		getContentPane().add(fieldSecondLine);
		fieldSecondLine.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Word 1:");
		lblNewLabel_2.setBounds(43, 106, 45, 13);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Word 2:");
		lblNewLabel_3.setBounds(147, 106, 45, 13);
		getContentPane().add(lblNewLabel_3);
		
		fieldFirstWord = new JTextField();
		fieldFirstWord.setColumns(10);
		fieldFirstWord.setBounds(98, 103, 20, 19);
		getContentPane().add(fieldFirstWord);
		
		fieldSecondWord = new JTextField();
		fieldSecondWord.setColumns(10);
		fieldSecondWord.setBounds(207, 103, 20, 19);
		getContentPane().add(fieldSecondWord);
		
		buttonSwapLines = new JButton("Swap Lines");
		buttonSwapLines.addActionListener(this);
		
		buttonSwapLines.setBounds(254, 72, 110, 21);
		getContentPane().add(buttonSwapLines);
		buttonSwapLines.setEnabled(false);

		
		buttonSwapWords = new JButton("Swap Words");
		buttonSwapWords.addActionListener(this);
		buttonSwapWords.setBounds(254, 102, 110, 21);
		getContentPane().add(buttonSwapWords);

		buttonSwapWords.setEnabled(false);
		
		selectedLine = new JTextField();
		selectedLine.setColumns(10);
		selectedLine.setBounds(191, 140, 20, 19);
		getContentPane().add(selectedLine);
		selectedLine.setEditable(false);
		
		JLabel lblNewLabel_4 = new JLabel("Selected Line:");
		lblNewLabel_4.setBounds(100, 143, 100, 13);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Selected Word:");
		lblNewLabel_5.setBounds(215, 143, 100, 13);
		getContentPane().add(lblNewLabel_5);
		
		selectedWord = new JTextField();
		selectedWord.setColumns(10);
		selectedWord.setBounds(311, 140, 20, 19);
		getContentPane().add(selectedWord);
		selectedWord.setEditable(false);
		
		buttonSetFirst = new JButton("Set Element 1");
		buttonSetFirst.addActionListener(this);
		buttonSetFirst.setEnabled(false);
		
		buttonSetFirst.setBounds(130, 169, 120, 21);
		getContentPane().add(buttonSetFirst);
		
		buttonSetSecond = new JButton("Set Element 2");
		buttonSetSecond.addActionListener(this);
		buttonSetSecond.setBounds(254, 169, 137, 21);
		getContentPane().add(buttonSetSecond);
		buttonSetSecond.setEnabled(false);
		
		

		
		jt = new JTable(model);
		pane = new JScrollPane();
		
		
		
		this.setVisible(true);

	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == openFileButton) {
			JFileChooser fileChooser = new JFileChooser();
			int isFileChosen = fileChooser.showOpenDialog(null);
			if(isFileChosen == JFileChooser.APPROVE_OPTION) {
				this.path = fileChooser.getSelectedFile().getAbsolutePath();
				buttonSwapLines.setEnabled(true);
				buttonSwapWords.setEnabled(true);
				buttonSetFirst.setEnabled(true);
				buttonSetSecond.setEnabled(true);
				makeJTable();
			}
		}	
		if(e.getSource() == buttonSwapLines) {
			try {
			int firstLine = Integer.parseInt(fieldFirstLine.getText()); 
			int secondLine = Integer.parseInt(fieldSecondLine.getText());
			this.flr.swapLines(firstLine,secondLine );
			makeJTable();
			} catch (NumberFormatException ex) {
				System.out.println("Invalid input");
			}
			} 
		if (e.getSource() == buttonSwapWords) {
			try {
			int firstLine = Integer.parseInt(fieldFirstLine.getText());
			int secondLine = Integer.parseInt(fieldSecondLine.getText());
			int firstWord = Integer.parseInt(fieldFirstWord.getText());
			int secondWord = Integer.parseInt(fieldSecondWord.getText());
			
			flr.swapWordsFromIndex(firstLine - 1, secondLine - 1, firstWord -1, secondWord -1);
			makeJTable();
			} catch (NumberFormatException ex) {
				System.out.println("Invalid input");
			}
			
		}
		if (e.getSource() == buttonSetFirst) {
			fieldFirstLine.setText(selectedLine.getText());
			fieldFirstWord.setText(selectedWord.getText());
			//makeJTable();
			
		}
		if (e.getSource() == buttonSetSecond) {
			fieldSecondLine.setText(selectedLine.getText());
			fieldSecondWord.setText(selectedWord.getText());
		//	makeJTable();
			
			
		}	
	} 
	
	private void makeJTable() {
		remove(jt);
		remove(pane);
		file = new File(this.path);
		flr = new FileReader(file); 
		lineArr = new String[flr.getNumberOfLines()][flr.longestLine()];
		columns = new String[flr.longestLine()];
		//System.out.println(flr.longestLine());
		for(int i = 0; i < flr.getNumberOfLines(); i++) {							
			
			for(int j = 0; j < flr.getLineFromIndex(i).getWordCountInLine(); j++) {
			try {
				this.lineArr[i][j] = flr.getLineFromIndex(i).findWordByIndex(j);
			} catch (emptyIndexException e) {
				System.out.println(e.getMessage());
			}
				
			}
			
		}
		for (int i = 0; i < flr.longestLine(); i++) {
			this.columns[i] = String.valueOf(i +1);
		}	
		this.jt = new JTable(lineArr, columns);
		
		jt.setPreferredScrollableViewportSize(new Dimension(100, 100));
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jt.setBounds(21, 200, 300, 122);
		this.pane = new JScrollPane();
		pane.setBounds(43, 203, 400, 200);
		getContentPane().add(pane);
		getContentPane().add(jt);
		this.pane.setViewportView(jt);
		jt.revalidate();
		jt.repaint();
		addDataFromTable();
		
	}
	
	private void  addDataFromTable() {
		
		jt.addMouseListener(new java.awt.event.MouseAdapter(){
			
		@Override
			public void mouseClicked(java.awt.event.MouseEvent event) {
					int row = jt.rowAtPoint(event.getPoint());
					int col = jt.columnAtPoint(event.getPoint());
						if (row >= 0 && col >=0) {
		
								selectedLine.setText(String.valueOf(row +1));
								selectedWord.setText(String.valueOf(col +1));
			
						}
			}				
			
		});
				
			
				
	 }
}
