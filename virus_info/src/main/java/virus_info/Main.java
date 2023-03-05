package virus_info;

import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

public class Main {
	private String massage;
	
	public Main() {   //constractor
		super();
		this.massage = Encryption("hello world");
	}
	
	public String getMassage() {  //getter for massage
		return this.massage;
	} 
	
	public void setMassage(String msj) {   //setter for massage
		this.massage = msj;
	}
	
	public void Display() {     //function to display the plan text
		System.out.println(this.massage);
	}
	
	public String HashFunction(byte[] msj, String algo) {     // hash function to get hash value of encrypted text
		String hash="";
		try {
			MessageDigest msj_d = MessageDigest.getInstance(algo);
			msj_d.update(msj);
			byte[] d_bytes = msj_d.digest();
			hash= DatatypeConverter.printHexBinary(d_bytes).toLowerCase();
		}
		catch(Exception e) {
			
		}
		return hash;
	}
	
	public String Encryption(String msj) { //function to encrypt plan text
		int size = msj.length();    //getting length
	//	System.out.println(size);    //printing size
		int[] ascii_array = new int[size+1];   //array to store ascii values of each char of massage
		int maximum = 130, minimum = 10;     
		int key = (int)(Math.random()*(maximum-minimum+1)+minimum); //genrating rundom key
		String encrypted="";       //will be the encrypted massage in the end
		
		
		for(int i=0;i<size;i++)     //converting into ascii and adding key to each value
		{
			ascii_array[i] = msj.charAt(i)+key;
		}
		
		
		ascii_array[size]=key;      //storing key in the array 
		
		
//		for(int i=0;i<size+1;i++)    //printing the converted array 
//		System.out.print(ascii_array[i]+" ");
//		System.out.print("\n\n\n\n");
		
		for(int i=0;i<size+1;i++)     // converting to ascii again
		{
			encrypted+=Character.toString(ascii_array[i]);
		}
		
		this.setMassage(encrypted);    //final encrypted massage
		return encrypted;
	}
	
	public void decryption(String msj) { //function to decrypt the encrypted massage
		int size = msj.length();               //getting length
		int[] ascii_array = new int[size];     //array to store ascii values of massage
	//	System.out.println(size);               //printing size(length) of array 
		int key=msj.charAt(size-1);             // getting key (ascii to decimal conversion)
		String original="";                //for storing the decrypted massage
		for(int i=0;i<size-1;i++)                // for decryption
		{
			ascii_array[i] =msj.charAt(i)-key;
		//	System.out.print(ascii_array[i]+ "  ");
			original+=Character.toString(ascii_array[i]);
			
			
		}
		System.out.print("\n\n\n\n");
		this.setMassage(original);          //final decrypted massage
		
		
	}
	public static void main(String[] args){   //driver function
		Main obj = new Main();
		System.out.print("Encrypted Text at the start: ");
		obj.Display();
		System.out.println("\n\nSignature using SHA-1: "+ obj.HashFunction(obj.getMassage().getBytes(), "SHA-1"));
		System.out.print("\n\n\n################   Decryption call   ################\n\n");
		obj.decryption(obj.getMassage());
		System.out.print("Decrypted Text:   ");
		obj.Display();
		
		System.out.print("\n\n\n################   Encryption call again  ################\n\n");
		obj.Encryption(obj.getMassage());
		System.out.print("Encrypted Text:  ");
		obj.Display();
		System.out.println("\n\nSignature: "+ obj.HashFunction(obj.getMassage().getBytes(), "SHA-1"));
		System.out.print("\n\n\n################   Decryption call again  ################\n\n");
		
		obj.decryption(obj.getMassage());
		System.out.print("Decrypted Text:   ");
		obj.Display();
		
		
	}
}
