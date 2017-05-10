
import java.awt.*;
import javax.swing.*;

public class FrameTest extends SimpleMenu{
	public static void main(String[] args)
	{
		//w�tek dystrybucji zdarze� ramki - steruje wysy�aniem zdarzen,np. klikni�cie myszy etc
		//kod umieszczony w metodzie run zostanie wykonany na pewno przez watek uruchomiony przez klasy swing(watek obslugi interf graf)
		//kod biblioteki swing nie jest wielowatkowy
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				SimpleMenu frame = new SimpleMenu();
				
				frame.setSize(screenWidth,screenHeight);
				//okre�lamy, co chcemy �eby si� sta�o,gdy u�ytkownik zamknie ramk� aplikacji(zamykamy program)
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
				frame.setTitle("Simple Menu");
				//tutaj powodujemy, �e ramka si� wy�wietli
				frame.setVisible(true);
			
			}
		});
	}

}
