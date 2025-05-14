package lk.ijse.bakerymanagment.model;

import lk.ijse.bakerymanagment.dto.CustomerDto;
import lk.ijse.bakerymanagment.dto.FeedbackDto;
import lk.ijse.bakerymanagment.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static lk.ijse.bakerymanagment.dto.CustomerDto.*;

public class FeedbackModel {
    public boolean saveFeedback(FeedbackDto feedbackDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("INSERT INTO feedback VALUES (?,?,?,?,?)",
                feedbackDto.getFeedbackId(),
                feedbackDto.getCustomerId(),
                feedbackDto.getOrderId(),
                feedbackDto.getRating(),
                feedbackDto.getComment()

        );
    }
    public boolean updateFeedback(FeedbackDto feedbackDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("UPDATE feedback SET feedback_id=?, customer_id=? , order_id=? , rating=?, comment=?  , WHERE feedback_id=?",
                feedbackDto.getFeedbackId(),
                feedbackDto.getCustomerId(),
                feedbackDto.getOrderId(),
                feedbackDto.getRating(),
                feedbackDto.getComment()
        );
    }
    public boolean deleteFeedback(String FeedbackId) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("DELETE FROM feedback WHERE feedback_id = ?",
                FeedbackId);
    }
    public FeedbackDto searchFeedback(String FeedbackId) throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM feedback WHERE feedback_id = ? ",
                FeedbackId);
        if (resultSet.next()) {
            FeedbackDto dto = new FeedbackDto(
                    resultSet.getString("FeedbackId"),
                    resultSet.getString("CustomerId"),
                    resultSet.getString("OrderId"),
                    resultSet.getInt("Rating"),
                    resultSet.getString("Comment")
            );
            return dto;
        }
        return null;
    }
    public ArrayList<FeedbackDto> getAllFeedback() throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM feedback");
        ArrayList<FeedbackDto> feedbackDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
            FeedbackDto dto = new FeedbackDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getString(5)
            );
            feedbackDtoArrayList.add(dto);
        }
        return feedbackDtoArrayList;
    }
    public String getNextFeedbackId() throws ClassNotFoundException , SQLException{
        ResultSet resultSet = CrudUtil.execute("SELECT feedback_id FROM feedback ORDER BY feedback_id DESC LIMIT 1");
        char tableCharacter = 'F';

        if(resultSet.next()){
            String lastId = resultSet.getString(1);
            String lastIdNumberString = lastId.substring(1);
            int lastIdNumber = Integer.parseInt(lastIdNumberString);
            int nextIdNumber = lastIdNumber + 1;
            String nextIdString = String.format(tableCharacter + "%03d" , nextIdNumber);
            return nextIdString;
        }
        return tableCharacter +"001";
    }

}
