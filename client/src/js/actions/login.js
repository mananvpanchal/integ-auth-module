/**
 * Created by Admin on 17-02-2016.
 */

import * as Constants from '../constants';
import axios from 'axios';


axios.defaults.baseURL = 'http://train.integdev.com:9000';

const createXHRAction = (type, data) => {
	let action = createDefaultAction(type, data);
	return Object.assign(action, { xhr: true });
};

const createNormalAction = (type, data) => {
	let action = createDefaultAction(type, data);
	return Object.assign(action, { xhr: false });
};

const createDefaultAction = (type, data) => {
	return {
		type,
		data
	};
};

export default (username, password) => {
	let action = createXHRAction(Constants.DO_LOGIN, {username, password});
	return Object.assign(action, 
		{ 
			url: "abc", 
			onSuccess: (dispatch, action) => {
				return action;
			},
			onFailure: (dispatch, action) => {
				return action;
			}
		});
};