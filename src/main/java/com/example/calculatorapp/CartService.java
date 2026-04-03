package com.example.calculatorapp;

import java.sql.*;
import java.util.List;

public class CartService {

    public static void saveCartRecords(CalculatorModel calculatorModel, List<CalculatorModel.Item> items) throws SQLException{

        Connection conn = null;
        PreparedStatement pstmtCart = null;
        PreparedStatement pstmtItems = null;
        try{
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sqlCartRecords = "INSERT INTO cart_records (total_items, total_cost, language) VALUES (?,?,?)";

            pstmtCart = conn.prepareStatement(sqlCartRecords, Statement.RETURN_GENERATED_KEYS);

            pstmtCart.setInt(1, calculatorModel.getTotalItems());
            pstmtCart.setDouble(2, calculatorModel.getTotalCost());
            pstmtCart.setString(3, calculatorModel.getLanguage());

            pstmtCart.executeUpdate();

            int generatedCartRecordId = -1;
            try(ResultSet rs = pstmtCart.getGeneratedKeys()){
                if(rs.next()){
                    generatedCartRecordId = rs.getInt(1);
                }
            }

            if(generatedCartRecordId == -1){
                throw new SQLException("cannot get generatedCartRecordId");
            }

            String sqlCartItems = "INSERT INTO cart_items (cart_record_id, item_number, price, quantity, subtotal) VALUES (?,?,?,?,?)";
            pstmtItems = conn.prepareStatement(sqlCartItems);
            int num = 0;

            for(CalculatorModel.Item item : items){
                double quantity = item.getQuantity();
                double unitPrice = item.getUnitPrice();
                pstmtItems.setInt(1, generatedCartRecordId);
                pstmtItems.setInt(2, num+1);
                pstmtItems.setDouble(3, unitPrice);
                pstmtItems.setInt(4, (int) quantity);
                pstmtItems.setDouble(5, quantity * unitPrice);

                pstmtItems.addBatch();
                num += 1;
            }

            pstmtItems.executeBatch();
            conn.commit();
            System.out.println("Save successfully");

        } catch (SQLException e){
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (pstmtCart != null) pstmtCart.close();
            if (pstmtItems != null) pstmtItems.close();
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }
}
