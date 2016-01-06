package com.integ.spamodule.authentication.handler;

import com.integ.spamodule.authentication.authen.PasswordHashUtil;
import com.integ.spamodule.authentication.exception.ApplicationException;
import com.integ.spamodule.authentication.model.User;
import com.integ.spamodule.database.Database;
import com.integ.spamodule.database.DatabaseFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Author: Manan
 * Date: 23-12-2015 14:09
 */

@Path("open/user")
public class UserHandler {

    @Path("add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addUser(User user) throws Exception {
        String salt = PasswordHashUtil.generateSalt();
        String passwordHash = PasswordHashUtil.createHash(user.getPassword(), salt);
        Database database = null;
        boolean error = false;
        try {
            database = DatabaseFactory.getInstance().createDatabase();
            database.open();
            database.executePreparedUpdate("insert into spa_users (username, password, firstname, lastname, usertype, pswd_salt) values (?, ?, ?, ?, ?, ?)",
                    new Object[]{user.getUsername(),passwordHash , user.getFirstName(), user.getLastName(), "admin", salt});
            database.commit();
        } catch (Exception ex) {
            error = true;
            throw new ApplicationException("Error adding user", ex);
        } finally {
            if(database!=null) {
                database.close(!error);
            }
        }
        return "success";
    }
}
