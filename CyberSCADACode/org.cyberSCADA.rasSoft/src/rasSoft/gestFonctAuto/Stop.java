package rasSoft.gestFonctAuto;
import rasSoft.principale.MasterModbus;

public class Stop {

	public static void main(String args[])
	{
		MasterModbus m = new MasterModbus("172.20.25.41:502");
		m.WriteCoil("102", false, "1");
	}
}
