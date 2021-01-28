/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lahiru De silva
 */
public class AscendingSort implements StrategySort{
    
    private static Connection connection = Database_Connection.getConnection();

    @Override
    public List<Reservation> SortScreeningDate() {
        List<Reservation> allBookiingList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("select * from reservations order by screeningDate ASC;");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            int bookId = rs.getInt("reservationId");
            int userId = rs.getInt("userId");
            int movieId = rs.getInt("movieId");
            String screeningDate = rs.getString("screeningDate");
            String screeningTime = rs.getString("screeningTime");
            String theaterName= rs.getString("theaterName");
            int ticketAmount = rs.getInt("NoofTickets");
            double TotalPrice = Double.parseDouble(rs.getString("TotalPrice"));
            String reservationDate = rs.getString("reservationDate");

            //getting all the values in the ResultSet object separately into String variables
            Reservation reservations = new Reservation(bookId,userId ,movieId, screeningDate,theaterName, screeningTime, ticketAmount, TotalPrice,reservationDate);
                allBookiingList.add(reservations);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allBookiingList;
    }
    
}
