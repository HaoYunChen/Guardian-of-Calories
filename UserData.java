
import java.util.*;
import java.io.*;
import java.lang.*;

public class UserData {

  static Formatter x;

  public static void GetUserData() throws Throwable {
    

    String tempString = "";
		boolean test = false;
    char tempChar;
		boolean yes;
    

    System.out.println( "歡迎使用卡路里守護者！，告訴我你的名字或綽號吧！\\(≧ω≦)/ :" );
		System.out.print( ">> " );
    G.sName = G.sln.nextLine();
		G.clearScreen();
		System.out.println( "" );
		
    System.out.println( "確定你叫" + G.sName + "嗎？ （　是／否　）" );
    
    
		
		yes = G.YesOrNo(); // 是否
		

    if ( !yes ) {

      System.out.println( "再跟我說一次你的名字或綽號是什麼吧！ \\(≧ω≦)/" );
			System.out.print( ">> " );
      G.sName = G.sln.next();
			G.clearScreen();
			System.out.println( "" );
		
			

      System.out.println( G.sName + "你好！歡迎使用卡路里守護者 \\(^ω^)/" );
    } // if ( 要更改名稱 )
    else if ( yes ) {
      System.out.println( G.sName + "你好！歡迎使用卡路里守護者 \\(^ω^)/" );

    } // else if ( 否 )

  
    
		do {
			G.sGender = "";
			System.out.println( "你是男生還是女生呢？　（　男／女　）：" ) ; 
			while ( !G.sGender.equals( "男" ) && !G.sGender.equals( "女" ) ) {
				System.out.print( ">> " );
				G.sGender = G.sln.next();
				G.clearScreen();
				System.out.println( "" );
				
				if ( !G.sGender.equals( "男" ) && !G.sGender.equals( "女" ) ) {
					System.out.println( "輸入錯誤，請再輸入一次" );
				} // if ()
			
			} // while ()
			
			

			System.out.println( "你幾歲呢？：" ) ;
			G.sAge = G.InputInt();
			
			System.out.println( "你的性別是：" + G.sGender + "生" );
			System.out.println( "你的年齡是：" + G.sAge + "歲" );
			System.out.println( "確定嗎？ （　是／否　）" );
			yes = G.YesOrNo();
			
		} while ( !yes ); /////
		
    
    do {
			System.out.println( "請輸入身高：" ) ; 
			G.sHeight = G.InputInt();

			System.out.println( "請輸入體重：" ) ;
			G.sWeight = G.InputFloat();
			
			System.out.println( "你的身高是 " + G.sHeight + " 公分" );
			System.out.println( "你的體重是 " + G.sWeight + " 公斤" );
			System.out.println( "確定嗎？ （　是／否　）" );
			yes = G.YesOrNo();
			
		} while ( !yes );
		
    CalCount calcount = new CalCount(); 
    
    calcount.CountIdealWeight();  // 算一下
    
		System.out.println( "你現在的體重是：" + G.sWeight + " 公斤" );
    System.out.println( "而你的理想體重應該要是：" + G.sIdealWeight + " 公斤" );
    System.out.println( "你想要更改理想體重嗎？（　是／否　）" );
    
    yes = G.YesOrNo();
    
    
    if ( yes ) {
     
      System.out.println( "請輸入您的理想體重：" );
			G.sIdealWeight = G.InputFloat();
      
      
      System.out.println( "您現在的理想體重為：" + G.sIdealWeight + " 公斤" );
    } // if ( 要更改理想體重 )
    
    
    System.out.println( "請輸入活動量：( 無, 輕度, 中度, 重度 )\n" ) ; 
		System.out.println( "無：從事輕度活動，如看書、看電視、駕駛、打電腦、辦公事務等不會流汗者。\n" );
		System.out.println( "輕度：一天約１小時不激烈的動態活動，如步行、伸展操、逛街、打掃收拾等。\n" );
		System.out.println( "中度：從事中度勞動量的工作，如站立工作者、護士、業務等。或是一天約１小時較強態活動，如快走、爬樓梯、舞蹈、騎腳踏車。\n" );
		System.out.println( "重度：從事重度勞動量的工作，如重物搬運的勞動者，或一天約有１小時激烈運動，如游泳、登山、足球、網球等會大量流汗者。\n" );
		System.out.print( ">> " );
    G.sWorkOutType = G.sln.next();
		G.clearScreen();
		System.out.println( "" );
		
    
    while ( !test ) {


      if ( G.sWorkOutType.equals( "無" ) || G.sWorkOutType.equals( "輕度" )
           || G.sWorkOutType.equals( "中度" ) || G.sWorkOutType.equals( "重度" ) ) {
        test = true;
      } // if ()
      else {
        System.out.println( "輸入錯誤，請再試一次");

        G.sWorkOutType = G.sln.next();
				
      } // else
    
    } // while ( !test )
    test = false;
    

    calcount.CountDailyCal();
  
    System.out.println( "您距離您的理想體重只剩 " + ( G.sWeight - G.sIdealWeight ) + " 公斤！" );
    System.out.println( G.sName + "我們一起加油唷 \\(^ω^)/" );
		
		G.PressAnyKeyToContinue();
  
  } // GetUserData()
	
	
	
	
	// 讀檔寫檔 //
  
  static void OpenWriteFile() throws Throwable {
    
    try { 
      x = new Formatter ( "GOCData.txt" );
    
    } // try
    catch ( Exception e ) {
      System.out.println( "smthing is wrong." );
    } // catch()
  
  } // OpenWriteFile()
  
  
  static boolean OpenReadFile() throws Throwable {
    
    try {
      G.sFileReader = new Scanner( new File( "GOCData.txt" ) );
      // System.out.println( "檔案找到了:)" );
      return true;
    } // try
    catch( FileNotFoundException e ) {
      return false;
    } // catch
    
    
  } // CreateTxt
  static void AddData() throws Throwable {
    x.format( "%s " , G.sName );
    x.format( "%d ", G.sHeight );
    x.format( "%f ", G.sWeight );
    x.format( "%f ", G.sIdealWeight );
    x.format( "%s ", G.sWorkOutType );
		x.format( "%d ", G.sSumOfCalories );
		x.format( "%s ", G.sCurrentDate );
		x.format( "%d ", G.sDailyCal );
		x.format( "%d ", G.sBasicCarbohydrates );
		x.format( "%d ", G.sBasicProtein );
		x.format( "%d ", G.sBasicFat );
		
		x.format( "%d ", G.sBasicCarbohydratesSoFar );
		x.format( "%d ", G.sBasicProteinSoFar );
		x.format( "%d ", G.sBasicFatSoFar );
		x.format( "%s ", G.sGender );
		x.format( "%d ", G.sAge );
    
    
  } // AddData()
  
  static void CloseWriteFile() {
    x.close();
  } // CloseFile()
  
  static void CloseReadFile() {
    G.sFileReader.close();
  } // CloseFile()
  
  static void ReadData() throws Throwable {
    G.sFileReader = new Scanner( new File( "GOCData.txt" ) );
    G.sName = G.sFileReader.next();
    G.sHeight = G.sFileReader.nextInt();
    G.sWeight = G.sFileReader.nextFloat();
    G.sIdealWeight = G.sFileReader.nextFloat();
    G.sWorkOutType = G.sFileReader.next();
		G.sSumOfCalories = G.sFileReader.nextInt();
		G.sCurrentDate = G.sFileReader.next();
    G.sDailyCal = G.sFileReader.nextInt();
		G.sBasicCarbohydrates = G.sFileReader.nextInt();
		G.sBasicProtein = G.sFileReader.nextInt();
		G.sBasicFat = G.sFileReader.nextInt();
		G.sBasicCarbohydratesSoFar = G.sFileReader.nextInt();
		G.sBasicProteinSoFar = G.sFileReader.nextInt();
		G.sBasicFatSoFar = G.sFileReader.nextInt();
		G.sGender = G.sFileReader.next();
    G.sAge = G.sFileReader.nextInt();
  
  } // ReadData()

  
}
