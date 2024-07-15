package org.diary.service;

import org.diary.util.DataConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiaryService {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void addDiary(String title, String content) {
        try {
            conn = DataConnection.getConnection();
            String sql = "insert into diary (title, content) values (?, ?);";
            ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, content);
            int count = ps.executeUpdate();
            if (count == 0) {
                throw new SQLException("Add failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (ps != null)
                    ps.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException s) {
                s.printStackTrace();
            }


        }
    }
    public void getOneDiary(int id) {
        try {
            conn = DataConnection.getConnection();
            String sql = "select * from diary where id = ?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(
                        "id : " + rs.getInt("id") + ", " +
                                "title : " + rs.getString("title") + ", " +
                                "content : " + rs.getString("content") + ", " +
                                "date : " + rs.getDate("write_date") + ", "
                );
            } else {
                System.out.println("No diary matches");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (ps != null)
                    ps.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException s) {
                s.printStackTrace();
            }

        }
    }
    public void getAllDiaries() {
        try {
            conn = DataConnection.getConnection();
            String sql = "select * from diary;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);

            if (rs == null) {
                throw new SQLException("No diary is here");
            }

            while (rs.next()) {
                System.out.println(
                        "id : " + rs.getInt("id") + ", " +
                                "title : " + rs.getString("title") + ", " +
                                "content : " + rs.getString("content") + ", " +
                                "date : " + rs.getDate("write_date") + ", "
                );
            }
            System.out.println("===============================================");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (ps != null)
                    ps.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException s) {
                s.printStackTrace();
            }
        }
    }
    public void removeDiary(int id) {
        try {
            conn = DataConnection.getConnection();
            String sql = "delete from diary where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int count = ps.executeUpdate();

            if (count != 0) {
                System.out.println("Diary removed");
            } else {
                System.out.println("No such diary");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (ps != null)
                    ps.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException s) {
                s.printStackTrace();
            }

        }
    }
    public void modifyDiary(int id, String title, String content){
        try {
            conn = DataConnection.getConnection();
            String sql = "update diary set title = ?, content = ? where id = ?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setInt(3, id);
            int count = ps.executeUpdate();

            if(count == 1){
                System.out.println("Diary modified");
            }else {
                System.out.println("Error");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (ps != null)
                    ps.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException s) {
                s.printStackTrace();
            }

        }
    }
    public void searchDiaries(String title){
        try {
            conn = DataConnection.getConnection();
            title = "%" + title + "%";
            String sql = "select * from diary where title like ?;";
            ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println(
                        "id : " + rs.getInt("id") + ", " +
                                "title : " + rs.getString("title") + ", " +
                                "content : " + rs.getString("content") + ", " +
                                "date : " + rs.getDate("write_date") + ", "
                );
            } else {
                System.out.println("No diary matches");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (ps != null)
                    ps.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException s) {
                s.printStackTrace();
            }

        }
    }

}
