package com.integ.spamodule.authentication.authen;

import com.integ.spamodule.authentication.model.AuthenInfo;
import com.integ.spamodule.authentication.model.Credential;
import com.integ.spamodule.database.Database;
import com.integ.spamodule.database.DatabaseFactory;

import java.sql.ResultSet;

/**
 * Author: Manan
 * Date: 15-12-2015 04:18
 */

public class DefaultAuthenticator implements Authenticator {

    @Override
    public boolean authenticate(String username, String password) {
        Database database = null;
        boolean error = false;
        boolean authenticated = false;
        try {
            database = DatabaseFactory.getInstance().createDatabase();
            database.open();
            ResultSet set = database.executePreparedQuery("select salt, password from users where username = ?", new Object[]{username});
            if (set.next()) {
                String saltHash = set.getString("salt");
                String passwordHash = set.getString("password");
                authenticated = PasswordHashUtil.validatePassword(password, saltHash, passwordHash);
            }
            database.commit();
        } catch (Exception ex) {
            error = true;
        } finally {
            if(database!=null) {
                database.close(!error);
            }
        }
        return authenticated;
    }
}
