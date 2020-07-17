import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;


public class DailyCaloriesReport {
	
	private static final DateFormat mpDateFormat = new SimpleDateFormat("yyyy/MM/dd");
	private static Formatter x;
	String mDate;
	int mCalories = 0;
	float mWeight = 0;
	private int mpCar = 0;
	private int mpPro = 0;
	private int mpFat = 0;


	public void SetDate( String date ) throws Throwable {
		mDate = date;
	} // SetDate()

	public String GetDate() throws Throwable {
		return mDate;
	} // GetDate()

	public void SetCalories( int calories ) throws Throwable {
		mCalories = calories;
	} // SetCalories()

	public int GetCalories() throws Throwable {
		return mCalories;
	} // GetCalories()
	
	public void SetWeight( float weight ) throws Throwable {
		mWeight = weight;
	} // SetWeight()

	public float GetWeight() throws Throwable {
		return mWeight;
	} // GetWeight()
	
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
		
	
	static String GetCurrentDate() { // 當天的日期
		Date date = new Date();
		// System.out.println( mpDateFormat.format( date ) );
	  return mpDateFormat.format( date );
		
	} // GetCurrentDate()
	
	
	static void OpenWriteFile() throws Throwable {
    
    try { 
      FileWriter filewriter = new FileWriter ( "DailyReport.txt", true );
			x = new Formatter ( filewriter );
    
    } // try
    catch ( Exception e ) {
      System.out.println( "smthing is wrong." );
    } // catch()
  
  } // OpenWriteFile()
	
	static void AddData( String currentDate, int calories, float Weight, int car, int pro, int fat ) throws Throwable {
    x.format( "%s ", currentDate );
    x.format( "%d ", calories );
		x.format( "%d ", car );
		x.format( "%d ", pro );
		x.format( "%d ", fat );
		x.format( "%f\n", Weight );
		
    
    
    
  } // AddData()
  
  static void CloseWriteFile() {
    x.close();
  } // CloseFile()
  
  
  static boolean OpenReadFile() throws Throwable {
    
    try {
      G.sFileReader = new Scanner( new File( "DailyReport.txt" ) );
      // System.out.println( "檔案找到了:)" );
      return true;
    } // try
    catch( FileNotFoundException e ) {
      return false;
    } // catch
    
    
  } // CreateTxt
  
	
	static void ReadData() throws Throwable {
		
		int i = 0;
    G.sFileReader = new Scanner( new File( "DailyReport.txt" ) );
		G.sListOfDate = new ArrayList<DailyCaloriesReport>(); // 洗掉之前的從頭讀
		while ( G.sFileReader.hasNext() ) {
			
			G.sListOfDate.add( new DailyCaloriesReport() );
			G.sListOfDate.get( i ).SetDate( G.sFileReader.next() );
			G.sListOfDate.get( i ).SetCalories( G.sFileReader.nextInt() );
			G.sListOfDate.get( i ).SetCar( G.sFileReader.nextInt() );
			G.sListOfDate.get( i ).SetPro( G.sFileReader.nextInt() );
			G.sListOfDate.get( i ).SetFat( G.sFileReader.nextInt() );
			G.sListOfDate.get( i ).SetWeight( G.sFileReader.nextFloat() );
		  i++;
							
		} // while ( !eof )
		

  
  } // ReadData()
  
  static void CloseReadFile() {
    G.sFileReader.close();
  } // CloseFile()
} // class DateTime
