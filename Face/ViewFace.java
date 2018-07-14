// Draw face in cute cartoon style
import javax.swing.JFrame;

public class ViewFace
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();

		frame.setSize(600, 520);
		frame.setTitle("My Face");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		FaceComponent component = new FaceComponent();
		frame.add(component);

		frame.setVisible(true);
	}
}
