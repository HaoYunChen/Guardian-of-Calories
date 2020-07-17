
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Formatter;
import java.util.Scanner;


public class FoodSoFar {
	
	private String mpFoodName = "";
	private float mpQuantity = 0;
	private String mpQuantifier = "";
	private int mpCalories = 0;
	
	private int mpCar = 0;
	private int mpPro = 0;
	private int mpFat = 0;
	
	private static Formatter x;
	
	
	void SetName ( String name ) {
		mpFoodName = name;
	} // SetName()
	
	String GetName () {
		return mpFoodName;
	} // GetName()
	
	void SetQuantifier ( String quantifier ) {
		mpQuantifier = quantifier;
	} // SetQuantifier()
	
	String GetQuantifier () {
		return mpQuantifier;
	} // GetQuantifier()
	
	void SetQuantity ( float quantity ) {
		mpQuantity = quantity;
	} // SetQuantity()
	
	float GetQuantity () {
		return mpQuantity;
	} // GetQuantity()
	
	
	void SetCalories ( int calories ) {
		mpCalories = calories;
	} // SetCalories()
	
	int GetCalories () {
		return mpCalories;
	} // GetCalories()
	
	void SetCar ( int car ) {
		mpCar = car;
	} // SetCar()
	
	int GetCar () {
		return mpCar;
	} // GetCar()
	
	void SetPro ( int pro ) {
		mpPro = pro;
	} // SetPro()
	
	int GetPro () {
		return mpPro;
	} // GetPro()
	
	void SetFat ( int fat ) {
		mpFat = fat;
	} // SetFat()
	
	int GetFat () {
		return mpFat;
	} // GetFat()
	
		static void OpenAppendWriteFile() throws Throwable {
    
    try { 
      FileWriter filewriter = new FileWriter ( "FoodSoFar.txt", true );
			x = new Formatter ( filewriter );
    
    } // try
    catch ( Exception e ) {
      System.out.println( "smthing is wrong." );
    } // catch()
  
  } // OpenAppendWriteFile()
		
	static void OpenWriteFile() throws Throwable {
    
    try { 
      x = new Formatter ( "FoodSoFar.txt" );
    
    } // try
    catch ( Exception e ) {
      System.out.println( "smthing is wrong." );
    } // catch()
  
  } // OpenWriteFile()
	
	static void AddData( float quantity, String quantifier, String foodName, int calories, int car, int pro, int fat ) throws Throwable {
    x.format( "%f ", quantity );
		x.format( "%s ", quantifier );
		x.format( "%s ", foodName );
		x.format( "%d ", car );
		x.format( "%d ", pro );
		x.format( "%d ", fat );
    x.format( "%d \n\n", calories );
		
    
    
    
  } // AddData()
  
  static void CloseWriteFile() {
    x.close();
  } // CloseFile()
  
  
  static boolean OpenReadFile() throws Throwable {
    
    try {
      G.sFileReader = new Scanner( new File( "FoodSoFar.txt" ) );
      // System.out.println( "檔案找到了:)" );
      return true;
    } // try
    catch( FileNotFoundException e ) {
      return false;
    } // catch
    
    
  } // CreateTxt
  
	
	static void ReadData() throws Throwable {
			int i = 0;
			G.sFileReader = new Scanner( new File( "FoodSoFar.txt" ) );
		try {
			
			
			while ( G.sFileReader.hasNext() ) {
				G.sListOfFoodSoFar.add( new FoodSoFar() );
				G.sListOfFoodSoFar.get( i ).SetQuantity( G.sFileReader.nextFloat() );
				G.sListOfFoodSoFar.get( i ).SetQuantifier( G.sFileReader.next() );
				G.sListOfFoodSoFar.get( i ).SetName( G.sFileReader.next() );
				G.sListOfFoodSoFar.get( i ).SetCar( G.sFileReader.nextInt() );
				G.sListOfFoodSoFar.get( i ).SetPro( G.sFileReader.nextInt() );
				G.sListOfFoodSoFar.get( i ).SetFat( G.sFileReader.nextInt() );
				G.sListOfFoodSoFar.get( i ).SetCalories( G.sFileReader.nextInt() );
				
				i++;
							
		} // while ( !eof )
			
		} // try
		catch( Exception e ) {
		
		} // catch
		
		
		

  
  } // ReadData()
  
  static void CloseReadFile() {
    G.sFileReader.close();
  } // CloseFile()
	
	
} // class FoodSoFar
