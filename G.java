import java.util.*;
import java.io.*;
import java.lang.*;



class G{
  

  static Scanner sln = null;
  static Scanner sFileReader = null;
  static int sTestNum = -1;
  
  static void Init() throws Throwable {
    sln = new Scanner( System.in );
		sListOfFood = new ArrayList<Food>();
		sListOfDate = new ArrayList<DailyCaloriesReport>();
		sListOfFoodSoFar = new ArrayList<FoodSoFar>();
		sListOfNutrition = new ArrayList<Nutrition> ();
  } // Init()
  
	static String sGender = "";
	static int sAge = 0;
  static String sName = "";
  static int sHeight = 0;
  static float sWeight = 0;
  static float sIdealWeight = 0;
  static String sWorkOutType = "";
	static int sSumOfCalories = 0;
	
	// 三大營養素
	static int sBasicProtein = 0;
	static int sBasicFat = 0;
	static int sBasicCarbohydrates = 0;
	
	static int sBasicProteinSoFar = 0;
	static int sBasicFatSoFar = 0;
	static int sBasicCarbohydratesSoFar = 0;
	
	static ArrayList<Food> sListOfFood; // 使用者新增的食物
	static ArrayList<DailyCaloriesReport> sListOfDate; // 過去使用者的卡路里紀錄
	static ArrayList<FoodSoFar> sListOfFoodSoFar; // 使用者當日到目前輸入過的
	static ArrayList<Nutrition> sListOfNutrition;
	static String sCurrentDate = "";
  
  static int sDailyCal = 0; // 每日所需卡路里
  
  static final int sGrain = 70; // 一份全榖的熱量
  static final int sLowMilk = 120; // 一份低奶類的熱量
  static final int sHighMilk = 150; // 一份高奶類的熱量
  static final int sLowMeat = 55; // 一份低蛋豆魚肉熱量
  static final int sMidMeat = 75; // 一份中蛋豆魚肉熱量
  static final int sHighMeat = 120; // 一份高蛋豆魚肉熱量
  static final int sFat = 45; // 一份脂肪熱量
  static final int sFruit = 60; // 一份水果熱量
  static final int sVage = 25; // 一份蔬菜熱量

	static boolean YesOrNo() {
		
		String stringTemp;
		boolean test = false;
		
		while ( !test ) {
			System.out.print( ">> " );
			stringTemp = sln.next();
			G.clearScreen();
			System.out.println( "" );
			
      if ( stringTemp.equals( "是" ) || stringTemp.equals( "否" ) ) {
        test = true;
				if ( stringTemp.equals( "是" ) ) {
				  return true;
				} // if ( 是 )
				else {
				  return false;
				} // else ( 否 )
      } // if ()
      else {
        System.out.println( "輸入錯誤，請再試一次 ┴┴~\\(‵□′#) （　是／否　） ");
        // System.out.println( "您所輸入的字串為: |" + stringTemp + "|" );
        
      } // else

    } // while ( !test )
		
		return false;
		
	
	} // YesNo()
	
	
	static int InputInt() {
		
		boolean test = false;
		int tempInt = 0;
		String tempString;
		
		while ( !test ) {
				 
					try {
						System.out.print( ">> " );
						tempInt = sln.nextInt();
						G.clearScreen();
						System.out.println( "" );
						

						test = true;
					} // try
					catch( InputMismatchException e ) {
						System.out.println( "輸入錯誤，請再試一次 ┴┴~╰(‵□′#)" );
						tempString = sln.nextLine();
					} // catch

				} // while() 
		
		return tempInt;
	
	} // InputInt()
	
	static float InputFloat() {
		
		boolean test = false;
		float tempFloat = 0;
		String tempString;
		
		while ( !test ) {
				 
					try {
						System.out.print( ">> " );
						tempFloat = sln.nextFloat();
						G.clearScreen();
						System.out.println( "" );

						test = true;
					} // try
					catch( InputMismatchException e ) {
						System.out.println( "輸入錯誤，請再試一次 ┴┴~\\(‵□′#)" );
						tempString = sln.nextLine();
					} // catch

				} // while() 
		
		return tempFloat;
	
	} // InputInt()
  
	static void PressAnyKeyToContinue() throws Throwable { 
				
		System.out.println("\n請按下Enter繼續唷...ε=ε=ε=ε=ε=ε=(￣▽￣)╯");
		try {
				System.in.read();
		} // try  
		catch(Exception e) {
		} // catch
		System.out.println("");
  } // PressAnyKeyToContinue()
	
	static void EditUserFood() throws Throwable {
		String foodName;
		int newCalories;
		int i = 0;
		boolean yes;
		
		System.out.println( "請輸入要編輯的食物的名稱：" );
		System.out.print( ">> " );
		foodName = sln.next();
		G.clearScreen();
		System.out.println();
		
		do {
			while ( i < sListOfFood.size() && !foodName.equals( sListOfFood.get( i ).GetName() ) ) {
				i++;
			} // while ( 找找看 )
			
		
			if ( i == sListOfFood.size() ) {
				System.out.println("你似乎沒有輸入過" + foodName + "唷~" );
				System.out.println( "是否再試一次呢？（　是／否　）" );
				yes = YesOrNo();
				
				if ( yes ) {
					System.out.println( "請輸入要更改卡路里的食物的名稱：" );
					System.out.println( ">> " );
					foodName = sln.next();
					G.clearScreen();
					
				} // if ( yes )
			} // if ()
			else {
				System.out.println( "你要更改" + foodName + "的名稱嗎？　（　是／否　）" );
				yes = YesOrNo();
				if ( yes ) {
					System.out.println( "給他一個新名字吧！：" );
					System.out.print( ">> " );
					foodName = sln.next();
					G.clearScreen();
					sListOfFood.get( i ).SetName( foodName );
					
				} // if ( yes )
				
				System.out.println( "你要更改" + foodName + "的量詞嗎？　（　是／否　）" );
				yes = YesOrNo();
				if ( yes ) {
					System.out.println( "給他一個新量詞吧！：" );
					System.out.print( ">> " );
					sListOfFood.get( i ).SetQuantifier( sln.next() );
					G.clearScreen();
				} // if ( yes )
				
				System.out.println( "你要更改" + foodName + "的卡路里嗎？　（　是／否　）" );
				yes = YesOrNo();
				if ( yes ) {
					System.out.println( foodName + "的熱量是多少大卡呢？" );
					sListOfFood.get( i ).SetCalories( InputInt() );
					G.clearScreen();
				} // if ( yes )
				
				System.out.println( "你要更改" + foodName + "的營養素嗎？　（　是／否　）" );
				yes = YesOrNo();
				if ( yes ) {
					System.out.println( "請輸入" + foodName + "的碳水化合物克數：" );
					sListOfNutrition.get( i ).SetCar( InputInt() );

					System.out.println( "請輸入" + foodName + "的蛋白質克數：" );
					sListOfNutrition.get( i ).SetPro( InputInt() );
					System.out.println( "請輸入" + foodName + "的脂肪克數：" );
					sListOfNutrition.get( i ).SetFat( InputInt() );
					G.clearScreen();
				} // if ( yes )
				
				// 存檔
				Nutrition.OpenWriteFile();
				Nutrition.AddData();
				Nutrition.CloseWriteFile();
				// 存檔
				if ( Nutrition.OpenReadFile() ) { // 馬上讀馬上用
					Nutrition.ReadData();
					Nutrition.CloseReadFile();
				} // if ( something in the file )

				
				System.out.println( "名稱：" + foodName + " 量詞：" + sListOfFood.get( i ).GetQuantifier() + " 熱量："
				                     + sListOfFood.get( i ).GetCalories() );
				System.out.println( "營養素：\n" );
				System.out.println( "碳水化合物－－－－－：" + sListOfNutrition.get( i ).GetCar() + " 公克" );
				System.out.println( "蛋白質－－－－－－－：" + sListOfNutrition.get( i ).GetPro() + " 公克" );
				System.out.println( "脂肪－－－－－－－－：" + sListOfNutrition.get( i ).GetFat() + " 公克" );
				yes = false; // 為了跳出迴圈 
				
				Food.OpenWriteFile();
				Food.AddData(foodName, sListOfFood.get( i ).GetQuantifier(), sListOfFood.get( i ).GetCalories() );
				Food.CloseWriteFile();
				
			} // else 有找到
			
			i = 0;
		} while ( yes );
		
	} // EditUserFood()
	
	public static void clearScreen() {  
    System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");  
    
	} // clearScreen
	
	static void PrintList () {
		System.out.println( "　以下是所有你可以使用的功能，把這裡當你家吧 \\(^ω^)/\n" );
		System.out.println( "　＊＊＊＊＊－－卡路里　　相關－－＊＊＊＊＊\n" );

		System.out.println( "　A  - 紀錄卡路里" );
		System.out.println( "　LF - 列出所有使用者輸入的食物" );
		System.out.println( "　CS - 編輯特定食物的內容" );
		System.out.println( "　CC - 修改當日目前卡路里總和與營養素" );
		System.out.println( "　LA - 列出從古自今的卡路里紀錄\n" );

		System.out.println( "　＊＊＊＊＊－－基本資訊　相關－－＊＊＊＊＊\n" );

		System.out.println( "　L  - 列出所有你的基本資訊" );
		System.out.println( "　CN - 更改綽號" );
		System.out.println( "　CG - 更改性別" );
		System.out.println( "　CA - 更改年齡" );
		System.out.println( "　CH - 更改身高" );
		System.out.println( "　CW - 更改體重" );
		System.out.println( "　CE - 更改活動量" );
		System.out.println( "　CI - 更改理想體重" );
		System.out.println( "　CD - 更改每日建議卡路里" );
		System.out.println( "　CB - 更改營養素目標\n" );

		System.out.println( "　＊＊＊＊＊－－重置　　　相關－－＊＊＊＊＊\n" );
		System.out.println( "　RC - 重新計算每日建議卡路里與理想體重" );
		System.out.println( "　RB - 重新計算營養素目標\n" );

		System.out.println( "　＊＊＊＊＊－－掰掰　　　相關－－＊＊＊＊＊\n" );
		System.out.println( "　Q  - 離開卡路里守護者 q(〒□〒)p \n" );
	
		
	} // PrintList()
} // G