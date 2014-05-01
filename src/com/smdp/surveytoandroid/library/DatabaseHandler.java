//package com.smdp.surveytoandroid.library;
//
//import java.util.ArrayList;
//
//import com.smdp.surveytoandroid.questionstructure.Choice;
//import com.smdp.surveytoandroid.questionstructure.DichotomousQuestion;
//import com.smdp.surveytoandroid.questionstructure.MultipleChoiceQuestion;
//import com.smdp.surveytoandroid.questionstructure.OpenQuestion;
//import com.smdp.surveytoandroid.questionstructure.RankingQuestion;
//import com.smdp.surveytoandroid.questionstructure.RatingQuestion;
//import com.smdp.surveytoandroid.questionstructure.SumConstantQuestion;
//import com.smdp.surveytoandroid.questionstructure.Survey;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class DatabaseHandler extends SQLiteOpenHelper {
//
////	// All Static variables
////	// Database Version
////	private static final int DATABASE_VERSION = 1;
////
////	// Database Name
////	private static final String DATABASE_NAME = "Survey";
////
////	// Survey table name
////	private static final String TABLE_SURVEY = "survey";
////	private static final String TABLE_MULTIQUESTION = "multiquestion";
////	private static final String TABLE_RANKQUESTION = "rankquestion";
////	private static final String TABLE_RATEQUESTION = "ratequestion";
////	private static final String TABLE_DICOTQUESTION = "dicotquestion";
////	private static final String TABLE_SUMCONSTANT = "sumconstant";
////	private static final String TABLE_OPENQUESTION = "openquestion";
////	private static final String TABLE_MULTICHOICE = "multichoice";
////	private static final String TABLE_RANKCHOICE = "rankchoice";
////
////	// Survey Table Columns names
////	private static final String KEY_SURVEY_ID = "id";
////	private static final String KEY_SURVEY_NAME = "name";
////
////	// Multiple Choice Question Table Column names
////	private static final String KEY_MULTIQUESTION_ID = "id";
////	private static final String KEY_MULTIQUESTION_NAME = "name";
////	private static final String KEY_MULTIQUESTION_QUESTION = "question";
////	private static final String KEY_MULTIQUESTION_REQUIRED = "required";
////	private static final String KEY_MULTIQUESTION_ANSWER = "answer";
////	private static final String KEY_MULTIQUESTION_OTHER = "other";
////	private static final String KEY_MULTIQUESTION_SURVEY = "survey";
////
////	// Ranking Question Table Column names
////	private static final String KEY_RANKQUESTION_ID = "id";
////	private static final String KEY_RANKQUESTION_NAME = "name";
////	private static final String KEY_RANKQUESTION_QUESTION = "question";
////	private static final String KEY_RANKQUESTION_REQUIRED = "required";
////	private static final String KEY_RANKQUESTION_ANSWER = "answer";
////	private static final String KEY_RANKQUESTION_SURVEY = "survey";
////
////	// Rating Question Table Column names
////	private static final String KEY_RATEQUESTION_ID= "id";
////	private static final String KEY_RATEQUESTION_NAME = "name";
////	private static final String KEY_RATEQUESTION_QUESTION = "question";
////	private static final String KEY_RATEQUESTION_REQUIRED = "required";
////	private static final String KEY_RATEQUESTION_ANSWER = "answer";
////	private static final String KEY_RATEQUESTION_MIN = "min";
////	private static final String KEY_RATEQUESTION_MAX = "max";
////	private static final String KEY_RATEQUESTION_FIRST = "first";
////	private static final String KEY_RATEQUESTION_LAST = "last";
////	private static final String KEY_RATEQUESTION_SURVEY = "survey";
////
////	// Dichotomous Question Table Column names
////	private static final String KEY_DICOTQUESTION_ID = "id";
////	private static final String KEY_DICOTQUESTION_NAME = "name";
////	private static final String KEY_DICOTQUESTION_QUESTION = "question";
////	private static final String KEY_DICOTQUESTION_REQUIRED = "required";
////	private static final String KEY_DICOTQUESTION_ANSWER = "answer";
////	private static final String KEY_DICOTQUESTION_SURVEY = "survey";
////
////	// SumConstant Question Table Column names
////	private static final String KEY_SUMCONSTQUESTION_ID = "id";
////	private static final String KEY_SUMCONSTQUESTION_NAME = "name";
////	private static final String KEY_SUMCONSTQUESTION_QUESTION = "question";
////	private static final String KEY_SUMCONSTQUESTION_REQUIRED = "required";
////	private static final String KEY_SUMCONSTQUESTION_ANSWER = "answer";
////	private static final String KEY_SUMCONSTQUESTION_SURVEY = "survey";
////	private static final String KEY_SUMCONSTQUESTION_CONSTANT = "constant";
////
////	// Open Question Table Column names
////	private static final String KEY_OPENQUESTION_ID = "id";
////	private static final String KEY_OPENQUESTION_NAME = "name";
////	private static final String KEY_OPENQUESTION_QUESTION = "question";
////	private static final String KEY_OPENQUESTION_REQUIRED = "required";
////	private static final String KEY_OPENQUESTION_ANSWER = "answer";
////	private static final String KEY_OPENQUESTION_ANSWERLENGTH = "answerlength";
////	private static final String KEY_OPENQUESTION_SURVEY = "survey";
////
////	// Multi Choice Table Column names
////	private static final String KEY_MULTICHOICE_ID = "id";
////	private static final String KEY_MULTICHOICE_NAME = "name";
////	private static final String KEY_MULTICHOICE_DESC = "description";
////	private static final String KEY_MULTICHOICE_QUESTION = "question";
////
////	// Ranking Choice Table Column names
////	private static final String KEY_RANKCHOICE_ID = "id";
////	private static final String KEY_RANKCHOICE_NAME = "name";
////	private static final String KEY_RANKCHOICE_DESC = "description";
////	private static final String KEY_RANKCHOICE_QUESTION = "question";
////
////	private SQLiteDatabase db;
////
////	public DatabaseHandler(Context context) {
////		super(context, DATABASE_NAME, null, DATABASE_VERSION);
////	}
////
////	// Creating Tables
////	@Override
////	public void onCreate(SQLiteDatabase db) {
////		this.db = db;
////
////		// initial reset tables for debugging purposes
////		/* resetTables(); */
////
////		// survey table create script
////		String CREATE_SURVEY_TABLE = "CREATE TABLE " + TABLE_SURVEY + "("
////				 + KEY_SURVEY_NAME + " TEXT PRIMARY KEY" + ")";
////
////		// multiple choice question create table create script
////		String CREATE_MULTIQUESTION_TABLE = "CREATE TABLE "
////				+ TABLE_MULTIQUESTION + "(" + KEY_MULTIQUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
////				+ KEY_MULTIQUESTION_NAME + " TEXT UNIQUE," 
////				+ KEY_MULTIQUESTION_QUESTION + " TEXT,"
////				+ KEY_MULTIQUESTION_REQUIRED + " TINYINT,"
////				+ KEY_MULTIQUESTION_ANSWER + " TEXT," + KEY_MULTIQUESTION_OTHER
////				+ " TEXT," + KEY_MULTIQUESTION_SURVEY + " TEXT,"
////				+ " FOREIGN KEY (" + KEY_MULTIQUESTION_SURVEY + ") REFERENCES "
////				+ TABLE_SURVEY + " (" + KEY_SURVEY_NAME + ")" + ")";
////
////		// ranking question create table create script
////		String CREATE_RANKQUESTION_TABLE = "CREATE TABLE " + TABLE_RANKQUESTION
////				+ "(" + KEY_RANKCHOICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
////				+ KEY_RANKQUESTION_NAME + " TEXT UNIQUE,"
////				+ KEY_RANKQUESTION_QUESTION + " TEXT,"
////				+ KEY_RANKQUESTION_REQUIRED + " TINYINT,"
////				+ KEY_RANKQUESTION_ANSWER + " TEXT," + KEY_MULTIQUESTION_SURVEY
////				+ " TEXT," + " FOREIGN KEY (" + KEY_RANKQUESTION_SURVEY
////				+ ") REFERENCES " + TABLE_SURVEY + " (" + KEY_SURVEY_NAME + ")"
////				+ ")";
////
////		// ranking question create table create script
////		String CREATE_RATEQUESTION_TABLE = "CREATE TABLE " + TABLE_RATEQUESTION
////				+ "("+ KEY_RATEQUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
////				+ KEY_RATEQUESTION_NAME + " TEXT UNIQUE,"
////				+ KEY_RATEQUESTION_QUESTION + " TEXT,"
////				+ KEY_RATEQUESTION_REQUIRED + " TINYINT,"
////				+ KEY_RATEQUESTION_ANSWER + " TEXT," + KEY_RATEQUESTION_SURVEY
////				+ " TEXT," + KEY_RATEQUESTION_FIRST + " TEXT,"
////				+ KEY_RATEQUESTION_LAST + " TEXT," + KEY_RATEQUESTION_MIN
////				+ " INT," + KEY_RATEQUESTION_MAX + " INT," + " FOREIGN KEY ("
////				+ KEY_RATEQUESTION_SURVEY + ") REFERENCES " + TABLE_SURVEY
////				+ " (" + KEY_SURVEY_NAME + ")" + ")";
////		// dichotomous question create table create script
////		String CREATE_DICOTQUESTION_TABLE = "CREATE TABLE "
////				+ TABLE_DICOTQUESTION + "("+ KEY_DICOTQUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
////				+ KEY_DICOTQUESTION_NAME + " TEXT UNNIQUE," 
////				+ KEY_DICOTQUESTION_QUESTION + " TEXT,"
////				+ KEY_DICOTQUESTION_REQUIRED + " TINYINT,"
////				+ KEY_DICOTQUESTION_ANSWER + " TEXT,"
////				+ KEY_DICOTQUESTION_SURVEY + " TEXT," + " FOREIGN KEY ("
////				+ KEY_DICOTQUESTION_SURVEY + ") REFERENCES " + TABLE_SURVEY
////				+ " (" + KEY_SURVEY_NAME + ")" + ")";
////		// sumconstant question create table create script
////		String CREATE_SUMCONSTQUESTION_TABLE = "CREATE TABLE "
////				+ TABLE_SUMCONSTANT + "(" + KEY_SUMCONSTQUESTION_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
////				+ KEY_SUMCONSTQUESTION_NAME + " TEXT UNIQUE,"
////				+ KEY_SUMCONSTQUESTION_QUESTION
////				+ " TEXT," + KEY_SUMCONSTQUESTION_REQUIRED + " TINYINT,"
////				+ KEY_SUMCONSTQUESTION_ANSWER + " TEXT,"
////				+ KEY_SUMCONSTQUESTION_CONSTANT + " INT,"
////				+ KEY_SUMCONSTQUESTION_SURVEY + " TEXT," + " FOREIGN KEY ("
////				+ KEY_SUMCONSTQUESTION_SURVEY + ") REFERENCES " + TABLE_SURVEY
////				+ " (" + KEY_SURVEY_NAME + ")" + ")";
////		// OPEN question create table create script
////		String CREATE_OPENQUESTION_TABLE = "CREATE TABLE " + TABLE_OPENQUESTION
////				+ "("+ KEY_OPENQUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
////				+ KEY_OPENQUESTION_NAME + " TEXT UNIQUE,"
////				+ KEY_OPENQUESTION_QUESTION + " TEXT,"
////				+ KEY_OPENQUESTION_REQUIRED + " TINYINT,"
////				+ KEY_OPENQUESTION_ANSWER + " TEXT,"
////				+ KEY_OPENQUESTION_ANSWERLENGTH + " TEXT,"
////				+ KEY_OPENQUESTION_SURVEY + " TEXT," + " FOREIGN KEY ("
////				+ KEY_OPENQUESTION_SURVEY + ") REFERENCES " + TABLE_SURVEY
////				+ " (" + KEY_SURVEY_NAME + ")" + ")";
////		// multi choice create table create script
////		String CREATE_MULTICHOICE_TABLE = "CREATE TABLE " + TABLE_MULTICHOICE
////				+ "("+ KEY_MULTICHOICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
////				+ KEY_MULTICHOICE_NAME + " TEXT UNIQUE,"
////				+ KEY_MULTICHOICE_DESC + " TEXT," + KEY_MULTICHOICE_QUESTION
////				+ " TEXT," + " FOREIGN KEY (" + KEY_MULTICHOICE_QUESTION
////				+ ") REFERENCES " + TABLE_MULTIQUESTION + " ("
////				+ KEY_MULTIQUESTION_NAME + ")" + ")";
////
////		// choice create table create script
////		String CREATE_RANKCHOICE_TABLE = "CREATE TABLE " + TABLE_RANKCHOICE
////				+ "("+ KEY_RANKCHOICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," 
////				+ KEY_RANKCHOICE_NAME + " TEXT UNIQUE,"
////				+ KEY_RANKCHOICE_DESC + " TEXT," + KEY_RANKCHOICE_QUESTION
////				+ " TEXT," + " FOREIGN KEY (" + KEY_RANKCHOICE_QUESTION
////				+ ") REFERENCES " + TABLE_RANKQUESTION + " ("
////				+ KEY_RANKQUESTION_NAME + ")" + ")";
////
////		db.execSQL(CREATE_SURVEY_TABLE);
////		db.execSQL(CREATE_MULTIQUESTION_TABLE);
////		db.execSQL(CREATE_RANKQUESTION_TABLE);
////		db.execSQL(CREATE_RATEQUESTION_TABLE);
////		db.execSQL(CREATE_DICOTQUESTION_TABLE);
////		db.execSQL(CREATE_SUMCONSTQUESTION_TABLE);
////		db.execSQL(CREATE_OPENQUESTION_TABLE);
////		db.execSQL(CREATE_MULTICHOICE_TABLE);
////		db.execSQL(CREATE_RANKCHOICE_TABLE);
////		// add data to the database based on the created questions
////		this.addDbData();
////
////	}
////
////	// Upgrading database
////	@Override
////	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
////		// Drop older table if existed
////		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SURVEY);
////		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MULTIQUESTION);
////		db.execSQL("DROP TABLE IF EXISTS " + TABLE_RANKQUESTION);
////		db.execSQL("DROP TABLE IF EXISTS " + TABLE_RATEQUESTION);
////		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DICOTQUESTION);
////		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUMCONSTANT);
////		db.execSQL("DROP TABLE IF EXISTS " + TABLE_OPENQUESTION);
////		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MULTICHOICE);
////		db.execSQL("DROP TABLE IF EXISTS " + TABLE_RANKCHOICE);
////		// Create tables again
////		onCreate(db);
////	}
////
////	/**
////	 * Storing a survey in the db
////	 * */
////	public void addSurvey(Survey survey) {
////
////		ContentValues values = new ContentValues();
////		values.put(KEY_SURVEY_NAME, survey.getName()); // name
////
////		// Inserting Row
////		db.insert(TABLE_SURVEY, null, values);
////	}
////
////	/**
////	 * Storing a multiple choice question in the db
////	 * */
////	public void addMultiQuestion(MultipleChoiceQuestion multiquestion,
////			Survey survey) {
////
////		ContentValues values = new ContentValues();
////		values.put(KEY_MULTIQUESTION_NAME, multiquestion.getName()); // name
////		values.put(KEY_MULTIQUESTION_QUESTION, multiquestion.getQuestion()); // question
////		values.put(KEY_MULTIQUESTION_REQUIRED, multiquestion.getReq()); // required
////		values.put(KEY_MULTIQUESTION_ANSWER, multiquestion.getAnswer()); // answer
////		values.put(KEY_MULTIQUESTION_OTHER, multiquestion.isOther()); // other
////		values.put(KEY_MULTIQUESTION_SURVEY, survey.getName()); // survey
////
////		// Inserting Row
////		db.insert(TABLE_MULTIQUESTION, null, values);
////
////		// adding the choices associated with this question to the db
////		ContentValues valuesChoice = new ContentValues();
////		for (Choice choice : multiquestion.choices) {
////
////			valuesChoice.put(KEY_MULTICHOICE_NAME, choice.getName()); // name
////			valuesChoice.put(KEY_MULTICHOICE_DESC, choice.getDescription()); // description
////			valuesChoice.put(KEY_MULTICHOICE_QUESTION, multiquestion.getName()); // foreign
////			// key
////			// mutli
////			// choice
////			// Inserting row
////			db.insert(TABLE_MULTICHOICE, null, valuesChoice);
////		}
////
////	}
////
////	/**
////	 * Storing a ranking question in the db
////	 * */
////	public void addRankQuestion(RankingQuestion rankquestion, Survey survey) {
////
////		ContentValues values = new ContentValues();
////		values.put(KEY_RANKQUESTION_NAME, rankquestion.getName()); // name
////		values.put(KEY_RANKQUESTION_QUESTION, rankquestion.getQuestion()); // question
////		values.put(KEY_RANKQUESTION_REQUIRED, rankquestion.getReq()); // required
////		values.put(KEY_RANKQUESTION_ANSWER, rankquestion.getAnswer()); // answer
////		values.put(KEY_RANKQUESTION_SURVEY, survey.getName()); // survey
////
////		// Inserting Row
////		db.insert(TABLE_RANKQUESTION, null, values);
////
////		// adding the choices associated with this question to the db
////		ContentValues valuesChoice = new ContentValues();
////		for (Choice choice : rankquestion.choices) {
////
////			valuesChoice.put(KEY_RANKCHOICE_NAME, choice.getName()); // name
////			valuesChoice.put(KEY_RANKCHOICE_DESC, choice.getDescription()); // description
////			valuesChoice.put(KEY_RANKCHOICE_QUESTION, rankquestion.getName()); // foreign
////			// key
////			// mutli
////			// choice
////			// Inserting row
////			db.insert(TABLE_RANKCHOICE, null, valuesChoice);
////		}
////	}
////
////	/**
////	 * Storing a rating question in the db
////	 * */
////	public void addRateQuestion(RatingQuestion ratequestion, Survey survey) {
////
////		ContentValues values = new ContentValues();
////		values.put(KEY_RATEQUESTION_NAME, ratequestion.getName()); // name
////		values.put(KEY_RATEQUESTION_QUESTION, ratequestion.getQuestion()); // question
////		values.put(KEY_RATEQUESTION_REQUIRED, ratequestion.getReq()); // required
////		values.put(KEY_RATEQUESTION_ANSWER, ratequestion.getAnswer()); // answer
////		values.put(KEY_RATEQUESTION_SURVEY, survey.getName()); // survey
////		values.put(KEY_RATEQUESTION_MIN, ratequestion.getMin()); // min
////		values.put(KEY_RATEQUESTION_MAX, ratequestion.getMax()); // max
////		values.put(KEY_RATEQUESTION_FIRST, ratequestion.getFirst()); // first
////		values.put(KEY_RATEQUESTION_LAST, ratequestion.getLast()); // last
////
////		// Inserting Row
////		db.insert(TABLE_RATEQUESTION, null, values);
////	}
////
////	/**
////	 * Storing a dichotomous question in the db
////	 * */
////	public void addDicotQuestion(DichotomousQuestion dicotquestion,
////			Survey survey) {
////
////		ContentValues values = new ContentValues();
////		values.put(KEY_DICOTQUESTION_NAME, dicotquestion.getName()); // name
////		values.put(KEY_DICOTQUESTION_QUESTION, dicotquestion.getQuestion()); // question
////		values.put(KEY_DICOTQUESTION_REQUIRED, dicotquestion.getReq()); // required
////		values.put(KEY_DICOTQUESTION_ANSWER, dicotquestion.getAnswer()); // answer
////		values.put(KEY_DICOTQUESTION_SURVEY, survey.getName()); // survey
////
////		// Inserting Row
////		db.insert(TABLE_DICOTQUESTION, null, values);
////	}
////
////	/**
////	 * Storing a dichotomous question in the db
////	 * */
////	public void addSumConstQuestion(SumConstantQuestion sumconstquestion,
////			Survey survey) {
////
////		ContentValues values = new ContentValues();
////		values.put(KEY_SUMCONSTQUESTION_NAME, sumconstquestion.getName()); // name
////		values.put(KEY_SUMCONSTQUESTION_QUESTION,
////				sumconstquestion.getQuestion()); // question
////		values.put(KEY_SUMCONSTQUESTION_REQUIRED, sumconstquestion.getReq()); // required
////		values.put(KEY_SUMCONSTQUESTION_ANSWER, sumconstquestion.getAnswer()); // answer
////		values.put(KEY_SUMCONSTQUESTION_SURVEY, survey.getName()); // survey
////		values.put(KEY_SUMCONSTQUESTION_CONSTANT,
////				sumconstquestion.getConstant()); // constant int
////
////		// Inserting Row
////		db.insert(TABLE_SUMCONSTANT, null, values);
////	}
////
////	/**
////	 * Storing a open question in the db
////	 * */
////	public void addOpenQuestion(OpenQuestion openquestion, Survey survey) {
////
////		ContentValues values = new ContentValues();
////		values.put(KEY_OPENQUESTION_NAME, openquestion.getName()); // name
////		values.put(KEY_OPENQUESTION_QUESTION, openquestion.getQuestion()); // question
////		values.put(KEY_OPENQUESTION_REQUIRED, openquestion.getReq()); // required
////		values.put(KEY_OPENQUESTION_ANSWER, openquestion.getAnswer()); // answer
////		values.put(KEY_OPENQUESTION_SURVEY, survey.getName()); // survey
////
////		// Inserting Row
////		db.insert(TABLE_OPENQUESTION, null, values);
////	}
////
////	/**
////	 * Adding data to the database
////	 * */
////	private void addDbData() {
////
////		// create and add survey to db
////		Survey s1 = new Survey("Survey1");
////		addSurvey(s1);
////		// create choices question1
////		Choice choice1 = new Choice("choice1", "Superman");
////		Choice choice2 = new Choice("choice2", "Batman");
////		Choice choice3 = new Choice("choice3", "Spiderman");
////		Choice choice4 = new Choice("choice4", "Flash");
////
////		ArrayList<Choice> arreychoice1 = new ArrayList<Choice>();
////		arreychoice1.add(choice1);
////		arreychoice1.add(choice2);
////		arreychoice1.add(choice3);
////		arreychoice1.add(choice4);
////		// create choices question2
////		Choice choice5 = new Choice("choice5", "Thor");
////		Choice choice6 = new Choice("choice6", "Hulk");
////		Choice choice7 = new Choice("choice7", "Dr. Manhatan");
////		Choice choice8 = new Choice("choice8", "Green Lantern");
////
////		ArrayList<Choice> arreychoice2 = new ArrayList<Choice>();
////		arreychoice2.add(choice5);
////		arreychoice2.add(choice6);
////		arreychoice2.add(choice7);
////		arreychoice2.add(choice8);
////		// create choices question3
////		Choice choice9 = new Choice("choice9", "Yes");
////		Choice choice10 = new Choice("choice10", "No");
////
////		ArrayList<Choice> arreychoice3 = new ArrayList<Choice>();
////		arreychoice3.add(choice9);
////		arreychoice3.add(choice10);
////
////		// create choices question4
////		Choice choice11 = new Choice("choice11", "Yes");
////		Choice choice12 = new Choice("choice12", "No");
////
////		ArrayList<Choice> arreychoice4 = new ArrayList<Choice>();
////		arreychoice4.add(choice11);
////		arreychoice4.add(choice12);
////
////		// create multiple choice questions
////		MultipleChoiceQuestion m1 = new MultipleChoiceQuestion("q1",
////				"What is your favorite super hero?", true, true, arreychoice1);
////		MultipleChoiceQuestion m2 = new MultipleChoiceQuestion("q2",
////				"What is your least favorite super hero?", true, true,
////				arreychoice2);
////		RankingQuestion r1 = new RankingQuestion("q3", "Do you like Marvel?",
////				true, arreychoice3);
////		RankingQuestion r2 = new RankingQuestion("q4",
////				"Do you like DC Universe?", true, arreychoice4);
////		OpenQuestion o1 = new OpenQuestion("q5",
////				"Waht do you think about Marvel?", false, "100");
////		DichotomousQuestion d1 = new DichotomousQuestion("q6",
////				"Are you older than 20?", false);
////		SumConstantQuestion sum1 = new SumConstantQuestion("q7",
////				"Sum these values to", true, 100, arreychoice1);
////		RatingQuestion rate1 = new RatingQuestion("q8", "Rate marvel", false,
////				1, 10, "aweful", "awesome");
////
////		// adding questions to db
////		addMultiQuestion(m1, s1);
////		addMultiQuestion(m2, s1);
////		addRankQuestion(r1, s1);
////		addRankQuestion(r2, s1);
////		addOpenQuestion(o1, s1);
////		addSumConstQuestion(sum1, s1);
////		addDicotQuestion(d1, s1);
////		addRateQuestion(rate1, s1);
////
////	}
////
////	/**
////	 * Getting multi question data from database
////	 * */
////	public Cursor getMultiQuestions() {
////		// returns a cursor
////		String selectQuery = "SELECT  * FROM " + TABLE_MULTIQUESTION;
////
////		SQLiteDatabase db = this.getReadableDatabase();
////		Cursor cursor = db.rawQuery(selectQuery, null);
////		return cursor;
////	}
////
////	/**
////	 * Getting multi question data from database
////	 * */
////	public Cursor getRankQuestions() {
////		// returns a cursor
////		String selectQuery = "SELECT  * FROM " + TABLE_RANKQUESTION;
////
////		SQLiteDatabase db = this.getReadableDatabase();
////		Cursor cursor = db.rawQuery(selectQuery, null);
////		return cursor;
////	}
////
////	/**
////	 * Getting dichot question data from database
////	 * */
////	public Cursor getDichotQuestions() {
////		// returns a cursor
////		String selectQuery = "SELECT  * FROM " + TABLE_DICOTQUESTION;
////
////		SQLiteDatabase db = this.getReadableDatabase();
////		Cursor cursor = db.rawQuery(selectQuery, null);
////		return cursor;
////	}
////
////	/**
////	 * Getting dichot question data from database
////	 * */
////	public Cursor getSumConstantQuestions() {
////		// returns a cursor
////		String selectQuery = "SELECT  * FROM " + TABLE_SUMCONSTANT;
////
////		SQLiteDatabase db = this.getReadableDatabase();
////		Cursor cursor = db.rawQuery(selectQuery, null);
////		return cursor;
////	}
////
////	/**
////	 * Getting open question data from database
////	 * */
////	public Cursor getOpenQuestions() {
////		// returns a cursor
////		String selectQuery = "SELECT  * FROM " + TABLE_OPENQUESTION;
////
////		SQLiteDatabase db = this.getReadableDatabase();
////		Cursor cursor = db.rawQuery(selectQuery, null);
////		return cursor;
////	}
////
////	/**
////	 * Getting rating question data from database
////	 * */
////	public Cursor getRateQuestions() {
////		// returns a cursor
////		String selectQuery = "SELECT  * FROM " + TABLE_RATEQUESTION;
////
////		SQLiteDatabase db = this.getReadableDatabase();
////		Cursor cursor = db.rawQuery(selectQuery, null);
////		return cursor;
////	}
////
////	/**
////	 * Getting mutli question row count
////	 * */
////	public int multiRowCount() {
////		int row = 0;
////		String selectQuery = "SELECT  * FROM " + TABLE_MULTIQUESTION;
////		SQLiteDatabase db = this.getWritableDatabase();
////		Cursor cursor = db.rawQuery(selectQuery, null);
////		row = cursor.getCount();
////		return row;
////	}
////
////	/**
////	 * Getting multi choice data from database
////	 * */
////	public Cursor getMultiChoices(String questName) {
////		// returns a cursor
////		String selectQuery = "SELECT  * FROM " + TABLE_MULTICHOICE + " WHERE "
////				+ KEY_MULTICHOICE_QUESTION + " = '" + questName + "'";
////
////		SQLiteDatabase db = this.getReadableDatabase();
////		Cursor cursor = db.rawQuery(selectQuery, null);
////		return cursor;
////
////	}
////
////	/**
////	 * Getting rank choice data from database
////	 * */
////	public Cursor getRankChoices(String questName) {
////		// returns a cursor
////		String selectQuery = "SELECT  * FROM " + TABLE_RANKCHOICE + " WHERE "
////				+ KEY_RANKCHOICE_QUESTION + " = '" + questName + "'";
////
////		SQLiteDatabase db = this.getReadableDatabase();
////		Cursor cursor = db.rawQuery(selectQuery, null);
////		return cursor;
////
////	}
////
////	/**
////	 * Re create database Delete all tables and create them again
////	 * */
////	public void resetTables() {
////		// Delete All Rows
////		db.delete(TABLE_SURVEY, null, null);
////		db.delete(TABLE_MULTICHOICE, null, null);
////		db.delete(TABLE_RANKCHOICE, null, null);
////		db.delete(TABLE_MULTIQUESTION, null, null);
////		db.delete(TABLE_SUMCONSTANT, null, null);
////		db.delete(TABLE_RANKQUESTION, null, null);
////		db.delete(TABLE_RATEQUESTION, null, null);
////		db.delete(TABLE_OPENQUESTION, null, null);
////		db.delete(TABLE_DICOTQUESTION, null, null);
////	}
//
//}