package cloudplaces.restapi.mappings;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rd
 */
@RestController
@Api(value="Property Resources", description = "Shows property resources")
public class PropertyResources 
{
    @ApiOperation("Returns a list of properties")
    @GetMapping("get_properties/{name}/{location}/{price}/{n_rooms}")
    public ArrayList<Object> GetProperties(
            @PathVariable("name") final String name,
            @PathVariable("location") final String location,
            @PathVariable("radius") final float radius,
            @PathVariable("price") final float price,
            @PathVariable("n_rooms") final int n_rooms
            )
    {return new ArrayList<>();}
    
    
    @ApiOperation("Returns a property")
    @GetMapping("get_property/{id}")
    public Object GetProperty(
            @PathVariable("id") final long id
            )
    {return new Object();}
    
    
    @ApiOperation("Adds a property")
    @PostMapping("add_property/{name}/{location}/{price}/{n_rooms}")
    public boolean AddProperty(
            @PathVariable("name") final String name,
            @PathVariable("location") final String location,
            @PathVariable("price") final float price,
            @PathVariable("n_rooms") final int n_rooms
            )
    {return true;}
    
    
    @ApiOperation("Edits a property")
    @PutMapping("edit_property/{id}")
    public boolean EditProperty(
            @PathVariable("id") final long id
            )
    {return true;}
    
    
    @ApiOperation("Adds a review")
    @PostMapping("add_review/{property_id}/{user_id}{review}")
    public boolean AddReview(
            @PathVariable("user_id") final long user_id,
            @PathVariable("property_id") final long property_id,
            @PathVariable("review") final String review
            )
    {return true;}
    
    
    @ApiOperation("Edits a review")
    @PutMapping("edit_review/{review_id}")
    public boolean EditReview(
            @PathVariable("review_id") final long review_id
            )
    {return true;}
    
    
    @ApiOperation("Deletes a review")
    @DeleteMapping("delete_review/{review_id}")
    public boolean DeleteReview(
            @PathVariable("review_id") final long review_id
            )
    {return true;}
    
    
    
    
    
}
