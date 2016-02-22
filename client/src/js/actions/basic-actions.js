/**
 * Created by Admin on 22-02-2016.
 */

import Constants from '../constants';

export const createStartAction = () => {
	return {
		type: Constants.ACTION_START
	};
};

export const createEndAction = () => {
	return {
		type: Constants.ACTION_END
	};
};