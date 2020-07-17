
class CalCount {

	static void CountIdealWeight() throws Throwable {
		G.sIdealWeight = ( G.sHeight ) * ( G.sHeight ) * 22 / 10000;

	} // CountIdeal()

	static void CountDailyCal() throws Throwable {
		double tempDouble = 0;
		
		if ( G.sWorkOutType.equals( "無" ) ) {
			G.sDailyCal = (int)G.sIdealWeight * 20;

		} // if ( no )

		else if ( G.sWorkOutType.equals( "輕度" ) ) {
			G.sDailyCal = (int)G.sIdealWeight * 25;

		} // else if ( little )

		else if ( G.sWorkOutType.equals( "中度" ) ) {
			G.sDailyCal = (int)G.sIdealWeight * 30;

		} // else if ( mid )

		else if ( G.sWorkOutType.equals( "重度" ) ) {
			G.sDailyCal = (int)G.sIdealWeight * 45;

		} // else if ( high )
		/*
		if ( G.sGender.equals( "男" ) ) {
			
			tempDouble = 66 + 13.7 * G.sWeight + 5 * G.sHeight - 6.8 * G.sAge;
			
		} // if( 男 )
		else if ( G.sGender.equals( "女" ) ) {
		
			tempDouble = 655 + 9.6 * G.sWeight + 1.8 * G.sHeight - 4.7 * G.sAge;
			
		} // if( 女 )
		
		
		if ( G.sWorkOutType.equals( "無" ) ) {
			tempDouble = tempDouble * 1.2;

		} // if ( no )

		else if ( G.sWorkOutType.equals( "輕度" ) ) {
			tempDouble = tempDouble * 1.3;

		} // else if ( little )

		else if ( G.sWorkOutType.equals( "中度" ) ) {
			tempDouble = tempDouble * 1.4;

		} // else if ( mid )

		else if ( G.sWorkOutType.equals( "重度" ) ) {
			tempDouble = tempDouble * 1.5;

		} // else if ( high )
		
		G.sDailyCal = ( int )tempDouble;
		*/
		
	} // CountDailyCal
	
	static void CountBasicNutrition() {
		G.sBasicCarbohydrates = G.sDailyCal / 10 * 5 / 4;
		G.sBasicProtein = G.sDailyCal / 10 * 3 / 4;
		G.sBasicFat = G.sDailyCal / 10 * 2 / 9;
	} // CountBasicNutrition()
	

} // CalCount