import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class passwordstrength
{
	
	  public static void main (String[] args) throws IOException

	  {
		
	    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	    String password; 
	    System.out.println("Enter your password: ");
	    password = br.readLine();
	    passwordstrength pws = new passwordstrength();
	    pws.getPasswordStrength(password);
	    
	  }
	  
	  public void getPasswordStrength(String password) throws FileNotFoundException
	  {
		  
		  
		  Pattern pat = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		  Matcher mat = pat.matcher(password);
		  boolean containsSpecial = mat.find();
		  int length = password.length();
		  int strength= 0;
		  boolean containsNumbers = password.matches(".*\\d+.*");
		  boolean containsLowercase = !password.equals(password.toUpperCase());
		  boolean containsUppercase = !password.equals(password.toLowerCase());
		  if(length >=8)
		  {
			  //increase strength by 1 point if the password length is more than 7
			  strength+=1;
			  System.out.println("Length criteria matched(>=8)");
		  }  
		  if(containsUppercase)
		  {
			//increase strength by 1 point if the password contains upper case alphabet
			  strength += 1;
			  System.out.println("Contains Upper Case alphabet[A-Z]");
		  }
		  if(containsLowercase)
		  {
			  //increase strength by 1 point if the password contains lower case alphabet
			  strength += 1;
			  System.out.println("Contais Lower Case alphabet[a-z]");
		  }
		  if(containsNumbers)
		  {
			  //increase strength by 1 point if the password contains numbers
			  strength += 1;
			  System.out.println("Contains numbers[0-9]");
		  }
		  if(containsSpecial)
		  {
			//increase strength by 3 points if the password contains special symbols
			  strength += 3;
			  System.out.println("Contains Special Characters");
		  }
		  FileReader file = new FileReader("words");
		  Scanner sc = new Scanner(file);
		  List<String> list=new ArrayList<>();
		  while(sc.hasNextLine()){
			  list.add(sc.nextLine().toLowerCase()); 
		  }
		  if(list.contains(password)){
			  System.out.println("Your password contains dictionary words");
		  }
		  else
		  {
			  String pwd=password.toLowerCase();
			  pwd=pwd.replaceAll("[\\d]", "");
			  pwd=pwd.replaceAll("[!@#$%^&*()_+-.:;,]", "");
			  if(list.contains(pwd))
			  {
				  System.out.println("Your password contains dictionary words");
			  }
			  else
			  {
				//increase strength by 3 points if the password is not a dictionary word
				  strength+=3;
				  System.out.println("Your password doesn't contain dictionary words");
			  }
		  }
		 
		  sc.close();
		  if(strength>=0&&strength<4){
		  System.out.println("Your password is Weak.\nPassword Strength: "+strength);
		  }
		  else if(strength>=4&&strength<7){
			  System.out.println("Your password is Fair.\nPassword Strength: "+strength);
			  }
		  else{
			  System.out.println("Your password is Strong.\nPassword Strength: "+strength);
			  }
	  }
}
