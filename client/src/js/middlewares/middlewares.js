/**
 * Created by Admin on 18-02-2016.
 */

import Constants from '../constants';
import axios from 'axios';
import { createStartAction, createEndAction } from '../actions/basic-actions';

export const axiosMiddleware = () => {
	return (next) => {
		return (action) => {
			if (action.xhr) {
				next(createStartAction());
				let startAction = Object.assign({}, action, {type: action.type + Constants.START});
				next(startAction);
				let promise;
				if (action.data === undefined) {
					promise = axios.get(action.url);
				} else {
					promise = axios.post(action.url, action.data);
				}
				promise.then(
					(res) => {
						next(createEndAction());
						let successAction = Object.assign({}, action, {type: action.type, res: res});
						if(typeof action.onSuccess === 'function') {
							return action.onSuccess(next, successAction);
						} else {
							return next(successAction);
						}
					}
				).catch(
					(err) => {
						next(createEndAction());
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