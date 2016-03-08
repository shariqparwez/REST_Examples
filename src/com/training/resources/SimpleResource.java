package com.training.resources;

import java.util.HashMap;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.owlike.genson.Genson;

@Path("/simple")
public class SimpleResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getMessage(){
		return "You have configured REST successfully";
	}
	
	@GET
	@Path("/name")
	@Produces(MediaType.APPLICATION_JSON)
	public String getEmployee(){
		
		String empRep = null;
		
		Employee emp = new Employee(101,"Shariq");
		
		Genson serializer = new Genson();
		
		empRep = serializer.serialize(emp);
		
		return empRep;
	}
	
	@POST
	@Path("/add")
	public String addEmployee(@FormParam("id") int empId, @FormParam("name") String empName){
		String message = null;
		Employee emp = new Employee(empId, empName);
		message = emp.toString();
		return "One Employee wih " + message + "Added";
	}
	
	
	@DELETE
	@Path("/delete/{empId}")
	public String deleteEmployee(@PathParam("empId") int empId){
		return "One employee with id " + empId + " deleted"; 
	}
	
	@Path("/findAll/{dept}")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response findAllEmployees(@QueryParam("dept") String department){
		HashMap<String, Employee> list = new HashMap<>();
		list.put("HR", new Employee(101,"Shariq"));
		list.put("IT", new Employee(102,"Shayma"));
		list.put("AD", new Employee(103,"Shibli"));
		
		Employee found = list.get(department);
		return Response.status(200).entity(found).build();
		
	}
}
