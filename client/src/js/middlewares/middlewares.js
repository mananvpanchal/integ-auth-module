/**
 * Created by Admin on 18-02-2016.
 */

import Constants from '../constants';
import axios from 'axios';

export const axiosMiddleware = () => {
	return (next) => {
		return (action) => {
			if (action.xhr) {
				next(Constants.ACTION_START);
				let startAction = Object.assign({}, action, {type: action.type + Constants.START});
				next(startAction);
				let promise;
				if (action.data === undefined) {
					promise = axios.get(action.url);
				} else {
					promise = axios.push(action.url, action.data);
				}
				promise.then(
					(res) => {
						next(Constants.ACTION_END);
						let successAction = Object.assign({}, action, {type: action.type, res: res});
						if(typeof action.onSuccess === 'function') {
							return action.onSuccess(next, successAction);
						} else {
							return next(successAction);
						}
					}
				).catch(
					(err) => {
						next(Constants.ACTION_END);
						let errorAction = Object.assign({}, action, {type: action.type + Constants.FAILURE, err: err});
						if(typeof action.onFailure === 'function') {
							return action.onFailure(next, errorAction);
						} else {
							return next(errorAction);
						}
					}
				);
			} else {
				return next(action);
			}
		};
	};
};