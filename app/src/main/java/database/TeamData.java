package database;

public class TeamData {

    //private variables
    int _id;
    int team_number = 0;
    int match_number = 0;
    boolean alliance_red = false;
    boolean robot_auto = false;
    boolean tote_auto = false;
    int number_totes_auto = 0;
    boolean container_auto = false;
    int number_containers_auto = 0;
    boolean assisted_totes_auto = false;
    int number_totes_stacked_auto = 0;

    // Empty constructor
    public TeamData(){

    }
    // constructor
    public TeamData(int id, int team_number, int match_number, boolean alliance_red, boolean robot_auto, boolean tote_auto, int number_totes_auto, boolean container_auto, int number_containers_auto, boolean assisted_totes_auto, int number_totes_stacked_auto){
        this._id = id;
        this.team_number = team_number;
        this.match_number = match_number;
        this.alliance_red = alliance_red;
        this.robot_auto = robot_auto;
        this.tote_auto = tote_auto;
        this.number_totes_auto = number_totes_auto;
        this.container_auto = container_auto;
        this.number_containers_auto = number_containers_auto;
        this.assisted_totes_auto = assisted_totes_auto;
        this.number_totes_stacked_auto = number_totes_stacked_auto;
    }

    // constructor
    public TeamData(int team_number, int match_number, boolean alliance_red, boolean robot_auto, boolean tote_auto, int number_totes_auto, boolean container_auto, int number_containers_auto, boolean assisted_totes_auto, int number_totes_stacked_auto){
        this.team_number = team_number;
        this.match_number = match_number;
        this.alliance_red = alliance_red;
        this.robot_auto = robot_auto;
        this.tote_auto = tote_auto;
        this.number_totes_auto = number_totes_auto;
        this.container_auto = container_auto;
        this.number_containers_auto = number_containers_auto;
        this.assisted_totes_auto = assisted_totes_auto;
        this.number_totes_stacked_auto = number_totes_stacked_auto;
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

    // getting alliance
    public boolean getAlliance(){
        return this.alliance_red;
    }

    // setting alliance
    public void setAlliance(boolean alliance){
        this.alliance_red = alliance;
    }


    public boolean getRobotAuto(){
        return this.robot_auto;
    }


    public void setRobotAuto(boolean robot_auto){
        this.robot_auto = robot_auto;
    }

    public boolean getToteAuto(){
        return this.tote_auto;
    }


    public void setToteAuto(boolean tote_auto){
        this.tote_auto = tote_auto;
    }

    public int getNumberTotesAuto(){
        return this.number_totes_auto;
    }


    public void setRNumberTotesAuto(int number_totes_auto){
        this.number_totes_auto = number_totes_auto;
    }

    public boolean getContainerAuto(){
        return this.container_auto;
    }


    public void setContainer_auto(boolean container_auto){
        this.container_auto = container_auto;
    }

    public int getNumberContainersAuto(){
        return this.number_containers_auto;
    }


    public void setNumberContainersAuto(int number_containers_auto){
        this.number_containers_auto = number_containers_auto;
    }

    public boolean getAssistedTotesAuto(){
        return this.assisted_totes_auto;
    }


    public void setAssistedTotesAuto(boolean assisted_totes_auto){
        this.assisted_totes_auto = assisted_totes_auto;
    }

    public int getNumberStackedTotesAuto(){
        return this.number_totes_stacked_auto;
    }


    public void setNumberStackedTotesAuto(int number_totes_stacked_auto){
        this.number_totes_stacked_auto = number_totes_stacked_auto;
    }




}