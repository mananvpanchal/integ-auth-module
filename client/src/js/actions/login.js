/**
 * Created by Admin on 17-02-2016.
 */

import * as Constants from '../constants';
import axios from 'axios';

axios.defaults.baseURL = 'http://train.integdev.com:9000';

export default (username, password) => {
	return {
		type: Constants.DO_LOGIN,
		cred: { username, password }
	};
};