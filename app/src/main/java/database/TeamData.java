package database;

public class TeamData {

    //private variables
//    int _id;
    int team_number = 0;
    int match_number = 0;
    boolean alliance_red = false;
    boolean robot_auto = false;
    //boolean tote_auto = false;
    int number_totes_auto = 0;
    // boolean container_auto = false;
    int number_containers_auto = 0;
    //  boolean assisted_totes_auto = false;
    int number_totes_stacked_auto = 0;

    int containers_center_auto = 0;


    int toteLevel1 = 0;
    int toteLevel2 = 0;
    int toteLevel3 = 0;
    int toteLevel4 = 0;
    int toteLevel5 = 0;
    int toteLevel6 = 0;

    int canLevel1 = 0;
    int canLevel2 = 0;
    int canLevel3 = 0;
    int canLevel4 = 0;
    int canLevel5 = 0;
    int canLevel6 = 0;



    int coopLevel1 = 0;
    int coopLevel2 = 0;
    int coopLevel3 = 0;
    int coopLevel4 = 0;

    int noodle = 0;
    int coop = 0;

    String notes = " ";


    public TeamData(int team_number, int match_number, boolean alliance_red) {
        this.team_number = team_number;
        this.match_number = match_number;
        this.alliance_red = alliance_red;
    }

    public TeamData() {

    }


    public TeamData(int team_number, int match_number, boolean alliance_red, boolean robot_auto,
                    int number_totes_auto, int number_containers_auto,
                    int number_totes_stacked_auto, int containers_center_auto, int tote_level1, int tote_level2, int tote_level3,
                    int tote_level4, int tote_level5, int tote_level6, int can_level1, int can_level2,
                    int can_level3, int can_level4, int can_level5, int can_level6, int noodle, int coop, String notes
    ) {
        this.team_number = team_number;
        this.match_number = match_number;
        this.alliance_red = alliance_red;
        this.robot_auto = robot_auto;
        //this.tote_auto = tote_auto;
        this.number_totes_auto = number_totes_auto;
        // this.container_auto = container_auto;
        this.number_containers_auto = number_containers_auto;
        // this.assisted_totes_auto = assisted_totes_auto;
        this.number_totes_stacked_auto = number_totes_stacked_auto;
        this.containers_center_auto = containers_center_auto;

        this.toteLevel1 = tote_level1;
        this.toteLevel2 = tote_level2;
        this.toteLevel3 = tote_level3;
        this.toteLevel4 = tote_level4;
        this.toteLevel5 = tote_level5;
        this.toteLevel6 = tote_level6;

        this.canLevel1 = can_level1;
        this.canLevel2 = can_level2;
        this.canLevel3 = can_level3;
        this.canLevel4 = can_level4;
        this.canLevel5 = can_level5;
        this.canLevel6 = can_level6;

        this.noodle = noodle;
        this.coop = coop;
        this.notes = notes;


    }

    // getting team number
    public int getTeamNumber() {
        return this.team_number;
    }

    // setting team number
    public void setTeamNumber(int number) {
        this.team_number = number;
    }

    // getting match numberS
    public int getMatchNumber() {
        return this.match_number;
    }

    // setting match number
    public void setMatchNumber(int match_number) {
        this.match_number = match_number;
    }

    // getting alliance
    public boolean getAlliance() {
        return this.alliance_red;
    }

    // setting alliance
    public void setAlliance(boolean alliance) {
        this.alliance_red = alliance;
    }


    public boolean getRobotAuto() {
        return this.robot_auto;
    }


    public void setRobotAuto(boolean robot_auto) {
        this.robot_auto = robot_auto;
    }


    public int getNumberTotesAuto() {
        return this.number_totes_auto;
    }


    public void setNumberTotesAuto(int number_totes_auto) {
        this.number_totes_auto = number_totes_auto;
    }

    public int getNumberContainersAuto() {
        return this.number_containers_auto;
    }


    public void setNumberContainersAuto(int number_containers_auto) {
        this.number_containers_auto = number_containers_auto;
    }

    public int getNumberStackedTotesAuto() {
        return this.number_totes_stacked_auto;
    }


    public void setNumberStackedTotesAuto(int number_totes_stacked_auto) {
        this.number_totes_stacked_auto = number_totes_stacked_auto;
    }


    public int getToteLevel1() {
        return toteLevel1;
    }

    public void setToteLevel1(int toteLevel1) {
        this.toteLevel1 = toteLevel1;
    }

    public int getToteLevel2() {
        return toteLevel2;
    }

    public void setToteLevel2(int toteLevel2) {
        this.toteLevel2 = toteLevel2;
    }

    public int getToteLevel3() {
        return toteLevel3;
    }

    public void setToteLevel3(int toteLevel3) {
        this.toteLevel3 = toteLevel3;
    }

    public int getToteLevel4() {
        return toteLevel4;
    }

    public void setToteLevel4(int toteLevel4) {
        this.toteLevel4 = toteLevel4;
    }

    public int getToteLevel5() {
        return toteLevel5;
    }

    public void setToteLevel5(int toteLevel5) {
        this.toteLevel5 = toteLevel5;
    }

    public int getToteLevel6() {
        return toteLevel6;
    }

    public void setToteLevel6(int toteLevel6) {
        this.toteLevel6 = toteLevel6;
    }

    public int getCanLevel1() {
        return canLevel1;
    }

    public void setCanLevel1(int canLevel1) {
        this.canLevel1 = canLevel1;
    }

    public int getCanLevel2() {
        return canLevel2;
    }

    public void setCanLevel2(int canLevel2) {
        this.canLevel2 = canLevel2;
    }

    public int getCanLevel3() {
        return canLevel3;
    }

    public void setCanLevel3(int canLevel3) {
        this.canLevel3 = canLevel3;
    }

    public int getCanLevel4() {
        return canLevel4;
    }

    public void setCanLevel4(int canLevel4) {
        this.canLevel4 = canLevel4;
    }

    public int getCanLevel5() {
        return canLevel5;
    }

    public void setCanLevel5(int canLevel5) {
        this.canLevel5 = canLevel5;
    }

    public int getCanLevel6() {
        return canLevel6;
    }

    public void setCanLevel6(int canLevel6) {
        this.canLevel6 = canLevel6;
    }

    public int getNoodle() {
        return noodle;
    }

    public void setNoodle(int noodle) {
        this.noodle = noodle;
    }

    public int getCoop() {
        return coop;
    }

    public void setCoop(int coop) {
        this.coop = coop;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getContainers_center_auto() {
        return containers_center_auto;
    }

    public void setContainers_center_auto(int containers_center_auto) {
        this.containers_center_auto = containers_center_auto;
    }

    public int getCoopLevel1() {
        return coopLevel1;
    }

    public void setCoopLevel1(int coopLevel1) {
        this.coopLevel1 = coopLevel1;
    }

    public int getCoopLevel2() {
        return coopLevel2;
    }

    public void setCoopLevel2(int coopLevel2) {
        this.coopLevel2 = coopLevel2;
    }

    public int getCoopLevel3() {
        return coopLevel3;
    }

    public void setCoopLevel3(int coopLevel3) {
        this.coopLevel3 = coopLevel3;
    }

    public int getCoopLevel4() {
        return coopLevel4;
    }

    public void setCoopLevel4(int coopLevel4) {
        this.coopLevel4 = coopLevel4;
    }

}