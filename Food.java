

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;

public class Food {
	
	private String mpName;
	private int mpCalories;
	private String mpQuantifier;
	
	private static Formatter x;
	
	
	
	void SetName ( String name ) {
		mpName = name;
	} // SetName()
	
	String GetName () {
		return mpName;
	} // GetName()
	
	void SetQuantifier ( String quantifier ) {
		mpQuantifier = quantifier;
	} // SetQuantifier()
	
	String GetQuantifier () {
		return mpQuantifier;
	} // GetQuantifier()
	
	void SetCalories ( int calories ) {
		mpCalories = calories;
	} // SetCalories()
	
	int GetCalories () {
		return mpCalories;
	} // GetCalories()
	
	
	static void OpenAppendWriteFile() throws Throwable {
    
    try { 
      FileWriter filewriter = new FileWriter ( "UserInput.txt", true );
			x = new Formatter ( filewriter );
    
    } // try
    catch ( Exception e ) {
      System.out.println( "smthing is wrong." );
    } // catch()
  
  } // OpenAppendWriteFile()
	
	static void OpenWriteFile() throws Throwable {
    
    try { 
			x = new Formatter ( "UserInput.txt" );
    
    } // try
    catch ( Exception e ) {
      System.out.println( "smthing is wrong." );
    } // catch()
  
  } // OpenWriteFile()
	
	static void AddData( String foodName, String quantifier, int calories ) throws Throwable {
    x.format( "%s ", foodName );
		x.format( "%s ", quantifier );
    x.format( "%d\n", calories );
    
    
    
  } // AddData()
  
  static void CloseWriteFile() {
    x.close();
  } // CloseFile()
  
  
  static boolean OpenReadFile() throws Throwable {
    
    try {
      G.sFileReader = new Scanner( new File( "UserInput.txt" ) );
      // System.out.println( "檔案找到了:)" );
      return true;
    } // try
    catch( FileNotFoundException e ) {
      return false;
    } // catch
    
    
  } // CreateTxt
  
	
	static void ReadData() throws Throwable {
		
		int i = 0;
    G.sFileReader = new Scanner( new File( "UserInput.txt" ) );

		while ( G.sFileReader.hasNext() ) {
			G.sListOfFood.add( new Food() );
			G.sListOfFood.get( i ).SetName( G.sFileReader.next() );
			G.sListOfFood.get( i ).SetQuantifier( G.sFileReader.next() );
			G.sListOfFood.get( i ).SetCalories( G.sFileReader.nextInt() );
		  i++;
							
		} // while ( !eof )
		

  
  } // ReadData()
  
  static void CloseReadFile() {
    G.sFileReader.close();
  } // CloseFile()
  

	
	
} // class Food
