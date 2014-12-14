package com.example.frcscouting2015;

import java.util.ArrayList;

public class DataHandler {
	
	public static final int FEEDER_ZONE = 0, TRUSS_ZONE = 1, GOAL_ZONE = 2;
	public static final int MISS_GOAL = -1, NULL_GOAL = 0, BOTTOM_GOAL = 1, TOP_GOAL = 2;
	
	private static int teamNum=0, match=0, score=0, cycle=0,
			
				catch_fzone=0, catch_tzone=0, catch_gzone=0, //feeder, truss, goal
				throw_fzone=0, throw_tzone=0, throw_gzone=0, //feeder, truss, goal
				
				top_auto=0, topMisses_auto=0, bottom_auto=0, topHot_auto=0, botHot_auto=0,
				top_tele=0, topMisses_tele=0, bottom_tele=0;
	
	private static int tempPass=0, tempCatch=0, tempTrussPass=0, tempTrussCatch=0, tempTrussMiss=0,
			
				tempPass_fzone=0, tempPass_tzone, tempPass_gzone,
				tempCatch_fzone=0, tempCatch_tzone, tempCatch_gzone
				
				,tempTopMisses_tele = 0;
	
	private static ArrayList<Integer> passArray = new ArrayList<Integer>(), 
							   catchArray = new ArrayList<Integer>(),
							   trussCatchArray = new ArrayList<Integer>(),
							   trussPassArray = new ArrayList<Integer>(),
							   trussMissArray = new ArrayList<Integer>(),
							   cycleGoalsArray = new ArrayList<Integer>(),
							   cycleTimeArray = new ArrayList<Integer>();
	
	private static String info="", teamName="";
	private static boolean movement=false, defense=false, isRed=false, clockStarted=false, paused=false;
	private static double startTime=0, cycleTime=0;
	
	//INTERFACE METHODS
	public static void passed(int zone) {
		switch(zone){
		case FEEDER_ZONE:
			throw_fzone ++;
			tempPass_fzone ++;
			break;
		case TRUSS_ZONE:
			throw_tzone ++;
			tempPass_tzone ++;
			break;
		case GOAL_ZONE:
			throw_gzone ++;
			tempPass_gzone ++;
			break;
		}tempPass ++;
	}
	public static void caught(int zone) {
		switch(zone){
		case FEEDER_ZONE:
			catch_fzone ++;
			tempCatch_fzone ++;
			break;
		case TRUSS_ZONE:
			catch_tzone ++;
			tempCatch_tzone ++;
			break;
		case GOAL_ZONE:
			catch_gzone ++;
			tempCatch_gzone ++;
			break;
		}tempCatch ++;
	}
	public static void cancelPass(int zone) {
		switch(zone){
		case FEEDER_ZONE:
			if(tempPass_fzone>0){
				tempPass_fzone --;
				throw_fzone --;
			}
			break;
		case TRUSS_ZONE:
			if(tempPass_tzone>0){
				tempPass_tzone --;
				throw_tzone --;
			}
			break;
		case GOAL_ZONE:
			if(tempPass_gzone>0){
				tempPass_gzone --;
				throw_gzone --;
			}
			break;
		}
		if(tempPass>0)
			tempPass --;
	}
	public static void cancelCatch(int zone) {
		switch(zone){
		case FEEDER_ZONE:
			if(tempCatch_fzone>0){
				tempCatch_fzone --;
				catch_fzone --;
			}
			break;
		case TRUSS_ZONE:
			if(tempCatch_tzone>0){
				tempCatch_tzone --;
				catch_tzone --;
			}
			break;
		case GOAL_ZONE:
			if(tempCatch_gzone>0){
				tempCatch_gzone --;
				catch_gzone --;
			}
			break;
		}
		if(tempCatch>0)
			tempCatch --;
	}
	public static void trussPassed() {tempTrussPass ++;}
	public static void trussCaught() {tempTrussCatch ++;}
	public static void trussMissed() {tempTrussMiss ++;}
	public static void cancelTopMiss() {
		if(topMisses_tele>0){
			topMisses_tele --;
			tempTopMisses_tele --;
		}
	}
	
	public static void beginMatch() {
		startTime = (System.nanoTime() / 1000000000);
		cycleTime = 0;
		clockStarted = true;
		paused = false;
	}
	
	public static void stopTimer() {
		cycleTime += (System.nanoTime() / 1000000000) - startTime;
		paused = true;
	}
	
	public static void startTimer() {
		startTime = (System.nanoTime() / 1000000000);
		paused = false;
	}
	
	public static boolean isPaused() {
		return paused;
	}
	
	public static void clearAuto() {
		top_auto=
		topMisses_auto=
		bottom_auto=
		topHot_auto=
		botHot_auto=0;
	}
	
	public static void clearTrussInfo() {
		tempTrussPass=
		tempTrussCatch=
		tempTrussMiss=0;
	}
	public static void clear() {
		clearAuto();
		clearTrussInfo();
		
		tempPass=
		tempCatch=
		tempTrussPass=
		tempTrussCatch=
		tempTrussMiss=
		
		top_tele=
		bottom_tele=
		topMisses_tele=
		
		catch_fzone=
		catch_tzone=
		catch_gzone=
		
		throw_fzone=
		throw_tzone=
		throw_gzone=
		
		tempPass_fzone=
		tempPass_tzone=
		tempPass_gzone=
		
		tempCatch_fzone=
		tempCatch_tzone=
		tempCatch_gzone=
		tempTopMisses_tele=
		cycle=
		score=0;
		
		passArray.clear();
		catchArray.clear();
		trussPassArray.clear();
		trussCatchArray.clear();
		trussMissArray.clear();
		cycleGoalsArray.clear();
		cycleTimeArray.clear();
		
		clockStarted = false;
	}
	
	//ADDING METHODS
	private static void addPass(int num) {passArray.add(num);}
	private static void addCatch(int num) {catchArray.add(num);}
	private static void addTrussPass(int num) {trussPassArray.add(num);}
	private static void addTrussCatch(int num) {trussCatchArray.add(num);}
	private static void addTrussMiss(int num) {trussMissArray.add(num);}
	
	private static void score(int type) {cycleGoalsArray.add(type);}
	
	public static void endCycle() {
		
		stopTimer();
		
		addPass(tempPass);
		addCatch(tempCatch);
		addTrussPass(tempTrussPass);
		addTrussCatch(tempTrussCatch);
		addTrussMiss(tempTrussMiss);
		
		//gets the length of the cycle in seconds
		cycleTimeArray.add((int)(cycleTime));
		cycleTime = 0;
		
		//adds to the score
		int goalType = NULL_GOAL;
		try{
			goalType = getGoalThisCycle(cycle);
		}catch(Exception ex){
			score(NULL_GOAL);	
		}
		
		int	assists = tempPass + tempCatch, 
			points = 0;
		
		switch(goalType){
		case BOTTOM_GOAL:
			points += 1;
			break;
		case TOP_GOAL:
			points += 10;
			break;
		}
		
		points += assists * 10;
		addToScore(points);
		
		//resets all temp variables
		tempPass=
		tempCatch=
		tempTrussPass=
		tempTrussCatch=
		tempTrussMiss=
		
		tempPass_fzone=
		tempPass_tzone=
		tempPass_gzone=
		
		tempCatch_fzone=
		tempCatch_tzone=
		tempCatch_gzone=
		
		tempTopMisses_tele=0;
		
		cycle ++;
		
		startTimer();
		
	}
	
	public static void addToScore(int num) {score += num;}
	
	public static void addBottomGoalAuto(boolean isHot) {
		if(isHot){
			botHot_auto ++;
		}else{
			bottom_auto ++;
		}
	}
	public static void addBottomGoalTele() {
		bottom_tele ++;
		score(BOTTOM_GOAL);
	}
	
	public static void addTopGoalAuto(boolean isHot, boolean missed){
		if(missed){
			topMisses_auto ++;
			return;
		}if(isHot){
			topHot_auto ++;
		}else{
			top_auto ++;
		}
	}
	public static void addTopGoalTele(boolean missed){
		if(missed){
			topMisses_tele ++;
			tempTopMisses_tele ++;
			score(MISS_GOAL);
			return;
		}top_tele ++;
		score(TOP_GOAL);
	}
	
	//SETTING METHODS
	public static void setTeamNum(int num) {teamNum = num;}
	public static void setMatchNum(int num) {match = num;}
	public static void setScore(int num) {score = num;}
	
	public static void setToRedTeam() {isRed = true;}
	public static void setMoved(boolean b) {movement = b;}
	public static void setDefense(boolean b) {defense = b;}
	public static void setInfo(String s) {info = s;}
	public static void setTeamName(String s) {teamName = s;}
	
	//GETTING METHODS
	public static String getInfo() {return info;} //returns additional info
	public static String getTeamName() {return teamName;} //returns team name as string

	public static int getTotalThrownAssists() {return throw_fzone+throw_tzone+throw_gzone;}
	public static int getThrownAssistsThisCycle() {return tempPass;}
	public static int getThrownAssistsFrom(int zone) {
		switch(zone){
		case FEEDER_ZONE:
			return throw_fzone;
		case TRUSS_ZONE:
			return throw_tzone;
		case GOAL_ZONE:
			return throw_gzone;
		default:
			return 0;
		}
	}
	public static int getThrownAssistsThisCycle(int zone) {
		switch(zone){
		case FEEDER_ZONE:
			return tempPass_fzone;
		case TRUSS_ZONE:
			return tempPass_tzone;
		case GOAL_ZONE:
			return tempPass_gzone;
		default:
			return 0;
		}
	}
	
	public static int getTotalCaughtAssists() {return catch_fzone+catch_tzone+catch_gzone;}
	public static int getCaughtAssistsThisCycle() {return tempCatch;}
	public static int getCaughtAssistsFrom(int zone) {
		switch(zone){
		case FEEDER_ZONE:
			return catch_fzone;
		case TRUSS_ZONE:
			return catch_tzone;
		case GOAL_ZONE:
			return catch_gzone;
		default:
			return 0;
		}
	}
	public static int getCaughtAssistsThisCycle(int zone) {
		switch(zone){
		case FEEDER_ZONE:
			return tempCatch_fzone;
		case TRUSS_ZONE:
			return tempCatch_tzone;
		case GOAL_ZONE:
			return tempCatch_gzone;
		default:
			return 0;
		}
	}
	
	public static int getTopGoalsAuto() {return top_auto;} //returns number of top goals made in autonomous
	public static int getTopHotGoals() {return topHot_auto;} //returns number of top hot goals made
	public static int getTopGoalsTele() {return top_tele;} //returns number of top goals made in tele-op
	
	public static int getTopGoalMissesAuto() {return topMisses_auto;} //returns top goal misses in autonomous
	
	public static int getBotGoalsAuto() {return bottom_auto;} //returns number of bottom goals made in autonomous
	public static int getBotHotGoals() {return botHot_auto;} //returns number of bottom hot goals made
	public static int getBotGoalsTele() {return bottom_tele;} //returns number of bottom goals made in tele-op
	
	public static int getTopGoalMissesTele() {return tempTopMisses_tele;} //returns top goal misses in tele-op
	
	public static int getTrussPassesThisCycle() {return tempTrussPass;} //returns truss passes in current cycle
	public static int getTrussCatchesThisCycle() {return tempTrussCatch;} //returns truss catches in current cycle
	public static int getTrussMissesThisCycle() {return tempTrussMiss;} //returns truss misses in current cycle
	
	public static int getTotalTrussPasses() {
		int sum = 0;
		for(int i:trussPassArray){
			sum += i;
		}
		return sum;
	}
	public static int getTotalTrussCatches() {
		int sum = 0;
		for(int i:trussCatchArray){
			sum += i;
		}
		return sum;
	}
	
	public static int getCycles() {return cycle;} //returns number of cycles
	public static int getScore() {return score;} //returns robot's score
	public static int getTeamNum() {return teamNum;} //returns team number
	public static int getMatchNum() {return match;} //returns match number
	
	public static int getCycleLength(int i) {return cycleTimeArray.get(i);} //returns length of cycle in seconds
	public static int getGoalThisCycle(int i) {return cycleGoalsArray.get(i);} //returns the goal type for given cycle
	
	public static boolean didMove() {return movement;} //returns true if robot moved in autonomous
	public static boolean didDefend() {return defense;} //returns true if team defended
	public static boolean isRed() {return isRed;} //returns true if team red
	public static boolean isClockRunning() {return clockStarted;} //checks to see if teleop has started
	
	public static String getPasses() {
		//returns passes one long string
		//integers are separated by commas
		if(passArray.isEmpty()) 
			return "'0,0,0'";
		String result = "'"+passArray.get(0);
		for(int i=1;i<passArray.size();i++){
			result += ","+passArray.get(i);
		}
		
		int lengthNeeded = 3 - passArray.size();
		if(lengthNeeded > 0)
			for(int i=0; i<lengthNeeded; i++)
				result += ",0";
		
		return result + "'";
	}
	public static String getCatches() {
		//returns catches one long string
		//integers are separated by commas
		if(catchArray.isEmpty()) 
			return "'0,0,0'";
		String result = "'"+catchArray.get(0);
		for(int i=1;i<catchArray.size();i++){
			result += ","+catchArray.get(i);
		}
		
		int lengthNeeded = 3 - catchArray.size();
		if(lengthNeeded > 0)
			for(int i=0; i<lengthNeeded; i++)
				result += ",0";
		
		return result + "'";
	}
	public static String getTrussPasses() {
		//returns truss passes one long string
		//integers are separated by commas
		if(trussPassArray.isEmpty()) 
			return "'0,0,0'";
		String result = "'"+trussPassArray.get(0);
		for(int i=1;i<trussPassArray.size();i++){
			result += ","+trussPassArray.get(i);
		}
		
		int lengthNeeded = 3 - trussPassArray.size();
		if(lengthNeeded > 0)
			for(int i=0; i<lengthNeeded; i++)
				result += ",0";
		
		return result + "'";
	}
	public static String getTrussCatches() {
		//returns truss catches one long string
		//integers are separated by commas
		if(trussCatchArray.isEmpty()) 
			return "'0,0,0'";
		String result = "'"+trussCatchArray.get(0);
		for(int i=1;i<trussCatchArray.size();i++){
			result += ","+trussCatchArray.get(i);
		}
		
		int lengthNeeded = 3 - trussCatchArray.size();
		if(lengthNeeded > 0)
			for(int i=0; i<lengthNeeded; i++)
				result += ",0";
		
		return result + "'";
	}
	public static String getTrussMisses() {
		//returns truss misses one long string
		//integers are separated by commas
		if(trussMissArray.isEmpty()) 
			return "'0,0,0'";
		String result = "'"+trussMissArray.get(0);
		for(int i=1;i<trussMissArray.size();i++){
			result += ","+trussMissArray.get(i);
		}
		
		int lengthNeeded = 3 - trussMissArray.size();
		if(lengthNeeded > 0)
			for(int i=0; i<lengthNeeded; i++)
				result += ",0";
		
		return result + "'";
	}
	public static String getCycleGoals() {
		//returns goals one long string
		//integers are separated by commas
		if(cycleGoalsArray.isEmpty()) 
			return "'0,0,0'";
		String result = "'"+cycleGoalsArray.get(0);
		for(int i=1;i<cycleGoalsArray.size();i++){
			result += ","+cycleGoalsArray.get(i);
		}
		
		int lengthNeeded = 3 - cycleGoalsArray.size();
		if(lengthNeeded > 0)
			for(int i=0; i<lengthNeeded; i++)
				result += ",0";
		
		return result + "'";
	}
	public static String getCycleTimes() {
		//returns lengths of cycles in seconds in one long string
		//integers are separated by commas
		if(cycleTimeArray.isEmpty()) 
			return "'0,0,0'";
		String result = "'"+cycleTimeArray.get(0);
		for(int i=1;i<cycleTimeArray.size();i++){
			result += ","+cycleTimeArray.get(i);
		}
		
		int lengthNeeded = 3 - cycleTimeArray.size();
		if(lengthNeeded > 0)
			for(int i=0; i<lengthNeeded; i++)
				result += ",0";
		
		return result + "'";
	}
	
	public static int booleanToInt(boolean b) {
		if(b)
			return 1;
		else
			return 0;
	}
	
	public static String getAllData() {
		//returns all of the data in one long string to convert to a QR code
		String result = "@stormscouting ";
		result += getTeamNum()+","							//gets team number
				  +getMatchNum()+","						//gets match number
				  +booleanToInt(isRed())+","				//gets whether alliance is red
				  
				  //AUTONOMOUS
				  +getTopGoalsAuto()+","					//gets top goals in autonomous
				  +getTopGoalMissesAuto()+","				//gets top goal misses in autonomous
				  +getTopHotGoals()+","						//gets top hot goals
				  +getBotGoalsAuto()+","					//gets bottom goals in autonomous
				  +getBotHotGoals()+","						//gets bottom hot goals
				  +booleanToInt(didDefend())+","			//gets whether or not team defended
				  +booleanToInt(didMove())+","				//gets whether or not team moved
				  
				  
				  //TELE OP
				  +getCycles()+","							//gets total cycle number
				  +getPasses()+","							//gets assist pass Array
				  +getThrownAssistsFrom(FEEDER_ZONE)+","	//gets passes from feeder zone
				  +getThrownAssistsFrom(TRUSS_ZONE)+","		//gets passes from truss zone
				  +getThrownAssistsFrom(GOAL_ZONE)+","		//gets passes from goal zone
				  +getCatches()+","							//gets assist catch array
				  +getCaughtAssistsFrom(FEEDER_ZONE)+","	//gets catches from feeder zone
				  +getCaughtAssistsFrom(TRUSS_ZONE)+","		//gets catches from truss zone
				  +getCaughtAssistsFrom(GOAL_ZONE)+","		//gets catches from goal zone
				  +getTrussPasses()+","						//gets all the truss passes
				  +getTrussMisses()+","						//gets all the missed truss passes
				  +getTrussCatches()+","					//gets all the truss catches
				  +getTopGoalsTele()+","					//gets all top goals made
				  +getTopGoalMissesTele()+","				//gets all top goal misses
				  +getBotGoalsTele()+","					//gets all bottom goals made
				  +getCycleGoals()+","						//gets all the goals
				  +getCycleTimes();							//gets length of cycles in seconds
		
		return result;
				  
	}
	
}
