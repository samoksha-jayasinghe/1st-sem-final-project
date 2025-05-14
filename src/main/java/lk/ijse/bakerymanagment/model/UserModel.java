package lk.ijse.bakerymanagment.model;

import lk.ijse.bakerymanagment.dto.UsersDto;
import lk.ijse.bakerymanagment.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserModel {
    public boolean saveUser(UsersDto usersDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("INSERT INTO users VALUES (?,?,?,?,?)",
                usersDto.getUserId(),
                usersDto.getName(),
                usersDto.getAddress(),
                usersDto.getEmail(),
                usersDto.getContact()

        );
    }
    public boolean updateUser(UsersDto usersDto) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("UPDATE users SET name=?, address=? , email=?, contact=?  , WHERE user_id=?",
                usersDto.getUserId(),
                usersDto.getName(),
                usersDto.getAddress(),
                usersDto.getEmail(),
                usersDto.getContact()
        );
    }
    public boolean deleteUser(String UserId) throws ClassNotFoundException , SQLException {
        return CrudUtil.execute("DELETE FROM users WHERE user_id = ?",
                UserId);
    }
    public UsersDto searchUser(String UserId) throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM users WHERE user_id = ? ",
                UserId);
        if (resultSet.next()) {
            UsersDto dto = new UsersDto(
                    resultSet.getString("UserId"),
                    resultSet.getString("Name"),
                    resultSet.getString("Address"),
                    resultSet.getString("Email"),
                    resultSet.getInt("Contact")
            );
            return dto;
        }
        return null;
    }
    public ArrayList<UsersDto> getAllUser() throws ClassNotFoundException , SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM users");
        ArrayList<UsersDto> usersDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
            UsersDto dto = new UsersDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            );
            usersDtoArrayList.add(dto);
        }
        return usersDtoArrayList;
    }
    public String getNextUserId() throws ClassNotFoundException , SQLException{
        ResultSet resultSet = CrudUtil.execute("SELECT user_id FROM users ORDER BY user_id DESC LIMIT 1");
        char tableCharacter = 'A';

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
