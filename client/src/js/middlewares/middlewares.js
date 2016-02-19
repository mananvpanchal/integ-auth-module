/**
 * Created by Admin on 18-02-2016.
 */

import Constants from '../constants';
import axios from 'axios';

export const axiosMiddleware = () => {
	return (next) => {
		return (action) => {
			if( action.type.indexOf(Constants.SERVER)!== -1) {
				let startAction = Object.assign({}, action, {type: action.type + Constants.START});
				next(startAction);
				let promise;
				if(startAction.data === undefined) {
					promise = axios.get(startAction.url);
				} else {
					promise = axios.push(startAction.url, startAction.data);
				}
				promise.then(
					(res) => {
						let successAction = Object.assign({}, action, {type: action.type + Constants.SUCCESS, res: res.data});
						return next(successAction);
					}
				).catch(
					(err) => {
						let errorAction = Object.assign({}, action, {type: action.type + Constants.FAILURE, err: err});
						return next(errorAction);
					}
				);
			} else {
				return next(action);
			}
		};
	};
};