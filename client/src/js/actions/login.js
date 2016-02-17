/**
 * Created by Admin on 17-02-2016.
 */

import * as Constants from '../constants';
import axios from 'axios';

axios.defaults.baseURL = 'http://train.integdev.com:9000';

const loginAction = (username, password) => {
	return {
		type: Constants.DO_LOGIN,
		cred: { username, password }
	};
};

export default (username, password) => {
	return (dispatch, getState) => {
		dispatch(loginAction(username, password));
		let res = axios.get('/api');
		console.log(res);
		return res;
	};
};