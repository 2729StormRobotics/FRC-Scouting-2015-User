package database;

public class TeamData {

    //private variables
    int _id;
    int team_number;
    int match_number;
//    Boolean robot_auto = false;
//    Boolean tote_auto = false;
//    int number_totes_auto;
//    Boolean container_auto;
//    int number_containers_auto;
//    Boolean assisted_totes_auto;
//    int number_totes_stacked_auto;

    // Empty constructor
    public TeamData(){

    }
    // constructor
    public TeamData(int id, int team_number, int match_number){
        this._id = id;
        this.team_number = team_number;
        this.match_number = match_number;
//        this.robot_auto = robot_auto;
//        this.tote_auto = tote_auto;
//        this.number_totes_auto = number_totes_auto;
//        this.container_auto = container_auto;
//        this.number_containers_auto = number_containers_auto;
//        this.assisted_totes_auto = assisted_totes_auto;
//        this.number_totes_stacked_auto = number_totes_stacked_auto;
    }

    // constructor
    public TeamData(int team_number, int match_number){
        this.team_number = team_number;
        this.match_number = match_number;
//        this.robot_auto = robot_auto;
//        this.tote_auto = tote_auto;
//        this.number_totes_auto = number_totes_auto;
//        this.container_auto = container_auto;
//        this.number_containers_auto = number_containers_auto;
//        this.assisted_totes_auto = assisted_totes_auto;
//        this.number_totes_stacked_auto = number_totes_stacked_auto;
    }
    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }

    // getting team number
    public int getTeamNumber(){
        return this.team_number;
    }

    // setting team number
    public void setTeamNumber(int number){
        this.team_number = number;
    }

    // getting match numberS
    public int getMatchNumber(){
        return this.match_number;
    }

    // setting match number
    public void setMatchNumber(int match_number){
        this.match_number = match_number;
    }
}