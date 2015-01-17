package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    private static DatabaseHandler sInstance = null;
    // Database Version
    private static final int DATABASE_VERSION = 5;

    // Database Name
    private static final String DATABASE_NAME = "Team_Manager";

    // Contacts table name
    private static final String TABLE_TEAM = "Team_Data";

    // Contacts Table Columns names
   // private static final String KEY_ID = "id";
    //main
    private static final String KEY_TEAM_NUMBER = "team_number";
    private static final String KEY_MATCH_NUMBER = "match_number";
    private static final String KEY_ALLIANCE = "alliance";
    //auto
    private static final String KEY_ROBOT_AUTO = "robot_auto";
    private static final String KEY_TOTE_AUTO = "tote_auto";
    private static final String KEY_NUMBER_TOTES_AUTO = "number_totes_auto";
    private static final String KEY_CONTAINER_AUTO = "container_auto";
    private static final String KEY_NUMBER_CONTAINERS_AUTO = "number_containers_auto";
    private static final String KEY_ASSISTED_TOTES_AUTO = "assisted_totes_auto";
    private static final String KEY_NUMBER_TOTES_STACKED_AUTO = "number_totes_stacked_auto";
    //telop


    private static  final String KEY_TOTE_LEVEL1 = "tote_level1";
    private static  final String KEY_TOTE_LEVEL2 = "tote_level2";
    private static  final String KEY_TOTE_LEVEL3 = "tote_level3";
    private static  final String KEY_TOTE_LEVEL4 = "tote_level4";
    private static  final String KEY_TOTE_LEVEL5 = "tote_level5";
    private static  final String KEY_TOTE_LEVEL6 = "tote_level6";

    private static  final String KEY_CAN_LEVEL1 = "can_level1";
    private static  final String KEY_CAN_LEVEL2 = "can_level2";
    private static  final String KEY_CAN_LEVEL3 = "can_level3";
    private static  final String KEY_CAN_LEVEL4 = "can_level4";
    private static  final String KEY_CAN_LEVEL5 = "can_level5";
    private static  final String KEY_CAN_LEVEL6 = "can_level6";

    private static  final String KEY_NOODLE = "noodle";
    private static final String KEY_COOP = "coop";

    private Context mCtx;

    public static DatabaseHandler getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseHandler(context.getApplicationContext());
        }
        return sInstance;
    }


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mCtx = context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TEAM = "CREATE TABLE " + TABLE_TEAM + "("
                + KEY_TEAM_NUMBER + " INTEGER,"
                + KEY_MATCH_NUMBER + " INTEGER,"
                + KEY_ALLIANCE + " INTEGER,"
                + KEY_ROBOT_AUTO + " INTEGER,"
                + KEY_TOTE_AUTO + " INTEGER,"
                + KEY_NUMBER_TOTES_AUTO + " INTEGER,"
                + KEY_CONTAINER_AUTO + " INTEGER,"
                + KEY_NUMBER_CONTAINERS_AUTO + " INTEGER,"
                + KEY_ASSISTED_TOTES_AUTO + " INTEGER,"
                + KEY_NUMBER_TOTES_STACKED_AUTO + " INTEGER,"
                + KEY_TOTE_LEVEL1 + " INTEGER,"
                + KEY_TOTE_LEVEL2 + " INTEGER,"
                + KEY_TOTE_LEVEL3 + " INTEGER,"
                + KEY_TOTE_LEVEL4 + " INTEGER,"
                + KEY_TOTE_LEVEL5 + " INTEGER,"
                + KEY_TOTE_LEVEL6 + " INTEGER,"
                + KEY_CAN_LEVEL1 + " INTEGER,"
                + KEY_CAN_LEVEL2 + " INTEGER,"
                + KEY_CAN_LEVEL3 + " INTEGER,"
                + KEY_CAN_LEVEL4 + " INTEGER,"
                + KEY_CAN_LEVEL5 + " INTEGER,"
                + KEY_CAN_LEVEL6 + " INTEGER,"
                + KEY_NOODLE + " INTEGER,"
                + KEY_COOP + " INTEGER"
                + ")";
        db.execSQL(CREATE_TEAM);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAM);

        // Create tables again
        onCreate(db);
    }

    private ContentValues  addValues(TeamData teamData){
        ContentValues values = new ContentValues();
        //main
        values.put(KEY_TEAM_NUMBER, teamData.getTeamNumber());
        values.put(KEY_MATCH_NUMBER,teamData.getMatchNumber());
        values.put(KEY_ALLIANCE, teamData.getAlliance());
        //auto
        values.put(KEY_ROBOT_AUTO, teamData.getRobotAuto());
        values.put(KEY_TOTE_AUTO, teamData.getToteAuto());
        values.put(KEY_NUMBER_TOTES_AUTO, teamData.getNumberTotesAuto());
        values.put(KEY_CONTAINER_AUTO, teamData.getContainerAuto());
        values.put(KEY_NUMBER_CONTAINERS_AUTO, teamData.getNumberContainersAuto());
        values.put(KEY_ASSISTED_TOTES_AUTO, teamData.getAssistedTotesAuto());
        values.put(KEY_NUMBER_TOTES_STACKED_AUTO, teamData.getNumberStackedTotesAuto());

        //tele
        values.put(KEY_TOTE_LEVEL1, teamData.getToteLevel1());
        values.put(KEY_TOTE_LEVEL2, teamData.getToteLevel2());
        values.put(KEY_TOTE_LEVEL3, teamData.getToteLevel3());
        values.put(KEY_TOTE_LEVEL4, teamData.getToteLevel4());
        values.put(KEY_TOTE_LEVEL5, teamData.getToteLevel5());
        values.put(KEY_TOTE_LEVEL6, teamData.getToteLevel6());

        values.put(KEY_CAN_LEVEL1, teamData.getCanLevel1());
        values.put(KEY_CAN_LEVEL2, teamData.getCanLevel2());
        values.put(KEY_CAN_LEVEL3, teamData.getCanLevel3());
        values.put(KEY_CAN_LEVEL4, teamData.getCanLevel4());
        values.put(KEY_CAN_LEVEL5, teamData.getCanLevel5());
        values.put(KEY_CAN_LEVEL6, teamData.getCanLevel6());

        values.put(KEY_NOODLE, teamData.getNoodle());
        values.put(KEY_COOP, teamData.getCoop());

        return values;
    }

    public void addTeamData(TeamData teamData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = addValues(teamData);

        // Inserting Row
        db.insert(TABLE_TEAM, null, values);
        db.close(); // Closing database connection
    }

    public TeamData getTeamData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TEAM, null, KEY_TEAM_NUMBER + " = ?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        TeamData teamData = new TeamData(
                cursor.getInt(0), cursor.getInt(1), cursor.getInt(2)>0, cursor.getInt(3)>0, cursor.getInt(4)>0, cursor.getInt(5), cursor.getInt(6)>0, cursor.getInt(7), cursor.getInt(8)>0, cursor.getInt(9), cursor.getInt(10), cursor.getInt(11), cursor.getInt(12), cursor.getInt(13), cursor.getInt(14), cursor.getInt(15), cursor.getInt(16), cursor.getInt(17), cursor.getInt(18), cursor.getInt(19), cursor.getInt(20), cursor.getInt(21), cursor.getInt(22), cursor.getInt(23));
        return teamData;
    }

    public void clearTable()   {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TEAM, null,null);
    }

    // Updating
    public int updateTeamData(TeamData teamData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = addValues(teamData);
        //Log.d("robot_auto database", String.valueOf(teamData.getRobotAuto()));
       // Log.d("robot_auto database values list", String.valueOf(values.get(KEY_ROBOT_AUTO)));


        //TODO teleop

        // updating row
        return db.update(TABLE_TEAM, values, KEY_TEAM_NUMBER + " = ? AND "  + KEY_MATCH_NUMBER +  "= ?",
                new String[] { String.valueOf(teamData.getTeamNumber()), String.valueOf(teamData.getMatchNumber())});
    }

    public List<TeamData> getAllTeamData() {
        List<TeamData> teamDataList = new ArrayList<TeamData>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TEAM;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TeamData teamData = new TeamData();
                //main
                teamData.setTeamNumber(cursor.getInt(0));
              /*  Log.d("robot_auto database team number", String.valueOf(cursor.getInt(0)));
                Log.d("robot_auto database match number", String.valueOf(cursor.getInt(1)));
                Log.d("robot_auto database red alliance", String.valueOf(cursor.getInt(2)));
                Log.d("robot_auto database robot auto", String.valueOf(cursor.getInt(3)));*/
                teamData.setMatchNumber(cursor.getInt(1));
                teamData.setAlliance(cursor.getInt(2)>0);
                //Auto
                teamData.setRobotAuto(cursor.getInt(3)>0);
                teamData.setToteAuto(cursor.getInt(4)>0);
                teamData.setNumberTotesAuto(cursor.getInt(5));
                teamData.setContainerAuto(cursor.getInt(6) > 0);
                teamData.setNumberContainersAuto(cursor.getInt(7));
                teamData.setAssistedTotesAuto(cursor.getInt(8)>0);
                teamData.setNumberStackedTotesAuto(cursor.getInt(9));

                //TODO Tele-op

                teamData.setToteLevel1(cursor.getInt(10));
                teamData.setToteLevel2(cursor.getInt(11));
                teamData.setToteLevel3(cursor.getInt(12));
                teamData.setToteLevel4(cursor.getInt(13));
                teamData.setToteLevel5(cursor.getInt(14));
                teamData.setToteLevel6(cursor.getInt(15));

                teamData.setCanLevel1(cursor.getInt(16));
                teamData.setCanLevel2(cursor.getInt(17));
                teamData.setCanLevel3(cursor.getInt(18));
                teamData.setCanLevel4(cursor.getInt(19));
                teamData.setCanLevel5(cursor.getInt(20));
                teamData.setCanLevel6(cursor.getInt(21));

                teamData.setNoodle(cursor.getInt(22));
                teamData.setCoop(cursor.getInt(23));


                // Adding contact to list
                teamDataList.add(teamData);
            } while (cursor.moveToNext());
        }

        // return contact list
        return teamDataList;
    }


}