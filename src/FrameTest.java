
import java.awt.*;
import javax.swing.*;

public class FrameTest extends SimpleMenu{
	public static void main(String[] args)
	{
		//w¹tek dystrybucji zdarzeñ ramki - steruje wysy³aniem zdarzen,np. klikniêcie myszy etc
		//kod umieszczony w metodzie run zostanie wykonany na pewno przez watek uruchomiony przez klasy swing(watek obslugi interf graf)
		//kod biblioteki swing nie jest wielowatkowy
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				SimpleMenu frame = new SimpleMenu();
				
				frame.setSize(screenWidth,screenHeight);
				//okreœlamy, co chcemy ¿eby siê sta³o,gdy u¿ytkownik zamknie ramkê aplikacji(zamykamy program)
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
				frame.setTitle("Simple Menu");
				//tutaj powodujemy, ¿e ramka siê wyœwietli
				frame.setVisible(true);
			
			}
		});
	}

}
