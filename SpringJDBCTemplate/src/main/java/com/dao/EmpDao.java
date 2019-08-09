package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.model.Employee;

public class EmpDao
{
	JdbcTemplate template;  
	  
	public void setTemplate(JdbcTemplate template) 
	{  
	    this.template = template;  
	}  
	
	public int save(Employee e){  
	    String sql="insert into employee(name,salary,designation) values('"+e.getName()+"',"+e.getSalary()+",'"+e.getDesignation()+"')";  
	    return template.update(sql);  
	}

	public List<Employee> getEmployees(){  
	    return template.query("select * from employee",new RowMapper<Employee>(){  
	        public Employee mapRow(ResultSet rs, int row) throws SQLException {  
	        	Employee e=new Employee();  
	            e.setId(rs.getInt(1));  
	            e.setName(rs.getString(2));  
	            e.setSalary(rs.getString(3));  
	            e.setDesignation(rs.getString(4));  
	            return e;  
	        }  
	    });  
	}

	public int delete(int id){  
	    String sql="delete from employee where id="+id+"";  
	    return template.update(sql);  
	}    
	public Employee getEmpById(int id){  
	    String sql="select * from employee where id=?";  
	    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Employee>(Employee.class));  
	}
	
	public int update(Employee p){  
	    String sql="update employee set name='"+p.getName()+"', salary="+p.getSalary()+",designation='"+p.getDesignation()+"' where id="+p.getId()+"";  
	    return template.update(sql);  
	}
}
