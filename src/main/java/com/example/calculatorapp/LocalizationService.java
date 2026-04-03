package com.example.calculatorapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class LocalizationService {

    public static Map<String, String> getLabelsByLanguage(String language){
        Map<String, String> labels = new HashMap<>();
        String sql = "SELECT `key`, `value` FROM localization_strings WHERE language = ?";

        try (Connection conn = DBUtil.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, language);
            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    labels.put(rs.getString("key"), rs.getString("value"));
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return labels;
    }
}
