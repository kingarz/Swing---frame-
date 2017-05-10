import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.file.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.EditorKit;

import java.util.Scanner;

//klasa SimpleFrame jedynie ró¿ni siê od klasy JFrame domyœlnymi rozmiarami ramki
public class SimpleMenu extends JFrame {

	public static int screenWidth;
	public static int screenHeight;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu editBar = new JMenu("Edit");
	private JMenu fileBar = new JMenu("File");
	private JMenuItem pasteItem = new JMenuItem("Paste",
			new ImageIcon("C:/Users/Kinga/Documents/JavaProjects/SwingFrame/paste.png"));
	private JMenuItem copyItem = new JMenuItem("Copy",
			new ImageIcon("C:/Users/Kinga/Documents/JavaProjects/SwingFrame/copy.jpg"));
	private JMenuItem openItem = new JMenuItem("Open",
			new ImageIcon("C:/Users/Kinga/Documents/JavaProjects/SwingFrame/open.png"));
	private JMenuItem cutItem = new JMenuItem("Cut",
			new ImageIcon("C:/Users/Kinga/Documents/JavaProjects/SwingFrame/cut.png"));
	private JMenuItem saveItem = new JMenuItem("Save",
			new ImageIcon("C:/Users/Kinga/Documents/JavaProjects/SwingFrame/save.png"));
	private JMenuItem clearAll = new JMenuItem("Clear");
	private JTextField filename = new JTextField(), dir = new JTextField();
	private JTextArea a = new JTextArea();// (screenWidth,screenHeight);
	private JScrollPane scrollPane;
	private File file;

	public SimpleMenu() {
		//Ustawiam domyœly rozmiar dla ramki
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		screenWidth = screenSize.width / 2;
		screenHeight = screenSize.height / 2;
		
		setLocationByPlatform(true);
		//rozk³ad elementów - 1 kolumna na "komponent", ka¿dy element ma taki sam rozmiar
		setLayout(new GridLayout());
		//ustalamy nasze menu
		setJMenuBar(menuBar);
		//dodajemy g³owne zak³adki do menu
		menuBar.add(fileBar);
		menuBar.add(editBar);
		//dodajemy elementy do zak³adki edycji
		editBar.add(pasteItem);
		//dodajemy separatory pomiêdzy elementami 
		editBar.addSeparator();
		editBar.add(copyItem);
		editBar.addSeparator();
		editBar.add(cutItem);
		editBar.addSeparator();
		editBar.add(clearAll);
		//analogicznie dla zak³adki file
		fileBar.add(openItem);
		fileBar.add(saveItem);
		//dodajemy zdarzenie które nast¹pi w momencie wybrania którejœ z "zak³adek"
		clearAll.addActionListener(new ClearEvent());
		openItem.addActionListener(new OpenEvent());
		saveItem.addActionListener(new SaveEvent());
		ActionMap m = a.getActionMap();
		Action copy = m.get(DefaultEditorKit.copyAction);
		copyItem.addActionListener(copy);
		Action paste = m.get(DefaultEditorKit.pasteAction);
		pasteItem.addActionListener(paste);
		//mo¿liwoœæ edycji pola tekstowego
		a.setEditable(true);
		a.setColumns(screenHeight);
		a.setRows(screenWidth);
		a.setWrapStyleWord(true);
		a.setLineWrap(true);
		//suwak do textArea
		scrollPane = new JScrollPane(a, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(a);

		scrollPane.setVisible(true);
		// a.setVisible(true);
		scrollPane.setViewportView(a);
		add(scrollPane);

		pack();
	}

	public class OpenEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser c = null;
			// ustawianie stylu dla explorera plików do otwarcia domyslnej dla konkretnego systemu operacyjnego
			LookAndFeel previousLF = UIManager.getLookAndFeel();
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				c = new JFileChooser();
				UIManager.setLookAndFeel(previousLF);
			} catch (IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException
					| ClassNotFoundException et) {
			}
			// Demonstrate "Open" dialog:
			int rVal = c.showOpenDialog(null);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				file = c.getSelectedFile();
				// wyswietlanie pliku txt

				try {
					InputStream in = new FileInputStream(file);
					a.setText("");
					a.read(new InputStreamReader(in), null);

				} catch (IOException ex) {
				}

				System.out.println("Selected file: " + file.getAbsolutePath());

			}
			if (rVal == JFileChooser.CANCEL_OPTION) {
				filename.setText("You pressed cancel");
				dir.setText("");
			}
		}

	}

	class SaveEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser c = null;
			LookAndFeel previousLF = UIManager.getLookAndFeel();
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				c = new JFileChooser();
				UIManager.setLookAndFeel(previousLF);
			} catch (IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException
					| ClassNotFoundException et) {
			}
			// Demonstrate "Save" dialog:
			int rVal = c.showSaveDialog(SimpleMenu.this);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				File saved = c.getSelectedFile();
				// file na fileoutput
				try {
					OutputStream x = new FileOutputStream(saved);

					x.write(a.getText().getBytes());
					x.close();
				} catch (Exception exp) {

				}
				filename.setText(c.getSelectedFile().getName());
				dir.setText(c.getCurrentDirectory().toString());
			}
			if (rVal == JFileChooser.CANCEL_OPTION) {
				filename.setText("You pressed cancel");
				dir.setText("");
			}
		}
	}

	public class ClearEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (a != null) {
				a.setText(null);
			}
		}
	}

}
