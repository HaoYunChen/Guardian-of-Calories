import java.util.*;
import java.io.*;
import java.lang.*;

public class MainProcess {
	
	static void ProcessFood() throws Throwable {
		boolean yes = true;
		
//		System.out.println( "現在來跟我說說你今天吃了些什麼吧" + G.sName + "！" );
//		System.out.println( "問題1! 如果你吃了半碗飯該怎麼打呢!?" );
//		System.out.println( "請你跟我這樣打: 0.5 飯" );
//		System.out.println( "問題2! 如果你喝了240cc的低脂牛奶該怎麼打呢！？" );
//		System.out.println( "請你跟我這樣打: 240 低脂牛奶" );
//		System.out.println( "＊小提醒！水果和蔬菜請用幾分滿的碗為份量喔！＊" );
//		System.out.println( "＊小提醒！一份肉是四根手指這麼大喔!( 一兩手掌大 )＊" );
//		System.out.println( "像是8分滿的碗的水果：　8 水果 （不用分什麼香蕉蘋果芭樂唷！）" );
//		System.out.println( "很簡單吧! 那麼我們開始囉! ( 請以全榖根莖、油脂、水果、蔬菜、蛋豆魚肉、奶，這六類為基礎分類唷！（自己增加的除外） )\n" );
		
		
		while ( yes ) {
			InputFood();
			System.out.println( "是否繼續輸入其他食物呢？（　是／否　）" );
			yes = G.YesOrNo();
		
		} // while ( !yes )
		
		
	
	} // ProcessFood() 用來分析食物和計算當日卡路里
	
	static boolean InputFood() throws Throwable {
		
		float quantity = 0;
		int tempInt = 0;
		String foodName;
		String quantifier = "";
		boolean yes;
		String eatOrDrink = "吃"; // 吃/喝
		
		do {
			System.out.println( "請輸入食物名稱：( 輸入\"取消\"可以取消輸入 )" );
			System.out.print( ">> " );
			foodName = G.sln.next();
			G.clearScreen();
			System.out.println( "" );
			
			if ( foodName.equals( "取消" ) ) {
				return false;
			} // if 
			
			quantifier = GetQuantifier( foodName );
			// get quantifier
			
			if ( quantifier.equals( "cc" ) || quantifier.equals( "罐" ) || quantifier.equals( "杯" ) ) {
				eatOrDrink = "喝";
			} // if ( 喝的 )
			
			
			if ( !quantifier.equals( "" ) ) {
				System.out.println( "你" + eatOrDrink + "了幾" + quantifier + "的" + foodName + "呢？" ); // 我自己覺得這真的很用心拉
				quantity = G.InputFloat();
				System.out.println( "確定是 " + quantity + " " + quantifier + " 的 " + foodName + " 嗎? （　是／否　）" );
				yes = G.YesOrNo();
	
			
			
			} // if ( 使用者想增加該食物 )
			else {
				yes = false;
			} // else 使用者不想增加該食物
			
		} while ( !yes );
		
		int countCal = CountFoodCal( foodName, quantifier, quantity );
		G.sSumOfCalories = G.sSumOfCalories + countCal;
		
		
		
		double tempCar = 0;
		double tempPro = 0;
		double tempFat = 0;
		boolean found = false;
		int i = 0;
		
		
		int j = 0;
	
		while ( i < G.sListOfNutrition.size() ) {
			
			if ( foodName.equals( G.sListOfNutrition.get( i ).GetFoodName() ) ) {
				j = i;
				found = true;
			} // if ()
			
			i++;
		} // while()
		
		if ( found ) {
			tempCar = G.sListOfNutrition.get( j ).GetCar();
			tempPro = G.sListOfNutrition.get( j ).GetPro();
			tempFat = G.sListOfNutrition.get( j ).GetFat();
			// System.out.println( "Car: " + tempCar + "Pro: " + tempPro + "Fat: " + tempFat );
		} // if ( i != 0 )
		
		
		if ( countCal != 0 && !found ) { // 如果get完之後還是0
			do {
				System.out.println( "請輸入" + foodName + "的碳水化合物克數：" );
				tempCar = G.InputInt();
				System.out.println( "請輸入" + foodName + "的蛋白質克數：" );
				tempPro = G.InputInt();
				System.out.println( "請輸入" + foodName + "的脂肪克數：" );
				tempFat = G.InputInt();
				
				
				System.out.println( foodName + "的營養素是：\n\n" );
				System.out.println( "碳水化合物－－－－－：" + tempCar + " 公克" );
				System.out.println( "蛋白質－－－－－－－：" + tempPro + " 公克" );
				System.out.println( "脂肪－－－－－－－－：" + tempFat + " 公克" );
				
				System.out.println( "確定嗎？　（　是／否　）" );
				yes = G.YesOrNo();
			} while ( !yes );
			
			if ( yes ) {
				G.sListOfNutrition.add( new Nutrition() );
				G.sListOfNutrition.get( G.sListOfNutrition.size() - 1 ).SetFoodName( foodName );
				G.sListOfNutrition.get( G.sListOfNutrition.size() - 1 ).SetCar( (int)tempCar );
				G.sListOfNutrition.get( G.sListOfNutrition.size() - 1 ).SetPro( (int)tempPro );
				G.sListOfNutrition.get( G.sListOfNutrition.size() - 1 ).SetFat( (int)tempFat );
			
			} // yes
			
			// 存檔
			Nutrition.OpenWriteFile();
			Nutrition.AddData();
			Nutrition.CloseWriteFile();
			// 存檔
			if ( Nutrition.OpenReadFile() ) { // 馬上讀馬上用
				Nutrition.ReadData();
				Nutrition.CloseReadFile();
			} // if ( something in the file )
		
		} // if ( 沒輸入過 )
		
		
		
		G.sBasicCarbohydratesSoFar = G.sBasicCarbohydratesSoFar + (int)( tempCar * quantity );
		G.sBasicProteinSoFar = G.sBasicProteinSoFar + (int)( tempPro * quantity );
		G.sBasicFatSoFar = G.sBasicFatSoFar + (int)( tempFat * quantity );
		
		
		
		
		// 存檔
		FoodSoFar.OpenAppendWriteFile();
		FoodSoFar.AddData( quantity, quantifier, foodName, countCal, (int)( tempCar * quantity ), (int)( tempPro * quantity ), (int)( tempFat * quantity ) );
		FoodSoFar.CloseWriteFile();
		// 存檔
		G.sListOfFoodSoFar.clear();
		// 讀檔
		if ( FoodSoFar.OpenReadFile() ) { // 當日使用者所輸入的食物
      FoodSoFar.ReadData();
      FoodSoFar.CloseReadFile();
    } // if ( something in the file )
		// 讀檔
		
		
		System.out.println( "今日到目前為止的總熱量為 " + G.sSumOfCalories + " 大卡");
		System.out.println( "今天已攝取的營養素　：\n" );
						
		if ( ( G.sBasicCarbohydrates - G.sBasicCarbohydratesSoFar ) >= 0 ) {
			System.out.println( "碳水化合物－－－－－： " + G.sBasicCarbohydratesSoFar + " 公克 "  + "距離目標還有 " + ( G.sBasicCarbohydrates - G.sBasicCarbohydratesSoFar ) + " 公克" );
		} // if ( > ) 
		else {
			System.out.println( "碳水化合物－－－－－： " + G.sBasicCarbohydratesSoFar + " 公克 "  + "超出目標 " + ( G.sBasicCarbohydratesSoFar - G.sBasicCarbohydrates ) + " 公克 " );
		} // else
		
		if ( ( G.sBasicProtein - G.sBasicProteinSoFar ) >= 0 ) {
			System.out.println( "蛋白質－－－－－－－： " + G.sBasicProteinSoFar + " 公克 "  + "距離目標還有 " + ( G.sBasicProtein - G.sBasicProteinSoFar ) + " 公克" );
		} // if ( > ) 
		else {
			System.out.println( "蛋白質－－－－－－－： " + G.sBasicProteinSoFar + " 公克 "  + "超出目標 " + ( G.sBasicProteinSoFar - G.sBasicProtein ) + " 公克" );
		} // else
		
		if ( (  G.sBasicFat - G.sBasicFatSoFar ) >= 0 ) {
			System.out.println( "脂肪－－－－－－－－： " + G.sBasicFatSoFar + " 公克 "  + "距離目標還有 " + ( G.sBasicFat - G.sBasicFatSoFar ) + " 公克" );
		} // if ( > ) 
		else {
			System.out.println( "脂肪－－－－－－－－： " + G.sBasicFatSoFar + " 公克 "  + "超出目標 " + ( G.sBasicFatSoFar - G.sBasicFat ) + " 公克" );
		} // else
		
		
		
		if ( G.sSumOfCalories > G.sDailyCal ) {
			System.out.println( "你的每日卡路里超標 " + ( G.sSumOfCalories - G.sDailyCal ) + " 大卡拉～～～～～放下你手中的食物立地成佛！（誤）(￣y▽￣)╭" );
		} // if ( 警告 )
		else {
			System.out.println( "\n距離你的每日建議熱量還有 " + ( G.sDailyCal - G.sSumOfCalories ) + " 大卡 \\(≧ω≦)/\n" );
		} // else
		
		
		return true;
	} // InputFood()
	
	static int CountFoodCal( String foodName, String quantifier, float quantity ) throws Throwable {
		
		double tempDouble = 0;
		int calOfFood = 0;
		boolean yes;
		boolean found = false;
		
		switch ( foodName ) {
			
			// 全榖
			
			case "飯" : 
				tempDouble = G.sGrain * 4 * quantity; // 4分之1碗
				break;
				
			case "麵" : 
			case "米粉" :
			case "冬粉" : 
			case "米苔目" :
			case "通心粉" : 
			case "義大利麵" :	
			case "地瓜" : 
			case "芋頭" :	
			case "南瓜" :
			case "馬鈴薯" : 
			case "粥" :
			case "稀飯" : 
			case "山藥" :	
			case "麵線" : 
			case "菱角" :
			case "皇帝豆" :
				tempDouble = G.sGrain * 2 * quantity; // 2分之1碗
				break;
				
			case "綠豆" :	
			case "粉圓" :
			case "栗子" : 
			case "蓮子" :
			case "小湯圓" : 
			case "花豆" :	
			case "五穀粉" : 
			case "藕粉" :
			case "碗豆仁" :
				tempDouble = G.sGrain / 2 * quantity; // 2湯匙
				break;
				
			case "燕麥片" : 
			case "紅豆" :
			case "薏仁" :
				tempDouble = G.sGrain / 3 * quantity; // 3湯匙
				break;
				
			case "玉米粒" :
				tempDouble = G.sGrain / 5 * quantity; // 5湯匙
				break;
								
			case "水餃皮" :
				tempDouble = G.sGrain / 3 * quantity; // 3張
				break;
				
			case "餛飩皮" :
				tempDouble = G.sGrain / 7 * quantity; // 7張
				break;
			
			case "潤餅皮" :
				tempDouble = G.sGrain / 2 * quantity; // 2張
				break;
				
			case "燒餅" :
				tempDouble = G.sGrain * 4 * quantity + 22; // + 一份油
				break;
			
			case "油條" :
				tempDouble = G.sGrain * 2 * quantity + 45; // + 兩份油
				break;
				
			case "小餐包" :
				tempDouble = G.sGrain * quantity; // 1
				break;
				
			case "蘇打餅" :
				tempDouble = G.sGrain / 3 * quantity; // 3片
				break;
				
			// 奶類
				
			case "低脂牛奶" :
				tempDouble = G.sLowMilk * quantity / 240; 
				break;
				
			case "全脂牛奶" :
				tempDouble = G.sHighMilk * quantity / 240; 
				break;
				
			case "起司" :
				tempDouble = G.sHighMilk * quantity / 2; 
				break;
				
			case "低脂優格" :
				tempDouble = G.sLowMilk * quantity / 180; 
				break;
				
			case "全脂優格" :
				tempDouble = G.sHighMilk * quantity / 180; 
				break;
				
			case "低脂優酪乳" :
				tempDouble = G.sLowMilk * quantity / 200; 
				break;
				
			case "全脂優酪乳" :
				tempDouble = G.sHighMilk * quantity / 200; 
				break;
				
			// 水果
			
			case "水果" :
				tempDouble = G.sFruit * quantity / 8; // 八分滿的碗
				break;
				
		  // 油脂
				
			case "植物油" :
			case "豬油" :
			case "植物性奶油" :
			
				tempDouble = G.sFat * quantity * 3; 
				break;
				
			case "杏仁果" :
			case "芝麻醬" :
			case "沙茶醬" :
			case "美乃滋" :
			
				tempDouble = G.sFat * quantity * 2; 
				break;
				
			case "開心果" :
			case "核桃" :
			case "腰果" :
			case "松子" :
			case "花生醬" :
			case "花生粉" :
			case "鮮奶油" :
			case "芝麻粒" :
			
				tempDouble = G.sFat * quantity; 
				break;
				
			case "南瓜子" :
			
				tempDouble = G.sFat * quantity / 1.5 ; 
				break;
				
			case "瓜子" :
			case "葵瓜子" :
			case "花生" :
			
				tempDouble = G.sFat * quantity / 2 ; 
				break;
				
			// 青菜	
			case "蔬菜" :
			case "青菜" :
			case "菜" :
			
				tempDouble = G.sVage * quantity * 2 ; 
				break;
				
			// 蛋豆魚肉
			case "魚" :
			case "蝦" :
			case "花枝" :
			case "章魚" :
			case "牡蠣" :
			case "蛤蠣" :
			case "海參" :
			case "豬大里肌" :
			case "肉乾" :
			case "雞里肌" :
			case "雞胸肉" :
			case "雞腿" :
			case "黃豆" :
			case "毛豆" :
			case "豆漿" :
			case "濕豆包" :
			case "麵腸" :
				
				tempDouble = G.sLowMeat * quantity ; // 低脂
				break;
				
			case "虱目魚" :
			case "魚鬆" :
			case "花枝丸" :
			case "魚丸" :
			case "肉鬆" :
			case "豬排" :
			case "雞翅" :
			case "雞排" :
			case "雞爪" :
			case "豬肚" :
			case "豬小腸" :
			case "豬舌" :
			case "豬腳" :
			case "雞蛋" :
			case "蛋" :
			case "豆干" :
			case "豆腐" :
			case "豆乾" :
			case "素雞" :
			case "油豆腐" :
			
				
				tempDouble = G.sMidMeat * quantity ; // 中脂
				break;
				
			case "秋刀魚" :
			case "鱈魚" :
			case "豬後腿肉" :
			case "臘肉" :
			case "豬肉酥" :
			case "內臟" :
			case "麵筋泡" :
			
				
				tempDouble = G.sHighMeat * quantity ; // 高脂
				break;
				
			case "豬蹄膀" :
			case "梅花肉" :
			case "豬前腿肉" :
			case "五花肉" :
			case "豬大腸" :
			case "香腸" :
			case "熱狗" :
			
				
				tempDouble = G.sHighMeat * quantity * 1.2 ; // 超高脂
				break;
				
			case "無糖豆漿" :
			
				tempDouble = G.sLowMeat * quantity / 260 ; // 超高脂
				break;
				
			default :
				
				// 從使用者做的清單讀
				for ( int i = 0 ; i < G.sListOfFood.size() ; i++ ) {
				  if ( foodName.equals( G.sListOfFood.get( i ).GetName() ) ) {
					  tempDouble = G.sListOfFood.get( i ).GetCalories() * quantity;
						found = true;
					} // if ()
				
				} // for()
				
				if ( !found ) {
					System.out.println( "食物名稱：" + foodName );
					
						do {
							System.out.println( "一" + quantifier + foodName + "大約多少卡路里呢？" );
							tempDouble = G.InputInt();

							System.out.println( "確定是 " + ( int )tempDouble + " 卡嗎? （　是／否　）" );
							yes = G.YesOrNo();
						
						} while ( !yes );
						

						// 寫檔
						Food.OpenAppendWriteFile();
						Food.AddData( foodName, quantifier, ( int )tempDouble );
						Food.CloseWriteFile();

						// 馬上讀檔讓使用者可以使用
						if ( Food.OpenReadFile() ) { // 這個是boolean
							Food.ReadData();
							Food.CloseReadFile();
						} // if ( something in the file )
						
						tempDouble = tempDouble * quantity; // 乘上他吃的份數
				
				} // if ( 沒找到 )
				
				
			break;
			
			
			
			
		
		} // switch()
		
		calOfFood = ( int )tempDouble; // 換成int
		
		return calOfFood;
		
	
	} // CountFoodCal()
	
	static String GetQuantifier( String foodName ) throws Throwable {
		boolean yes;
		boolean found = false;
		String quantifier = "";
		
		switch ( foodName ) {
			
			// 全榖
			
			case "飯" : 
				quantifier = "碗";
				break;
				
			case "麵" : 
			case "米粉" :
			case "冬粉" : 
			case "米苔目" :
			case "通心粉" : 
			case "義大利麵" :	
			case "地瓜" : 
			case "芋頭" :	
			case "南瓜" :
			case "馬鈴薯" : 
			case "粥" :
			case "稀飯" : 
			case "山藥" :	
			case "麵線" : 
			case "菱角" :
			case "皇帝豆" :
				quantifier = "碗";
				break;
				
			case "綠豆" :	
			case "粉圓" :
			case "栗子" : 
			case "蓮子" :
			case "小湯圓" : 
			case "花豆" :	
			case "五穀粉" : 
			case "藕粉" :
			case "碗豆仁" :
				quantifier = "湯匙";
				break;
				
			case "燕麥片" : 
			case "紅豆" :
			case "薏仁" :
				quantifier = "湯匙";
				break;
				
			case "玉米粒" :
				quantifier = "湯匙";
				break;
								
			case "水餃皮" :
				quantifier = "張";
				break;
				
			case "餛飩皮" :
				quantifier = "張";
				break;
			
			case "潤餅皮" :
				quantifier = "張";
				break;
				
			case "燒餅" :
				quantifier = "個";
				break;
			
			case "油條" :
				quantifier = "根";
				break;
				
			case "小餐包" :
				quantifier = "個";
				break;
				
			case "蘇打餅" :
				quantifier = "片";
				break;
				
			// 奶類
				
			case "低脂牛奶" :
				quantifier = "cc";
				break;
				
			case "全脂牛奶" :
				quantifier = "cc";
				break;
				
			case "起司" :
				quantifier = "片";
				break;
				
			case "低脂優格" :
				quantifier = "克"; 
				break;
				
			case "全脂優格" :
				quantifier = "克";
				break;
				
			case "低脂優酪乳" :
				quantifier = "cc";
				break;
				
			case "全脂優酪乳" :
				quantifier = "cc";
				break;
				
			// 水果
			
			case "水果" :
				quantifier = "碗";
				break;
				
		  // 油脂
				
			case "植物油" :
			case "豬油" :
			case "植物性奶油" :
			
				quantifier = "湯匙";
				break;
				
			case "杏仁果" :
			case "芝麻醬" :
			case "沙茶醬" :
			case "美乃滋" :
			
				quantifier = "湯匙";
				break;
				
			case "開心果" :
			case "核桃" :
			case "腰果" :
			case "松子" :
			case "花生醬" :
			case "花生粉" :
			case "鮮奶油" :
			case "芝麻粒" :
			
				quantifier = "湯匙";
				break;
				
			case "南瓜子" :
			
				quantifier = "湯匙";
				break;
				
			case "瓜子" :
			case "葵瓜子" :
			case "花生" :
			case "黃豆" :
			
				quantifier = "湯匙";
				break;
				
			// 青菜	
			case "蔬菜" :
			case "青菜" :
			case "菜" :
			
				quantifier = "碗";
				break;
				
			// 蛋豆魚肉
			case "魚" :
			case "蝦" :
			case "花枝" :
			case "章魚" :
			case "牡蠣" :
			case "蛤蠣" :
			case "海參" :
			case "豬大里肌" :
			case "肉乾" :
			case "雞里肌" :
			case "雞胸肉" :
			case "雞腿" :
			case "毛豆" :
			case "濕豆包" :
			case "麵腸" :
				
				quantifier = "份";
				break;
				
			case "虱目魚" :
			case "魚鬆" :
			case "花枝丸" :
			case "魚丸" :
			case "肉鬆" :
			case "豬排" :
			case "雞翅" :
			case "雞排" :
			case "雞爪" :
			case "豬肚" :
			case "豬小腸" :
			case "豬舌" :
			case "豬腳" :
			case "雞蛋" :
			case "蛋" :
			case "豆干" :
			case "豆腐" :
			case "豆乾" :
			case "素雞" :
			case "油豆腐" :
			
				
				quantifier = "份";
				break;
				
			case "秋刀魚" :
			case "鱈魚" :
			case "豬後腿肉" :
			case "臘肉" :
			case "豬肉酥" :
			case "內臟" :
			case "麵筋泡" :
			
				
				quantifier = "份";
				break;
				
			case "豬蹄膀" :
			case "梅花肉" :
			case "豬前腿肉" :
			case "五花肉" :
			case "豬大腸" :
			case "香腸" :
			case "熱狗" :
			
				
				quantifier = "份";
				break;
				
			case "無糖豆漿" :
			case "豆漿" :
			
				quantifier = "cc";
				break;
				
			default :
				
				// 從使用者做的清單讀
				for ( int i = 0 ; i < G.sListOfFood.size() ; i++ ) {
				  if ( foodName.equals( G.sListOfFood.get( i ).GetName() ) ) {
					  quantifier = G.sListOfFood.get( i ).GetQuantifier();
						found = true;
					} // if ()
				
				} // for()
				
				if ( !found ) {
					System.out.println( "找不到您所輸入的食物 \"" + foodName + "\" ，是否另外增加呢？　（　是／否　）" );
					yes = G.YesOrNo();

					if ( yes ) {
						System.out.println( "食物名稱：" + foodName );
						System.out.println( "請輸入該食物的量詞 ( 方便你日後計算用 )：" );
						System.out.print( ">> " );
						quantifier = G.sln.next();
						G.clearScreen();
						System.out.println( "" );
						

					} // if ( yes )
				
				} // if ( 沒找到 )
				
				
			break;
		
		} // switch()
		
		return quantifier;
		
		
	} // GetQuantifier()
	
} // class MainProcess
