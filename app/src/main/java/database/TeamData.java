package database;

import java.util.ArrayList;

public class TeamData {

    //private variables
//    int _id;
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

    int toteLevel1;
    int toteLevel2;
    int toteLevel3;
    int toteLevel4;
    int toteLevel5;
    int toteLevel6;

    int canLevel1;
    int canLevel2;
    int canLevel3;
    int canLevel4;
    int canLevel5;
    int canLevel6;

    boolean noodle;


    // Empty constructor
    public TeamData(){

    }
/*    // constructor
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
    }*/

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
//    // getting ID
//    public int getID(){
//        return this._id;
//    }
//
//    // setting id
//    public void setID(int id){
//        this._id = id;
//    }

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


    public void setNumberTotesAuto(int number_totes_auto){
        this.number_totes_auto = number_totes_auto;
    }

    public boolean getContainerAuto(){
        return this.container_auto;
    }


    public void setContainerAuto(boolean container_auto){
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

    public boolean isNoodle() {
        return noodle;
    }

    public void setNoodle(boolean noodle) {
        this.noodle = noodle;
    }


}