package rasSoft.principale;

//package net;
import java.net.InetAddress;


import net.wimpi.modbus.Modbus;
import net.wimpi.modbus.ModbusSlaveException;
//import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ReadCoilsRequest;
import net.wimpi.modbus.msg.ReadCoilsResponse;
import net.wimpi.modbus.msg.ReadInputDiscretesRequest;
import net.wimpi.modbus.msg.ReadInputDiscretesResponse;
import net.wimpi.modbus.msg.ReadInputRegistersRequest;
import net.wimpi.modbus.msg.ReadInputRegistersResponse;
import net.wimpi.modbus.msg.ReadMultipleRegistersRequest;
import net.wimpi.modbus.msg.ReadMultipleRegistersResponse;
import net.wimpi.modbus.msg.WriteCoilRequest;
import net.wimpi.modbus.msg.WriteCoilResponse;
import net.wimpi.modbus.msg.WriteMultipleCoilsRequest;
import net.wimpi.modbus.msg.WriteMultipleCoilsResponse;
import net.wimpi.modbus.msg.WriteMultipleRegistersRequest;
import net.wimpi.modbus.msg.WriteMultipleRegistersResponse;
import net.wimpi.modbus.msg.WriteSingleRegisterRequest;
import net.wimpi.modbus.msg.WriteSingleRegisterResponse;
import net.wimpi.modbus.net.TCPMasterConnection;
import net.wimpi.modbus.procimg.Register;
import net.wimpi.modbus.procimg.SimpleInputRegister;
import net.wimpi.modbus.util.BitVector;

public class MasterModbus {

	public TCPMasterConnection con = null; //the connection
	public ModbusTCPTransaction trans = null; //the transaction

	/* Variables for storing the parameters */
	public InetAddress adresse = null; //the slave's address
	public int port = Modbus.DEFAULT_PORT;
	public int ref = 0; //the reference; offset where to start reading from
	public int count = 0; //the number of DI's to read
	public int repeat = 1; //a loop for repeating the transaction
	
	public int[] number;
	/**
	 * 0 : SimpleDigitalIn
	 * 1 : SimpleDigitalOut
	 * 2 : SimpleInputRegister
	 * 3 : SimpleRegister
	 */
	
	public MasterModbus(String a)
	{
		try {
			int idx = a.indexOf(':');
			if(idx != 0){ 
				port = Integer.parseInt(a.substring(idx+1));
				a = a.substring(0,idx);
			}
			adresse = InetAddress.getByName(a);
			System.out.println("adresse : "+adresse);
			System.out.println("port : "+port);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}
		
		number = new int[4];
		setNumber(initNumber());
		System.out.println("nombre : "+ number[0] +"  " + number[1]+ "  "+ number[2]+"  "+number[3] );
	}

	public static void main(String[] args) {

		MasterModbus m = new MasterModbus("127.0.1.1:4444");
		
		boolean b[] = new boolean[2];
		b[0] = false;b[1] = true;
		
		int i[] = new int[2];
		i[0] = 26;i[1] = 45;

		//m.WriteMultipleRegister("192.168.0.20:5555", "0",i,"3");
		//m.WriteMultipleCoil("192.168.0.23:5555", "0",b,"3");
		//m.WriteCoil("12",false,"1");
		String t = m.ReadCoil("18","1","1");
		//m.ReadInputDiscrete("0","20","1");
		//m.WriteRegister("192.168.0.23:5555", "1",22,"3");
		//m.ReadInputRegister("0","100","1");
		//m.ReadRegister("0","100","1");
		
		System.out.println(t);
		
	}//main

	public void setNumber(int n[])
	{
		number = n;
	}
	public int[] getNumber()
	{
		return number;
	}
	

	public int[] initNumber()
	{
		int n[] = new int[4];
		int j = 0;
		for(j=0;j<4;j++)n[j]=0;
		
		try {
			ReadInputDiscretesRequest reqInputDiscrete = null;
			ReadCoilsRequest reqCoil = null;
			ReadInputRegistersRequest reqInputRegister = null;
			ReadMultipleRegistersRequest reqMultpipleRegister = null;
			
			//1. Open the connection
			con = new TCPMasterConnection(adresse);
			con.setPort(port);
			con.connect();

			//2. Prepare the transaction
			trans = new ModbusTCPTransaction(con);
			
			boolean b = true;
			int k = 0;
			//SimpleDigitalIn
			while(b)
			{
				try{
					reqInputDiscrete = new ReadInputDiscretesRequest(0, k);
					trans.setRequest(reqInputDiscrete);
					trans.execute();
					n[0]++;k++;
				}
				catch(ModbusSlaveException ex)
				{
					b = false;
				}
			}
			n[0]--;
			b = true;k = 0;
			//SimpleDigitalOut
			while(b)
			{
				try{
					reqCoil = new ReadCoilsRequest(0, k);
					trans.setRequest(reqCoil);
					trans.execute();
					n[1]++;k++;
				}
				catch(ModbusSlaveException ex)
				{
					b = false;
				}
			}
			n[1]--;
			b = true;k = 0;
			//SimpleInputRegister
			while(b)
			{
				try{
					reqInputRegister = new ReadInputRegistersRequest(0, k);
					trans.setRequest(reqInputRegister);
					trans.execute();
					n[2]++;k++;
				}
				catch(ModbusSlaveException ex)
				{
					b = false;
				}
			}
			n[2]--;
			b = true;k = 0;
			//SimpleRegister
			while(b)
			{
				try{
					reqMultpipleRegister = new ReadMultipleRegistersRequest(0, k);
					trans.setRequest(reqMultpipleRegister);
					trans.execute();
					n[3]++;k++;
				}
				catch(ModbusSlaveException ex)
				{
					b = false;
				}
			}
			n[3]--;
			//6. Close the connection
			con.close();


		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return n;
	}
	
	
	/**
	 * Correspond aux SimpleDigitalIn
	 * @param adresse
	 * @param reference
	 * @param counter
	 * @param sup
	 */
	public String ReadInputDiscrete(String reference, String counter, String ...sup)
	{
		try {
			/* The important instances of the classes mentioned before */
			ReadInputDiscretesRequest req = null; //the request
			ReadInputDiscretesResponse res = null; //the response

			System.out.println("ReadInputDiscrete");

			//1. Setup the parameters
			try {
				ref = Integer.decode(reference).intValue();
				count = Integer.decode(counter).intValue();
				//System.out.println("reference : "+ref);
				//System.out.println("count : "+count);
				if (sup.length == 1) {
					repeat = Integer.parseInt(sup[0]);
					//System.out.println("repeat : "+repeat);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(1);
			}
			//2. Open the connection
			con = new TCPMasterConnection(adresse);
			con.setPort(port);
			con.connect();

			//3. Prepare the request
			req = new ReadInputDiscretesRequest(ref, count);

			//4. Prepare the transaction
			trans = new ModbusTCPTransaction(con);
			trans.setRequest(req);

			//5. Execute the transaction repeat times
			int k = 0;
			do {
				trans.execute();
				res = (ReadInputDiscretesResponse) trans.getResponse();

				System.out.println("Digital Inputs Status=" + res.getDiscretes().toString());
				k++;
			} while (k < repeat);

			//6. Close the connection
			con.close();
			return res.getDiscretes().toString().substring(res.getDiscretes().toString().length()-count-1);

		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

	/**
	 * Correspond aux SimpleInputRegister
	 * @param adresse
	 * @param reference
	 * @param counter
	 * @param sup
	 */
	public int[] ReadInputRegister(String reference, String counter, String ...sup)
	{
		try {
			/* The important instances of the classes mentioned before */
			ReadInputRegistersRequest req = null; //the request
			ReadInputRegistersResponse res = null; //the response

			System.out.println("ReadInputRegister");

			//1. Setup the parameters
			try {
				ref = Integer.decode(reference).intValue();
				count = Integer.decode(counter).intValue();
				System.out.println("reference : "+ref);
				System.out.println("count : "+count);
				if (sup.length == 1) {
					repeat = Integer.parseInt(sup[0]);
					System.out.println("repeat : "+repeat);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(1);
			}
			//2. Open the connection
			con = new TCPMasterConnection(adresse);
			con.setPort(port);
			con.connect();

			//3. Prepare the request
			req = new ReadInputRegistersRequest(ref, count);

			//4. Prepare the transaction
			trans = new ModbusTCPTransaction(con);
			trans.setRequest(req);

			//5. Execute the transaction repeat times
			int k = 0;int s[] = new int[count];
			do {
				trans.execute();
				res = (ReadInputRegistersResponse) trans.getResponse();
				int i = 0;
				
				for(i=0;i<count;i++)
				{
					System.out.println("Registers "+i+" Inputs Status=" + res.getRegisterValue(i));
					s[i] = res.getRegisterValue(i);
				}
				k++;
			} while (k < repeat);

			//6. Close the connection
			con.close();
			return s;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	 /** 
	 * Correspond aux SimpleRegister
	 * @param adresse
	 * @param reference
	 * @param counter
	 * @param sup
	 */
	public int[] ReadRegister(String reference, String counter, String ...sup)
	{
		try {
			/* The important instances of the classes mentioned before */
			ReadMultipleRegistersRequest req = null; //the request
			ReadMultipleRegistersResponse res = null; //the response

			System.out.println("ReadRegister");

			//1. Setup the parameters
			try {
				ref = Integer.decode(reference).intValue();
				count = Integer.decode(counter).intValue();
				System.out.println("reference : "+ref);
				System.out.println("count : "+count);
				if (sup.length == 1) {
					repeat = Integer.parseInt(sup[0]);
					System.out.println("repeat : "+repeat);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(1);
			}
			//2. Open the connection
			con = new TCPMasterConnection(adresse);
			con.setPort(port);
			con.connect();

			//3. Prepare the request
			req = new ReadMultipleRegistersRequest(ref, count);

			//4. Prepare the transaction
			trans = new ModbusTCPTransaction(con);
			trans.setRequest(req);

			//5. Execute the transaction repeat times
			int k = 0;int s[] = new int[count];
			do {
				trans.execute();
				res = (ReadMultipleRegistersResponse) trans.getResponse();
				int i = 0;
				for(i=0;i<count;i++)
				{
					System.out.println("Registers "+i+" Status=" + res.getRegisterValue(i));
					s[i] = res.getRegisterValue(i);
				}
				k++;
			} while (k < repeat);

			//6. Close the connection
			con.close();
			return s;

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * Correspond aux SimpleDigitalOut
	 * @param adresse
	 * @param reference
	 * @param counter
	 * @param sup
	 */
	public synchronized String ReadCoil(String reference, String counter, String ...sup)
	{
		try {
			/* The important instances of the classes mentioned before */
			ReadCoilsRequest req = null; //the request
			ReadCoilsResponse res = null; //the response

			//System.out.println("ReadCoil");

			//1. Setup the parameters
			try {
				ref = Integer.decode(reference).intValue();
				count = Integer.decode(counter).intValue();
				//System.out.println("reference : "+ref);
				//System.out.println("count : "+count);
				if (sup.length == 1) {
					repeat = Integer.parseInt(sup[0]);
					//System.out.println("repeat : "+repeat);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(1);
			}
			//2. Open the connection
			con = new TCPMasterConnection(adresse);
			con.setPort(port);
			con.connect();

			//3. Prepare the request
			req = new ReadCoilsRequest(ref, count);

			//4. Prepare the transaction
			trans = new ModbusTCPTransaction(con);
			trans.setRequest(req);

			//5. Execute the transaction repeat times
			int k = 0;
			do {
				trans.execute();
				res = (ReadCoilsResponse) trans.getResponse();

				//System.out.println("Coils Status=" + res.getCoils());
				k++;
			} while (k < repeat);

			//6. Close the connection
			con.close();
			return res.getCoils().toString().substring(res.getCoils().toString().length()-count-1,res.getCoils().toString().length()-1);

		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

	/**
	 * Correspond aux SimpleDigitalOut
	 * @param adresse
	 * @param reference
	 * @param value
	 * @param sup
	 */
	public synchronized void WriteCoil(String reference, boolean value, String ...sup)
	{
		try {
			WriteCoilRequest req = null;
			WriteCoilResponse res = null;

			System.out.println("WriteCoil");			

			//1. Setup the parameters
			try {
				ref = Integer.decode(reference).intValue();
				System.out.println("reference : "+ref);
				if (sup.length == 1) {
					repeat = Integer.parseInt(sup[0]);
					System.out.println("repeat : "+repeat);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(1);
			}

			//2. Open the connection
			con = new TCPMasterConnection(adresse);
			con.setPort(port);
			con.connect();

			//3. Prepare the request
			req = new WriteCoilRequest(ref, value);

			//4. Prepare the transaction
			trans = new ModbusTCPTransaction(con);
			trans.setRequest(req);

			//5. Execute the transaction repeat times
			int k = 0;
			do {
				trans.execute();
				res = (WriteCoilResponse) trans.getResponse();
				System.out.println("Digital Inputs Status=" + res.getCoil());
				k++;
			} while (k < repeat);

			//6. Close the connection
			con.close();


		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Correspond aux SimpleDigitalOut
	 * @param adresse
	 * @param reference
	 * @param value
	 * @param sup
	 */
	public void WriteMultipleCoil(String reference, boolean value[], String ...sup)
	{
		try {
			WriteMultipleCoilsRequest req = null;
			WriteMultipleCoilsResponse res = null;

			System.out.println("WriteCoil");			

			//1. Setup the parameters
			try {
				ref = Integer.decode(reference).intValue();
				System.out.println("reference : "+ref);
				if (sup.length == 1) {
					repeat = Integer.parseInt(sup[0]);
					System.out.println("repeat : "+repeat);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(1);
			}

			//2. Open the connection
			con = new TCPMasterConnection(adresse);
			con.setPort(port);
			con.connect();

			//3. Prepare the request
			int i = 0;
			BitVector bv = new BitVector(value.length);
			for(i = 0;i<value.length;i++)
			{
				bv.setBit(i, value[i]);
			}
			req = new WriteMultipleCoilsRequest(ref, bv);

			//4. Prepare the transaction
			trans = new ModbusTCPTransaction(con);
			trans.setRequest(req);

			//5. Execute the transaction repeat times
			int k = 0;
			do {
				trans.execute();
				res = (WriteMultipleCoilsResponse) trans.getResponse();
				System.out.println("Digital Inputs Status=" + res.toString());
				k++;
			} while (k < repeat);

			//6. Close the connection
			con.close();


		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Correspond aux SimpleRegister
	 * @param adresse
	 * @param reference
	 * @param counter
	 * @param sup
	 */
	public void WriteRegister(String reference, int value, String ...sup)
	{
		try {
			/* The important instances of the classes mentioned before */
			WriteSingleRegisterRequest req = null; //the request
			WriteSingleRegisterResponse res = null; //the response

			System.out.println("WriteRegister");

			//1. Setup the parameters
			try {
				ref = Integer.decode(reference).intValue();
				System.out.println("reference : "+ref);
				System.out.println("count : "+count);
				if (sup.length == 1) {
					repeat = Integer.parseInt(sup[0]);
					System.out.println("repeat : "+repeat);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(1);
			}
			//2. Open the connection
			con = new TCPMasterConnection(adresse);
			con.setPort(port);
			con.connect();

			//3. Prepare the request
			Register reg = new SimpleInputRegister(value);
			req = new WriteSingleRegisterRequest(ref, reg);


			//4. Prepare the transaction
			trans = new ModbusTCPTransaction(con);
			trans.setRequest(req);

			//5. Execute the transaction repeat times
			int k = 0;
			do {
				trans.execute();
				res = (WriteSingleRegisterResponse) trans.getResponse();

				System.out.println("Registers Inputs Status=" + res.getRegisterValue());
				k++;
			} while (k < repeat);

			//6. Close the connection
			con.close();


		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Correspond aux SimpleRegister
	 * @param adresse
	 * @param reference
	 * @param counter
	 * @param sup
	 */
	public void WriteMultipleRegister(String reference, int value[], String ...sup)
	{
		try {
			/* The important instances of the classes mentioned before */
			WriteMultipleRegistersRequest req = null; //the request
			WriteMultipleRegistersResponse res = null; //the response

			System.out.println("WriteRegister");

			//1. Setup the parameters
			try {
				ref = Integer.decode(reference).intValue();
				System.out.println("reference : "+ref);
				System.out.println("count : "+count);
				if (sup.length == 1) {
					repeat = Integer.parseInt(sup[0]);
					System.out.println("repeat : "+repeat);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				System.exit(1);
			}
			//2. Open the connection
			con = new TCPMasterConnection(adresse);
			con.setPort(port);
			con.connect();

			//3. Prepare the request
			Register reg[] = new SimpleInputRegister[value.length];
			int i = 0;
			for(i=0;i<value.length;i++)
			{
				reg[i] = new SimpleInputRegister();
				reg[i].setValue(value[i]);
			}
			req = new WriteMultipleRegistersRequest(ref, reg);


			//4. Prepare the transaction
			trans = new ModbusTCPTransaction(con);
			trans.setRequest(req);

			//5. Execute the transaction repeat times
			int k = 0;
			do {
				trans.execute();
				res = (WriteMultipleRegistersResponse) trans.getResponse();
				k++;
			} while (k < repeat);

			//6. Close the connection
			con.close();


		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
