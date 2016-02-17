package com.integ.core.authentication.authen;

import com.integ.core.authentication.exception.AuthenticationException;
import com.integ.core.authentication.model.User;
import com.integ.core.database.Database;
import com.integ.core.database.DatabaseFactory;

import java.sql.ResultSet;

/**
 * Author: Manan
 * Date: 15-12-2015 04:18
 */

public class DefaultAuthenticator implements Authenticator {

    @Override
    public User authenticate(String username, String password) throws AuthenticationException {
        Database database = null;
        boolean error = false;
        User user = new User();
        try {
            database = DatabaseFactory.getInstance().createDatabase();
            database.open();
            ResultSet set = database.executePreparedQuery("select usertype, pswd_salt, password from spa_users where username = ?", new Object[]{username});
            if (set.next()) {
                String saltHash = set.getString("pswd_salt");
                String passwordHash = set.getString("password");
                user.setUsername(username);
                user.setUserType(set.getString("usertype"));
                user.setAuthenticated(PasswordHashUtil.validatePassword(password, saltHash, passwordHash));
            }
            database.commit();
        } catch (Exception ex) {
            error = true;
            throw new AuthenticationException("Error authentication user", ex);
        } finally {
            if(database!=null) {
                database.close(!error);
            }
        }
        return user;
    }
}
