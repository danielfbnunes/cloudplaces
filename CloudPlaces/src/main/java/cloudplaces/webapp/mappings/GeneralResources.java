/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudplaces.webapp.mappings;

import cloudplaces.webapp.database_queries.GeneralQueries;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "General Resources")
public class GeneralResources {
    
    @Autowired
    GeneralQueries query;
    
    @ApiOperation("Reloads database")
    @GetMapping("api/reloaddb")
    public void reloadDatabase(){
        query.reloadDatabase();
    }
}
