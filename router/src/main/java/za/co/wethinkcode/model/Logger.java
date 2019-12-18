package za.co.wethinkcode.model;

import java.sql.*;

public class Logger
{
    private Connection connection   = null;
    private Statement statement     = null;
    private ResultSet resultSet     = null;

    // this should be placed in a folder where all components can access
    public void connectDataBase() throws SQLException
    {

        //JDBC driver name and database URL
        //String JDBC_DRIVER = "org.sqlite.JDBC";
        String DB_URL = "jdbc:sqlite:fixme.db";

        // STEP 1: Register JDBC driver
        //Class.forName(JDBC_DRIVER);

        //STEP 2: Open a connection
        this.connection = DriverManager.getConnection(DB_URL);
    }

    // this should be placed in a folder where all components can access
    public void closeDataBase() throws SQLException
    {
        // STEP 4: Clean-up environment
        this.statement.close();
        this.connection.close();
    }

    // Table containing transactions between Market and the Broker.
    // keeps record of Fix Messages
    public void createTransactionTable() throws SQLException
    {
        this.connectDataBase();

        // //STEP 3: Execute a query
        // Let's make it that two markets cannot sell the same product?
        this.statement = this.connection.createStatement();
        String sql =    "CREATE TABLE IF NOT EXISTS transactions"
                + "(ProductName varchar(255) UNIQUE NOT NULL,"
                + "ProductCode varchar(255) UNIQUE NOT NULL,"
                + "Price int NOT NULL, "
                + "Quantity int NOT NULL, "
                + "MarketName varchar(255) NOT NULL)";

        this.statement.execute(sql);
        this.closeDataBase();
    }


//    public void createDataBase() throws ClassNotFoundException, SQLException
//    {
//        this.connectDataBase();
//
//        // //STEP 3: Execute a query
//        this.statement = this.connection.createStatement();
////        String sql = "CREATE TABLE IF NOT EXISTS savedProfiles" +
////                "(CharacterName varchar(255) UNIQUE NOT NULL, " +
////                " PositionX int NOT NULL," +
////                " PositionY int NOT NULL, " +
////                " XP int NOT NULL, " +
////                " HP int NOT NULL, " +
////                " AP int NOT NULL, " +
////                " DP int NOT NULL)";
//
//        this.statement.execute(sql);
//        this.closeDataBase();
//    }

//    public void saveToDataBase(Hero player) throws SQLException
//    {
////        GameModel gameModel = Main.gameModel;
////
////        this.connectDataBase();
////        this.connection.setAutoCommit(false);
////
////        @NotEmpty(message = "Name cannot be empty")
////        String name = player.getName();
////
////        @Min(value = 1, message = "Level is out of bounds") // hardcoded
////        @Max(value = 150, message = "Max level is 5 - You have managed to escape") // hardcoded
////                int level = player.getLevel();
////
////        @Min(value = 0, message = "Experience is out of bounds") // hardcoded
////        @Max(value = 12200, message = "Max Experience is 12200 - You have managed to escape") // hardcoded
////                int experience = player.getExperience();
////
////        @Min(value = 0)
////        int hitPoints = player.getHitPoints();
////        @Min(value = 0)
////        int attackPoints = player.getAttackPoints();
////        @Min(value = 0)
////        int defencePoints = player.getDefencePoints();
////
////        @Size(min = 9, max = 29, message = "Map out of bounds")
////        int positionX = player.getPosition().getCoordinateX();
////        @Size(min = 9, max = 29, message = "Map out of bounds")
////        int positionY = player.getPosition().getCoordinateY();
////
////
////        int workoutLevel = gameModel.workoutLevel(experience);
////        if (hitPoints < 0 || attackPoints < 0)
////            System.out.println("error"); //throw new InvalidSave();
////        if (level > workoutLevel)
////        {
////            if (gameModel.gameLevel[gameModel.gameLevel.length] < experience)
////                System.out.println("error"); //throw new InvalidSave();
////            level = workoutLevel;
////        }
////
////        int workoutMapSize = gameModel.gameMap.workoutMapSize(level);
////        if (workoutMapSize < positionX || workoutMapSize < positionY )
////            System.out.println("error"); //throw new InvalidSave();
////
////        this.statement = connection.createStatement();
////        String sql = "REPLACE INTO savedProfiles (CharacterName, PositionX, PositionY, XP, HP, AP, DP)" +
////                "VALUES" +
////                "('" + name + "'," +
////                positionX + " , " +
////                positionY + " , " +
////                experience + " , " +
////                hitPoints + " , " +
////                attackPoints + " , " +
////                defencePoints + " )";
////
////        this.statement.execute(sql);
////        this.connection.commit();
////
////        this.closeDataBase();
//    }
//
//    public Hero fetchFromDataBase(String savedName) throws SQLException
//    {
////        this.connectDataBase();
////
////        @NotEmpty(message = "Name cannot be empty")
////        String name = savedName;
////
////        this.statement = connection.createStatement();
////
////        String sql = "SELECT * " +
////                "FROM savedProfiles " +
////                "WHERE CharacterName = '"+ savedName +"'" +
////                "COLLATE NOCASE";
////
////        this.resultSet = statement.executeQuery(sql);
////
////        Hero result = null;
////
////        while (resultSet.next())
////        {
////            String characterName    = resultSet.getString("CharacterName");
////            int characterPosX       = resultSet.getInt("PositionX");
////            int characterPosY       = resultSet.getInt("PositionY");
////            int characterXP         = resultSet.getInt("XP");
////            int characterHP         = resultSet.getInt("HP");
////            int characterAP         = resultSet.getInt("AP");
////            int characterDP         = resultSet.getInt("DP");
////
////            // System.out.println("characterName : " +  characterName);
////            // System.out.println("characterPosX : " +  characterPosX);
////            // System.out.println("characterPosY : " +  characterPosY);
////            // System.out.println("characterXP : " +  characterXP);
////            // System.out.println("characterHP : " +  characterHP);
////            // System.out.println("characterAP: " +  characterAP);
////            // System.out.println("characterDP: " +  characterDP);
////
////            result = new Rogue(characterName, characterPosX, characterPosY, characterXP, characterHP, characterAP, characterDP);
////        }
////
////        this.resultSet.close();
////        this.closeDataBase();
////        return result;
//    }
//
//    public void deleteFromDataBase(String savedName) throws SQLException
//    {
//        this.connectDataBase();
//
//        this.connection.setAutoCommit(false);
//
//        @NotEmpty(message = "Name cannot be empty")
//        String name = savedName;
//
//        this.statement = connection.createStatement();
//        //String sql = "SELECT * FROM savedProfiles";
//        String sql = "DELETE FROM savedProfiles WHERE CharacterName = '" + savedName + "'";
//
//        this.statement.executeUpdate(sql);
//        //statement.execute(sql);
//        this.connection.commit();
//
//        this.closeDataBase();
//
//    }
//
//    public List<Hero> showAllFromDataBase() throws SQLException
//    {
//        List<Hero> saves = new ArrayList<Hero>();
//
//        this.connectDataBase();
//
//        this.statement = connection.createStatement();
//
//        String sql = "SELECT * FROM savedProfiles";
//
//        this.resultSet = statement.executeQuery(sql);
//
//        while ( resultSet.next() )
//        {
//            String characterName    = resultSet.getString("CharacterName");
//            int characterPosX       = resultSet.getInt("PositionX");
//            int characterPosY       = resultSet.getInt("PositionY");
//            int characterXP         = resultSet.getInt("XP");
//            int characterHP         = resultSet.getInt("HP");
//            int characterAP         = resultSet.getInt("AP");
//            int characterDP         = resultSet.getInt("DP");
//
//            saves.add(new Rogue(characterName, characterPosX, characterPosY, characterXP, characterHP, characterAP, characterDP));
//
//
//            // System.out.println("characterName : " +  characterName);
//            // System.out.println("characterPosX : " +  characterPosX);
//            // System.out.println("characterPosY : " +  characterPosY);
//            // System.out.println("characterXP : " +  characterXP);
//            // System.out.println("characterHP : " +  characterHP);
//            // System.out.println("characterAP: " +  characterAP);
//            // System.out.println("characterDP: " +  characterDP);
//        }
//
//        this.resultSet.close();
//        this.closeDataBase();
//        return saves;
//    }
}
