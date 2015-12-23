package com.integ.spamodule.authentication.authen;

import com.integ.spamodule.authentication.exception.AuthenticationException;
import com.integ.spamodule.authentication.model.UserInfo;
import com.integ.spamodule.database.Database;
import com.integ.spamodule.database.DatabaseFactory;

import java.sql.ResultSet;

/**
 * Author: Manan
 * Date: 15-12-2015 04:18
 */

public class DefaultAuthenticator implements Authenticator {

    @Override
    public UserInfo authenticate(String username, String password) throws AuthenticationException {
        Database database = null;
        boolean error = false;
        UserInfo userInfo = new UserInfo();
        try {
            database = DatabaseFactory.getInstance().createDatabase();
            database.open();
            ResultSet set = database.executePreparedQuery("select usertype, pswd_salt, password from spa_users where username = ?", new Object[]{username});
            if (set.next()) {
                String saltHash = set.getString("pswd_salt");
                String passwordHash = set.getString("password");
                userInfo.setUsername(username);
                userInfo.setUserType(set.getString("usertype"));
                userInfo.setAuthenticated(PasswordHashUtil.validatePassword(password, saltHash, passwordHash));
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
        return userInfo;
    }
}
