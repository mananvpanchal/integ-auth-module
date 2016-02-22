/**
 * Created by Admin on 17-02-2016.
 */

import Constants from '../constants';
import axios from 'axios';


axios.defaults.baseURL = 'http://train.integdev.com:9000';

const createXHRAction = (type, url, data) => {
	let action = createDefaultAction(type, data);
	return Object.assign(action, { xhr: true, url: url });
};

const createNormalAction = (type, data) => {
	let action = createDefaultAction(type, data);
	return Object.assign(action, { xhr: false });
};

const createDefaultAction = (type, data) => {
	return {
		type: type,
		data: data
	};
};

/*
 type: Constants.DO_LOGIN,
 data: {username, password},
 url: 'http://localhost:8080/authentication_war_exploded/auth/open/dologin',
 onSuccess: (dispatch, action) => {
 alert('success');
 return action;
 },
 onFailure: (dispatch, action) => {
 alert('failure');
 return action;
 }
 */
export default (username, password) => {
	let action = createXHRAction(Constants.DO_LOGIN, 'http://localhost:8080/authentication_war_exploded/auth/open/dologin', {username, password});
	return Object.assign(action, 
		{
			onSuccess: (dispatch, action) => {
				alert('success');
				return action;
			},
			onFailure: (dispatch, action) => {
				alert('error');
				return action;
			}
		});
};