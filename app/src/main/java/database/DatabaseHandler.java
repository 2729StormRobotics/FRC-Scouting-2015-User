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
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "Team_Manager";

    // Contacts table name
    private static final String TABLE_TEAM = "Team_Data";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TEAM_NUMBER = "team_number";
    private static final String KEY_MATCH_NUMBER = "match_number";
    private static final String KEY_ALLIANCE = "alliance";
    private static final String KEY_ROBOT_AUTO = "robot_auto";
    private static final String KEY_TOTE_AUTO = "tote_auto";
    private static final String KEY_NUMBER_TOTES_AUTO = "number_totes_auto";
    private static final String KEY_CONTAINER_AUTO = "container_auto";
    private static final String KEY_NUMBER_CONTAINERS_AUTO = "number_containers_auto";
    private static final String KEY_ASSISTED_TOTES_AUTO = "assisted_totes_auto";
    private static final String KEY_NUMBER_TOTES_STACKED_AUTO = "number_totes_stacked_auto";
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
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TEAM_NUMBER + " INTEGER,"
                + KEY_MATCH_NUMBER + " INTEGER,"
                + KEY_ALLIANCE + " INTEGER,"
                + KEY_ROBOT_AUTO + " INTEGER,"
                + KEY_TOTE_AUTO + " INTEGER,"
                + KEY_NUMBER_TOTES_AUTO + " INTEGER,"
                + KEY_CONTAINER_AUTO + " INTEGER,"
                + KEY_NUMBER_CONTAINERS_AUTO + " INTEGER,"
                + KEY_ASSISTED_TOTES_AUTO + " INTEGER,"
                + KEY_NUMBER_TOTES_STACKED_AUTO + " INTEGER"
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

    public void addTeamData(TeamData teamData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TEAM_NUMBER, teamData.getTeamNumber());
        values.put(KEY_MATCH_NUMBER,teamData.getMatchNumber());
        values.put(KEY_ALLIANCE, teamData.getAlliance());
        values.put(KEY_ROBOT_AUTO, teamData.getRobotAuto());
        values.put(KEY_TOTE_AUTO, teamData.getToteAuto());
        values.put(KEY_NUMBER_TOTES_AUTO, teamData.getNumberTotesAuto());
        values.put(KEY_CONTAINER_AUTO, teamData.getContainerAuto());
        values.put(KEY_NUMBER_CONTAINERS_AUTO, teamData.getNumberContainersAuto());
        values.put(KEY_ASSISTED_TOTES_AUTO, teamData.getAssistedTotesAuto());
        values.put(KEY_NUMBER_TOTES_STACKED_AUTO, teamData.getNumberStackedTotesAuto());

        // Inserting Row
        db.insert(TABLE_TEAM, null, values);
        db.close(); // Closing database connection
    }

    public TeamData getTeamData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TEAM, new String[] { KEY_ID,
                        KEY_TEAM_NUMBER, KEY_MATCH_NUMBER, KEY_ALLIANCE, KEY_ROBOT_AUTO, KEY_TOTE_AUTO, KEY_NUMBER_TOTES_AUTO, KEY_CONTAINER_AUTO, KEY_NUMBER_CONTAINERS_AUTO, KEY_ASSISTED_TOTES_AUTO, KEY_NUMBER_TOTES_STACKED_AUTO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        TeamData teamData = new TeamData(Integer.parseInt(cursor.getString(0)),
                cursor.getInt(1), cursor.getInt(2), cursor.getInt(3)>0, cursor.getInt(4)>0, cursor.getInt(5)>0, cursor.getInt(6), cursor.getInt(7)>0, cursor.getInt(8), cursor.getInt(9)>0, cursor.getInt(10));
        return teamData;
    }

    public void clearTable()   {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TEAM, null,null);
    }

    // Updating
    public int updateTeamData(TeamData teamData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TEAM_NUMBER, teamData.getTeamNumber());
        values.put(KEY_MATCH_NUMBER, teamData.getMatchNumber());
        values.put(KEY_ALLIANCE, teamData.getAlliance());
        values.put(KEY_ROBOT_AUTO, teamData.getRobotAuto());
        values.put(KEY_TOTE_AUTO, teamData.getToteAuto());
        values.put(KEY_NUMBER_TOTES_AUTO, teamData.getNumberTotesAuto());
        values.put(KEY_CONTAINER_AUTO, teamData.getContainerAuto());
        values.put(KEY_NUMBER_CONTAINERS_AUTO, teamData.getNumberContainersAuto());
        values.put(KEY_ASSISTED_TOTES_AUTO, teamData.getAssistedTotesAuto());
        values.put(KEY_NUMBER_TOTES_STACKED_AUTO, teamData.getAssistedTotesAuto());

        // updating row
        return db.update(TABLE_TEAM, values, KEY_ID + " = ?",
                new String[] { String.valueOf(teamData.getID()) });
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
                teamData.setID(Integer.parseInt(cursor.getString(0)));
                teamData.setTeamNumber(cursor.getInt(1));
                teamData.setMatchNumber(cursor.getInt(2));
                // Adding contact to list
                teamDataList.add(teamData);
            } while (cursor.moveToNext());
        }

        // return contact list
        return teamDataList;
    }


}