
/*

作者: 應華四乙 10357204 陳浩雲

Life is disappointing, this is why you need to stay strong.

*/


import java.util.*;
import java.io.*;
import java.lang.*;




public class GuardianOfCalories {

	static float mOriginalWeight = 0;
		
  public static void main( String[] args ) throws Throwable {
    String stringtemp = null;
    boolean test = false;
    String stringTemp = "";
		boolean yes;
		float tempFloat = 0;
		
		int i = 0;
		
    G.Init();
		
		G.clearScreen(); // 跳過bat file的說明
		
    // UserData userData = new UserData();
    
    if ( UserData.OpenReadFile() ) { // 讀入使用者資料
      UserData.ReadData();
      UserData.CloseReadFile();
    } // if ( something in the file )
		

    
    if ( Food.OpenReadFile() ) { // 讀入使用者補充的食物資料
      Food.ReadData();
      Food.CloseReadFile();
    } // if ( something in the file )
		
		if ( DailyCaloriesReport.OpenReadFile() ) { // 讀入使用者過去所有的卡路里及體重紀錄
      DailyCaloriesReport.ReadData();
      DailyCaloriesReport.CloseReadFile();
    } // if ( something in the file )
		
		if ( FoodSoFar.OpenReadFile() ) { // 當日使用者所輸入的食物
      FoodSoFar.ReadData();
      FoodSoFar.CloseReadFile();
    } // if ( something in the file )
		
		if ( Nutrition.OpenReadFile() ) { // 營養
      Nutrition.ReadData();
      Nutrition.CloseReadFile();
    } // if ( something in the file )
		
    
  
    if ( G.sHeight == 0 ) {
      
      UserData.GetUserData(); // Q & A
			// 存檔
			UserData.OpenWriteFile();
			UserData.AddData();
			UserData.CloseWriteFile();
			// 存檔
     
    } // if ( No Data )
    else {
      
			System.out.println( G.sName + "你回來了!\\(≧▽≦)/ 是否要更改體重呢? （　是／否　）" );
			yes = G.YesOrNo();
			if ( yes ) {
				mOriginalWeight = G.sWeight; // 上一次輸入的體重
			  System.out.println( "請輸入體重: " ) ;
				tempFloat = G.InputInt();
				if ( ( G.sWeight - tempFloat ) > 0 ) {
					System.out.print( "哇你瘦了" ) ;
					System.out.printf( "%.2f", ( G.sWeight - tempFloat ) );
					System.out.println( "公斤耶！要堅持下去喔！" );
					
				
				
				} // if ( 瘦了 )
				else {
					System.out.println( "最近吃得有點好喔～沒關係我們一起繼續努力吧！\\(▔▽▔\")\n" ) ;
				
				} // else ( 胖了 )
				
				G.PressAnyKeyToContinue();
				
				G.sWeight = tempFloat;
				
				UserData.OpenWriteFile();
				UserData.AddData();
				UserData.CloseWriteFile();
				System.out.println( "你現在的體重是 " + G.sWeight + " 公斤" ) ;
				System.out.println( "距離你的理想體重只剩 " + ( G.sWeight - G.sIdealWeight ) + " 公斤了，加油！！\\(⊙O⊙)/" ) ;
			} // if ( 要更改 )
      
    } // else
    
    
    // 從這邊開始
		
		
		// System.out.println( G.sCurrentDate + " " + DailyCaloriesReport.GetCurrentDate() );
		
		if ( G.sCurrentDate.equals( "" ) ) {
			G.sCurrentDate = DailyCaloriesReport.GetCurrentDate();
			// 存日期
			UserData.OpenWriteFile();
			UserData.AddData();
			UserData.CloseWriteFile();
			// 存日期
		} // if ( 第一天 )
		else if ( G.sCurrentDate.equals( DailyCaloriesReport.GetCurrentDate() ) ) {
		  // 還在當天的話就不改動G.sSumOfCalories
		
		} // else if ()
		else if ( !G.sCurrentDate.equals( DailyCaloriesReport.GetCurrentDate() ) ) {
			DailyCaloriesReport.OpenWriteFile();
			if ( mOriginalWeight == 0 ) {
				DailyCaloriesReport.AddData( G.sCurrentDate, G.sSumOfCalories, G.sWeight, G.sBasicCarbohydratesSoFar, G.sBasicProteinSoFar, G.sBasicFatSoFar ); // 把前一天的卡路里記起來
			} // if ( 沒有更改體重 )
			else {
				DailyCaloriesReport.AddData(G.sCurrentDate, G.sSumOfCalories, mOriginalWeight, G.sBasicCarbohydratesSoFar,G.sBasicProteinSoFar, G.sBasicFatSoFar  ); // 把前一天的卡路里記起來
			} // else 有更改體重
			
			DailyCaloriesReport.CloseWriteFile();
			
			if ( DailyCaloriesReport.OpenReadFile() ) { // 馬上記馬上讀
				DailyCaloriesReport.ReadData();
				DailyCaloriesReport.CloseReadFile();
			} // if ( something in the file )
			
			
			G.sBasicCarbohydratesSoFar = 0;
			G.sBasicProteinSoFar = 0;
			G.sBasicFatSoFar = 0;
			G.sSumOfCalories = 0;
			G.sCurrentDate = DailyCaloriesReport.GetCurrentDate(); // 這邊要存，不然使用者用到一半沒退出會多一個數據
			
			// 存檔
			UserData.OpenWriteFile();
			UserData.AddData();
			UserData.CloseWriteFile();
			// 存檔
			
			// 重新foodSoFar
			FoodSoFar.OpenWriteFile();
			FoodSoFar.CloseWriteFile();
			// 重新foodSoFar
			
			G.sListOfFoodSoFar.clear();
		} // else if ( 換一天了 )
		
		
		
		if ( G.sListOfDate.size() > 0 ) {
			System.out.println( "" );
			System.out.println( "＊＊＊＊＊　最一開始的紀錄　＊＊＊＊＊");
			System.out.println( "" );
			
			System.out.println( "　" + G.sListOfDate.get( 0 ).GetDate() + " " + G.sListOfDate.get( 0 ).GetCalories() + "大卡 " + 
								G.sListOfDate.get( 0 ).GetWeight() + "公斤"	);
			
			System.out.println( "" );
			System.out.println( "＊＊＊＊＊　最近五天的紀錄　＊＊＊＊＊");
			System.out.println( "" );
			
			if ( G.sListOfDate.size() < 5 ) {
				for ( i = 0 ; i < G.sListOfDate.size()  ; i++ ) { // 不到五天
					System.out.println( "　" + G.sListOfDate.get( i ).GetDate() + " " + G.sListOfDate.get( i ).GetCalories() + "大卡 " + 
								G.sListOfDate.get( i ).GetWeight() + "公斤"	);

				} // for( 印所有日期和卡路里 )
			} // if ()
			else {
				for ( i = G.sListOfDate.size() - 5 ; i < G.sListOfDate.size()  ; i++ ) { // 大於等於五天
					System.out.println( "　" + G.sListOfDate.get( i ).GetDate() + " " + G.sListOfDate.get( i ).GetCalories() + "大卡 " + 
								G.sListOfDate.get( i ).GetWeight() + "公斤"	);

				} // for( 印所有日期和卡路里 )

			} // else

			System.out.println( "" );
			
		} // if ( 有資料 )
		else {
			System.out.println( "" );
		} // else
		
		int tempCal = G.sDailyCal;
		CalCount.CountDailyCal(); 
		if ( tempCal != G.sDailyCal ) {
			G.sDailyCal = tempCal;
		} // if ( 如果使用者有更改過的話 )
		
		
		System.out.println( "你的每日建議卡路里是： " + G.sDailyCal + " 大卡" );
		System.out.println( "你今天已經吃了　　　： " + G.sSumOfCalories + " 大卡" );
		System.out.println( "今天已攝取的營養素　：\n\n"
							              + "碳水化合物－－－－－： " + G.sBasicCarbohydratesSoFar + " 公克\n"
											      + "蛋白質－－－－－－－： " + G.sBasicProteinSoFar + " 公克\n"
													  + "脂肪－－－－－－－－： " + G.sBasicFatSoFar + " 公克\n" );
		
		if ( G.sBasicFat == 0 ) {
			CalCount.CountBasicNutrition();
		} // if ( 沒設定or第一次 )
											
		System.out.println( "你目前的營養素目標是：\n\n"
							              + "碳水化合物－－－－－： " + G.sBasicCarbohydrates + " 公克\n"
											      + "蛋白質－－－－－－－： " + G.sBasicProtein + " 公克\n"
													  + "脂肪－－－－－－－－： " + G.sBasicFat + " 公克\n" );
		
		
		G.PressAnyKeyToContinue();
		
		
		G.PrintList();

		
		
		////////// 系統IO處理GOGOGO ///////
		
		String inputString = "";
		System.out.print( ">> " );
		inputString = G.sln.next();
		G.clearScreen();
		
		if ( inputString.equals( "Q" ) ) {
				System.out.println( "確定要離開卡路里守護者嗎？　（　是／否　）" );
				yes = G.YesOrNo();
				if ( !yes ) {
					System.out.println( "請輸入你想使用的功能：" );
					System.out.print( ">> " );
					inputString = G.sln.next();
					G.clearScreen();
				} // if ( 不要離開 )
			} // if ( 確定使用者是否真的要退出 )
		
		while ( !inputString.equals( "Q" ) ) {
			switch ( inputString ) {
				case "L" :
					System.out.println( "列出所有你的基本資訊：\n" );
					
					System.out.println( "綽號／名字－－－－－： " + G.sName );
					System.out.println( "性別－－－－－－－－： " + G.sGender + "生" );
					System.out.println( "年齡－－－－－－－－： " + G.sAge + " 歲" );
					System.out.println( "身高－－－－－－－－： " + G.sHeight + " 公分" );
					System.out.println( "體重－－－－－－－－： " + G.sWeight + " 公斤" );
					System.out.println( "理想體重－－－－－－： " + G.sIdealWeight + " 公斤" );
					System.out.println( "活動量－－－－－－－： " + G.sWorkOutType );
					System.out.println( "今天已攝取的卡路里－： " + G.sSumOfCalories + " 大卡" );
					System.out.println( "每日建議卡路里－－－： " + G.sDailyCal + " 大卡" );
					
					System.out.println( "今天已攝取的營養素　：\n\n"
							              + "碳水化合物－－－－－： " + G.sBasicCarbohydratesSoFar + " 公克\n"
											      + "蛋白質－－－－－－－： " + G.sBasicProteinSoFar + " 公克\n"
													  + "脂肪－－－－－－－－： " + G.sBasicFatSoFar + " 公克\n" );
					System.out.println( "營養素目標－－－－－：\n\n"
							              + "碳水化合物－－－－－： " + G.sBasicCarbohydrates + " 公克\n"
											      + "蛋白質－－－－－－－： " + G.sBasicProtein + " 公克\n"
													  + "脂肪－－－－－－－－： " + G.sBasicFat + " 公克\n" );
					G.PressAnyKeyToContinue();
					break; // ok
					
					
				case "LA" :
					System.out.println( "列出從古自今的卡路里紀錄：\n" );
					
					
					if ( G.sListOfDate.size() == 0 ) {
						System.out.println( "空空如也～\\(￣◇￣)/" );
					} // if ( 沒紀錄 )
					for ( i = 0 ; i < G.sListOfDate.size()  ; i++ ) { // ALL
					System.out.println( G.sListOfDate.get( i ).GetDate() + " " + G.sListOfDate.get( i ).GetCalories() + "大卡 " + 
								G.sListOfDate.get( i ).GetWeight() + "公斤"	);

					} // for( 印所有日期和卡路里 )
					G.PressAnyKeyToContinue();
					break; // ok
					
				case "CD" :
					System.out.println( "更改每日建議卡路里：\n" );
					
					System.out.println( "你現在的每日建議卡路里是 " + G.sDailyCal + " 大卡"  );
					System.out.println( "請輸入你希望的每日建議卡路里：" );
					G.sDailyCal = G.InputInt();
					System.out.println( "你現在的每日建議卡路里是 " + G.sDailyCal + " 大卡"  );
					G.PressAnyKeyToContinue();
					break; // ok
					
				case "CB" :
					System.out.println( "更改營養素目標：\n" );
					
					System.out.println( "你目前的營養素目標是：\n\n"
							              + "碳水化合物－－－－－：" + G.sBasicCarbohydrates + " 公克\n"
											      + "蛋白質－－－－－－－：" + G.sBasicProtein + " 公克\n"
													  + "脂肪－－－－－－－－：" + G.sBasicFat + " 公克\n" );
		
					
					System.out.println( "請輸入你希望的碳水化合物克數：" );
					G.sBasicCarbohydrates = G.InputInt();
					System.out.println( "請輸入你希望的蛋白質克數：" );
					G.sBasicProtein = G.InputInt();
					System.out.println( "請輸入你希望的脂肪克數：" );
					G.sBasicFat = G.InputInt();
					System.out.println( "你目前的營養素目標是：\n\n"
							              + "碳水化合物－－－－－：" + G.sBasicCarbohydrates + " 公克\n"
											      + "蛋白質－－－－－－－：" + G.sBasicProtein + " 公克\n"
													  + "脂肪－－－－－－－－：" + G.sBasicFat + " 公克\n" );
					G.PressAnyKeyToContinue();
					break; // ok
					
				case "CI" :
					System.out.println( "更改理想體重：\n" );
					
					System.out.println( "你現在的理想體重是 " + G.sIdealWeight + " 公斤"  );
					System.out.println( "請輸入你希望的理想體重：" );
					G.sIdealWeight = G.InputFloat();
					System.out.println( "你現在的理想體重是 " + G.sIdealWeight + " 公斤"  );
					G.PressAnyKeyToContinue();
					break; // ok
					
				case "CE" :
					System.out.println( "更改活動量：\n" );
					
					System.out.println( "你現在的活動量為： " + G.sWorkOutType );
					System.out.println( "請輸入新的活動量：( 無, 輕度, 中度, 重度 )\n" ) ; 
					System.out.println( "無：從事輕度活動，如看書、看電視、駕駛、打電腦、辦公事務等不會流汗者。\n" );
					System.out.println( "輕度：一天約１小時不激烈的動態活動，如步行、伸展操、逛街、打掃收拾等。\n" );
					System.out.println( "中度：從事中度勞動量的工作，如站立工作者、護士、業務等。或是一天約１小時較強態活動，如快走、爬樓梯、舞蹈、騎腳踏車。\n" );
					System.out.println( "重度：從事重度勞動量的工作，如重物搬運的勞動者，或一天約有１小時激烈運動，如游泳、登山、足球、網球等會大量流汗者。\n" );
					System.out.print( ">> " );
					G.sWorkOutType = G.sln.next();
					G.clearScreen();


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
					System.out.println( "你現在的活動量為： " + G.sWorkOutType );
					
					G.PressAnyKeyToContinue();
					break; // 
					
				case "CN" :
					System.out.println( "更改綽號：\n" );
					
					System.out.println( G.sName + "安安，想換名字了嗎？（　是／否　） " );
					yes = G.YesOrNo();
					if ( yes ) {
						System.out.println( "請輸入你的名字或綽號：" );
						G.sName = G.sln.next();
						System.out.println( G.sName + "安安！" );
					
					} // if ( yes )
					else {
						System.out.println( "對嘛～" + G.sName + "這麼好聽的名字幹嘛改呢～" );
					} // else
					
					
					
					
					G.PressAnyKeyToContinue();
					break; // ok
					
					
				case "CS" :
					System.out.println( "編輯特定食物的內容：\n" );
					
					System.out.println( "請注意～只能修改自己輸入的食物的卡路里唷！系統預設的無法被更改" );
					
					G.EditUserFood();
					
					
					G.PressAnyKeyToContinue();
					break; // ok
					
				case "CH" :
					System.out.println( "更改身高：\n" );
					
					System.out.println( "你目前的身高是 " + G.sHeight + " 公分"  );
					System.out.println( "請輸入你新的身高：" );
					G.sHeight = G.InputInt();
					System.out.println( "你新的身高是 " + G.sHeight + " 公分"  );
					G.PressAnyKeyToContinue();
					break; // ok
					
				case "CG" :
					System.out.println( "更改性別：\n" );
					
					System.out.println( "你目前的性別： " + G.sGender + "生"  );
					System.out.println( "請輸入你想更改的性別 （　男／女　） ：" );
					G.sGender = "";
					while ( !G.sGender.equals( "男" ) && !G.sGender.equals( "女" ) ) {
						System.out.print( ">> " );
						G.sGender = G.sln.next();
						G.clearScreen();
						
						if ( !G.sGender.equals( "男" ) && !G.sGender.equals( "女" ) ) {
							System.out.println( "輸入錯誤，請再輸入一次" );
						} // if ()

					} // while ()
					System.out.println( "你現在的性別： " + G.sGender + "生"  );
					G.PressAnyKeyToContinue();
					break; //
					
				case "CA" :
					System.out.println( "更改年齡：\n" );
					
					System.out.println( "你目前的年齡是 " + G.sAge + "歲"  );
					System.out.println( "請輸入你想更改為：" );
					G.sAge = G.InputInt();
					System.out.println( "你目前的年齡是 " + G.sAge + "歲"  );
					G.PressAnyKeyToContinue();
					break; // ok
					
				case "CW" :
					System.out.println( "更改體重：\n" );
					
					System.out.println( "你目前的體重是 " + G.sWeight + " 公斤"  );
					System.out.println( "請輸入你新的體重：" );
					G.sWeight = G.InputFloat();
					System.out.println( "你新的體重是 " + G.sWeight + " 公斤"  );
					G.PressAnyKeyToContinue();
					break; // ok
					
				case "RC" :
					System.out.println( "重新計算每日建議卡路里與理想體重：\n" );
					
					System.out.println( "你現在的每日建議卡路里是 " + G.sDailyCal + " 大卡"  );
					System.out.println( "你現在的理想體重是 " + G.sIdealWeight + " 公斤"  );
					System.out.println( "是否要重新計算每日建議卡路里與理想體重呢？　（　依系統預設推薦　）　（　是／否　）　"  );
					
					yes = G.YesOrNo();
					
					if ( yes ) {
						CalCount.CountDailyCal();
						CalCount.CountIdealWeight();

						System.out.println( "你現在的每日建議卡路里是 " + G.sDailyCal + " 大卡"  );
						System.out.println( "你現在的理想體重是 " + G.sIdealWeight + " 公斤"  );
					
					} // if ( yes )
					else {
						System.out.println( "你的每日建議卡路里與理想體重沒有被更改"  );
					} // else
					
					
					G.PressAnyKeyToContinue();
					break; // ok
					
				case "LF" :
					System.out.println( "列出所有使用者輸入的食物：\n" );
					
					
					if ( G.sListOfFood.size() == 0 ) {
						System.out.println( "空空如也～\\(￣◇￣)/" );
					} // if ( 0 )
					for ( i = 0 ; i < G.sListOfFood.size() ; i++ ) {
						System.out.print( ( i + 1 ) + ". 名稱：" + G.sListOfFood.get( i ).GetName()
																	+ " 量詞：" + G.sListOfFood.get( i ).GetQuantifier() 
																	+ " 熱量：" + G.sListOfFood.get( i ).GetCalories() );
						
						
						for ( int j = 0 ; j < G.sListOfNutrition.size() ; j++ ) {
							
							if ( G.sListOfNutrition.get( j ).GetFoodName().equals( G.sListOfFood.get( i ).GetName() ) ) {
									System.out.println( " 碳水化合物：" + G.sListOfNutrition.get( j ).GetCar()
																			+ " 蛋白質：" + G.sListOfNutrition.get( j ).GetPro()
																			+ " 脂肪：" + G.sListOfNutrition.get( j ).GetFat() );
							} // if ()
							
						} // for(j)
						
						
						
						
					} // for (i)
					
					
					
					G.PressAnyKeyToContinue();
					break; // ok
					
				case "CC" :
					System.out.println( "修改當日目前卡路里總和與營養素：\n" );
					
					System.out.println( "今天到目前為止的總熱量是 " + G.sSumOfCalories + " 大卡"  );
					System.out.println( "你想更改為多少大卡呢：" );
					G.sSumOfCalories = G.InputInt();
					System.out.println( "今天到目前為止的總熱量是 " + G.sSumOfCalories + " 大卡"  );
					
					System.out.println( "今天已攝取的營養素　：\n\n"
							              + "碳水化合物－－－－－： " + G.sBasicCarbohydratesSoFar + " 公克\n"
											      + "蛋白質－－－－－－－： " + G.sBasicProteinSoFar + " 公克\n"
													  + "脂肪－－－－－－－－： " + G.sBasicFatSoFar + " 公克\n" );
					
					System.out.println( "請輸入你目前為止的碳水化合物克數：" );
					G.sBasicCarbohydratesSoFar = G.InputInt();
					System.out.println( "請輸入你目前為止的的蛋白質克數：" );
					G.sBasicProteinSoFar = G.InputInt();
					System.out.println( "請輸入你目前為止的的脂肪克數：" );
					G.sBasicFatSoFar = G.InputInt();
					System.out.println( "你目前為止的營養素攝取量是：\n\n"
							              + "碳水化合物－－－－－：" + G.sBasicCarbohydratesSoFar + " 公克\n"
											      + "蛋白質－－－－－－－：" + G.sBasicProteinSoFar + " 公克\n"
													  + "脂肪－－－－－－－－：" + G.sBasicFatSoFar + " 公克\n" );
					
					G.PressAnyKeyToContinue();
					break;
					
				case "RB" :
					System.out.println( "重新計算營養素目標：\n" );
					
					System.out.println( "你目前的營養素目標是：\n\n"
							              + "碳水化合物－－－－－：" + G.sBasicCarbohydrates + " 公克\n"
											      + "蛋白質－－－－－－－：" + G.sBasicProtein + " 公克\n"
													  + "脂肪－－－－－－－－：" + G.sBasicFat + " 公克\n" );
					System.out.println( "是否要重新計算營養素目標呢？　（　依系統推薦比例　）　（　是／否　）"  );
					
					yes = G.YesOrNo();
					
					if ( yes ) {
						CalCount.CountBasicNutrition();

						System.out.println( "你的營養素目標已被更改為：\n\n"
							                + "碳水化合物－－－－－－－：" + G.sBasicCarbohydrates + " 公克\n"
											        + "蛋白質－－－－－－－－－：" + G.sBasicProtein + " 公克\n"
													    + "脂肪－－－－－－－－－－：" + G.sBasicFat + " 公克\n" );
						
					
					} // if ( yes )
					else {
						System.out.println( "你的營養素目標沒有被更改"  );
					} // else
					G.PressAnyKeyToContinue();
					
					break; // ok
					
				case "A" :
					System.out.println( "紀錄卡路里：\n" );
					
					if ( G.sListOfFoodSoFar.size() > 0 ) {
						System.out.println( "今天到目前已經輸入過：" );
					} // if ()

					for ( i = 0 ; i < G.sListOfFoodSoFar.size() ; i++ ) {
						System.out.println( G.sListOfFoodSoFar.get( i ).GetQuantity() + G.sListOfFoodSoFar.get( i ).GetQuantifier()
																+ G.sListOfFoodSoFar.get( i ).GetName() + " " + G.sListOfFoodSoFar.get( i ).GetCalories() + "大卡 "
																+ "碳水化合物：" + G.sListOfFoodSoFar.get( i ).GetCar() + "公克 "
																+ "蛋白質：" + G.sListOfFoodSoFar.get( i ).GetPro() + "公克 "
																+ "脂肪：" + G.sListOfFoodSoFar.get( i ).GetFat() + "公克 ");
					} // for()

					System.out.println( "" );
					MainProcess.ProcessFood();
					G.PressAnyKeyToContinue();
					break; // ok
					
				default :
					System.out.println( "輸入錯誤，請再試一次 (╯≧▽≦)╯ ~╩═══╩" );
					G.PressAnyKeyToContinue();
					break;
			
			} // switch()
			
			// 存檔
			UserData.OpenWriteFile();
			UserData.AddData();
			UserData.CloseWriteFile();
			// 存檔
			
			System.out.println( "" );
			G.PrintList();
		
		
			// G.clearScreen();
			System.out.print( ">> " );
			inputString = G.sln.next();
			
			G.clearScreen();
			
			if ( inputString.equals( "Q" ) ) {
				System.out.println( "確定要離開卡路里守護者嗎？　（　是／否　）" );
				yes = G.YesOrNo();
				if ( !yes ) {
					G.PrintList();
					System.out.println( "請輸入你想使用的功能：" );
					System.out.print( ">> " );
					inputString = G.sln.next();
					G.clearScreen();
					
				} // if ( 不要離開 )
			} // if ( 確定使用者是否真的要退出 )
			
			
		} // while ( !=q )
		

		//////////////////////////////////
		
	
    
    System.out.println( "謝謝你的使用! 期待下次與你再相會～" );
		G.PressAnyKeyToContinue();
		
		
  
  
  } // main()
} // class GuardianOfCalories
