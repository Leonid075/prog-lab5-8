package ru.p3xi.cm;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import ru.p3xi.labwork.*;

public class DBManager {
    private String url = "jdbc:postgresql://localhost:5432/studs";
    private String password = "4fhrJf1xhmXDJatd";
    private String user = "s471855";
    private Connection connection = null;

    public DBManager() throws SQLException {
        this.connection = DriverManager.getConnection(this.url, this.user, this.password);
        this.connection.setAutoCommit(true);
    }

    public synchronized Integer addUser(String username, String password) throws SQLException {
        String insertUser = "INSERT INTO users (name, password) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows == 0) {
            return -1;
        }
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        } else {
            return -1;
        }
    }

    public synchronized HashMap<String, String> getUsers() throws SQLException {
        HashMap<String, String> users = new HashMap<>();
        String query = "SELECT id, name, password FROM users";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            users.put(rs.getString("name"), rs.getString("password"));
        }

        return users;
    }

    public synchronized ArrayList<LabWork> getLabWorks() throws SQLException {
        ArrayList<LabWork> labWorks = new ArrayList<>();
        String query = "SELECT id, name, x, y, creationdate, minimalpoint, difficulty, disciplinename, lecturehours, practicehours, labscount, owner FROM labworks";

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            try {
                LabWork.Builder builder = new LabWork.Builder();

                builder.setId(rs.getLong("id"))
                        .setName(rs.getString("name"))
                        .setCreationDate(rs.getTimestamp("creationdate").toLocalDateTime())
                        .setMinimalPoint(rs.getFloat("minimalpoint"))
                        .setDifficulty(Difficulty.valueOf(rs.getString("difficulty")))
                        .setOwner(rs.getString("owner"));

                Coordinates.Builder coordBuilder = new Coordinates.Builder();
                coordBuilder.setX(rs.getInt("x")).setY(BigDecimal.valueOf(rs.getFloat("y")));
                builder.setCoordinates(coordBuilder.build());

                Discipline.Builder discBuilder = new Discipline.Builder();
                discBuilder.setName(rs.getString("disciplinename"))
                        .setLectureHours(rs.getLong("lecturehours"))
                        .setPracticeHours(rs.getLong("practicehours"))
                        .setLabsCount(rs.getInt("labscount"));
                builder.setDiscipline(discBuilder.build());

                labWorks.add(builder.build());
            } catch (ValueException e) {
                throw new SQLException("Error building LabWork from database: " + e.getMessage());
            }
        }

        return labWorks;
    }

    public synchronized Long addLabWork(LabWork labWork) throws SQLException {
        String insertLabWork = "INSERT INTO labworks (name, x, y, creationdate, minimalpoint, difficulty, disciplinename, lecturehours, practicehours, labscount, owner) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertLabWork,
                Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, labWork.getName());
        preparedStatement.setInt(2, labWork.getCoordinates().getX());
        preparedStatement.setFloat(3, labWork.getCoordinates().getY().floatValue());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(labWork.getCreationDate()));
        preparedStatement.setFloat(5, labWork.getMinimalPoint());
        preparedStatement.setString(6, labWork.getDifficulty().name());
        preparedStatement.setString(7, labWork.getDiscipline().getName());
        preparedStatement.setLong(8, labWork.getDiscipline().getLectureHours());
        preparedStatement.setLong(9, labWork.getDiscipline().getPracticeHours());
        preparedStatement.setInt(10, labWork.getDiscipline().getLabsCount());
        preparedStatement.setString(11, labWork.getOwner());

        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows == 0) {
            return -1l;
        }
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getLong(1);
        } else {
            return -1l;
        }
    }

    public synchronized Integer updateLabWork(LabWork labWork) throws SQLException {
        String updateLabWork = "UPDATE labworks SET name = ?, x = ?, y = ?, creationdate = ?, minimalpoint = ?, difficulty = ?, disciplinename = ?, lecturehours = ?, practicehours = ?, labscount = ?, owner = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateLabWork);
        preparedStatement.setString(1, labWork.getName());
        preparedStatement.setInt(2, labWork.getCoordinates().getX());
        preparedStatement.setFloat(3, labWork.getCoordinates().getY().floatValue());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(labWork.getCreationDate()));
        preparedStatement.setFloat(5, labWork.getMinimalPoint());
        preparedStatement.setString(6, labWork.getDifficulty().name());
        preparedStatement.setString(7, labWork.getDiscipline().getName());
        preparedStatement.setLong(8, labWork.getDiscipline().getLectureHours());
        preparedStatement.setLong(9, labWork.getDiscipline().getPracticeHours());
        preparedStatement.setInt(10, labWork.getDiscipline().getLabsCount());
        preparedStatement.setString(11, labWork.getOwner());
        preparedStatement.setLong(12, labWork.getId());

        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows == 0) {
            return -1;
        }
        return affectedRows;
    }

    public synchronized Integer deleteLabWork(Long id) throws SQLException {
        String deleteLabWork = "DELETE FROM labworks WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteLabWork);
        preparedStatement.setLong(1, id);

        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows == 0) {
            return -1;
        }
        return affectedRows;
    }

    public synchronized Integer deleteAllByOwner(String owner) throws SQLException {
        String deleteAllByOwner = "DELETE FROM labworks WHERE owner = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteAllByOwner);
        preparedStatement.setString(1, owner);

        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows == 0) {
            return -1;
        }
        return affectedRows;
    }

}
