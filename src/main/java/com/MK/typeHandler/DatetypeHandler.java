package com.MK.typeHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DatetypeHandler extends BaseTypeHandler<Date> {
    //将Java数据类型转化为数据库中的类型
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        long datetime=date.getTime();
        preparedStatement.setLong(i,datetime);
    }
    //将数据库中的类型转化为Java中的类型
    @Override
    public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
        //参数s是对应的数据库中的字段名
        long along=resultSet.getLong(s);
        Date date=new Date(along);
        return date;
    }

    @Override
    public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
        //参数i是对应表中的字段位置
        long along=resultSet.getLong(i);
        Date date=new Date(i);
        return date;
    }

    @Override
    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        long aLong = callableStatement.getLong(i);
        Date date=new Date(i);
        return date;
    }
}
