import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;


public class Nutrition {
	
	private static Formatter x;
	private String mpFoodName = "";
	private int mpCar = 0;
	private int mpPro = 0;
	private int mpFat = 0;


	public void SetFoodName( String foodName ) throws Throwable {
		mpFoodName = foodName;
	} // SetFoodName()

	public String GetFoodName() throws Throwable {
		return mpFoodName;
	} // GetFoodName()

	
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
		
	
	
	static void OpenWriteFile() throws Throwable {
    
    try { 
      x = new Formatter ( "Nutrition.txt" );
    
    } // try
    catch ( Exception e ) {
      System.out.println( "smthing is wrong." );
    } // catch()
  
  } // OpenAppendWriteFile()
	
	
	
	static void AddData() throws Throwable {
		for ( int i = 0 ; i < G.sListOfNutrition.size() ; i++ ) {
			x.format( "%s ", G.sListOfNutrition.get( i ).GetFoodName() );
			x.format( "%d ", G.sListOfNutrition.get( i ).GetCar() );
			x.format( "%d ", G.sListOfNutrition.get( i ).GetPro() );
			x.format( "%d ", G.sListOfNutrition.get( i ).GetFat() );
		
		} // for()
    
		
		
    
    
    
  } // AddData()
  
  static void CloseWriteFile() {
    x.close();
  } // CloseFile()
  
  
  static boolean OpenReadFile() throws Throwable {
    
    try {
      G.sFileReader = new Scanner( new File( "Nutrition.txt" ) );
      // System.out.println( "檔案找到了:)" );
      return true;
    } // try
    catch( FileNotFoundException e ) {
      return false;
    } // catch
    
    
  } // CreateTxt
  
	
	static void ReadData() throws Throwable {
		
		int i = 0;
    G.sFileReader = new Scanner( new File( "Nutrition.txt" ) );
		G.sListOfNutrition = new ArrayList<Nutrition>();
		while ( G.sFileReader.hasNext() ) {
			
			G.sListOfNutrition.add( new Nutrition() );
			G.sListOfNutrition.get( i ).SetFoodName( G.sFileReader.next() );
			G.sListOfNutrition.get( i ).SetCar( G.sFileReader.nextInt() );
			G.sListOfNutrition.get( i ).SetPro( G.sFileReader.nextInt() );
			G.sListOfNutrition.get( i ).SetFat( G.sFileReader.nextInt() );

		  i++;
							
		} // while ( !eof )
		

  
  } // ReadData()
  
  static void CloseReadFile() {
    G.sFileReader.close();
  } // CloseFile()
} // class DateTime
