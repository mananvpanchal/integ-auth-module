/**
 * Created by Admin on 18-02-2016.
 */

import axios from 'axios';

export const axiosMiddleware = () => {
	return (next) => {
		return (action) => {
			let startAction = Object.assign({}, action, {type: action.type + "_START"});
			next(startAction);
			axios.push(startAction.url, startAction.data)
				.then(
				(res) => {
					let successAction = Object.assign({}, action, {type: action.type + "_SUCCESS", res: res.data});
					next(successAction);
				}
			)
				.catch(
				(err) => {
					let errorAction = Object.assign({}, action, {type: action.type + "_ERROR", err: err});
					next(errorAction);
				}
			);
		};
	};
};